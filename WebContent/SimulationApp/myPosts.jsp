<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取我创建的论坛
List<ForumOne> fones=(List)request.getAttribute("fones");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的帖子</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="cont">
   <h1 class="tit tit5"><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 我的帖子</span></a></h1>
   <%
   for(int i=0;i<fones.size();i++){
   %>
   <div class="post">
       <h2 class="ntit2"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fones.get(i).getId() %>"><%=fones.get(i).getTitle() %></a></h2>
       <div class="myname"><p class="rlyname rlyname2"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fones.get(i).getId() %>">
       <%
       if(fones.get(i).getImg()!=null){
    	   
    	%>
    	   <img src="<%=request.getContextPath()%><%=fones.get(i).getImg()%>" />
    	   <%
    	   
       }else{
       %>
       <img src="SimulationApp/images/logod.png" />
       
       <%
       }
       %>
       
       
       <%=fones.get(i).getContent() %></a> </p><p class="nate"><span><img src="SimulationApp/images/time.png" /><%=fones.get(i).getTime() %></span>  <a href="#" title="回复" ><img src="SimulationApp/images/huif.png" /><%=fones.get(i).getTotal() %></a></p></div>
   </div>
   <%
   }
   %>
</div>
<!--cont-->


</body>
</html>
