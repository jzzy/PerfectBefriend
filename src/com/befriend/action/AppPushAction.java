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
 * @describe app push����
 */
public class AppPushAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	private static final int BEHAVIOR_NUM = 10;// ͳ�Ƶ���Ϊ��
	private static final int NEWS_TYPE_NUM = 5;// ���ŷ�����
	private static final float deviation = 0.03f;//��6��7

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
	 * ��������
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
				 * ͷ������ ���������ࣨ���� �Ƽ��������� ������ʱ������
				 */
				news = newsDAO.getRecentlyNews(pageSize, currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("�����ݸ���");
				}
				break;
			case News.RECOMMEND:
				/**
				 * �Ƽ� ������Ϣ+�û���Ϊ
				 */
				if (StringUtils.isNumeric(userId.trim()))
				{
					/**
					 * ����userId�����Ƽ�ָ�꣺������Ϣ��������Ϊ�� ����������ǩ�ı���
					 */
					int realSize = 0;// ʵ��push������,��һ��ΪpageSize
					Integer _userId = Integer.valueOf(userId.trim());
					User user = userDAO.byid(_userId);
					List<Behavior> typeBehaviors = behaviorDAO.findByUserIdType(NEWS_TYPE_NUM, _userId, Behavior.NEWS_TYPE);
					long typeSum = typeBehaviors.size();//��ֹ������Ϊ0
					for (Behavior behavior : typeBehaviors)
					{
						typeSum += behavior.getCount();
					}
					for (Behavior behavior : typeBehaviors)
					{
						/**
						 * ���ݱ�������6��7������������ŵĵ���
						 */
						int occupy = (int) ((behavior.getCount()+1) / (float) typeSum+deviation)* pageSize;
						realSize += occupy;
						behavior.setOccupy(occupy);
					}

					labelBehaviors = behaviorDAO.findByUserIdType(BEHAVIOR_NUM, _userId,Behavior.LABEL);
					/**
					 * ȡ�����һ�ܵ�����
					 */
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					String startTime = sdf.format(calendar.getTime());
					calendar.add(Calendar.DAY_OF_YEAR, 7);
					String endTime = sdf.format(calendar.getTime());
					List<News> recentlyNews = newsDAO.getRecentlyNews(startTime, endTime);
					/**
					 * ���ձ�ǩͳ�ƽ�������
					 */
					
					/**
					 * ��������Ű���push�����������
					 */
					int[] startP = new int[NEWS_TYPE_NUM];
					if (MathUtils.min(startP) >= recentlyNews.size())
					{
						/**
						 * ����������Ŷ�������
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("�����ݸ���");
					} else
					{
						/**
						 * �ҵ���ǰҳ��ĸ������ŷ������ʼ�α�
						 * indexs��ʾ�������ൽ��ǰҳʵ�����е�������
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
									 * �ƶ��α�
									 */
									indexs[j]++;
									startP[j] = i;
								}
							}
						}
						/**
						 * ���ݸ���������ʼ�α����push
						 */
						for(int i = 0;i < typeBehaviors.size();i++)
						{
							for(int j = 0; j<typeBehaviors.get(i).getOccupy();j++)
							{
								/**
								 * ѭ��push��������
								 * �жϸ������Ż������ſ���push
								 */
								if(startP[j] < recentlyNews.size())
								{
									news.add(recentlyNews.get(startP[j]++));
								}
							}
						}
						/**
						 * �ж����push�õ������ų���
						 */
						if (news.size() > 0)
							msg.setCode(Message.SUCCESS);
						else
						{
							msg.setCode(Message.FAILED);
							msg.setStatement("�����ݸ���");
						}

					}
				} else
				{
					msg.setCode(Message.ERROR);
					msg.setStatement("�û�id��Ϊ����");
				}
				break;
			case News.LOCAL:
				/**
				 * ���� ��������ʾ���ţ�Ĭ����ʾ����
				 */
				news = newsDAO.getRecentlyNews(type, province, city, pageSize,
						currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("�����ݸ���");
				}
				break;
			default:
				/**
				 * ������������
				 */
				news = newsDAO.getRecentlyNews(type, pageSize, currentPage);
				if (news.size() > 0)
					msg.setCode(Message.SUCCESS);
				else
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("�����ݸ���");
				}
				break;
			}
		} else
		{
			msg.setCode(Message.ERROR);
			msg.setContent("�������Ϊ�գ�");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJson(msg));
	}
	/**
	 * ���űȽ���
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
