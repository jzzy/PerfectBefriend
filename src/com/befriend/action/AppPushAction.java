package com.befriend.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.BehaviorDAO;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Behavior;
import com.befriend.entity.News;
import com.befriend.entity.User;
import com.befriend.util.JsonUtil;
import com.befriend.util.MathUtils;
import com.befriend.util.Message;
import com.befriend.util.PageUtil;

/**
 * @author STerOTto
 * @describe app push规则
 */
public class AppPushAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	private static final int BEHAVIOR_NUM = 10;// 统计的行为数
	private static final int NEWS_TYPE_NUM = 5;// 新闻分类数
	private static final float deviation = 0.03f;//舍6进7

	private NewsDAO newsDAO;
	private UserDAO userDAO;
	private BehaviorDAO behaviorDAO;

	private PageUtil page;
	private int currentPage = 1;
	private int pageSize = 10;
	private Message msg;
	private String type;
	private String province;
	private String city;
	private String userId;
	
	private List<Behavior> labelBehaviors;

	/**
	 * 推送新闻
	 * 
	 * @throws IOException
	 */
	public void pushNews() throws IOException
	{
		msg = new Message();
		List<News> news = new ArrayList<News>();
		if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(userId))
		{
			switch (Integer.valueOf(type.trim()))
			{
			case News.TOP_NEWS:
				/**
				 * 头条新闻 其他所有类（除了 推荐）的新闻 按最新时间排序
				 */
				news = newsDAO.getRecentlyNews(pageSize, currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("无内容更新");
				}
				break;
			case News.RECOMMEND:
				/**
				 * 推荐 个人信息+用户行为
				 */
				if (StringUtils.isNumeric(userId.trim()))
				{
					/**
					 * 根据userId查找推荐指标：个人信息，个人行为表 计算各类别或标签的比例
					 */
					int realSize = 0;// 实际push新闻数,不一定为pageSize
					Integer _userId = Integer.valueOf(userId.trim());
					User user = userDAO.byid(_userId);
					List<Behavior> typeBehaviors = behaviorDAO.findByUserIdType(NEWS_TYPE_NUM, _userId, Behavior.NEWS_TYPE);
					long typeSum = typeBehaviors.size();//防止被除数为0
					for (Behavior behavior : typeBehaviors)
					{
						typeSum += behavior.getCount();
					}
					for (Behavior behavior : typeBehaviors)
					{
						/**
						 * 根据比例，舍6进7，计算各个新闻的调数
						 */
						int occupy = (int) ((behavior.getCount()+1) / (float) typeSum+deviation)* pageSize;
						realSize += occupy;
						behavior.setOccupy(occupy);
					}

					labelBehaviors = behaviorDAO.findByUserIdType(BEHAVIOR_NUM, _userId,Behavior.LABEL);
					/**
					 * 取出最近一周的新闻
					 */
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					String startTime = sdf.format(calendar.getTime());
					calendar.add(Calendar.DAY_OF_YEAR, 7);
					String endTime = sdf.format(calendar.getTime());
					List<News> recentlyNews = newsDAO.getRecentlyNews(startTime, endTime);
					/**
					 * 按照标签统计进行排序
					 */
					
					/**
					 * 将最近新闻按照push规则进行排序
					 */
					int[] startP = new int[NEWS_TYPE_NUM];
					if (MathUtils.min(startP) >= recentlyNews.size())
					{
						/**
						 * 所有类别新闻都推送完
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("无内容更新");
					} else
					{
						/**
						 * 找到当前页码的各个新闻分类的起始游标
						 * indexs表示各个分类到当前页实际已有的新闻数
						 */
						int[] indexs = new int[NEWS_TYPE_NUM];
						for (int i=0;i<recentlyNews.size();i++)
						{
							for (int j = 0; j < typeBehaviors.size(); j++)
							{
								Behavior behavior = typeBehaviors.get(j);
								if (String.valueOf(recentlyNews.get(i).getType()).equalsIgnoreCase(behavior.getKeyword())
										&& indexs[j] < behavior.getOccupy()* (currentPage - 1))
								{
									/**
									 * 移动游标
									 */
									indexs[j]++;
									startP[j] = i;
								}
							}
						}
						/**
						 * 根据各类新闻起始游标进行push
						 */
						for(int i = 0;i < typeBehaviors.size();i++)
						{
							for(int j = 0; j<typeBehaviors.get(i).getOccupy();j++)
							{
								/**
								 * 循环push各类新闻
								 * 判断该类新闻还有新闻可以push
								 */
								if(startP[j] < recentlyNews.size())
								{
									news.add(recentlyNews.get(startP[j]++));
								}
							}
						}
						/**
						 * 判断最后push得到的新闻长度
						 */
						if (news.size() > 0)
							msg.setCode(Message.SUCCESS);
						else
						{
							msg.setCode(Message.FAILED);
							msg.setStatement("无内容更新");
						}

					}
				} else
				{
					msg.setCode(Message.ERROR);
					msg.setStatement("用户id不为数字");
				}
				break;
			case News.LOCAL:
				/**
				 * 本地 按地区显示新闻，默认显示北京
				 */
				news = newsDAO.getRecentlyNews(type, province, city, pageSize,
						currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("无内容更新");
				}
				break;
			default:
				/**
				 * 其它类别的新闻
				 */
				news = newsDAO.getRecentlyNews(type, pageSize, currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("无内容更新");
				}
				break;
			}
		} else
		{
			msg.setCode(Message.ERROR);
			msg.setContent("请求参数为空！");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJson(msg));
	}
	/**
	 * 新闻比较器
	 * @author STerOTto
	 */
	private class NewsComparator implements Comparator<News>
	{
		@Override
		public int compare(News frist, News second) 
		{
			if(labelBehaviors.size() == 0)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}

	}

	public AppPushAction(NewsDAO newsDAO, UserDAO userDAO)
	{
		super();
		this.newsDAO = newsDAO;
		this.userDAO = userDAO;
	}

	public PageUtil getPage()
	{
		return page;
	}

	public void setPage(PageUtil page)
	{
		this.page = page;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public Message getMsg()
	{
		return msg;
	}

	public void setMsg(Message msg)
	{
		this.msg = msg;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

}
