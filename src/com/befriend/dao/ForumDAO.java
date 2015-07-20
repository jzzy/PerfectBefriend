package com.befriend.dao;


import java.util.List;

import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.User;

public interface ForumDAO {
	/**
	 * 论坛dao
	 * @param ForumOne
	 */
	
	
		//添加新论坛
		public void save(ForumOne ForumOne);
		// 通过标题模糊查询
		public List<ForumOne> likeTitle(String title);
		// 通过标题模糊查询 分页
		public List<ForumOne> likeTitle(int pageSize, int currentPage,String title);
		//查询所有 论坛
		public List<ForumOne> getForumOneALL(int pageSize, int currentPage);
		//查询所有 论坛 不包括 type类型
		public List<ForumOne> getForumOneNotALL(int pageSize, int currentPage,int type);
		//查询所有 论坛 不包括 type类型
		public List<ForumOne> getForumOneALL234(int pageSize, int currentPage);
		//查询所有 论坛   按照类别查询
		
		public List<ForumOne> getForumOneALL(int pageSize, int currentPage,int type);
		//查询所有 论坛
		public List<ForumOne> getForumOneALL();
		//查询所有 论坛 不包括type类型
		public List<ForumOne> getForumOneNotALL(int type);
		//查询所有 论坛 不包括type类型
		public List<ForumOne> getForumOneALL234();
		//查询所有 论坛 按照类别查询
		public List<ForumOne> getForumOneALL(int type);
		//查询省级所有 论坛 type=5 desc
		
		public List<ForumOne> getForumOneareaALL(String area);
		//查询省级所有 论坛 type=5 desc
		
		public List<ForumOne> getForumOneareaALL(String area,int model);
		//查询省级所有 论坛 type=5 desc
		
		public List<ForumOne> getForumOneareaALL(int pageSize, int currentPage,String area,int model);
		//查询省级所有 论坛
		public List<ForumOne> getForumOneareassALL(String area,String areas);
		//查询省级市级论坛
		public List<ForumOne> getForumOneareasALL(String areas);
		//按照分类 查询所有论坛
		public List<ForumOne> gettypeForumOneALL(int type);
		//ForumOneid 查询论坛
		public ForumOne getForumOne(int id);
		//ForumOneid 通过用户id查询论坛
		public  List<ForumOne> getUseridForumOne(int id);
		//ForumOne修改
		public void update(ForumOne ForumOne);
		//ForumOne删除
		public void Remove(ForumOne ForumOne);
	/**
	 *Two 	
	 * @param ForumTwo
	 */
		//ForumTw0 查询回复内容
		public List<ForumTwo> getFuserALL(int userid);
		//ForumTw0 添加回复
		public void save(ForumTwo ForumTwo);
		//ForumTw0 查询回复内容 asc
		public List<ForumTwo> getForumTwoALL(int forumid);
		//ForumTwo删除
		public void Remove(ForumTwo ForumTwo);
		//根据id查看 评论
		public ForumTwo getForumTwoid(int id);
		//更新
		public void update(ForumTwo forumTwo);
	/**
	 * Three	
	 * @param ForumThree
	 */
		//ForumThree删除
		public void Remove(ForumThree ForumThree);		
		
		//Forumthree 用户相互回复
		public void save(ForumThree ForumThree);
		//Forumthree  查询 评论下的 回复内容
		public List<ForumThree> getForumThreeALL(int forumid,int forumtwoid);
		//Forumthree 查询回复内容
		public List<ForumThree> getForumThreeALL(int forumid);
		//Forumthree 查询回复内容
		public ForumThree getForumThree(int id);
		
		
		
		
		
}
