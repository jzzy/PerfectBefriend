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
	 * @param name
	 * @return
	 */
	public UserGroup find(int userId,String name);
	/**
	 * @param userId
	 * @param isDefault
	 * @return
	 */
	public UserGroup findDefault(int userId,int isDefault);
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
