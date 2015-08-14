package com.befriend.dao;

import java.util.List;

import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumOneType;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.ForumTwoType;

public interface ForumDAO {
	
	public void save(ForumOne ForumOne);

	
	public List<ForumOne> likeTitle(String title);

	
	public List<ForumOne> likeTitle(int pageSize, int currentPage, String title);

	public List<ForumOne> getForumOneALL(int pageSize, int currentPage);


	public List<ForumOne> getForumOneNotALL(int pageSize, int currentPage,
			int type);

	
	public List<ForumOne> getForumOneALL234(int pageSize, int currentPage);



	public List<ForumOne> getForumOneALL(int pageSize, int currentPage, int type);

	
	public List<ForumOne> getForumOneALL();

	
	public List<ForumOne> getForumOneNotALL(int type);

	
	public List<ForumOne> getForumOneALL234();

	
	public List<ForumOne> getForumOneALL(int type);

	

	public List<ForumOne> getForumOneareaALL(String area);

	

	public List<ForumOne> getForumOneareaALL(String area, int model);

	

	public List<ForumOne> getForumOneareaALL(int pageSize, int currentPage,
			String area, int model);

	
	public List<ForumOne> getForumOneareassALL(String area, String areas);

	
	public List<ForumOne> getForumOneareasALL(String areas);

	
	public List<ForumOne> gettypeForumOneALL(int type);


	public ForumOne getForumOne(int id);
	public int getForumOne(String title);
	public List<ForumOne> getForumOne(int pageSize, int currentPage,
			String title);


	
	public List<ForumOne> getUseridForumOne(int id);

	public void update(ForumOne ForumOne);

	
	public void Remove(ForumOne ForumOne);

	/**
	 * Two
	 * 
	 * @param ForumTwo
	 */
	
	public List<ForumTwo> getFuserALL(int userid);

	
	public void save(ForumTwo ForumTwo);

	
	public List<ForumTwo> getForumTwoALL(int forumid);

	
	public void Remove(ForumTwo ForumTwo);

	
	public ForumTwo getForumTwoid(int id);

	
	public void update(ForumTwo forumTwo);

	/**
	 * Three
	 * 
	 * @param ForumThree
	 */
	
	public void Remove(ForumThree ForumThree);


	public void save(ForumThree ForumThree);

	
	public List<ForumThree> getForumThreeALL(int forumid, int forumtwoid);

	
	public List<ForumThree> getForumThreeALL(int forumid);

	
	public ForumThree getForumThree(int id);

	/**
	 * ForumOneType ForumTwoType
	 * 
	 * @param id
	 * @return
	 */
	public ForumOneType getByIdForumOneType(int id);
	public ForumTwoType getByIdForumTwoType(int id);
	public List<ForumOneType> getForumOneTypeAll();
	public void save(ForumOneType fot);
	public void save(ForumTwoType ftt);
	public void Remove(ForumOneType fot);
	public void Remove(ForumTwoType ftt);
	public void update(ForumOneType fot);
	public void update(ForumTwoType ftt);
	
}
