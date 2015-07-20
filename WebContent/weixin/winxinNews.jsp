<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>新闻</title>
<link href="weixin/css/style.css" rel="stylesheet" type="text/css" />
<script  src="weixin/js/TouchSlide.1.1.js"></script>
</head>
<%
//获取前8个新闻
List<News> nl=(List)request.getAttribute("nl");
//获取2个健康导航
List<News> nj=(List)request.getAttribute("nj");
//获取地区
String Address=request.getAttribute("Address").toString();
if(nl==null||nj==null||Address==null){
	return;
}
if(nl.size()<8||nj.size()<2){
	return;
}
%>
<body>
<div class="cont">
   <div class="address"><span class="currad"><%=Address %></span><a href="#">[切换城市]</a></div>
   <!--nav-->
   <!-- <a href="news_more.html" class="more">></a> -->
   <h1 class="tit"><span>本地新闻</span></h1>
   <div  id="slideBox"  class="slideBox">
			  <div  class="bd">
					<div  class="tempWrap"  style="overflow:hidden; position:relative;">
                        <ul  style="width: 3360px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition: 200ms; -webkit-transition: 200ms; -webkit-transform: translate(-1680px, 0px) translateZ(0px);">
							<%
								for(int i=0;i<4;i++){
									
							%>
							
							<li  style="display: table-cell; vertical-align: top; width:100%;">
								
								<a  class="pic" href="<%=request.getContextPath()%>/weiXniNewsId?id=<%=nl.get(i).getId()%>"><img  src="<%=request.getContextPath()+nl.get(i).getImgmax()%>"></a>															
								
							</li>
                           <%
                           } 
                           %>
                       </ul>
                    </div>
				</div>
                <!--bd-->
				<div  class="hd">
					<ul><li  class="">1</li><li  class="">2</li><li  class="on">3</li><li  class="">4</li></ul>
				</div>
   </div>
   <!--slideBox-->
 <%
				for(int i=4;i<nl.size();i++){
			%>
				<dl class="list">
    				<dt><a href="<%=request.getContextPath()%>/weiXniNewsId?id=<%=nl.get(i).getId()%>"><img src="<%=request.getContextPath()+nl.get(i).getImg() %>"/></a></dt>
     			    <dd>
     			    <p class="subtit"> <a  class="pic" href="<%=request.getContextPath()%>/weiXniNewsId?id=<%=nl.get(i).getId()%>"><%=nl.get(i).getTitle() %></a></p>
       				
        		      <p><%=nl.get(i).getSummary() %></p>
     		       </dd>
  				 </dl>	
							
           <%
			}
            %>
            
            <!-- <a href="news_more.html" class="more">></a> -->
          <h1 class="tit" ><span>健康导航</span></h1>
   
 			<%
			for(int i=0;i<nj.size();i++){
			%>
   				<!--list-->
   
   				<dl class="list border0">
    				  <dt><a href="<%=request.getContextPath()%>/weiXniNewsId?id=<%=nj.get(i).getId()%>"><img src="<%=request.getContextPath()+nj.get(i).getImg() %>" /></a></dt>
     					 <dd>
         				 <p class="subtit"> <a  class="pic" href="<%=request.getContextPath()%>/weiXniNewsId?id=<%=nj.get(i).getId()%>"><%=nj.get(i).getTitle() %></a></p>
         					 <p><%=nj.get(i).getSummary() %></p>
      					</dd>
   				</dl>
   
     		<%
			}
            %>
  
</div>
<!--cont-->
<script  type="text/javascript">
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放
				});
			</script>
</body>
</html>
    