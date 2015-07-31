package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.UserGroupDAO;
import com.befriend.entity.UserGroup;
@Transactional
public class UserGroupDAOImpl implements UserGroupDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserGroup find(int id)
	{
		return entityManager.find(UserGroup.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findByUserId(Integer userId)
	{
		Query query = entityManager.createQuery("select u from UserGroup u where u.userId = :userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public void save(UserGroup userGroup)
	{
		entityManager.persist(userGroup);
	}

	@Override
	public void update(UserGroup userGroup)
	{
		entityManager.merge(userGroup);
	}

	@Override
	public void remove(UserGroup userGroup)
	{
		entityManager.remove(userGroup);
	}

}
