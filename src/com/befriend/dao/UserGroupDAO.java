package com.befriend.dao;

import java.util.List;

import com.befriend.entity.UserGroup;

/**
 * @author sterotto
 */
public interface UserGroupDAO
{
	/**
	 * @param id
	 * @return
	 */
	public UserGroup find(int id);
	/**
	 * @param userId
	 * @return
	 */
	public List<UserGroup> findByUserId(Integer userId);
	/**
	 * @param userGroup
	 */
	public void save(UserGroup userGroup);
	/**
	 * @param userGroup
	 */
	public void update(UserGroup userGroup);
	/**
	 * @param userGroup
	 */
	public void remove(UserGroup userGroup);
}
