package com.befriend.dao;

import java.util.List;

import com.befriend.entity.App;
import com.befriend.entity.AppUp;



public interface AppDAO {
    	
	//添加app
	public void Save(App app);
	//通过id查询app
	public App byid(int id);
	//删除app
	public void remove(App app);
	//查询num个  app
	public List<App> All(int num);
	//分页查询
	public List<App> FAll(int currentPage,int pageSize);
	//查询全部app
	public List<App> ALL();
	//统计app下载次数
	public void Ds(App app);
	
}
