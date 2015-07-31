package com.befriend.dao;

import com.befriend.entity.GroupFriend;

/**
 * @author sterotto
 *
 */
public interface GroupInfoDAO
{
	/**
	 * @param id
	 * @return
	 */
	public GroupFriend find(int id);
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
