package com.befriend.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.befriend.base.BaseAction;
import com.befriend.dao.GroupInfoDAO;
import com.befriend.dao.UserDAO;
import com.befriend.dao.UserGroupDAO;
import com.befriend.entity.GroupFriend;
import com.befriend.entity.User;
import com.befriend.entity.UserGroup;
import com.befriend.util.JsonUtil;
import com.befriend.util.Message;

public class FriendAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private UserGroupDAO userGroupDAO;
	private GroupInfoDAO GroupInfoDAO;

	private String userId;

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
			User user = userDAO.getUserGroup(Integer.valueOf(userId));
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

	public FriendAction(UserDAO userDAO, UserGroupDAO userGroupDAO, com.befriend.dao.GroupInfoDAO groupInfoDAO)
	{
		super();
		this.userDAO = userDAO;
		this.userGroupDAO = userGroupDAO;
		GroupInfoDAO = groupInfoDAO;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

}
