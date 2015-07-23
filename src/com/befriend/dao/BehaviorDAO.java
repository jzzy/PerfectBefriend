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
	 * @param NumOfRecords ��Ҫ��ѯ�ļ�¼��
	 * @param userId
	 * @param type �ؼ������
	 * @return
	 */
	public List<Behavior> findByUserIdType(int NumOfRecords,Integer userId,int type);

}
