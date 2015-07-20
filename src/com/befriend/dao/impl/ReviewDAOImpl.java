package com.befriend.dao.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.ReviewDAO;
import com.befriend.entity.Review;
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
	public List<Review> Allu(String username)
	{
		Query query = entityManager.createQuery("select u from Review u  where"
				+ " u.username=:username order"
			      + " by u.time desc");
		query.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public void save(Review review)
	{
			entityManager.persist(review);
		
	}

	@Override
	public List<Review> unid(String username, int newsid)
	{
		Query query = entityManager.createQuery("select u from Review u where"
				+ " u.username=:username and u.newsid=:newsid order"
			      + " by u.time desc");
		
		query.setParameter("username", username);
		query.setParameter("newsid", newsid);
		 return query.getResultList();
	}

	@Override
	public void remove(Review review)
	{
		entityManager.remove(review);
		
	}

	@Override
	public Review byid(int reviewid,String username) {
		Query query = entityManager.createQuery("select u from Review u  where"
				+ " u.id=:id and u.username=:username order"
			      + " by u.time desc");
		
		query.setParameter("id", reviewid);
		query.setParameter("username", username);
		List<Review>  r =query.getResultList();
		if(r.size()>0)
			return r.get(0);
		return null;
		
		 
		
	}
	

}
