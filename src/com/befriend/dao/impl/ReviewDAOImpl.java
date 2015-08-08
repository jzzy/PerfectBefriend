package com.befriend.dao.impl;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.ReviewDAO;
import com.befriend.entity.Review;
@SuppressWarnings("unchecked")
@Transactional
public class ReviewDAOImpl implements ReviewDAO
{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public List<Review> Alln(int newsid)
	{
		Query query = entityManager.createQuery("select u from Review u  where"
				+ " u.newsid=:newsid order"
			      + " by u.time desc");
		query.setParameter("newsid", newsid);
		return query.getResultList();
	}

	@Override
	public List<Review> Allu(int userid)
	{
		Query query = entityManager.createQuery("select u from Review u  where"
				+ " u.userid=:userid order"
			      + " by u.time desc");
		query.setParameter("userid", userid);
		return query.getResultList();
	}

	@Override
	public void save(Review review)
	{
			entityManager.persist(review);
		
	}

	@Override
	public List<Review> unid(int userid, int newsid)
	{
		Query query = entityManager.createQuery("select u from Review u where"
				+ " u.userid=:userid and u.newsid=:newsid order"
			      + " by u.time desc");
		
		query.setParameter("userid", userid);
		query.setParameter("newsid", newsid);
		 return query.getResultList();
	}

	@Override
	public void remove(Review review)
	{
		entityManager.remove(review);
		
	}

	@Override
	public Review byid(int reviewid) {
		Query query = entityManager.createQuery("select u from Review u  where"
				+ " u.id=:id ");
		
		query.setParameter("id", reviewid);
		List<Review>  r =query.getResultList();
		if(r.size()>0)
			return r.get(0);
		return null;
		
		 
		
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		entityManager.merge(review);
	}
	

}
