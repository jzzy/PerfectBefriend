<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询统计信息</title>
</head>
<body style="background: #adc">
<%
List<Stas> android=(List)request.getAttribute("android");
List<Stas> ios=(List)request.getAttribute("ios");
List<Stas> web=(List)request.getAttribute("web");
List<Stas> other=(List)request.getAttribute("other");
List<Stas> syn=(List)request.getAttribute("syn");

%>
<form style="background: #adc" action="">
<table>
<tr>
<td width="100px">时间</td>
<td width="100px">新增游客</td>
<td width="100px">注册用户</td>
<td width="100px">登入用户</td>
<td width="100px">Max同时在线</td>
<td width="100px">客户端下载量</td>
<td width="100px">来自</td>
<td width="100px">地区</td>
</tr>
</table>
</form>

<form style="background:#75fda8;" action="">
<p >android</p>
<table>
<%
if(android!=null){
for(int i =0;i<android.size();i++){
%>
<tr>
<td width="100px"><%=android.get(i).getTime() %></td>
<td width="100px"><%=android.get(i).getVored() %></td>
<td width="100px"><%=android.get(i).getUsersaved() %></td>
<td width="100px"><%=android.get(i).getUserlogined() %></td>
<td width="100px"><%=android.get(i).getUsersyned() %></td>
<td width="100px"><%=android.get(i).getDownloaded() %></td>
<td width="100px"><%=android.get(i).getOs()%></td>
<td width="100px">全部android</td>
</tr>
<%
}
}
%>
</table>
</form>

<form style="background: #d28383" action="">
<p>ios</p>
<table>
<%
if(ios!=null){
for(int i =0;i<ios.size();i++){
%>


<tr>
<td width="100px"><%=ios.get(i).getTime() %></td>
<td width="100px"><%=ios.get(i).getVored() %></td>
<td width="100px"><%=ios.get(i).getUsersaved() %></td>
<td width="100px"><%=ios.get(i).getUserlogined() %></td>
<td width="100px"><%=ios.get(i).getUsersyned() %></td>
<td width="100px"><%=ios.get(i).getDownloaded() %></td>
<td width="100px"><%=ios.get(i).getOs()%></td>
<td width="100px">全部ios</td>
</tr>
<%
}}
%>
</table>
</form>
<form style="background: #d26383" action="">
<p>syn</p>
<table>
<%
if(syn!=null){
for(int i =0;i<syn.size();i++){
%>


<tr>
<td width="100px"><%=syn.get(i).getTime() %></td>
<td width="100px">不统计</td>
<td width="100px"><%=syn.get(i).getUsersaved() %></td>
<td width="100px"><%=syn.get(i).getUserlogined() %></td>
<td width="100px"><%=syn.get(i).getUsersyned() %></td>
<td width="100px">不统计</td>
<td width="100px"><%=syn.get(i).getOs()%></td>
<td width="100px">全部syn</td>
</tr>
<%
}
}
%>
</table>
</form>
<form style="background: #adc" action="">
<p>web</p>
<table>
<%
if(web!=null){
for(int i =0;i<web.size();i++){
%>


<tr>
<td width="100px"><%=web.get(i).getTime() %></td>
<td width="100px">不统计</td>
<td width="100px"><%=web.get(i).getUsersaved() %></td>
<td width="100px"><%=web.get(i).getUserlogined() %></td>
<td width="100px"><%=web.get(i).getUsersyned() %></td>
<td width="100px">不统计</td>
<td width="100px"><%=web.get(i).getOs()%></td>
<td width="100px">全部web</td>
</tr>
<%
}
}
%>
</table>
</form>
<form style="background: #adc" action="">
<p>其他</p>
<table>
<%
if(other!=null){
for(int i =0;i<other.size();i++){
%>


<tr>
<td width="100px"><%=other.get(i).getTime() %></td>
<%
if(other.get(i).getOs().equals("web")){
%>
<td width="100px">不统计</td>
<%
}else{
%>
<td width="100px"><%=other.get(i).getVored() %></td>
<%
}
%>
<td width="100px"><%=other.get(i).getUsersaved() %></td>
<td width="100px"><%=other.get(i).getUserlogined() %></td>
<td width="100px"><%=other.get(i).getUsersyned() %></td>

<%
if(other.get(i).getOs().equals("web")){
%>
<td width="100px">不统计</td>
<%
}else{
%>
<td width="100px"><%=other.get(i).getDownloaded() %></td>
<%
}
%>
<td width="100px"><%=other.get(i).getOs()%></td>
<td width="100px"><%=other.get(i).getProvince()%></td>
</tr>
<%
}
}
%>
</table>
</form>
</body>
</html>