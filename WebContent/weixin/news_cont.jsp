<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看新闻详情</title>
<link href="weixin/css/style.css" rel="stylesheet" type="text/css" />

</head>
<%
//获取新闻
News n=(News)request.getAttribute("n");
//获取品论新闻的用户信息
List<User> ul=(List)request.getAttribute("ul");
//获取新闻品论
List<Review> rl=(List)request.getAttribute("rl");
if(n==null){
%>
<p>没有新闻</p>
<%}else{ %>
<body>
<div class="cont"><!--top-->
 <div class="newtit">
       <h2 class="ntit"><%=n.getTitle() %></h2>
       <%
       if(n.getArea()!=null&&n.getAreas()!=null){
       %>
       
       <div class="date"><%="发布日期:"+n.getTime()+"   "+n.getArea()+n.getAreas() %></div>
		<% 
       }else if(n.getAreas()!=null){
		%>
		 <div class="date"><%="发布日期:"+n.getTime()+"   "+n.getArea()+n.getAreas() %></div>
		<%
		}else{
       %>
        <div class="date"><%="发布日期:"+n.getTime()+"   "+n.getArea() %></div>
       <%
       }
       %>
   </div>
   <!--newtit-->
   <div class="zhaiyao"><b><%=n.getSummary() %></div>
   <!--zhaiyao-->
   <div class="contimg"><p><img src="<%=request.getContextPath()+n.getImgmax() %>" width="100%"></p></div>
   <p class="nditail"><%=n.getContent() %></p>
    <% 
   	if(rl!=null&&ul!=null){
   		%>
   		  <h2  class="nditail">评论</h2>
   		<% 
   	for(int i=0;i<rl.size();i++){
   		if(ul.get(i)!=null&&rl.get(i)!=null){
   %>
  
   <form class="nditail" action="">
   <table>
 
   <tr>
   <td><%=ul.get(i).getNickname() %></td>
   </tr>
   <tr>
   <td>
  <%=rl.get(i).getReview() %> 
   </td>
   </tr> 
   <tr>
   <td>
   <%=rl.get(i).getTime() %>
   </td>
   </tr> 
   </table>
   </form>
     <% 
   		}
   	}
   	}else{
    
    %>
    
    <%
   	}
    %>
   
   <h1 class="tit tit3"><span>更多热点</span><a href="#" class="more">></a></h1>
   <ul class="hot">
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
   </ul>
   <!--hot-->

</div>
<!--cont-->

</body>
<%} %>
</html>