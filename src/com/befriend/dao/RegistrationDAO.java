package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Registrationsa;


public interface RegistrationDAO {
		
		public void save(Registrationsa R);

		
		public void remove(Registrationsa R);

	
		public void update(Registrationsa R);
		
		public Registrationsa addressty(String address,String addcity);
		
		public List<Registrationsa> address(String address);
}