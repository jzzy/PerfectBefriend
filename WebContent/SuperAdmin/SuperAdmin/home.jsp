<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家长天地后台</title>
</head>
<body>
<%
//获取管理员
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null||admin.getLevel()!=1){
	//重新登入
	return;
}
%>
	当前那用户是:<%=admin.getAdmin()%><br>
<br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/newshome.jsp">1,新闻管理&查看用户注册信息</a><br>
	<br>
		<a href="<%=request.getContextPath()%>/SuperAdmin/AdminForum/forumhome.jsp">2,用户权限管理and论坛管理</a><br>
		<br>
	<a href="<%=request.getContextPath()%>/Appset">3,App上传&管理</a><br>
	<br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/SuperAdmin/App/JZTDUP.jsp">4,家长天地客户端更新&调整更新内容</a><br>

	
</body>
</html>