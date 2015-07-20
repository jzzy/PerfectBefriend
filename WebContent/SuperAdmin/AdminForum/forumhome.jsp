<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛管理</title>
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
	<H3><a href="<%=request.getContextPath()%>/SuperAdmin/AdminForum/Forumsave.jsp">添加论坛</a><br></H3>
		<!-- <H3><a href="<%=request.getContextPath()%>/ForumLookall">查看普通论坛</a><br></H3> -->
		<H3><a href="<%=request.getContextPath()%>/ForumLookalltype?model=1">查看专家答疑论坛</a><br></H3>
		<H3><a href="<%=request.getContextPath()%>/ForumLookalltype?model=2">查看学前论坛</a><br></H3>
		<H3><a href="<%=request.getContextPath()%>/ForumLookalltype?model=3">查看小学论坛</a><br></H3>
		<H3><a href="<%=request.getContextPath()%>/ForumLookalltype?model=4">查看中学论坛</a><br></H3>
		<H3><a href="<%=request.getContextPath()%>/ForumLookalltype?model=5">查看地区论坛</a><br></H3>
	<H3>	<a href="<%=request.getContextPath()%>/getUseradmin">查看管理</a><br></H3>	
	<H3><a href="<%=request.getContextPath()%>/getUser">新增各地管理</a></H3>		

</body>
</html>