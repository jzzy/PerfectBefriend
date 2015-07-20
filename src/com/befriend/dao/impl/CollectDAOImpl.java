package com.befriend.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.befriend.dao.CollectDAO;
import com.befriend.entity.Collect;
@Transactional
public class CollectDAOImpl implements CollectDAO
{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Collect> Alln(int newsid)
	{
		Query query = entityManager.createQuery("select u from Collect u  where"
				+ " u.newsid=:newsid order"
			      + " by u.time desc");
		query.setParameter("newsid", newsid);
		return query.getResultList();
	}

	@Override
	public List<Collect> Allu(int userid)
	{
		Query query = entityManager.createQuery("select u from Collect u  where"
				+ " u.userid=:userid order"
			      + " by u.time desc");
		query.setParameter("userid", userid);
		
		
		return query.getResultList();
	}

	@Override
	public void save(Collect collect)
	{
		entityManager.persist(collect);
		
	}

	@Override
	public Collect unid(int userid, int newsid)
	{
		Query query = entityManager.createQuery("select u from Collect u where"
						+ " u.userid=:userid and u.newsid=:newsid");
		query.setMaxResults(1);
		query.setParameter("userid", userid);
		query.setParameter("newsid", newsid);
		@SuppressWarnings("unchecked")
		List<Collect> collect = query.getResultList();
		if (collect.size() > 0)
				return collect.get(0);
			return null;
	}

	@Override
	public void remove(Collect collect)
	{
		entityManager.remove(collect);
		
	}

}
