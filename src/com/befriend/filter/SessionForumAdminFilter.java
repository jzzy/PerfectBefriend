package com.befriend.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.befriend.entity.Admin;
import com.befriend.util.OpeFunction;

public class SessionForumAdminFilter implements Filter {
	 private FilterConfig filterConfig;
	    public OpeFunction util;
	    @Override
	    public void destroy() {
		System.out.println("SessionForumAdminFilter������");
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response,
		    FilterChain chain) throws IOException, ServletException {
		try {
		    
		
		@SuppressWarnings("static-access")
		HttpSession session=util.request().getSession();
		// ��������ý��˹�������struts2������֮ǰʹ�ã�ʹ�����·���
		//session = ((HttpServletRequest)request).getSession();
		 request.setCharacterEncoding("UTF-8");// ��������������ָ���ı���   
		 System.out.println("SessionForumAdminFilter����ǰ");
		
		 
		 
			Admin admin = (Admin) session.getAttribute("admin");

			if (admin == null) {
			
			//System.out.println("��ʼ���ˣ�����");
			// ���session�в����ڵ�¼��ʵ�壬�򵯳�����ʾ���µ�¼
			// ����request��response���ַ�������ֹ����
			request.setCharacterEncoding("GBK");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			String loginPage = "/Befriend/SuperAdmin/SuperAdmin.jsp";
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">");
			builder.append("alert('����Ҫ��̳��������Ա��Ȩ�ޣ����������µ��룡');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
		}else{
			System.out.println("��������ԱsessionΪ��" + admin.getAdmin()
					+ "-����ʱ��Ϊ��" + util.getNowTime());
			//�жϹ���Ա���� 1�� ��߹��� 2������ ���� 3����̳����
			if(admin.getLevel()==1||admin.getLevel()==3){
				 chain.doFilter(request, response);//���С������ߵ��¸�����Ŀ����Դ��
				
			}else{
				System.out.println("�㲻����̳����!");
				util.Out().println("�㲻����̳����!");
			}
		
		}
		
		// System.out.println("����ʱ��"+util.getNowTime());
		} catch (Exception e) {
		   util.Out().println(e.getMessage());
		   
		}
		
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 System.out.println("SessionForumAdminFilter��ʼ����");
		 this.filterConfig = filterConfig;
		
	    }

		}

