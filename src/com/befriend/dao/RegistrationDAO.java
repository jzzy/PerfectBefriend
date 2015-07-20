package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Registrationsa;


public interface RegistrationDAO {
		// 添加
		public void save(Registrationsa R);

		// 删除
		public void remove(Registrationsa R);

		// 修改
		public void update(Registrationsa R);
		//通过省市查询
		public Registrationsa addressty(String address,String addcity);
		//通过省查询
		public List<Registrationsa> address(String address);
}