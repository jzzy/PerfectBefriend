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
List<User> ush=(List)request.getAttribute("GetUsertimeus");
String timeq=(String)request.getAttribute("timeq");
String timeh=(String)request.getAttribute("timeh");
if(timeh==null){
	return;
}
%>
<%
if(ush.size()==0){
%>
<form action="">
<tr>
<td>没有您要的用户</td>
</tr>
</form>
<%
return;
} %>
<h4 style="color: blue;"><%=timeq%>^到^<%=timeh %>一共注册<%=ush.size() %>个用户</h4>
<form  action="">
<table>
<tr>
<td width="300" >用户名为</td>
<td width="180" >省市地区</td>
<td width="300" >注册时间</td>

</tr>
</table>
</form>
<% 
for(int i=0;i<ush.size();i++){
%>
<form style="background: #bcc" action="">
<table>
<tr>
<td width="300"><%=ush.get(i).getUsername() %></td>
<td width="180"><%=ush.get(i).getAddress() %><%=ush.get(i).getAddcity()%></td>
<td width="300"><%=ush.get(i).getTime() %></td>

</tr>
</table>
</form>

<%
}
%>


</body>
</html>