<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
  
//按照时间排序  11个

List<News> Hottime=(List)request.getAttribute("Hottime");
//最热3

List<News> Hottest=(List)request.getAttribute("Hottest");
//本地新闻2

List<News> Hotarea=(List)request.getAttribute("Hotarea");
//轻松驿站1

List<News> typeqs=(List)request.getAttribute("typeqs");
//健康导航1

List<News> typejk=(List)request.getAttribute("typejk");
if(Hottime==null||Hottest==null||Hotarea==null||typeqs==null||typejk==null){
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script  src="SimulationApp/js/TouchSlide.1.1.js"></script>
</head>

<body>
<div class="cont">

   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="SimulationApp/images/xw_03.png" height="40" /></a>
      
       <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html"><img src="SimulationApp/images/xw_07.png" height="40" /></a></p>
   </div>
   <!--top-->
   <ul class="nav">
       <li class="current"><a href="<%=request.getContextPath()%>/webNewsA10">今日必读</a></li>
      <li><a href="<%=request.getContextPath()%>/SimulationApp/exchange2.jsp">社区互动</a></li>
      <li><a href="<%=request.getContextPath()%>/webGetapp">精彩内容</a></li>
      <li ><a href="<%=request.getContextPath()%>/SimulationApp/personal_information.jsp">我的信息</a></li>   </ul>
   <!--nav-->

 <!--topfixed-->
 
  <%
						for(int i=0;i<1;i++){
							if(Hottime.get(i)!=null)
							{
						%>
   <div class="adimg3">
      <a  class="pic"  href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottime.get(i).getId()%>"><img  src="<%=request.getContextPath()%><%=Hottime.get(i).getImgmax()%>"></a>
	  <a  class="tit"  href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottime.get(i).getId()%>"><%=Hottime.get(i).getTitle()%></a>
   </div>
   	<%
                         }
                         }
                         %>
   <%
						for(int i=1;i<9;i++){
							if(Hottime.get(i)!=null)
							{
						%>
   <dl class="list">
      <dt><a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottime.get(i).getId()%>"><img src="<%=request.getContextPath()%><%=Hottime.get(i).getImgmax()%>"/></a></dt>
      <a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottime.get(i).getId()%>">
      <dd>
      <%
      if(i<=5){
      %>
           <b style="color:#666"><img width="16px" src="<%=request.getContextPath()%>/SimulationApp/images/new-30-.png"/><%=Hottime.get(i).getTitle()%></b>
          
          <%
      }else{
          %>
          <b style="color:#666"><%=Hottime.get(i).getTitle()%></b>
          
          <%
          } 
          %>
          <p  class="subtit"><%=Hottime.get(i).getSummary()%> </p>
      </dd>
      </a>
   </dl>
  						<%
                         }
                         }
                         %>
    <!--list-->
   <h1 class="tit"><span>本地资讯</span><a href="<%=request.getContextPath()%>/webHotareaf" class="more">更多></a></h1>
   
      <%
						for(int i=0;i<Hotarea.size();i++){
							if(Hotarea.get(i)!=null)
							{
						%>
   <dl class="list">
      <dt>
      <a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hotarea.get(i).getId()%>">
      <img src="<%=request.getContextPath()%><%=Hotarea.get(i).getImgmax() %>" />
      </a>
      </dt>
     <a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hotarea.get(i).getId()%>">
      <dd>
           <b style="color:#666"><%=Hotarea.get(i).getTitle() %></b>
          <p><%=Hotarea.get(i).getSummary() %></p>
      </dd>
      </a>
   </dl>
   <%
							}
							}
   %>                      
                         
                         
   <!--list-->
   <h1 class="tit"><span>健康导航</span><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=7" class="more">更多></a></h1>
   
      <%
						for(int i=0;i<typejk.size();i++){
							if(typejk.get(i)!=null)
							{
						%>
   <dl class="list border0">
      <dt><a href="<%=request.getContextPath()%>/webNewsId?id=<%=typejk.get(i).getId()%>"><img src="<%=request.getContextPath()%><%=typejk.get(i).getImgmax() %>" /></a></dt>
      <a href="<%=request.getContextPath()%>/webNewsId?id=<%=typejk.get(i).getId()%>">
      <dd>
          <b style="color:#666"><%=typejk.get(i).getTitle() %></b>
          <p><%=typejk.get(i).getSummary() %></p>
      </dd>
      </a>
   </dl>
   <%
							}
							}
   %>
   <!--list-->
   <h1 class="tit"><span>轻松驿站</span><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=8" class="more">更多></a></h1>
    <%
						for(int i=0;i<typeqs.size();i++){
							if(typeqs.get(i)!=null)
							{
						%>
   						
   <dl class="list border0">
      <dt><a href="<%=request.getContextPath()%>/webNewsId?id=<%=typeqs.get(i).getId()%>"><img src="<%=request.getContextPath()%><%=typeqs.get(i).getImgmax()%>" /></a></dt>
     <a href="<%=request.getContextPath()%>/webNewsId?id=<%=typeqs.get(i).getId()%>">
     
      <dd>
        <b style="color:#666"><%=typeqs.get(i).getTitle() %></b>
          <p><%=typeqs.get(i).getSummary() %></p>
      </dd>
      </a>
   </dl>
    <%
							}
							}
   %>
    				 
   <!--list-->
   <h1 class="tit"><span>热文排名</span><a href="<%=request.getContextPath()%>/webHottest?pageSize=10&currentPage=1" class="more">更多></a></h1>
   
   
   <%
						for(int i=0;i<Hottest.size();i++){
							
						%>
   <dl class="list">
      <dt><a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottest.get(i).getId()%>"><img src="<%=request.getContextPath()%><%=Hottest.get(i).getImgmax() %>" /></a></dt>
     <a href="<%=request.getContextPath()%>/webNewsId?id=<%=Hottest.get(i).getId()%>">
      <dd>
         <b style="color:#666"><%=Hottest.get(i).getTitle() %></b>
          <p><%=Hottest.get(i).getSummary() %></p>
      </dd>
      </a>
   </dl>
   <%
							
							}
   %>
   
  
  
   <!--list-->
    <h1 class="tit"><span>精彩专栏</span></h1>
    <div class="jingcai">
        <ul class="jinglist">
           <li><a href="SimulationApp/Education_guide.jsp"><img src="SimulationApp/images/1shengxue.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=2"><img src="SimulationApp/images/2jiaozi.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=3"><img src="SimulationApp/images/3chengzhang.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=5"><img src="SimulationApp/images/4xingqu.png"/></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=4"><img src="SimulationApp/images/5liuxue.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=6"><img src="SimulationApp/images/6mingren.png" /></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=7" ><img src="SimulationApp/images/7jiankang.png"/></a></li>
           <li><a href="<%=request.getContextPath()%>/webNewtype?pageSize=10&currentPage=1&tp=8" ><img src="SimulationApp/images/8yizhan.png"/></a></li>
        </ul>
        
    </div>
    <!--jingcai-->
</div>
<!--cont-->
<script  type="text/javascript">
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:false,//自动分页
					autoPlay:false //自动播放
				});
			</script>
</body>
</html>
