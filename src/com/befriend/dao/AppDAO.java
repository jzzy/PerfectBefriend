package com.befriend.dao;

import java.util.List;

import com.befriend.entity.App;
import com.befriend.entity.AppUp;



public interface AppDAO {
    	
	//���app
	public void Save(App app);
	//ͨ��id��ѯapp
	public App byid(int id);
	//ɾ��app
	public void remove(App app);
	//��ѯnum��  app
	public List<App> All(int num);
	//��ҳ��ѯ
	public List<App> FAll(int currentPage,int pageSize);
	//��ѯȫ��app
	public List<App> ALL();
	//ͳ��app���ش���
	public void Ds(App app);
	
}
