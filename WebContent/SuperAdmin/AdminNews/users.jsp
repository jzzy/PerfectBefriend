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
//List<User> ush=(List)request.getAttribute("GetUsertimeus");
String timeq=(String)request.getAttribute("timeq");
String timeh=(String)request.getAttribute("timeh");
Integer count=(Integer)request.getAttribute("count");
%>
<h4 style="color: blue;"><%=timeq%>^到^<%=timeh %>一共注册<%=count %>个用户</h4>

</body>
</html>