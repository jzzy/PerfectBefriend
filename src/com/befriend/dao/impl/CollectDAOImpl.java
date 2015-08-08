package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.CollectDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.Collect;
import com.befriend.entity.Support;
@SuppressWarnings("unchecked")
@Transactional
public class CollectDAOImpl implements CollectDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Collect> Alln(int newsid) {
		Query query = entityManager
				.createQuery("select u from Collect u  where"
						+ " u.newsid=:newsid order" + " by u.time desc");
		query.setParameter("newsid", newsid);
		return query.getResultList();
	}

	@Override
	public List<Collect> Allu(int userid) {
		Query query = entityManager
				.createQuery("select u from Collect u  where"
						+ " u.userid=:userid order" + " by u.time desc");
		query.setParameter("userid", userid);

		return query.getResultList();
	}

	@Override
	public void save(Collect collect) {
		entityManager.persist(collect);

	}

	@Override
	public Collect unid(int userid, int newsid) {
		Query query = entityManager.createQuery("select u from Collect u where"
				+ " u.userid=:userid and u.newsid=:newsid");
		query.setMaxResults(1);
		query.setParameter("userid", userid);
		query.setParameter("newsid", newsid);
		List<Collect> collect = query.getResultList();
		if (collect.size() > 0)
			return collect.get(0);
		return null;
	}

	@Override
	public void remove(Collect collect) {
		entityManager.remove(collect);

	}

	@Override
	public List<Support> Frequency(int comefrom, int objectid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Support u where"
						+ " u.objectid=:objectid and u.comefrom=:comefrom order by u.time desc");
		query.setParameter("objectid", objectid);
		query.setParameter("comefrom", comefrom);
		return query.getResultList();
	}

	@Override
	public List<Support> ILikeToo(int comefrom, int userid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Support u where"
						+ " u.userid=:userid and u.comefrom=:comefrom order by u.time desc");
		query.setParameter("userid", userid);
		query.setParameter("comefrom", comefrom);
		return query.getResultList();
	}

	@Override
	public void save(Support st) {
		// TODO Auto-generated method stub
		entityManager.persist(st);
	}

	@Override
	public Support Whether(int comefrom, int userid, int objectid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Support u where"
						+ " u.userid=:userid and u.comefrom=:comefrom and u.objectid=:objectid order by u.time desc");
		query.setParameter("userid", userid);
		query.setParameter("objectid", objectid);
		query.setParameter("comefrom", comefrom);
		List<Support> sl = query.getResultList();
		if (sl.size() > 0) {
			return sl.get(0);
		}
		return null;
	}

	@Override
	public void remove(Support st) {
		// TODO Auto-generated method stub
		entityManager.remove(st);
	}

	@Override
	public List<Attention> Frequency_A(int comefrom, int objectid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Attention u where"
						+ " u.objectid=:objectid and u.comefrom=:comefrom order by u.time desc");
		query.setParameter("objectid", objectid);
		query.setParameter("comefrom", comefrom);
		return query.getResultList();
	}

	@Override
	public List<Attention> ILikeToo_A(int comefrom, int userid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Attention u where"
						+ " u.userid=:userid and u.comefrom=:comefrom order by u.time desc");
		query.setParameter("userid", userid);
		query.setParameter("comefrom", comefrom);
		return query.getResultList();
	}

	@Override
	public void save(Attention at) {
		// TODO Auto-generated method stub
		entityManager.persist(at);

	}

	
	@Override
	public Attention Whether_A(int comefrom, int userid, int objectid) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select u from Attention u where"
						+ " u.userid=:userid and u.comefrom=:comefrom and u.objectid=:objectid");
		query.setParameter("userid", userid);
		query.setParameter("objectid", objectid);
		query.setParameter("comefrom", comefrom);
		List<Attention> sl = query.getResultList();
		if (sl.size() > 0) {
			return sl.get(0);
		}else{
		return null;
		}
	}

	@Override
	public void remove(Attention at) {
		// TODO Auto-generated method stub
		entityManager.remove(at);
	}

	@Override
	public void update(Collect collect) {
		// TODO Auto-generated method stub
		entityManager.merge(collect);
	}

}
