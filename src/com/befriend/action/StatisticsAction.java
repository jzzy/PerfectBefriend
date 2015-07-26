package com.befriend.action;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.BehaviorDAO;
import com.befriend.entity.Behavior;

/**
 * @author STerOTto
 * @describe 行为统计
 *
 */
public class StatisticsAction extends BaseAction 
{
	private static final long serialVersionUID = 1L;
	
	private BehaviorDAO behaviorDAO;
	
	private String keyword;
	private Integer userId;
	
	/**
	 * 行为分析统计
	 */
	public void statistics()
	{
		Behavior behavior = behaviorDAO.findByUserKeyword(userId, keyword);
		if(behavior == null)
		{
			/**
			 * 没有当前用户关键词
			 * 新建一个统计记录
			 */
			behavior = new Behavior();
			behavior.setUserId(userId);
			behavior.setKeyword(keyword);
			if(StringUtils.isNumeric(keyword))
				behavior.setType(Behavior.NEWS_TYPE);
			else
				behavior.setType(Behavior.LABEL);
			behavior.setCount(1l);
			behaviorDAO.save(behavior);
			
		}
		else
		{
			behavior.setCount(behavior.getCount()+1L);
			behaviorDAO.update(behavior);
		}
		
	}
	
	public StatisticsAction(BehaviorDAO behaviorDAO) {
		super();
		this.behaviorDAO = behaviorDAO;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
