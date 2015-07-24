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

}
