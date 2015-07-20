<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=tf-8">
<title>用户详细信息</title>
<script type="text/javascript" >

function ck()
{
 if(confirm("是否继续？"))
 {
	
  return true;
 }
 else{
	   return false;
 }
}
   </script>
</head>
<body>
<%
User u=(User)request.getAttribute("u");
%>


<div style="background:#adc">
<form action="SetQU" method="post" enctype="multipart/form-data">
<table>
<input type="text" name="id" value=<%=u.getId()%> style="display: none">
<tr>
<td>用户名为:<%=u.getUsername() %></td>
</tr>
<tr>
<td>用户地址:<%=u.getAddress()%><%=u.getAddcity()%></td>
</tr>
<tr>
<td>用户手机号:<%=u.getPhone()%></td>
</tr>
<tr>
<td width="380">用户注册时间:<%=u.getTime()%></td>
</tr>
<tr>
<td>用户孩子所在学校:<%=u.getSchool()%></td>
</tr>
<!-- 普通用户 -->
<%
if(u.getCompetence()==0){
%>
<tr>

<td width="180">市级权限</td>
</tr>
<tr>

<td width="180"><a href="SetQU?competence2=1&id=<%=u.getId()%>" onclick="return ck();"> 授权市级管理员</a></td>
</tr>
<%} %>
<!-- 市级管理员-->
<%
if(u.getCompetence()==1){
%>
<tr>

<td width="180">用户为市级管理员</td>
<td width="180"><a href="SetQU?competence2=3&id=<%=u.getId()%>" onclick="return ck();"> 撤销市级管理员</a></td>
</tr>

<%} %>
<!-- 省级管理员-->
<%
if(u.getCompetence()==2){
%>
<tr>

<td width="180">用户为省级管理员</td>
<td width="180"><a href="SetQU?competence2=3&id=<%=u.getId()%>" onclick="return ck();"> 撤销省级管理员</a></td>
</tr>

<%} %>
<!-- 用户没有被禁言-->
<%if(u.getGag()==0){ %>
<td width="180">是否禁言用户</td>
<td width="180"><a href="Gag?gag=1&username=<%=u.getUsername()%>" onclick="return ck();"> 禁言</a></td>
<%} %>
<!-- 用户已经被禁言-->
<%if(u.getGag()==1){ %>
<td width="180">是否禁言用户</td>
<td width="180"><a href="Gag?gag=0&username=<%=u.getUsername()%>" onclick="return ck();"> 取消禁言</a></td>
<%} %>
</table>
</form>
<!-- 返回主页的链接-->
 <a href=<%=request.getContextPath()%>/SuperAdmin/UserAdmin/User/UserInfo.jsp>返回首页</a>
</div>
</body>
</html>