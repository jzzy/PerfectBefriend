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
       }else if(n.getTypes()!=null){
		%>
		 <div class="date"><%="发布日期:"+n.getTime()+"   "+n.getType() +" "+n.getTypes()%></div>
		<%
		}else if(n.getType()!=null){
       %>
        <div class="date"><%="发布日期:"+n.getTime()+"   "+n.getType()%></div>
       <%
       }else{
       %>
        <div class="date"><%="发布日期:"+n.getTime()%></div>
       <%
       }
       %>
   <a href="<%=request.getContextPath()%>/webCsave?newsid=<%=n.getId()%>">收藏</a>
   </div>
   <!--newtit-->

   <div class="zhaiyao"><b><%=n.getSummary() %></div>
   <!--zhaiyao-->
   <div class="contimg"><p><img src="<%=request.getContextPath()+n.getImgmax() %>" width="100%"></p></div>
   <p class="nditail"><%=n.getContent() %></p>
    <% 
   	if(rl!=null&&ul!=null){
   		%>
   		  <h3  class="post">评论</h3>
   		<% 
   	for(int i=0;i<rl.size();i++){
   		if(ul.get(i)!=null&&rl.get(i)!=null){
   %>
  
  
   <div class="post">
       <h2 class="ntit2"><%=ul.get(i).getNickname() %></h2>
       <div class="myname"><p class="rlyname rlyname2">
       <%
       if(ul.get(i).getImg()!=null){
       %>
       <img src="<%=request.getContextPath()%><%=ul.get(i).getImg()%>" />
       <%
       }else{
       %>
        <img src="<%=request.getContextPath()%>/SimulationApp/images/logod.png" />
       <%
       }
       %>
       <%=rl.get(i).getReview() %> 
       <p class="nate"><span><img src="<%=request.getContextPath()%>/SimulationApp/images/time.png" /><%=rl.get(i).getTime() %></span></p></div> 
         </div>
  
     <% 
   		}
   	}
   	}else{
    
    %>
    
    <%
   	}
    %>
    <br/><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="返回">
   <!--  
   <h1 class="tit tit3"><span>更多热点</span><a href="#" class="more">></a></h1>
   <ul class="hot">
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
      <li><a href="#">新闻1 啦啦啦啦啦啦啦啦阿拉</a></li>
   </ul>
   -->
   <!--hot-->
 <!--hot-->
   <div class="pingnum">
   <form action="webRsave">
   <table>
   <tr>
   <td>
    <input type="text" value="<%=n.getId() %>" name="newsid" style="display: none"/>
    <input type="text" value="" name="review"/>
    <input type="submit" value="评论"/>
   <%=n.getReviews() %>条评论
   </td>
   </tr>
   </table>
   </form>
     
   </div>
</div>
<!--cont-->

</body>
<%} %>
</html>
  
 
