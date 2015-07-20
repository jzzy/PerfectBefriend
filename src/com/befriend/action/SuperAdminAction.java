package com.befriend.action;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.befriend.dao.SuperAdminDAO;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;
import com.befriend.entity.*;
public class SuperAdminAction {
	private SuperAdminDAO SAdao;//��������Աdao
	public OpeFunction util;//������
	private String Admin;//��������Ա�˺�
	private String Pwd;//��������Ա����
	
	
	
	
	 /**
     * ���ں�̨������ ����� 
     * @return
     * @throws IOException
     */
    public String SuperAdminLogin() throws IOException {
	try {
	    HttpSession session = OpeFunction.request().getSession();
	    System.out.println("����Admin");
	    System.out.println("Admin�ǣ�" + Admin);
	    System.out.println("Pwd�ǣ�" + Pwd);
	    //��ȡ����Ա
	    Admin admin=SAdao.admin(Admin, Pwd);
	   // session.setMaxInactiveInterval(1*60);
	    if (admin!=null) {
	    	System.out.println(admin.getAdmin()+"����ɹ�");
	    	session.setAttribute("admin",admin);
	    	//����ȫ��
		if(admin.getLevel()==1){	
		    session.setAttribute("home","/SuperAdmin/SuperAdmin/home.jsp");
			return Action.SUCCESS;

		}else 
			//���Ź���
			if(admin.getLevel()==2){
				
			session.setAttribute("home","/SuperAdmin/AdminNews/newshome.jsp");
		    return Action.LOGIN;
		    
		}else
			//��̳����
			if(admin.getLevel()==3){
				
			session.setAttribute("home","/SuperAdmin/AdminForum/forumhome.jsp");
			System.out.println("forum");
		    return  "forum";
		}
		System.out.println("û�ж�ӦȨ��!");
		
	    } else {
		System.out.println("no");

		return Action.ERROR;
	    }
	} catch (Exception e) {
	    System.out.println("Admin�����쳣��" + e.getMessage());

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
