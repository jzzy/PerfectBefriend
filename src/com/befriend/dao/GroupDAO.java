package com.befriend.dao;

import java.util.List;
import java.util.Map;

import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;

public interface GroupDAO {

	public List<GroupChat> findByAll();

	public void save(GroupChat groupchat);

	public void Remove(GroupChat groupchat);

	public void Update(GroupChat groupchat);

	public GroupChat Findbyid(int groupid);
	/**
	 * 查询我的班级或群
	 * @param userid
	 * @param classgroup
	 * @return
	 */
	public List<GroupChat> Findbyuserid(int userid, int classgroup);

	public GroupChat Findbygroupno(int groupno);

	public List<GroupChat> likeGroupChat(int classgroup, String all,
			Map<String, String> paras);

	public GroupChat maxGroupno();

	public GroupChat Findbygroupiduserid(int groupid, int userid);

	public void save(Profile Profile);

	public void Remove(Profile Profile);

	public void Update(Profile Profile);

	public Profile FindProfilebyid(int groupid, int userid);

	public List<Profile> FindProfilebyid(int groupid);

	public List<Profile> FindProfilebyids(int groupid, int urp);

	public void save(GroupMembers GroupMembers);

	public void Remove(GroupMembers GroupMembers);

	public void Update(GroupMembers GroupMembers);
	
	public List<GroupMembers> FindGroupMembersbyid(int groupid, int urp);

	public List<GroupMembers> FindGroupMembersbyid(int groupid);

	public List<GroupMembers> FindGroupMembersbyurp(int groupid, int urp);

	public GroupMembers FindGroupMembersbygiduidAll(int groupid,
			int userid);

	public GroupMembers FindGroupMembersbygiduidurp(int groupid,
			int userid, int urp);

	public GroupMembers FindGroupMembersbygiduidurp(int groupid, int userid);

	public List<GroupMembers> FindUseridGroupbyid(int userid, int urp);

	public void save(Cis cis);

	public void Remove(Cis cis);

	public void Update(Cis cis);

	public List<Cis> FindbyGroupChatiduseridCis(int groupid, int userid);

	public List<Cis> Cisid(int groupid, int Cisid);

	public Cis Cisid(int Groupid, int Cisid, int Uid);

	public List<Cis> Cisuid(int uid);

	public List<Cis> Cisid(int groupid);

}
