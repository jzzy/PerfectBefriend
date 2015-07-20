<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取论坛
List<ForumOne> fone=(List)request.getAttribute("fones");
//获取论坛最新回复
List<ForumTwo> ftwo=(List)request.getAttribute("ftwos");
//获取论坛创建者
List<User> us=(List)request.getAttribute("us");
//获取共多少页
int cpe=(Integer)request.getAttribute("cpe");
//获取类型
int id=(Integer)request.getAttribute("id");
String type=null;
switch (id) {
case 1:
	type="专家答疑";
	break;

case 2:
	type="学前";
	break;
case 3:
	type="小学";
	break;
case 4:
	type="中学";
	break;
case 5:
	type="同城家长";	
	break;
default:
	System.out.println("请重新登入!");
	((HttpServletResponse) OpeFunction.response())
			.sendRedirect("/Befriend/SimulationApp/login.html");
	return ;			
}
//获取当前页数
int currentPage=(Integer)request.getAttribute("currentPage");
if(fone==null||us==null){
	%>
	<p>没有信息</p>
	<%
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看论坛</title>
<link href="SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/style.js"></script>
</head>

<body>
<div class="cont">

  
 <!--topfixed-->
    <a href="<%=request.getContextPath()%>/SimulationApp/post_publish.jsp" class="tname tit1"><span><%=type %>讨论社区</span><b>发表帖子</b></a>
    
               <%
                    for(int i=0;i<fone.size();i++){
                    %>
   <ul class="userlist">
      <li>
                <div class="taoluncont">        
            <div class="taolun">
         
                   <div class="tall">
                   <p class="tneitong">
                   <a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>">
                     <%
                   if(us.get(i).getImg()!=null){
                   %>
                   <img src="<%=request.getContextPath()%><%=us.get(i).getImg() %>" />
                   <%
                   }else{
                   %>
                    <img src="SimulationApp/images/logod.png" />
                   <%
                   }
                   %>
       
                   </a>
                   </p>
                   <div class="fl"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><%=us.get(i).getNickname() %><br /><span><%=fone.get(i).getTime() %></span></a></abbr></div></div>
                   <div class="huifu3">
                      <p class="other"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><b><%=fone.get(i).getTitle() %></b></a></p>
                      <p class="huifunierong"><a href="<%=request.getContextPath()%>/webForumLook?id=<%=fone.get(i).getId() %>"><%=fone.get(i).getContent() %></a></p>
                      <p class="date"><span class="fl">来源：<%=fone.get(i).getArea()+fone.get(i).getAreas() %></span><span class="fr"><img src="SimulationApp/images/pinglun.png" /><%=fone.get(i).getFrs() %><img src="SimulationApp/images/huif.png" /><%=fone.get(i).getfHits() %></span></p>
                  
                  <!--  <br />
                  <hr />
                  -->
                   </div>
                  
                  
               </div>
               <!--taolun -->   
                
          </div>
               
              
              
      </li>
      
      
   </ul>
    <%
                    }
                   %>
   <!-- 返回主页 -->
   
 	<!-- 返回上一页 -->
   
  
   
   <!--userlist-->
   
   <p align="center">一共<%=cpe %>页 当前是第<%=currentPage %>页</p>
   <ul class="page page2">
<li><a href="<%=request.getContextPath()%>/SimulationApp/exchange2.jsp">主页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=1">首页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=currentPage-1%>">上一页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=currentPage+1%>">下一页</a></li>
<li><a href="<%=request.getContextPath()%>/webForumApptype?model=<%=id%>&pageSize=10&currentPage=<%=cpe %>">末页</a></li>
<li><a href="javascript:history.back(-1);">返回</a></li>
</ul>
   <div class="my"><a href="<%=request.getContextPath()%>/webForumoneTouseid">我的帖子</a></div>
</div>
<!--cont-->

</body>
</html>