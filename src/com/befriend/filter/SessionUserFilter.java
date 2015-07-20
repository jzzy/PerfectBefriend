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

import com.befriend.entity.User;
import com.befriend.util.OpeFunction;

public class SessionUserFilter implements Filter  {
	  private FilterConfig filterConfig;
	    public OpeFunction util;
	    @Override
	    public void destroy() {
		System.out.println("SessionUserFilter销毁了");
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
		 System.out.println("SessionUserFilter过滤前");
		
		 
		
		User user=(User)session.getAttribute("useradmin");
		
		
		
		Object luntan=util.request().getAttribute("luntan");
	
		if(user==null){
			//System.out.println("开始过滤！！！");
			// 如果session中不存在登录者实体，则弹出框提示重新登录
			// 设置request和response的字符集，防止乱码
			request.setCharacterEncoding("GBK");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			String loginPage = null;
			//不等于空 说明从论坛登入来的
			if(luntan!=null){
				loginPage="/Befriend/SuperAdmin/UserAdminForum.jsp";
			}else{
				loginPage = "/Befriend/SuperAdmin/UserAdminInformation.jsp";
			}
			
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">");
			builder.append("alert('系统检测到您权限不对！！请您重新登入！');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
		}else{
		//System.out.println("obj"+obj);
		 chain.doFilter(request, response);//放行。让其走到下个链或目标资源中
		System.out.println("用户管理员"+user.getUsername()+"登入成功！");
		 
		
		}
		
		// System.out.println("过滤时间"+util.getNowTime());
		} catch (Exception e) {
		   util.Out().println(e.getMessage());
		   
		}
		
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 System.out.println("SessionUserFilter初始化了");
		 this.filterConfig = filterConfig;
		
	    }

		}


