package com.befriend.dao;


import java.util.List;

import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.User;

public interface ForumDAO {
	/**
	 * ��̳dao
	 * @param ForumOne
	 */
	
	
		//�������̳
		public void save(ForumOne ForumOne);
		// ͨ������ģ����ѯ
		public List<ForumOne> likeTitle(String title);
		// ͨ������ģ����ѯ ��ҳ
		public List<ForumOne> likeTitle(int pageSize, int currentPage,String title);
		//��ѯ���� ��̳
		public List<ForumOne> getForumOneALL(int pageSize, int currentPage);
		//��ѯ���� ��̳ ������ type����
		public List<ForumOne> getForumOneNotALL(int pageSize, int currentPage,int type);
		//��ѯ���� ��̳ ������ type����
		public List<ForumOne> getForumOneALL234(int pageSize, int currentPage);
		//��ѯ���� ��̳   ��������ѯ
		
		public List<ForumOne> getForumOneALL(int pageSize, int currentPage,int type);
		//��ѯ���� ��̳
		public List<ForumOne> getForumOneALL();
		//��ѯ���� ��̳ ������type����
		public List<ForumOne> getForumOneNotALL(int type);
		//��ѯ���� ��̳ ������type����
		public List<ForumOne> getForumOneALL234();
		//��ѯ���� ��̳ ��������ѯ
		public List<ForumOne> getForumOneALL(int type);
		//��ѯʡ������ ��̳ type=5 desc
		
		public List<ForumOne> getForumOneareaALL(String area);
		//��ѯʡ������ ��̳ type=5 desc
		
		public List<ForumOne> getForumOneareaALL(String area,int model);
		//��ѯʡ������ ��̳ type=5 desc
		
		public List<ForumOne> getForumOneareaALL(int pageSize, int currentPage,String area,int model);
		//��ѯʡ������ ��̳
		public List<ForumOne> getForumOneareassALL(String area,String areas);
		//��ѯʡ���м���̳
		public List<ForumOne> getForumOneareasALL(String areas);
		//���շ��� ��ѯ������̳
		public List<ForumOne> gettypeForumOneALL(int type);
		//ForumOneid ��ѯ��̳
		public ForumOne getForumOne(int id);
		//ForumOneid ͨ���û�id��ѯ��̳
		public  List<ForumOne> getUseridForumOne(int id);
		//ForumOne�޸�
		public void update(ForumOne ForumOne);
		//ForumOneɾ��
		public void Remove(ForumOne ForumOne);
	/**
	 *Two 	
	 * @param ForumTwo
	 */
		//ForumTw0 ��ѯ�ظ�����
		public List<ForumTwo> getFuserALL(int userid);
		//ForumTw0 ��ӻظ�
		public void save(ForumTwo ForumTwo);
		//ForumTw0 ��ѯ�ظ����� asc
		public List<ForumTwo> getForumTwoALL(int forumid);
		//ForumTwoɾ��
		public void Remove(ForumTwo ForumTwo);
		//����id�鿴 ����
		public ForumTwo getForumTwoid(int id);
		//����
		public void update(ForumTwo forumTwo);
	/**
	 * Three	
	 * @param ForumThree
	 */
		//ForumThreeɾ��
		public void Remove(ForumThree ForumThree);		
		
		//Forumthree �û��໥�ظ�
		public void save(ForumThree ForumThree);
		//Forumthree  ��ѯ �����µ� �ظ�����
		public List<ForumThree> getForumThreeALL(int forumid,int forumtwoid);
		//Forumthree ��ѯ�ظ�����
		public List<ForumThree> getForumThreeALL(int forumid);
		//Forumthree ��ѯ�ظ�����
		public ForumThree getForumThree(int id);
		
		
		
		
		
}
