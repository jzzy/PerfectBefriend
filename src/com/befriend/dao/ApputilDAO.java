package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.Stas;
import com.befriend.entity.Visitor;

/**
 * 	ͳ��  
 *  �ҳ���ؿͻ��˵� ���� 
 *  ���û���������
 * @author Administrator
 *
 */

public interface ApputilDAO
{
    	//���app
	public void Save(AppUp aup);
	//�޸�appjztd��Ϣ
	public void Update(AppUp aup);
	//�鿴�Ƿ����
	public AppUp UP();
	//�û���ӷ�����Ϣ
	public void Save(Feedback f);
	//�鿴�û�������Ϣ
	public List<Feedback> All();
	//ͨ�� ·����ѯ App
	public AppUp select(String Path);
	//ͨ�� id��ѯ App
	public AppUp appbyid(int id);
	//ɾ�� App
	public void Remove(AppUp aup);
	
	
	
	
	//����Ա����
	public Admin admin(String admin,String pwd);
	
	
	//ͳ���ο�����
	public void Save(Visitor vor);
	//�޸��ο���Ϣ
	public void Update(Visitor vor);
	//��ѯ�ο���Ϣ
	public Visitor sVisitor(String mac);
	//��Ѱ ���� ���ο�����
	public List<Visitor> VisitorTime(String time,String os);
	//��Ѱ ���� ���ο����� ��ϵͳ ������
	public List<Visitor> VisitorTime(String time, String os,String province);
		
		
	//����ͳ��
	public void Save(Stas sta);
	//�޸�ͳ��
	public void Update(Stas sta);
	//��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTime(String province,String os);
	//��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTimeother(String province);
	//��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTime(String time,String province,String os);
	//��ѯָ�������� ��ϵͳ
	public Stas StasTimeDay(String time,String os);
	//��ѯָ�������� ��ʡ ϵͳ
	public Stas StasTimeDay(String time,String os,String province);
}
