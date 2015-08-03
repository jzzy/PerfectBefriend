package com.befriend.dao;

import com.befriend.entity.GroupFriend;

/**
 * @author sterotto
 *
 */
public interface GroupFriendDAO
{
	/**
	 * @param id
	 * @return
	 */
	public GroupFriend find(int id);
	/**
	 * @param userId
	 * @param friendId
	 * @return
	 */
	public GroupFriend find(int userId,int friendId);
	/**
	 * @param groupInfo
	 */
	public void save(GroupFriend groupFriend);
	/**
	 * @param groupInfo
	 */
	public void update(GroupFriend groupFriend);
	/**
	 * @param groupInfo
	 */
	public void remove(GroupFriend groupFriend);

}
