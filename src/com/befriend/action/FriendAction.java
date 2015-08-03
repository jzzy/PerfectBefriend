package com.befriend.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.GroupFriendDAO;
import com.befriend.dao.UserDAO;
import com.befriend.dao.UserGroupDAO;
import com.befriend.entity.GroupFriend;
import com.befriend.entity.User;
import com.befriend.entity.UserGroup;
import com.befriend.util.JsonUtil;
import com.befriend.util.Message;
import com.befriend.util.OpeFunction;

public class FriendAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	public static final String YES = "y";
	public static final String NO = "n";

	private UserDAO userDAO;
	private UserGroupDAO userGroupDAO;
	private GroupFriendDAO groupFriendDAO;

	private String userId;
	private String friendId;
	private String groupId;
	private String agree;
	private String remark;

	/**
	 * get friend list
	 * @throws IOException
	 */
	public void getFriendsList() throws IOException
	{
		Message msg = new Message();
		/**
		 * check userId is a number
		 */
		if (StringUtils.isNumeric(userId))
		{
			/**
			 * if there is a user found by userId
			 * get his or her list of friends
			 */
			User user = userDAO.getUserGroup(Integer.valueOf(userId),GroupFriend.FRIEND);
			if (user != null)
			{
				 
				Set<UserGroup> userGroups = new HashSet<UserGroup>();
				for (UserGroup userGroup : user.getUserGroup())
				{

					Set<GroupFriend> groupFriends = new HashSet<GroupFriend>();
					for (GroupFriend groupFriend : userGroup.getGroupFriends())
					{
						/**
						 * find a friend by groupFriend's userId
						 */
						User friend = userDAO.byid(groupFriend.getUserId());
						if (friend != null)
						{
							groupFriend.setUsername(friend.getUsername());
							groupFriend.setNickname(friend.getNickname());
							groupFriend.setImg(friend.getImg());
						}
						groupFriends.add(groupFriend);
					}
					userGroup.setGroupFriends(groupFriends);
					
					userGroups.add(userGroup);
				}
				user.setUserGroup(userGroups);
				msg.setCode(Message.SUCCESS);
				msg.setContent(user);
			}
			else
			{
				msg.setCode(Message.NULL);
				msg.setStatement("user is null");
			}
		} else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("userId is not a number");
		}
		/**
		 * write response
		 */
		this.getJsonResponse().getWriter().print(JsonUtil.toJsonExpose(msg));
	}
	
	/**
	 * @param userId
	 * @param friendId
	 * @param groupId
	 * @describe add someone to friend
	 * check someone was friend
	 * check has send invitation and the groupId has been changed
	 * @throws IOException 
	 */
	public void addFriend() throws IOException
	{
		Message msg  = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId)&&StringUtils.isNumeric(groupId))
		{
			/**
			 * check the one is existed in friend list
			 */
			GroupFriend friend = groupFriendDAO.find(Integer.valueOf(userId), Integer.valueOf(friendId));
			if(friend != null)
			{
				/**
				 * if the one exist in friend list
				 * check his or her status
				 * check his or her group's information has changed
				 */
				if(friend.getStatus().intValue() == GroupFriend.FRIEND)
				{
					msg.setCode(Message.FAILED);
					msg.setStatement("he or her has been your friend");
				}
				else
					if(friend.getStatus().intValue() == GroupFriend.BLACKLIST)
					{
						msg.setCode(Message.FAILED);
						msg.setStatement("you have moved he or her to your blacklist");
					}
					else 
					{
						UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
						if(userGroup != null)
						{
							friend.setUserGroup(userGroup);
							friend.setStatus(GroupFriend.INVITE);
							groupFriendDAO.update(friend);
							msg.setCode(Message.SUCCESS);
							msg.setStatement("send a invitation success");
						}
							
					}
				
			}
			else
			{
				/**
				 * insert a new record
				 * send a invitation to the one your wanted be friend
				 */
				UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
				if(userGroup != null)
				{
					GroupFriend groupFriend = new GroupFriend();
					groupFriend.setUserGroup(userGroup);
					groupFriend.setUserId(Integer.valueOf(friendId));
					groupFriend.setStatus(GroupFriend.INVITE);
					groupFriend.setCreateTime(OpeFunction.getNowTime());
					/**
					 * Persistence
					 */
					groupFriendDAO.save(groupFriend);
					
					msg.setCode(Message.SUCCESS);
					msg.setStatement("success");
				}
				else
				{
					msg.setCode(Message.ERROR);
					msg.setStatement("parameter error");
				}
			}
		}
		else
		{
			msg.setCode(Message.ERROR);
			msg.setStatement("parameter error");
		}
		this.getJsonResponse().getWriter().print(JsonUtil.toJson(msg));
	}
	
	/**
	 * @param userId passive
	 * @param friendId initiative
	 * @param groupId passive
	 * @param remark choice
	 * @throws IOException 
	 * @describe user deal with the invitation his or her got
	 */
	public void handleInvitation() throws IOException
	{
		Message msg = new Message();
		if(StringUtils.isNumeric(userId)&&StringUtils.isNumeric(friendId))
		{
				if(YES.equalsIgnoreCase(agree))
				{
					/**
					 * find friend relationship from database
					 * if there is no relationship
					 * insert a new one
					 * else update database
					 * and the one who is invited need a group id
					 */
					GroupFriend initiative = groupFriendDAO.find(Integer.valueOf(friendId), Integer.valueOf(userId));
					if(initiative == null)
					{
						/**
						 * he or her didn't invite you to be a friend
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("he or her didn't invite you to be a friend");
					}
					else
					{
						String time = OpeFunction.getNowTime();
						/**
						 * update the friends relationship
						 */
						initiative.setStatus(GroupFriend.FRIEND);
						initiative.setCreateTime(time);
						groupFriendDAO.update(initiative);
						
						GroupFriend passive = groupFriendDAO.find(Integer.valueOf(userId),Integer.valueOf(friendId));
						UserGroup userGroup = userGroupDAO.find(Integer.valueOf(groupId));
						if(passive == null)
						{
							passive = new GroupFriend();
						}
						passive.setUserGroup(userGroup);
						passive.setUserId(Integer.valueOf(friendId));
						passive.setStatus(GroupFriend.FRIEND);
						passive.setRemark(remark);
						passive.setCreateTime(time);
						/**
						 * if passive's id is null update will insert a new record
						 */
						groupFriendDAO.update(passive);
						msg.setCode(Message.SUCCESS);
						msg.setStatement("now he or her is your friend");
						msg.setContent(true);
					}
					
				}
				else
				{
					GroupFriend initiative = groupFriendDAO.find(Integer.valueOf(friendId), Integer.valueOf(userId));
					if(initiative == null)
					{
						/**
						 * he or her didn't invite you to be a friend
						 */
						msg.setCode(Message.FAILED);
						msg.setStatement("he or her didn't invite you to be a friend");
					}
					else
					{
						initiative.setStatus(GroupFriend.REFUSE);
						groupFriendDAO.update(initiative);
						msg.setCode(Message.SUCCESS);
						msg.setStatement("you has be refused");
					}
				}
				this.getJsonResponse().getWriter().print(JsonUtil.toJson(msg));
		}
	}

	

	public FriendAction(UserDAO userDAO, UserGroupDAO userGroupDAO, GroupFriendDAO groupFriendDAO) {
		super();
		this.userDAO = userDAO;
		this.userGroupDAO = userGroupDAO;
		this.groupFriendDAO = groupFriendDAO;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
