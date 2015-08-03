package com.befriend.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.GroupFriendDAO;
import com.befriend.entity.GroupFriend;

@Transactional
public class GroupFriendDAOImpl implements GroupFriendDAO
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

	@Override
	public GroupFriend find(int userId, int friendId) 
	{
		Query query = entityManager.createQuery("select gFriends from User u inner join u.userGroup uGroup join uGroup.groupFriends gFriends where u.id = :userId and gFriends.userId = :friendId");
		query.setParameter("userId", userId);
		query.setParameter("friendId", friendId);
		if(query.getResultList().size()>0)
			return (GroupFriend) query.getResultList().get(0);
		else
			return null;
	}

}
