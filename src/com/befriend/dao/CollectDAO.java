package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
import com.befriend.entity.Support_News;
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
	
	/**
	 * ����
	 */
		//����newsid��ѯȫ����
		public List<Support_News> sNlln(int newsid);
		//����userid��ѯȫ����
		public List<Support_News> sAllu(int userid);
		//����userid newsid �����
		public void save(Support_News st);
		//����userid �� newsid  ��ѯ   ��ֹ�ظ���
		public Support_News sunid(int userid,int newsid);
		//ȡ����
		public void remove(Support_News st);
	
	

}
