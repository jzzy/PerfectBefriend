<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家长天地新闻管理后台</title>
</head>
<body>
<%
//获取管理员
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	//重新登入
	return;
}
%>
	当前那用户是:<%=admin.getAdmin()%><br>
	<br>
	<a href="<%=request.getContextPath()%>/GetUserAll">查看用户信息</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/ViewStatistics">查询统计信息</a><br>
	<a href="<%=request.getContextPath()%>/Newsget">管理新闻</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/kindeditor/jsp/AU.jsp">八大类新闻上传</a><br>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminNews/kindeditor/jsp/AUB.jsp">本地新闻上传</a><br>

</body>
</html>