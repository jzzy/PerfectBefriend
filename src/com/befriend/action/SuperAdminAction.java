package com.befriend.action;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.befriend.dao.SuperAdminDAO;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;
import com.befriend.entity.*;
public class SuperAdminAction {
	private SuperAdminDAO SAdao;//超级管理员dao
	public OpeFunction util;//工具类
	private String Admin;//超级管理员账号
	private String Pwd;//超级管理员密码
	
	
	
	
	 /**
     * 用于后台管理者 登入的 
     * @return
     * @throws IOException
     */
    public String SuperAdminLogin() throws IOException {
	try {
	    HttpSession session = OpeFunction.request().getSession();
	    System.out.println("进入Admin");
	    System.out.println("Admin是：" + Admin);
	    System.out.println("Pwd是：" + Pwd);
	    //获取管理员
	    Admin admin=SAdao.admin(Admin, Pwd);
	   // session.setMaxInactiveInterval(1*60);
	    if (admin!=null) {
	    	System.out.println(admin.getAdmin()+"登入成功");
	    	session.setAttribute("admin",admin);
	    	//管理全部
		if(admin.getLevel()==1){	
		    session.setAttribute("home","/SuperAdmin/SuperAdmin/home.jsp");
			return Action.SUCCESS;

		}else 
			//新闻管理
			if(admin.getLevel()==2){
				
			session.setAttribute("home","/SuperAdmin/AdminNews/newshome.jsp");
		    return Action.LOGIN;
		    
		}else
			//论坛管理
			if(admin.getLevel()==3){
				
			session.setAttribute("home","/SuperAdmin/AdminForum/forumhome.jsp");
			System.out.println("forum");
		    return  "forum";
		}
		System.out.println("没有对应权限!");
		
	    } else {
		System.out.println("no");

		return Action.ERROR;
	    }
	} catch (Exception e) {
	    System.out.println("Admin方法异常：" + e.getMessage());

	}
	return Action.ERROR;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public SuperAdminAction(SuperAdminDAO sAdao) {
		super();
		SAdao = sAdao;
	}


	public String getAdmin() {
		return Admin;
	}

	public void setAdmin(String admin) {
		Admin = admin;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

}
