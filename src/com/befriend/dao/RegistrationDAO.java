package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Registrationsa;


public interface RegistrationDAO {
		// ���
		public void save(Registrationsa R);

		// ɾ��
		public void remove(Registrationsa R);

		// �޸�
		public void update(Registrationsa R);
		//ͨ��ʡ�в�ѯ
		public Registrationsa addressty(String address,String addcity);
		//ͨ��ʡ��ѯ
		public List<Registrationsa> address(String address);
}