package com.befriend.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.befriend.entity.Admin;
import com.befriend.util.OpeFunction;

public class SessionSuperAdminFilter implements Filter {
    private FilterConfig filterConfig;
    public OpeFunction util;
    @Override
    public void destroy() {
	System.out.println("SessionSuperAdminFilter������");
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	try {
	    
	
	@SuppressWarnings("static-access")
	HttpSession session=util.request().getSession();
	// ��������ý��˹�������struts2������֮ǰʹ�ã�ʹ�����·���

	 request.setCharacterEncoding("UTF-8");// ��������������ָ���ı���   
	 System.out.println("SessionSuperAdminFilter����ǰ");
	 Admin admin=(Admin)session.getAttribute("admin");

	if(admin==null){
		
		//System.out.println("��ʼ���ˣ�����");
		// ���session�в����ڵ�¼��ʵ�壬�򵯳�����ʾ���µ�¼
		// ����request��response���ַ�������ֹ����
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		String loginPage = "/PerfectBefriend/SuperAdmin/SuperAdmin.jsp";
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('����Ҫϵͳ��������ԱȨ�ޣ����������µ��룡');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());
	}else{
		System.out.println("��������ԱsessionΪ��"+admin.getAdmin()+"-����ʱ��Ϊ��"+util.getNowTime());
		//�жϹ���Ա���� 1�� ��߹��� 2������ ���� 3����̳����
		if(admin.getLevel()==1){
			 chain.doFilter(request, response);//���С������ߵ��¸�����Ŀ����Դ��
			
		}else{
			System.out.println("�㲻��admin����!");
			util.Out().print("�㲻��admin����!");
		}
	
	 
	
	}

	} catch (Exception e) {
	   util.Out().println(e.getMessage());
	   
	}
	
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	 System.out.println("SessionSuperAdminFilter��ʼ����");
	 this.filterConfig = filterConfig;
	
    }

	}

