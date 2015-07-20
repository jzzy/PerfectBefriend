package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.FollectDAO;



import com.befriend.entity.Follect;

@Transactional
public class FollectDAOImpl  implements FollectDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Follect> Allf(int forumid) {
		Query query = entityManager.createQuery("select u from Follect u  where"
				+ " u.forumid=:forumid order"
			      + " by u.time desc");
		query.setParameter("forumid", forumid);
		return query.getResultList();
	}

	@Override
	public List<Follect> Allu(int userid) {
		Query query = entityManager.createQuery("select u from Follect u  where"
				+ " u.userid=:userid order"
			      + " by u.time desc");
		query.setParameter("userid", userid);
		return query.getResultList();
	}

	@Override
	public void save(Follect follect) {
		entityManager.persist(follect);
		
	}

	@Override
	public Follect ufid(int userid, int forumid) {
		Query query = entityManager.createQuery("select u from Follect u  where"
				+ " u.forumid=:forumid and u.userid=:userid");
		query.setParameter("forumid", forumid);
		query.setParameter("userid", userid);
		@SuppressWarnings("unchecked")
		List<Follect> Follect = query.getResultList();
		if (Follect.size() > 0)
				return Follect.get(0);
			return null;
	}

	@Override
	public void remove(Follect Follect) {
		entityManager.remove(Follect);
	}

	
	
	

}
