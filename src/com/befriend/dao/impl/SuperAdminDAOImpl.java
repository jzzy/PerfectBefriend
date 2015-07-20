package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.befriend.dao.SuperAdminDAO;
import com.befriend.entity.Admin;
@PersistenceContext
public class SuperAdminDAOImpl implements SuperAdminDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Admin admin(String admin, String pwd) {
	    Query query = entityManager.createQuery("select a from Admin a where a.admin=:admin and a.pwd=:pwd");
	    query.setParameter("admin", admin);
	    query.setParameter("pwd", pwd);
	    List<Admin> ad = query.getResultList();
		if (ad.size() > 0)
				return ad.get(0);
			return null;
	}
}
