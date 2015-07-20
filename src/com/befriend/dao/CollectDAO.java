package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
/**
 * 收藏方法
 * @author Administrator
 *
 */
public interface CollectDAO
{
	//根据newsid查询全部收藏
	public List<Collect> Alln(int newsid);
	//根据userid查询全部收藏
	public List<Collect> Allu(int userid);
	//根据userid newsid 添加收藏 
	public void save(Collect collect);
	//根据userid 和 newsid  查询   防止重复收藏
	public Collect unid(int userid,int newsid);
	//删除收藏
	public void remove(Collect collect);

}
