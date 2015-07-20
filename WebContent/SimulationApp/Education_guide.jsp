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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script>
$(function(){
  $(".jinglist").find("li").eq(3).find("a").addClass("borderno")
  $(".jinglist").find("li").eq(2).find("a").addClass("borderno")	
})
	

</script>
</head>

<body>
<div class="cont">
   <h1 class="tit tit6"><a href="<%=request.getContextPath()%>/webNewsA10" class="fl">&lt; 返回</a></h1>
   <div class="jingcai">
        <ul class="jinglist" id="jing">
           <li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=1" ><img src="images/1yx.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=2" ><img src="images/2xs.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=3" ><img src="images/3zk.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webTypes?pageSize=10&currentPage=1&tp=4" ><img src="images/4gk.png"/></a></li>
        </ul>
        
    </div>
    <!--jingcai-->
</div>
<!--cont-->

</body>
</html>