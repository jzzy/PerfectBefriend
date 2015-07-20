package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Collect;
import com.befriend.entity.Follect;

public interface FollectDAO {
		//根据forumid查询全部收藏
		public List<Follect> Allf(int forumid);
		//根据userid查询全部收藏
		public List<Follect> Allu(int userid);
		//根据userid forumid 添加收藏 
		public void save(Follect Follect);
		//根据userid 和 forumid  查询   防止重复收藏
		public Follect ufid(int userid,int forumid);
		//删除收藏
		public void remove(Follect Follect);
}
