package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.ApputilDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.entity.Visitor;

@Transactional
public class ApputilDAOImpl implements ApputilDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AppUp UP() {
		Query query = entityManager.createQuery("select u from AppUp u order"
				+ " by u.time desc");
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Save(Feedback f) {
		entityManager.persist(f);

	}

	@Override
	public List<Feedback> All() {
		Query query = entityManager.createQuery("select u from Feedback u"
				+ "order by u.time desc ");

		return query.getResultList();

	}

	@Override
	public void Save(AppUp aup) {
		entityManager.persist(aup);

	}

	@Override
	public AppUp select(String Path) {
		Query query = entityManager
				.createQuery("select u from AppUp u where u.path=:Path");
		// query.setMaxResults(1);
		query.setParameter("Path", Path);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Remove(AppUp aup) {
		entityManager.remove(aup);

	}

	@Override
	public Admin admin(String admin, String pwd) {
		Query query = entityManager
				.createQuery("select a from Admin a where a.admin=:admin and a.pwd=:pwd");
		query.setParameter("admin", admin);
		query.setParameter("pwd", pwd);
		List<Admin> ad = query.getResultList();
		if (ad.size() > 0)
			return ad.get(0);
		return null;
	}

	@Override
	public AppUp appbyid(int id) {
		Query query = entityManager
				.createQuery("select u from AppUp u where u.id=:id");
		// query.setMaxResults(1);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Update(AppUp aup) {
		entityManager.merge(aup);

	}

	@Override
	public void Save(Visitor vor) {
		entityManager.persist(vor);

	}

	@Override
	public void Update(Visitor vor) {
		entityManager.merge(vor);

	}

	@Override
	public Visitor sVisitor(String mac) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.appmac=:mac");
		// query.setMaxResults(1);
		query.setParameter("mac", mac);
		@SuppressWarnings("unchecked")
		List<Visitor> Visitor = query.getResultList();
		if (Visitor.size() > 0)
			return Visitor.get(0);
		return null;
	}

	@Override
	public void Save(Stas sta) {
		entityManager.persist(sta);

	}

	@Override
	public void Update(Stas sta) {
		entityManager.merge(sta);

	}

	@Override
	public List<Stas> StasTime(String province,String os) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.os=:os and u.province=:province  order by u.time desc,u.province desc,u.os desc");
		query.setParameter("os", os);
		query.setParameter("province", province);
		query.setMaxResults(7);
		
		return query.getResultList();

	}

	@Override
	public Stas StasTimeDay(String time,String os) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.time LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		if (query.getResultList().size() > 0)

			return (Stas) query.getResultList().get(0);

		return null;

	}


	@Override
	public Stas StasTimeDay(String time, String os, String province) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.time LIKE :time and u.os=:os and u.province=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		if (query.getResultList().size() > 0)

			return (Stas) query.getResultList().get(0);

		return null;
	}

	@Override
	public List<Visitor> VisitorTime(String time, String os) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.time LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		return query.getResultList();
	}

	@Override
	public List<Visitor> VisitorTime(String time, String os,String province) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.time LIKE :time and u.os=:os and u.province=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		return query.getResultList();
	}

	@Override
	public List<Stas> StasTime(String time, String province, String os) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stas> StasTimeother(String province) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.province!=:province  order by u.province desc,u.os desc,u.time desc");
		
		query.setParameter("province", province);
		//query.setMaxResults(7);
		
		return query.getResultList();
	}

	
}
