package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
import com.befriend.entity.Support;
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
	
	/**
	 * 点赞
	 */
		//根据newsid查询全部赞
		public List<Support> sNlln(int newsid);
		//根据userid查询全部赞
		public List<Support> sAllu(int userid);
		//根据userid newsid 添加赞
		public void save(Support st);
		//根据userid 和 newsid  查询   防止重复赞
		public Support sunid(int userid,int newsid);
		//取消赞
		public void remove(Support st);
	
	

}
