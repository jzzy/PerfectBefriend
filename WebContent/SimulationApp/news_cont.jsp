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
 <h1 class="tit tit5"><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 新闻详情</span></a></h1>
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
  <!--  <a href="<%=request.getContextPath()%>/webCsave?newsid=<%=n.getId()%>">收藏</a>--> 
   </div>
   <!--newtit-->

   <!--  <div class="zhaiyao"><b><%=n.getSummary() %></div>-->
   <!--zhaiyao-->
   <div class="contimg" style="text-align:center"><p><img src="<%=request.getContextPath()+n.getImgmax() %>"  width="90%"></p></div>
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
       <div class="myname">
       <p class="rlyname rlyname2">
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
       
     <p style="clear:both;"><%=rl.get(i).getReview() %></p>   
       </p>
       <p class="nate"><span><!-- <img src="<%=request.getContextPath()%>/SimulationApp/images/time.png" />--></span>
       </p>
       <br>
       <p class="nate"><span><%=rl.get(i).getTime() %></span>
       </p>
      
       </p>
       </div> 
         </div>
  
     <% 
   		}
   	}
   	}else{
    
    %>
    
    <%
   	}
    %>
   
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
   <p style="height: 300px;"></p>
   <!-- 回复楼主-->
 <form action="webRsave" method="post" >
 	<div class="pingnum pingnum3">	
	<!-- 获取论坛的id -->
	<input type="text" value="<%=n.getId() %>" name="newsid" style="display: none"/>
	
	<!-- 获取回复信息 -->
  <input type="text" id="review" class="shuru"  name="review" /><input type="submit" class="btijiao"  value="评论" />

	</div>
   
 </form>
 <!-- 
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
    -->
     
  
</div>
<!--cont-->

</body>
<%} %>
</html>
  
 
