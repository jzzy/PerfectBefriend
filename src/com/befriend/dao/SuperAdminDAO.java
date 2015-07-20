package com.befriend.dao;

import com.befriend.entity.Admin;

public interface SuperAdminDAO {
		//管理员登入
		public Admin admin(String admin,String pwd);
}
