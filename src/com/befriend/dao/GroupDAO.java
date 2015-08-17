package com.befriend.dao;

import java.util.List;
import java.util.Map;

import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;

public interface GroupDAO {
	/**
	 * Ⱥ��dao
	 * 
	 * @param
	 */
	/**
	 *  ͨ�� userid ��ѯ�� ����Ⱥ
	 * @param userid
	 * @return
	 */
	public List<GroupChat> findByAll();
	
	
	
	/**
	 * Ⱥ����
	 * 
	 * @param groupchat
	 */
	/**
	 * ���Ⱥ����
	 * @param groupchat
	 */
	public void save(GroupChat groupchat);

	/**
	 *  ɾ��Ⱥ����
	 * @param groupchat
	 */
	public void Remove(GroupChat groupchat);

	/**
	 * ����Ⱥ����
	 * @param groupchat
	 */
	public void Update(GroupChat groupchat);

	/**
	 *  ͨ��id��ѯȺ����
	 * @param groupid
	 * @return
	 */
	public GroupChat Findbyid(int groupid);

	/**
	 *  ͨ�� userid ��ѯ�� ����Ⱥ
	 * @param userid
	 * @return
	 */
	public List<GroupChat> Findbyuserid(int userid,int classgroup);
	/**
	 *  ͨ�� groupno ��ѯȺ
	 * @param groupno
	 * @return
	 */
	public GroupChat Findbygroupno(int groupno);
	public List<GroupChat> likeGroupChat(int classgroup,String all,Map<String,String> paras);
	public GroupChat maxGroupno();
	/**
	 *  ͨ�� groupno ��ѯȺ
	 * @param groupno
	 * @return
	 */
	public GroupChat Findbygroupiduserid(int groupid,int userid);

	
	
	
	
	/**
	 * Ⱥ ��������
	 */
	/**
	 *  �������
	 * @param Profile
	 */
	public void save(Profile Profile);

	/**
	 *  ɾ������
	 * @param Profile
	 */
	public void Remove(Profile Profile);

	/**
	 *  ��������
	 * @param Profile
	 */
	public void Update(Profile Profile);

	
	/**
	 *  ͨ�� Ⱥ id �û�id ��ѯ����
	 * @param GroupChatid
	 * @return
	 */
	public Profile FindProfilebyid(int GroupChatid,int userid);
	
	/**
	 *  ͨ�� Ⱥ id  ��ѯ����
	 * @param GroupChatid
	 * @return
	 */
	public List<Profile> FindProfilebyid(int GroupChatid);
	
	/**
	 *  ͨ�� Ⱥ id �� urp ��ѯ  0�ȴ����  1�ǳ�Ա 2 ��Ⱥ��Ա
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<Profile> FindProfilebyids(int GroupChatid,int urp);
	
	

	
	
	
	/**
	 * Ⱥ��ϵ����
	 */
	/**
	 *  ���Ⱥ��ϵ����
	 * @param GroupMembers
	 */
	public void save(GroupMembers GroupMembers);

	/**
	 *  ɾ��Ⱥ��ϵ����
	 * @param GroupMembers
	 */
	public void Remove(GroupMembers GroupMembers);

	/**
	 *  ����Ⱥ��ϵ����
	 * @param GroupMembers
	 */
	public void Update(GroupMembers GroupMembers);

	/**
	 *  ͨ�� Ⱥid ��urp 0 �ȴ���� 1�� Ⱥ��Ա 2�� ���߳�ȥ�� ��ѯ Ⱥ�û�
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid, int urp);
	/**
	 *  ͨ�� Ⱥid  ��ѯ Ⱥ�û�
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid);
	/**
	 *  ͨ�� Ⱥid  ��ѯ Ⱥ�û�
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyurp(int GroupChatid,int urp);

	/**
	 *  ͨ�� Ⱥid �� userid 
	 * @param GroupChatid
	 * @param 
	 * @return
	 */
	public  List<GroupMembers> FindGroupMembersbygiduidAll(int GroupChatid,int userid);
	
	/**
	 *  ͨ�� Ⱥid ��urp userid 0 �ȴ���� 1�� Ⱥ��Ա ��ѯ ���Ƿ����� 
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid,int userid, int urp);
	/**
	 *  ͨ�� Ⱥid ��userid ��ѯ���Ƿ����������
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid,int userid);

	/**
	 *  ͨ�� Ⱥuserid ��urp 0 �ȴ���� 1�� Ⱥ��Ա 2�� ���߳�ȥ�� ��ѯ�� �����Ⱥ �� ��������Ⱥ ���ҵ�Ⱥ
	 * @param userid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindUseridGroupbyid(int userid, int urp,int classgroup);

	
	
	
	
	
	/**
	 * Ⱥ�������� Cis
	 */

	/**
	 *  �������
	 * @param Cis
	 */
	public void save(Cis cis);

	/**
	 *  ɾ��
	 * @param Cis
	 */
	public void Remove(Cis cis);

	/**
	 *  ����Ⱥ��ϵ����
	 * @param Cis
	 */
	public void Update(Cis cis);
	/**
	 * ͨ��Ⱥid  �û�id ��ѯ Ⱥ�������¼
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public List<Cis>  FindbyGroupChatiduseridCis(int GroupChatid,int userid);
	/**
	 * ͨ��Ⱥid  �û�id ��ѯ Ⱥ�������¼  �������������  ��ѯ  ��Cisid �Ժ����Ϣ
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public List<Cis>  Cisid(int GroupChatid,int Cisid);
	/**
	 * ͨ��Ⱥid  �û�id cisid��ѯ Ⱥ�������¼  
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public Cis  Cisid(int Groupid,int Cisid,int Uid);
	/**
	 * ͨ���û�id�鿴 δ����Ϣ
	 * @param Cisid
	 * @return
	 */
	public List<Cis>  Cisuid(int uid);
	
	
	
	/**
	 * ���û��Cisid�Ͳ�ѯ���50����
	 * @param GroupChatid
	 * @return
	 */
	public List<Cis>  Cisid(int GroupChatid);
	
	
}
