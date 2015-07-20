<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询用户</title>
</head>
<body>
<%
List<User> ush=(List)request.getAttribute("usaha");
%>
<%
if(ush.size()==0){
%>
<form action="">
<tr>
<td>没有您要的用户!!!</td>
</tr>
</form>
<%} %>
<% 

for(int i=0;i<ush.size();i++){
	
%>




<%
	
	%>
	<form action="">
<tr>
<td><a href="getUserine?username=<%=ush.get(i).getUsername() %>" >用户名为:<%=ush.get(i).getUsername() %></a>地区为:<%=ush.get(i).getAddress() %><%=ush.get(i).getAddcity() %></td>
</tr>
<tr>
<td>头像是<img
			height="70" width="100"
			src="<%=request.getContextPath()+ush.get(i).getImg() %>" /></td>
</tr>
</form>
	<% 
	
}
%>


</body>
</html>