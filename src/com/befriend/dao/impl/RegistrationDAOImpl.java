package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.RegistrationDAO;
import com.befriend.entity.Registrationsa;

@Transactional
public class RegistrationDAOImpl implements RegistrationDAO{
	 @PersistenceContext
	    private EntityManager entityManager;

	@Override
	public void save(Registrationsa R) {
		// TODO Auto-generated method stub
		entityManager.persist(R);
		
	}

	@Override
	public void remove(Registrationsa R) {
		entityManager.remove(R);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Registrationsa R) {
		entityManager.merge(R);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registrationsa addressty(String address, String addcity) {
		Query query = entityManager
				.createQuery("select u from Registrationsa u where u.address=:address and u.addcity=:addcity");	
		query.setParameter("address", address);
		query.setParameter("addcity", addcity);
		 List<Registrationsa> r=query.getResultList();
		 if(r.size()>0){
			 return r.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public List<Registrationsa> address(String address) {
		Query query = entityManager
				.createQuery("select u from Registrationsa u where u.address=:address order"
			      + " by u.note365 desc ");	
		query.setParameter("address", address);
		
		return query.getResultList();
	}
}
