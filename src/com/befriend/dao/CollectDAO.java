package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
/**
 * �ղط���
 * @author Administrator
 *
 */
public interface CollectDAO
{
	//����newsid��ѯȫ���ղ�
	public List<Collect> Alln(int newsid);
	//����userid��ѯȫ���ղ�
	public List<Collect> Allu(int userid);
	//����userid newsid ����ղ� 
	public void save(Collect collect);
	//����userid �� newsid  ��ѯ   ��ֹ�ظ��ղ�
	public Collect unid(int userid,int newsid);
	//ɾ���ղ�
	public void remove(Collect collect);

}
