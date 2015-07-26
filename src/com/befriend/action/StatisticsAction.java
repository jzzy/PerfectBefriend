package com.befriend.action;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.BehaviorDAO;
import com.befriend.dao.NewsDAO;
import com.befriend.entity.Behavior;
import com.befriend.entity.News;

/**
 * @author STerOTto
 * @describe 行为统计
 *
 */
public class StatisticsAction extends BaseAction 
{
	private static final long serialVersionUID = 1L;
	
	private BehaviorDAO behaviorDAO;
	private NewsDAO newsDAO;
	
	private String userId;
	private String newsId;
	
	/**
	 * 行为分析统计
	 */
	public void statistics()
	{
		if(StringUtils.isNumeric(newsId)&&StringUtils.isNumeric(userId))
		{
			News news = newsDAO.byid(Integer.valueOf(newsId));
			System.out.println(news == null);
			if(news != null)
			{
				Behavior typeBehavior = behaviorDAO.findByUserKeyword(Integer.valueOf(userId), String.valueOf(news.getType()),Behavior.NEWS_TYPE);
				if(typeBehavior == null)
				{
					/**
					 * 没有当前用户关键词
					 * 新建一个统计记录
					 */
					typeBehavior = new Behavior();
					typeBehavior.setUserId(Integer.valueOf(userId));
					typeBehavior.setKeyword(String.valueOf(news.getType()));
					typeBehavior.setType(Behavior.NEWS_TYPE);
					typeBehavior.setCount(1l);
					behaviorDAO.save(typeBehavior);
					
				}
				else
				{
					typeBehavior.setCount(typeBehavior.getCount()+1L);
					behaviorDAO.update(typeBehavior);
				}
				if(!StringUtils.isEmpty(news.getLabel()))
				{
					String [] labels = news.getLabel().split(",");
					for (String keyword : labels) {
						Behavior labelBehavior = behaviorDAO.findByUserKeyword(Integer.valueOf(userId), keyword,Behavior.LABEL);
						if(labelBehavior == null)
						{
							/**
							 * 没有当前用户关键词
							 * 新建一个统计记录
							 */
							labelBehavior = new Behavior();
							labelBehavior.setUserId(Integer.valueOf(userId));
							labelBehavior.setKeyword(keyword);
							labelBehavior.setType(Behavior.LABEL);
							labelBehavior.setCount(1l);
							behaviorDAO.save(labelBehavior);
							
						}
						else
						{
							labelBehavior.setCount(labelBehavior.getCount()+1L);
							behaviorDAO.update(labelBehavior);
						}
					}
				}
			}
		}
		
	}
	

	public StatisticsAction(BehaviorDAO behaviorDAO, NewsDAO newsDAO) {
		super();
		this.behaviorDAO = behaviorDAO;
		this.newsDAO = newsDAO;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getNewsId() {
		return newsId;
	}


	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	
	

}
