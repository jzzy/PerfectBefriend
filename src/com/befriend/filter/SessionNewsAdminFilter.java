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

public class SessionNewsAdminFilter implements Filter {
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
			HttpSession session = util.request().getSession();
		
		
		
			Admin admin = (Admin) session.getAttribute("admin");

			if (admin == null) {
				
				
				request.setCharacterEncoding("GBK");
				response.setCharacterEncoding("GBK");
				PrintWriter out = response.getWriter();
				String loginPage = "/PerfectBefriend/SuperAdmin/SuperAdmin.jsp";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("alert('no2');");
				builder.append("window.top.location.href='");
				builder.append(loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			} else {
				
				if(admin.getLevel()==1||admin.getLevel()==2){
					 chain.doFilter(request, response);
					
				}else{
				
					util.Out().println("no2");
				}
			

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
