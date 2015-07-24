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
	 * @param NumOfRecords 需要查询的记录数
	 * @param userId
	 * @param type 关键词类别
	 * @return
	 */
	public List<Behavior> findByUserIdType(int NumOfRecords,Integer userId,int type);

}
