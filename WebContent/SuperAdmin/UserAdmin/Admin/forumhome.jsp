<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛管理</title>
</head>
<body>
	<%
	User u=(User)session.getAttribute("useradmin");
	
	if(u!=null){
	%>
	当前那用户是:<%=u.getUsername()%>
	<%
	}else
	{
		return;
	}
	%>
	<br>

	<H3>	<a href="<%=request.getContextPath()%>/getUadmin">查看管理员</a><br></H3>	
	<H3><a href="<%=request.getContextPath()%>/getUsera">新增管理员</a></H3>		
 <a href=<%=request.getContextPath()%>/SuperAdmin/UserAdmin/User/UserInfo.jsp>返回首页</a>
</body>
</html>