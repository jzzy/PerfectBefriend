/**
 * 
 */
package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Behavior;

/**
 * @author STerOTto
 *
 */
public interface BehaviorDAO
{
	/**
	 * 更新对象
	 * @param behavior
	 */
	public void update(Behavior behavior);
	/**
	 * 保存对象
	 * @param behavior
	 */
	public void save(Behavior behavior);
	
	/**
	 * @param NumOfRecords 需要查询的记录数
	 * @param userId
	 * @param type 关键词类别
	 * @return
	 */
	public List<Behavior> findByUserIdType(int NumOfRecords,Integer userId,int type);
	/**
	 * 根据关键词查询
	 * @param userId
	 * @param keyword
	 * @return
	 */
	public Behavior findByUserKeyword(Integer userId,String keyword);
	

}
