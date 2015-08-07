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
		
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response,
		    FilterChain chain) throws IOException, ServletException {
		try {
		    
		
		@SuppressWarnings("static-access")
		HttpSession session=util.request().getSession();
	
		
		 
		
		User user=(User)session.getAttribute("useradmin");
		
		
		
		Object luntan=util.request().getAttribute("luntan");
	
		if(user==null){
			
			request.setCharacterEncoding("GBK");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			String loginPage = null;
			//�����ڿ� ˵������̳��������
			if(luntan!=null){
				loginPage="/PerfectBefriend/SuperAdmin/UserAdminForum.jsp";
			}else{
				loginPage = "/PerfectBefriend/SuperAdmin/UserAdminInformation.jsp";
			}
			
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">");
			builder.append("alert('no User');");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
		}else{
	
		 chain.doFilter(request, response);
	
		 
		
		}
		
	
		} catch (Exception e) {
		   util.Out().println(e.getMessage());
		   
		}
		
	    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

	}

		}


