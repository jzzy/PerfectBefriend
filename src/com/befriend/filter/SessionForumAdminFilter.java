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
		System.out.println("SessionForumAdminFilter销毁了");
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response,
		    FilterChain chain) throws IOException, ServletException {
		try {
		    
		
		@SuppressWarnings("static-access")
		HttpSession session=util.request().getSession();
		// 如果你想用将此过滤器在struts2过滤器之前使用，使用如下方法
		//session = ((HttpServletRequest)request).getSession();
		 request.setCharacterEncoding("UTF-8");// 在请求里设置上指定的编码   
		 System.out.println("SessionForumAdminFilter过滤前");
		
		 
		 
			Admin admin = (Admin) session.getAttribute("admin");

			if (admin == null) {
			
			//System.out.println("开始过滤！！！");
			// 如果session中不存在登录者实体，则弹出框提示重新登录
			// 设置request和response的字符集，防止乱码
			request.setCharacterEncoding("GBK");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			String loginPage = "/Befriend/SuperAdmin/SuperAdmin.jsp";
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">");
			builder.append("alert('这需要论坛超级管理员的权限！！请您重新登入！');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
		}else{
			System.out.println("超级管理员session为：" + admin.getAdmin()
					+ "-登入时间为：" + util.getNowTime());
			//判断管理员级别 1是 最高管理 2是新闻 管理 3是论坛管理
			if(admin.getLevel()==1||admin.getLevel()==3){
				 chain.doFilter(request, response);//放行。让其走到下个链或目标资源中
				
			}else{
				System.out.println("你不是论坛管理!");
				util.Out().println("你不是论坛管理!");
			}
		
		}
		
		// System.out.println("过滤时间"+util.getNowTime());
		} catch (Exception e) {
		   util.Out().println(e.getMessage());
		   
		}
		
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 System.out.println("SessionForumAdminFilter初始化了");
		 this.filterConfig = filterConfig;
		
	    }

		}

