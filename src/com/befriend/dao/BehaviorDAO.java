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
	 * ���¶���
	 * @param behavior
	 */
	public void update(Behavior behavior);
	/**
	 * �������
	 * @param behavior
	 */
	public void save(Behavior behavior);
	
	/**
	 * @param NumOfRecords ��Ҫ��ѯ�ļ�¼��
	 * @param userId
	 * @param type �ؼ������
	 * @return
	 */
	public List<Behavior> findByUserIdType(int NumOfRecords,Integer userId,int type);
	/**
	 * ���ݹؼ��ʲ�ѯ
	 * @param userId
	 * @param keyword
	 * @return
	 */
	public Behavior findByUserKeyword(Integer userId,String keyword);
	

}
