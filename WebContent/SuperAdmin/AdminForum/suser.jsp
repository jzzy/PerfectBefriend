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
<script type="text/javascript" >

function ck()
{
 if(confirm("确定"))
 {
	
  return true;
 }
 else{
	   return false;
 }
}
   </script>
<body>
<%
List<User> ush=(List)request.getAttribute("usaha");
List<Password> pl=(List)request.getAttribute("pl");
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
//获取管理员
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	return;
}
for(int i=0;i<ush.size();i++){
	//等于1 就是 超级管理员
	if(admin.getLevel()==1){
		if(pl.get(i)==null||ush.get(i)==null){
			return;
		}
%>
<form action="">
<table>
<tr>
<td>
<a href="userLogout?id=<%=ush.get(i).getId() %>" onclick="return ck()"><input type="button" value="注销手机号" ></a>
</td>
</tr>
<tr>
<td><a href="getUserin?id=<%=ush.get(i).getId() %>" >用户名是:<%=ush.get(i).getUsername() %>----------密码为是:<%=pl.get(i).getPassword() %>手机号是:<%=ush.get(i).getPhone() %>用户账号是:<%=ush.get(i).getAccnumno() %>-----------</a></td>

<td>头像是<img
			height="70" width="100"
			src="<%=request.getContextPath()+ush.get(i).getImg() %>" /></td>
</tr>

</table>
<hr>
</form>

<%
		
	}else{
		if(ush.get(i)==null){
			return;
		}
	%>
	<form action="">
<tr>
<td><a href="getUserin?id=<%=ush.get(i).getId() %>" ><%=ush.get(i).getUsername() %></a></td>
</tr>
</form>
	<% 
	}
}
%>


</body>
</html>