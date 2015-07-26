package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.BehaviorDAO;
import com.befriend.entity.Behavior;
@Transactional
public class BehaviorDAOImpl implements BehaviorDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Behavior> findByUserIdType(int NumOfRecords, Integer userId, int type)
	{
		Query query = entityManager.createQuery("select u from Behavior u where u.userId = :userId and u.type = :type order by u.count desc");
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		query.setMaxResults(NumOfRecords);
		return query.getResultList();
	}

	@Override
	public void update(Behavior behavior) 
	{
		entityManager.merge(behavior);
	}

	@Override
	public void save(Behavior behavior) 
	{
		entityManager.persist(behavior);
	}

	@Override
	public Behavior findByUserKeyword(Integer userId, String keyword) 
	{
		Query query = entityManager.createQuery("select u from Behavior u where u.userId = :userId and u.keyword = :keyword");
		query.setParameter("userId", userId);
		query.setParameter("keyword", keyword);
		if(query.getResultList().size()>0)
		{
			if(query.getResultList().get(0) instanceof Behavior)
				return (Behavior) query.getResultList().get(0);
			else
				return null;
		}
		else
			return null;
	}

	@Override
	public Behavior findByUserKeyword(Integer userId, String keyword, int type) {
		Query query = entityManager.createQuery("select u from Behavior u where u.userId = :userId and u.type = :type and u.keyword = :keyword");
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		query.setParameter("keyword", keyword);
		if(query.getResultList().size()>0)
		{
			if(query.getResultList().get(0) instanceof Behavior)
				return (Behavior) query.getResultList().get(0);
			else
				return null;
		}
		else
			return null;
	}

}
