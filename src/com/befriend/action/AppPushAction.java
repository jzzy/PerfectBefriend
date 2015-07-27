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
			switch (Integer.valueOf(type))
			{
			case News.TOP_NEWS:
				/**
				 * 头条新闻 其他所有类（除了 推荐）的新闻 按最新时间排序
				 */
				news = newsDAO.getRecentlyNews(pageSize, currentPage);
				if (news.size() > 0)
				{
					msg.setCode(Message.SUCCESS);
					msg.setContent(news);
				}
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
				if (StringUtils.isNumeric(userId))
				{
					/**
					 * 根据userId查找推荐指标：个人信息，个人行为表 计算各类别或标签的比例
					 */
					@SuppressWarnings("unused")
					int realSize = 0;// 实际push新闻数,不一定为pageSize
					Integer _userId = Integer.valueOf(userId);
					User user = userDAO.byid(_userId);
					List<Behavior> typeBehaviors = behaviorDAO.findByUserIdType(NEWS_TYPE_NUM, _userId, Behavior.NEWS_TYPE);
					/**
					 * 新用户，添加行为统计
					 */
					if(typeBehaviors == null || typeBehaviors.size()==0)
					{
						typeBehaviors = new ArrayList<Behavior>();
						for (int i = 0; i < NEWS_TYPE_NUM; i++) 
						{
							Behavior behavior = new Behavior();
							behavior.setUserId(_userId);
							behavior.setKeyword(String.valueOf(i+1));
							behavior.setType(Behavior.NEWS_TYPE);
							behavior.setCount(1L);
							/**
							 * 存入数据库，并且加入行为列表
							 */
							behaviorDAO.save(behavior);
							typeBehaviors.add(behavior);
						}
						
					}
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
						int occupy = (int) (((behavior.getCount()+1f) / (float) typeSum+deviation)* pageSize);
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
					calendar.add(Calendar.DAY_OF_YEAR, -7);
					String endTime = sdf.format(calendar.getTime());
					List<News> recentlyNews = newsDAO.getRecentlyNewsByTime(startTime, endTime);
					/**
					 * 1,计算用户偏好向量<age,stage,sex,school,province,city,+统计偏好>
					 * 2,计算新闻与用户偏好相似度
					 * 3,进行排序
					 * 下面计算的是用户信息偏好权值
					 */
					List<Double> prefence = new ArrayList<>();
					if(!StringUtils.isEmpty(user.getChildrenage()))
						prefence.add(User.CHILD_AGE);
					else
						prefence.add(0d);
					
					if(!StringUtils.isEmpty(user.getStage()))
						prefence.add(User.STAGE);
					else
						prefence.add(0d);
					
					if(!StringUtils.isEmpty(user.getChildrensex()))
						prefence.add(User.CHILD_SEX);
					else
						prefence.add(0d);
					
					if(!StringUtils.isEmpty(user.getSchool()))
						prefence.add(User.SCHOOL);
					else
						prefence.add(0d);
					
					if(!StringUtils.isEmpty(user.getAddress()))
						prefence.add(User.PROVINCE);
					else
						prefence.add(0d);
					
					if(!StringUtils.isEmpty(user.getAddcity()))
						prefence.add(User.CITY);
					else
						prefence.add(0d);
					/**
					 * 计算用户行为偏好权值
					 */
					for(Behavior behavior:labelBehaviors)
					{
						prefence.add(MathUtils.getWeight(behavior.getCount()));
					}
					/**
					 * 计算新闻权值
					 */
					for (int i=0 ; i<recentlyNews.size() ; i++) 
					{
						News n = recentlyNews.get(i);
						List<Double> newsV = new ArrayList<>();
						if(StringUtils.isEmpty(n.getLabel()))
						{
							/**
							 * 若新闻标签为空，则新闻权值向量为零向量
							 */
							for(int j=0 ; j<prefence.size() ; j++)
							{
								newsV.add(0d);
							}
							
						}
						else
						{
							/**
							 * <age,stage,sex,school,province,city,+统计偏好>
							 * 新闻标签不为空，则求向量个分量权值
							 */
							if(!StringUtils.isEmpty(user.getChildrenage())&&n.getLabel().contains(user.getChildrenage()))
								newsV.add(User.CHILD_AGE);
							else
								newsV.add(0d);
							
							if(!StringUtils.isEmpty(user.getStage()) && n.getLabel().contains(user.getStage()))
								newsV.add(User.STAGE);
							else
								newsV.add(0d);
							
							if(!StringUtils.isEmpty(user.getChildrensex()) && n.getLabel().contains(user.getChildrensex()))
								newsV.add(User.CHILD_SEX);
							else
								newsV.add(0d);
							
							if(!StringUtils.isEmpty(user.getSchool()) && n.getLabel().contains(user.getSchool()))
								newsV.add(User.SCHOOL);
							else
								newsV.add(0d);
							
							if(!StringUtils.isEmpty(user.getAddress()) && n.getLabel().contains(user.getAddress()))
								newsV.add(User.PROVINCE);
							else
								newsV.add(0d);
							
							if(!StringUtils.isEmpty(user.getAddcity()) && n.getLabel().contains(user.getAddcity()))
								newsV.add(User.CITY);
							else
								newsV.add(0d);
							/**
							 * 个人行为权值比较	
							 */
							for(Behavior behavior:labelBehaviors)
							{
								if(n.getLabel().contains(behavior.getKeyword()))
									newsV.add(MathUtils.getWeight(behavior.getCount()));
								else
									newsV.add(0d);
							}
							
							/**
							 * 设置权值
							 */
							n.setSimilarity(MathUtils.sim(prefence, newsV));
							recentlyNews.set(i, n);
						}
						
						
					}
					recentlyNews.sort(new NewsComparator());
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
						int[] indexs = new int[typeBehaviors.size()];
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
								if(startP[i] < recentlyNews.size())
								{
									news.add(recentlyNews.get(startP[i]++));
								}
							}
						}
						/**
						 * 判断最后push得到的新闻长度
						 */
						if (news.size() > 0)
						{
							msg.setCode(Message.SUCCESS);
							msg.setContent(news);
						}
						else
						{
							msg.setCode(Message.FAILED);
							msg.setStatement("无push内容");
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
				news = newsDAO.getRecentlyNews(Integer.valueOf(type), province, city, pageSize,currentPage);
				if (news.size() > 0)
				{
					msg.setCode(Message.SUCCESS);
					msg.setContent(news);
				}
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
				if(Integer.valueOf(type)!=0)
				{
					news = newsDAO.getRecentlyNews(Integer.valueOf(type), pageSize, currentPage);
					if (news.size() > 0)
					{
						msg.setCode(Message.SUCCESS);
						msg.setContent(news);
					}
					else
					{
						msg.setCode(Message.FAILED);
						msg.setStatement("无内容更新");
					}
				}
				else
				{
					msg.setCode(Message.ERROR);
					msg.setContent("请求参数错误");
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
		public int compare(News first, News second) 
		{
			if(first.getSimilarity()<second.getSimilarity())
				return 1;
			else if(first.getSimilarity() > second.getSimilarity())
				return -1;
			else
				return 0;
		}

	}

	
	public AppPushAction(NewsDAO newsDAO, UserDAO userDAO, BehaviorDAO behaviorDAO) {
		super();
		this.newsDAO = newsDAO;
		this.userDAO = userDAO;
		this.behaviorDAO = behaviorDAO;
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
