<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%

ForumOne f=(ForumOne)request.getAttribute("fone");//论坛信息
User uu=(User)request.getAttribute("u");//论坛楼主信息
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<User> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<User> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
List fl=(List)request.getAttribute("fl");//论坛用户之间的回复信息

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>论坛详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="cont">
<h1 class="tit tit5">&lt; 论坛</h1>


<form action="">
<div class="allcity">
 <dl>
     <dt><img src="images/jczl_19.png" width="80" /></dt>
     <dd>
       <p class="ctit"><b><a href="#"><%=f.getTitle() %></a></b></p>
       <p><span><a href="#"><img src="images/rr.png" /><%=f.getTotal() %></a></span><span><a href="#"><img src="images/huif.png" /><%=f.getTitle() %></a></span></p>
     </dd>
 </dl>
 <p class="allguanzhu"><a href="#" class="guanzhu2">已关注</a></p>
</div>
</form>


<!--allcity-->
<div class="groupcont">
   <p class="ctit2">四年级四年级四年级四年级四年级</p>
   <p class="xiang"><span class="fl cuse"><a href="#" class="tou"><img src="images/tou.png" />四年级小青妈妈四年级小青妈妈</a></span><span class="fr time"><img src="images/time.png" />11/29  13：57　<a href="#"><img src="images/huif.png" />9999</a></span></p>
</div>
<!--grounpcont-->
<div class="groupcont">
   <p class="ctit2">四年级四年级四年级四年级四年级</p>
   <p class="xiang"><span class="fl cuse"><a href="#" class="tou"><img src="images/tou.png" />蜡笔小新</a></span><span class="fr time"><img src="images/time.png" />11/29  13：57　<a href="#"><img src="images/huif.png" />9999</a></span></p>
</div>
<!--grounpcont-->
<div class="my"><a href="#">发表新话题</a></div>
</div>
<!--cont-->
<input type="button" name="Submit" onclick="javascript:history.back(-1);" value="返回">
</body>
</html>
