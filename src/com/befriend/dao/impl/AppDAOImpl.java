package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.AppDAO;
import com.befriend.entity.App;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
@Transactional
public class AppDAOImpl implements AppDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void Save(App app) {
		// TODO Auto-generated method stub
		entityManager.persist(app);
	}

	@Override
	public App byid(int id) {
		Query query = entityManager.createQuery("select u from App u where u.id=:id");	
		query.setParameter("id",id);
		@SuppressWarnings("unchecked")
		List<App> app = query.getResultList();
		if (app.size() > 0)
				return app.get(0);
			return null;
	}

	@Override
	public void remove(App app) {
		entityManager.remove(app);	
	}

	@Override
	public List<App> All(int num) {
		Query query = entityManager.createQuery("select u from App u order"
			      + " by u.sequence desc ");
		if(num!=0){
		query.setMaxResults(num);
		}
		
		return query.getResultList();
	}


	@Override
	public List<App> FAll(int currentPage, int pageSize) {
		Query query = entityManager.createQuery("select u from App u order by u.sequence desc");
		//query.setMaxResults(4);
		//currentPage页数
		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<App> ALL() {
		Query query = entityManager.createQuery("select u from App u order"
			      + " by u.sequence desc ");	
	
		
		return query.getResultList();
	}

	@Override
	public void Ds(App app) {
		entityManager.merge(app);
		
	}


	

}
