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
<title>注册错误</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body style="background:#f9f9f9">
<div class="cont">
<%
Object phone=request.getAttribute("ph");
Object username=request.getAttribute("ue");
if(username==null){
%>
<h1 class="tit tit5">注册失败 手机号已经注册过</h1>
<%
}else if(phone==null){
%>
<h1 class="tit tit5">注册失败 该用户名已经被注册过</h1>
<%
}else{
%>
<h1 class="tit tit5">注册失败!</h1>
<%
}
%>
 <br/><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="返回">

<!--recont-->
</div>
<!--cont-->
</body>
</html>
