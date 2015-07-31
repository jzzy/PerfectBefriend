package com.befriend.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.GroupInfoDAO;
import com.befriend.entity.GroupFriend;

@Transactional
public class GroupInfoDAOImpl implements GroupInfoDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public GroupFriend find(int id)
	{
		return entityManager.find(GroupFriend.class, id);
	}

	@Override
	public void save(GroupFriend groupFriend)
	{
		entityManager.persist(groupFriend);
	}

	@Override
	public void update(GroupFriend groupFriend)
	{
		entityManager.merge(groupFriend);

	}

	@Override
	public void remove(GroupFriend groupFriend)
	{
		entityManager.remove(groupFriend);
	}

}
