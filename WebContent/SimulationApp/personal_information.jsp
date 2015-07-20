<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
User u=(User)session.getAttribute("u");
if(u==null){
	
	System.out.println("请重新登入!");
	((HttpServletResponse) OpeFunction.response())
			.sendRedirect("/Befriend/SimulationApp/login.html");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人资料</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="cont">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl">
       <img src="images/xw_03.png" height="40" />
       </a>
       <p class="set fr">
           
       <a href="<%=request.getContextPath()%>/SimulationApp/about.html">
       <img src="images/xw_07.png" height="40" />
       </a>
       
       </p>
      </div>
   <!--top-->
   <ul class="nav">
      <li><a href="<%=request.getContextPath()%>/webNewsA10">今日必读</a></li>
      <li><a href="<%=request.getContextPath()%>/SimulationApp/exchange2.jsp">社区互动</a></li>
      <li><a href="<%=request.getContextPath()%>/webGetapp">精彩内容</a></li>
      <li class="current"><a href="<%=request.getContextPath()%>/SimulationApp/personal_information.jsp">我的信息</a></li>
   </ul>
   <!--nav-->
   <div class="pinfor">
   
      <img src="images/mxx_02.png" width="100%" />
      
      <div class="twosubt">
         <p><a href="<%=request.getContextPath()%>/webNewsA10">主页 ></a></p>
         <!--  <p><a href="<%=request.getContextPath()%>/websessionrom">注销 ></a></p>-->
      </div>
      <!--twosubt-->
      <dl class="pcont">
         <dt>
         <%
         if(u.getImg()!=null){
        	 
         
         %>
         <img src="<%=request.getContextPath()%><%=u.getImg() %>" height="80" width="80" />
         <%
         }else{
         %>
          <img src="images/touxiang.png"  height="80" width="80" />
         <%
         }
         %>
         </dt>
         <dd class="name"><%=u.getNickname() %></dd>
         <dd><img src="images/adr.png" width="45" /><%=u.getAddress()+u.getAddcity()%>
         
         <img src="images/use.png" width="45" /><%=u.getStage() %></dd>
      </dl>
      <!--pcont-->
   </div>
   <!--pinfor-->
   <ul class="userlist2">
      <li><a href="<%=request.getContextPath()%>/SimulationApp/perfect_information.jsp"><span><img src="images/mxx_05.png" />我的资料</span><b>></b></a></li>
      <li><a href="<%=request.getContextPath()%>/webForumoneTouseid"><span><img src="images/mxx_08.png" />我的帖子</span><b>></b></a></li>
      <li><a href="<%=request.getContextPath()%>/webSearchBookmark"><span><img src="images/mxx_10.png" />我的收藏</span><b>></b></a></li>
      <li><a href="<%=request.getContextPath()%>/webReviewsusername"><span><img src="images/mxx_12.png" />我的评论</span><b>></b></a></li>
      <!--  
      <li><a href="#"><span><img src="images/mxx_14.png" />我的消息</span><b>></b></a></li>
      <li><a href="<%=request.getContextPath()%>/SimulationApp/change_password2.html"><span><img src="images/mxx_16.png" />修改密码</span><b>></b></a></li>
      -->
   </ul>
   <!--userlist-->
</div>
<!--cont-->
</body>
</html>

