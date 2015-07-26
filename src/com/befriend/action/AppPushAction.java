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
			switch (Integer.valueOf(type))
			{
			case News.TOP_NEWS:
				/**
				 * ͷ������ ���������ࣨ���� �Ƽ��������� ������ʱ������
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
					msg.setStatement("�����ݸ���");
				}
				break;
			case News.RECOMMEND:
				/**
				 * �Ƽ� ������Ϣ+�û���Ϊ
				 */
				if (StringUtils.isNumeric(userId))
				{
					/**
					 * ����userId�����Ƽ�ָ�꣺������Ϣ��������Ϊ�� ����������ǩ�ı���
					 */
					@SuppressWarnings("unused")
					int realSize = 0;// ʵ��push������,��һ��ΪpageSize
					Integer _userId = Integer.valueOf(userId);
					User user = userDAO.byid(_userId);
					List<Behavior> typeBehaviors = behaviorDAO.findByUserIdType(NEWS_TYPE_NUM, _userId, Behavior.NEWS_TYPE);
					/**
					 * ���û��������Ϊͳ��
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
							 * �������ݿ⣬���Ҽ�����Ϊ�б�
							 */
							behaviorDAO.save(behavior);
							typeBehaviors.add(behavior);
						}
						
					}
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
						int occupy = (int) (((behavior.getCount()+1f) / (float) typeSum+deviation)* pageSize);
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
					List<News> recentlyNews = newsDAO.getRecentlyNewsByTime(startTime, endTime);
					/**
					 * 1,�����û�ƫ������<age,stage,sex,school,province,city,+ͳ��ƫ��>
					 * 2,�����������û�ƫ�����ƶ�
					 * 3,��������
					 * �����������û���Ϣƫ��Ȩֵ
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
					 * �����û���Ϊƫ��Ȩֵ
					 */
					for(Behavior behavior:labelBehaviors)
					{
						prefence.add(MathUtils.getWeight(behavior.getCount()));
					}
					/**
					 * ��������Ȩֵ
					 */
					for (int i=0 ; i<recentlyNews.size() ; i++) 
					{
						News n = recentlyNews.get(i);
						List<Double> newsV = new ArrayList<>();
						if(StringUtils.isEmpty(n.getLabel()))
						{
							/**
							 * �����ű�ǩΪ�գ�������Ȩֵ����Ϊ������
							 */
							for(int j=0 ; j<prefence.size() ; j++)
							{
								newsV.add(0d);
							}
							
						}
						else
						{
							/**
							 * <age,stage,sex,school,province,city,+ͳ��ƫ��>
							 * ���ű�ǩ��Ϊ�գ���������������Ȩֵ
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
							 * ������ΪȨֵ�Ƚ�	
							 */
							for(Behavior behavior:labelBehaviors)
							{
								if(n.getLabel().contains(behavior.getKeyword()))
									newsV.add(MathUtils.getWeight(behavior.getCount()));
								else
									newsV.add(0d);
							}
							
							/**
							 * ����Ȩֵ
							 */
							n.setSimilarity(MathUtils.sim(prefence, newsV));
							recentlyNews.set(i, n);
						}
						
						
					}
					recentlyNews.sort(new NewsComparator());
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
								if(startP[i] < recentlyNews.size())
								{
									news.add(recentlyNews.get(startP[i]++));
								}
							}
						}
						/**
						 * �ж����push�õ������ų���
						 */
						if (news.size() > 0)
						{
							msg.setCode(Message.SUCCESS);
							msg.setContent(news);
						}
						else
						{
							msg.setCode(Message.FAILED);
							msg.setStatement("��push����");
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
				news = newsDAO.getRecentlyNews(Integer.valueOf(type), province, city, pageSize,currentPage);
				if (news.size() > 0)
				{
					msg.setCode(Message.SUCCESS);
					msg.setContent(news);
				}
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
						msg.setStatement("�����ݸ���");
					}
				}
				else
				{
					msg.setCode(Message.ERROR);
					msg.setContent("�����������");
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
