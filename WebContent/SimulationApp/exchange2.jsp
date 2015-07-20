<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区互动</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="cont">
 <div class="topfixed">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="images/xw_03.png" height="40" /></a>
     <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html"><img src="images/xw_07.png" height="40" /></a></p>
        </div>
   <!--top-->
   <ul class="nav">
     <li><a href="<%=request.getContextPath()%>/webNewsA10">今日必读</a></li>
      <li class="current"><a href="<%=request.getContextPath()%>/SimulationApp/exchange2.jsp">社区互动</a></li>
      <li><a href="<%=request.getContextPath()%>/webGetapp">精彩内容</a></li>
      <li ><a href="<%=request.getContextPath()%>/SimulationApp/personal_information.jsp">我的信息</a></li>
    </ul>
   <!--nav-->
 </div>
 <!--topfixed-->
 <div class="ltcont margin75">
   <dl class="lttit">
      <dt><img src="images/lticon.png" width="35"></dt>
      <dd>
         <p><b>全国论坛</b></p>
         <p>包罗万象 家长经验全分享</p>
      </dd>
   </dl>
  
   <a href="<%=request.getContextPath()%>/webForumApptype?model=2"><img height="60px" width="350px" src="images/xueqian.png" /></a><br>
   <a href="<%=request.getContextPath()%>/webForumApptype?model=3"><img height="60px" width="350px" src="images/xiaoxue.png" /></a><br>
   <a href="<%=request.getContextPath()%>/webForumApptype?model=4"><img height="60px" width="353px" src="images/zhongxue.png" /></a><br>
    <a href="<%=request.getContextPath()%>/webForumApptype?model=5"><img height="60px" width="353px" src="images/bendi.png" /></a>
   
   <!--  
   <div class="xiangm">
   <a href="<%=request.getContextPath()%>/webForumoneTouseid"><img src="images/atz.png" /></a>
   <a><img src="images/ahf.png"/></a>
   <a><img src="images/asc.png" /></a>
   </div>
   -->
 </div>
 <!--ltcont-->
</div>
<!--cont-->

</body>
</html>