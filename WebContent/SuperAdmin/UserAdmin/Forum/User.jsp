<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册人数</title>
</head>
<body>

<%
User u=(User)session.getAttribute("useradmin");
if(u==null){
	return;
}
%>
<div style="background:#adc">
当前那用户是:<%=u.getUsername()%>
<br>
<%
String area=(String)session.getAttribute("area");


%>
这是 <%=area %>的用户信息
<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200">地区</td>
<td width="150">今天注册人数</td>

<td width="150">7天内注册人数</td>

<td width="150">30天内注册人数</td>

<td width="150">365天内注册人数</td>

<td width="300">更新时间</td>
</tr>
</table>
</form>
<%
Registrationsa rsa=(Registrationsa)session.getAttribute("rg");
if(rsa!=null){
%>
<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200"><%=rsa.getAddress()+rsa.getAddcity() %></td>
<td width="150"><%=rsa.getNote() %></td>
<td width="150"><%=rsa.getNote7() %></td>
<td width="150"><%=rsa.getNote30() %></td>
<td width="150"><%=rsa.getNote365()%></td>
<td width="300"><%=rsa.getTime() %></td>
</tr>
</table>
</form>
<%
}
%>

<%
List<Registrationsa> lg=new ArrayList<Registrationsa>();
lg=(List<Registrationsa>)session.getAttribute("lg");
if(session.getAttribute("lg")!=null){
for(int i=0;i<lg.size();i++){
	

%>
<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200"><%=lg.get(i).getAddress()+lg.get(i).getAddcity() %></td>
<td width="150"><%=lg.get(i).getNote() %></td>
<td width="150"><%=lg.get(i).getNote7() %></td>
<td width="150"><%=lg.get(i).getNote30() %></td>
<td width="150"><%=lg.get(i).getNote365()%></td>
<td width="300"><%=lg.get(i).getTime() %></td>
</tr>
<table>
</form>

<%

}
}
%>


</div>
</body>
</html>