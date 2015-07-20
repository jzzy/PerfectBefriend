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
//获取新闻分类
String type=(String)request.getAttribute("type");
//获取新闻分类代号
int tp=(Integer)request.getAttribute("tp");
//获取新闻
List<News> nl=(List)request.getAttribute("nl");
if(nl==null||currentPage<=0||type==null){
	
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
<title><%=type %></title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="SimulationApp/js/jquery-1.2.6.pack.js"></script>

</head>

<body>
<div class="cont">
   <h1 class="tit tit6"><a href="<%=request.getContextPath()%>/webNewsA10" class="fl">&lt; 返回</a></h1>
   <div class="sstop">
   
   <%
   String pth=null;
   if(type.equals("小升初")){
	   pth="2xsc.png";
   }
   if(type.equals("幼升小")){
	   pth="3yx.png";
   }
   if(type.equals("中考")){
	   pth="4zk.png";
   }
   if(type.equals("高考")){
	   pth="4gk.png";
   }
   if(pth==null){
	   pth="logod.png";
   }
   %>
       <p class="simg"><img src="SimulationApp/images/<%=pth %>" height="120" /></p><p class="scont"><span>汇集数位顶尖名人的教子启示录，分享教育经验，启发父母的正确教育观念。</span></p>
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

<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=<%=tp%>">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=currentPage-1%>&tp=<%=tp%>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=currentPage+1%>&tp=<%=tp%>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=<%=a %>&tp=<%=tp%>">末页</a></li>
</ul>
<!--  
<ul class="page">
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
<li><a href="">共<%=a%>页</a></li>
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
<li><a href="">第<%=currentPage%>页</a></li>
<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
</ul>
-->
<!--cont-->

 
</body>
</html>
