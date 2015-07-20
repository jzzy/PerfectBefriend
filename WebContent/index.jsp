<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>Hello <%=request.getContextPath()%>!
</body>


<div style="background:#abc">
<hr></hr>
<a href="SuperAdmin/SuperAdmin.jsp">1.1系统管理员登入</a>
<a href="SuperAdmin/UserAdminForum.jsp">1.2用户论坛管理员登入</a>
<a href="SuperAdmin/UserAdminInformation.jsp">1.3查看用户注册信息</a>
<hr></hr>

</div>
<div style="background:#adc">
	<a href="<%=request.getContextPath()%>/Newsget">2.1管理新闻</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/Appset">2.2App管理</a><br>
	
	<a href="<%=request.getContextPath()%>/SuperAdmin/SuperAdmin/App/JZTDUP.jsp">2.3.家长天地App更新</a><br>	
	<hr></hr>
	<a href="<%=request.getContextPath()%>/SuperAdmin/AdminForum/Forumsave.jsp">2.4.添加论坛</a><br>	
	<a href="<%=request.getContextPath()%>/getUser">2.5.新增各地论坛管理员</a><br>
	<a href="<%=request.getContextPath()%>/getUseradmin">2.6.查询论坛管理员</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/weiXniBDN">3.1微信新闻首页</a><br>
	<a href="<%=request.getContextPath()%>/SimulationApp/login.html">3.2WEB用户登入</a><br>
	<hr></hr>
	<a href="<%=request.getContextPath()%>/weixin/Wonderful.html">3.3精彩互动</a><br>	
	<a href="<%=request.getContextPath()%>/weixin/Local_info.html">3.4本地资讯</a><br>
</div>


</html>
