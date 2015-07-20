package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.Stas;
import com.befriend.entity.Visitor;

/**
 * 	统计  
 *  家长天地客户端的 更新 
 *  和用户反馈数据
 * @author Administrator
 *
 */

public interface ApputilDAO
{
    	//添加app
	public void Save(AppUp aup);
	//修改appjztd信息
	public void Update(AppUp aup);
	//查看是否更新
	public AppUp UP();
	//用户添加反馈信息
	public void Save(Feedback f);
	//查看用户反馈信息
	public List<Feedback> All();
	//通过 路径查询 App
	public AppUp select(String Path);
	//通过 id查询 App
	public AppUp appbyid(int id);
	//删除 App
	public void Remove(AppUp aup);
	
	
	
	
	//管理员登入
	public Admin admin(String admin,String pwd);
	
	
	//统计游客数量
	public void Save(Visitor vor);
	//修改游客信息
	public void Update(Visitor vor);
	//查询游客信息
	public Visitor sVisitor(String mac);
	//查寻 当天 的游客数量
	public List<Visitor> VisitorTime(String time,String os);
	//查寻 当天 的游客数量 按系统 按地区
	public List<Visitor> VisitorTime(String time, String os,String province);
		
		
	//新增统计
	public void Save(Stas sta);
	//修改统计
	public void Update(Stas sta);
	//查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTime(String province,String os);
	//查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTimeother(String province);
	//查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTime(String time,String province,String os);
	//查询指定天数的 按系统
	public Stas StasTimeDay(String time,String os);
	//查询指定天数的 按省 系统
	public Stas StasTimeDay(String time,String os,String province);
}
