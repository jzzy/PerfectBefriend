<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%



//获取总的页数
int a=(Integer)request.getAttribute("a");
//获取第几页
int currentPage=(Integer)request.getAttribute("currentPage");
//获取新闻
List<News> nl=(List)request.getAttribute("nl");
if(nl==null||currentPage<=0){
	
	%> 
	<p>没有您要的信息</p>
	<% 
	return;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本地新闻</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/jquery-1.2.6.pack.js"></script>

</head>

<body>
<div class="cont">
   <h1 class="tit tit6"><a href="<%=request.getContextPath()%>/webNewsA10" class="fl">&lt; 返回</a></h1>
   <div class="sstop">
  
   </div>
   <ul class="specil_list">
   <%
   for(int i=0;i<nl.size();i++){
   %>
      <li><a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><span><%=nl.get(i).getTitle() %></span><b><%=nl.get(i).getTime() %><img src="SimulationApp/images/xing.png" /><%=nl.get(i).getHits()%></b></a></li>
    <%
   }
    %> 
   </ul>
    
   <br/>一共<%=a %>页 当前是第<%=currentPage %>页
</div>


  <!--more4-->

<ul class="page">

<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=1">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=currentPage-1%>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=currentPage+1%>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webHotareaf?pageSize=10&currentPage=<%=a %>">末页</a></li>
</ul>


</body>
</html>