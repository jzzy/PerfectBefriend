package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Cis;
import com.befriend.entity.GroupChat;
import com.befriend.entity.GroupMembers;
import com.befriend.entity.Profile;

public interface GroupDAO {
	/**
	 * 群聊dao
	 * 
	 * @param
	 */
	/**
	 *  通过 userid 查询我 建的群
	 * @param userid
	 * @return
	 */
	public List<GroupChat> findByAll();
	
	
	
	/**
	 * 群资料
	 * 
	 * @param groupchat
	 */
	/**
	 * 添加群资料
	 * @param groupchat
	 */
	public void save(GroupChat groupchat);

	/**
	 *  删除群资料
	 * @param groupchat
	 */
	public void Remove(GroupChat groupchat);

	/**
	 * 更新群资料
	 * @param groupchat
	 */
	public void Update(GroupChat groupchat);

	/**
	 *  通过id查询群资料
	 * @param groupid
	 * @return
	 */
	public GroupChat Findbyid(int groupid);

	/**
	 *  通过 userid 查询我 建的群
	 * @param userid
	 * @return
	 */
	public List<GroupChat> Findbyuserid(int userid);
	/**
	 *  通过 groupno 查询群
	 * @param groupno
	 * @return
	 */
	public GroupChat Findbygroupno(int groupno);
	/**
	 *  通过 groupno 查询群
	 * @param groupno
	 * @return
	 */
	public GroupChat Findbygroupiduserid(int groupid,int userid);

	
	
	
	
	/**
	 * 群 个人资料
	 */
	/**
	 *  添加资料
	 * @param Profile
	 */
	public void save(Profile Profile);

	/**
	 *  删除资料
	 * @param Profile
	 */
	public void Remove(Profile Profile);

	/**
	 *  更新资料
	 * @param Profile
	 */
	public void Update(Profile Profile);

	
	/**
	 *  通过 群 id 用户id 查询资料
	 * @param GroupChatid
	 * @return
	 */
	public Profile FindProfilebyid(int GroupChatid,int userid);
	
	/**
	 *  通过 群 id  查询资料
	 * @param GroupChatid
	 * @return
	 */
	public List<Profile> FindProfilebyid(int GroupChatid);
	
	/**
	 *  通过 群 id 和 urp 查询  0等待审核  1是成员 2 非群成员
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<Profile> FindProfilebyids(int GroupChatid,int urp);
	
	

	
	
	
	/**
	 * 群关系资料
	 */
	/**
	 *  添加群关系资料
	 * @param GroupMembers
	 */
	public void save(GroupMembers GroupMembers);

	/**
	 *  删除群关系资料
	 * @param GroupMembers
	 */
	public void Remove(GroupMembers GroupMembers);

	/**
	 *  更新群关系资料
	 * @param GroupMembers
	 */
	public void Update(GroupMembers GroupMembers);

	/**
	 *  通过 群id 和urp 0 等待审核 1是 群成员 2是 被踢出去的 查询 群用户
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid, int urp);
	/**
	 *  通过 群id  查询 群用户
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyid(int GroupChatid);
	/**
	 *  通过 群id  查询 群用户
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindGroupMembersbyurp(int GroupChatid,int urp);

	/**
	 *  通过 群id 和 userid 
	 * @param GroupChatid
	 * @param 
	 * @return
	 */
	public  List<GroupMembers> FindGroupMembersbygiduidAll(int GroupChatid,int userid);
	
	/**
	 *  通过 群id 和urp userid 0 等待审核 1是 群成员 查询 我是否加入过 
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid,int userid, int urp);
	/**
	 *  通过 群id 和userid 查询他是否曾经加入过
	 * @param GroupChatid
	 * @param urp
	 * @return
	 */
	public GroupMembers FindGroupMembersbygiduidurp(int GroupChatid,int userid);

	/**
	 *  通过 群userid 和urp 0 等待审核 1是 群成员 2是 被踢出去的 查询我 加入的群 我 申请加入的群 踢我的群
	 * @param userid
	 * @param urp
	 * @return
	 */
	public List<GroupMembers> FindUseridGroupbyid(int userid, int urp);

	
	
	
	
	
	/**
	 * 群聊天资料 Cis
	 */

	/**
	 *  添加聊天
	 * @param Cis
	 */
	public void save(Cis cis);

	/**
	 *  删除
	 * @param Cis
	 */
	public void Remove(Cis cis);

	/**
	 *  更新群关系资料
	 * @param Cis
	 */
	public void Update(Cis cis);
	/**
	 * 通过群id  用户id 查询 群的聊天记录
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public List<Cis>  FindbyGroupChatiduseridCis(int GroupChatid,int userid);
	/**
	 * 通过群id  用户id 查询 群的聊天记录  如果本地有数据  查询  改Cisid 以后的信息
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public List<Cis>  Cisid(int GroupChatid,int Cisid);
	/**
	 * 通过群id  用户id cisid查询 群的聊天记录  
	 * @param GroupChatid
	 * @param userid
	 * @return
	 */
	public Cis  Cisid(int Groupid,int Cisid,int Uid);
	/**
	 * 通过用户id查看 未读消息
	 * @param Cisid
	 * @return
	 */
	public List<Cis>  Cisuid(int uid);
	
	
	
	/**
	 * 如果没有Cisid就查询最近50条的
	 * @param GroupChatid
	 * @return
	 */
	public List<Cis>  Cisid(int GroupChatid);
	
	
}
