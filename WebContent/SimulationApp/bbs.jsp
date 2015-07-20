<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<% 
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<User> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<User> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
List fl=(List)request.getAttribute("fl");//论坛用户之间的回复信息
ForumOne f=(ForumOne)request.getAttribute("fone");//论坛信息
User uu=(User)request.getAttribute("u");//论坛楼主信息
int ri=1;
if(f==null){
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
<script type="text/javascript" src="SimulationApp/js/jquery.js"></script>
<script type="text/javascript" src="SimulationApp/js/style.js"></script>
<script type="text/javascript">
function showdiv(targetid,objN){
	   
    var target=document.getElementById(targetid);
    var clicktext=document.getElementById(objN)

          if (target.style.display=="block"){
              target.style.display="none";
             // clicktext.innerText="点击回复";


          } else {
              target.style.display="block";
              //clicktext.innerText='填写回复内容';
          }
 
}


function check(picForm)
{
	
	  var title = document.getElementById("reply").value;
	  
	
	   if (title==""||title==null||title.length>100)
    	{
    		alert("请填写100个汉字以内！"+title+picForm);
    	
    		return false;
  	}

return true;
}

</script>
</head>

<body>
<div class="cont">
<!-- <%=f.getType() %>论坛 -->
<h1 class="tit tit5"><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 论坛详情</span></a></h1>

<form action="webForumtwosaveapp" method="post">
<div class="bbs">
 <dl class="bbs1">
 	<!-- 判断是否有图片 用户头像  -->
 	<%
 	if(uu.getImg()==null){
 	%>
     <dt><img src="SimulationApp/images/logod.png" width="40" /></dt>
     
     <%
 	}else{
     %>
      <dt><img src="<%=request.getContextPath()%><%=uu.getImg() %>" width="40" /></dt>
      <%
 	}
      %>
          
      <dd>    
       <p class="ctit5"><b>
       <a id="showtext<%=f.getId() %>" onClick="showdiv('contentid<%=f.getId() %>','showtext<%=f.getId() %>')">
       <%=uu.getNickname()%>
       </a>
       <img src="SimulationApp/images/louzhu.png" width="41" /></b></p>
       <p class="date"><%=f.getTime()%></p>
     </dd>
 </dl>
 <h6 class="bbtit"><%=f.getTitle()%></h6>
 <p><%=f.getContent()%></p>
 
 
      <!-- 判断是否有图片论坛帖子图片 -->
 	<%
 	if(f.getImg()!=null){
 	%>
   
      <p class="bbimg"><img src="<%=request.getContextPath()%><%=f.getImg() %>" /></p>
     
     <%
 	}
     %>
   
    
 <!-- 回复楼主 -->
 <div class="pingnum pingnum2" id="contentid<%=f.getId() %>" style="display: none">		
	<!-- 获取论坛的id -->
	<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none""/>
	<!-- 获取回复信息 -->
		
  <input type="text"  id="reply" class="shuru" name="reply"  /><input class="btijiao2"  type="submit"   value="回复楼主<%=uu.getNickname()%>" />
</div> 

 </form>
 <%
 //楼层
 int r=0;
 //循环评论信息
 for(int i=0;i<fs.size();i++){
 	User u=us.get(i);
 	ForumTwo ft=fs.get(i);
 	if(i==0){
 		ri=fs.size()+1;	
 	}
 	ri=--ri;
 %>
 <form action="webForumthreesappadd" method="post" >
 <div class="huifu2">
 <p class="ctit6"><%=ri %>楼</p>
     <dl class="bbs2">
       <!-- 判断用户是否有图片 -->
 	<%
 	if(u.getImg()==null){
 	%>
   
      <dt><img src="SimulationApp/images/logod.png" width="40" /></dt>
     
     <%
 	}else{
     %>
       <dt><img src="<%=request.getContextPath()%><%=u.getImg() %>" width="40" /></dt>
      <%
 	}
      %>
       
         
          
         <dd>  
          
          <a id="showtext<%=ri %><%=ft.getId() %>" onClick="showdiv('contentid<%=ri %><%=ft.getId() %>','showtext<%=ri %><%=ft.getId() %>')"><%=u.getNickname()%></a>
           <p class="date" ><%=ft.getTime()%><a  style="float:right;" class="huiff fr" id="showtext<%=ri %><%=ft.getId() %>" onClick="showdiv('contentid<%=ri %><%=ft.getId() %>','showtext<%=ri %><%=ft.getId() %>')"><img src="<%=request.getContextPath()%>/SimulationApp/images/huifu2.png" width="70"></a></p>
         </dd>
     </dl>
     <p class="hfcont"><%=ft.getReply()%></p>
     
    
    
  
    
      <!-- 回复n楼的评论 -->
      
      
<div class="pingnum pingnum2"  id="contentid<%=ri %><%=ft.getId() %>" style="display: none">
	<!-- 获取要回复的用户id -->
	<input type="text" name="touserid" value="<%=u.getId()%>" style="display: none""/>
	<!-- 获取评论内容的id -->
	<input type="text" name="forumtwoid" value="<%=ft.getId() %>" style="display: none""/>
	<!-- 获取论坛的id -->
	<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none""/>
	
	<!-- 获取回复信息 -->
  <input type="text" id="reply" class="shuru" name="reply" /><input type="submit"  class="btijiao2" value="回复<%=u.getNickname() %>" />
	
</div>    
</form>

  <!显示各个楼层的用户互相回复信息>
<%	
//回复信息不为空进入
	if(fl.get(i)!=null){
		//获取回复信息
		List<ForumThree> fa=(List)fl.get(i);
		//获取 回复信息的用户
		List<User> fua=(List)fu.get(i);
		//获取 被恢复信息的用户
		List<User> futa=(List)fut.get(i);
		//循环播放回复信息
	for(int a=0;a<fa.size();a++){
		//回复内容不是空才进入
		if(fa.get(a)!=null){
			//获取第一个 用户id
			r=fa.get(a).getUserid();
	
%>
     
     
     <form action="webForumthreesappadd" method="post" >
     <div class="hui">
     
    
         <p class="htan"><span class="blue">
         <a id="showtext<%=ri%><%=ft.getId() %><%=fa.get(a).getId() %>" onClick="showdiv('contentid<%=ri%><%=ft.getId() %><%=fa.get(a).getId() %>','showtext<%=ri%><%=ft.getId() %><%=fa.get(a).getId() %>')">
          <%=fua.get(a).getNickname() %>
          </a>
          
          </span>回复 <span><%=futa.get(a).getNickname() %></span> :</p>
         <p class="hcont"> <%=fa.get(a).getReply() %></p>
         <p class="date"><%=fa.get(a).getTime() %></p>
         
         
          <!-- 回复n楼的评论 下面的回复信息 -->
        
        <div class="pingnum pingnum2" id="contentid<%=ri%><%=ft.getId() %><%=fa.get(a).getId() %>" style="display: none">
			<!-- 获取要回复的用户id -->
			<input type="text" name="touserid" value="<%=fua.get(a).getId()%>" style="display: none""/>
			<!-- 获取评论内容的id -->
			<input type="text" name="forumtwoid" value="<%=ft.getId() %>" style="display: none""/>
			<!-- 获取论坛的id -->
			<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none""/>
			<!-- 获取回复信息 -->
  			<input type="text" class="shuru" id="reply" name="reply"  /><input class="btijiao2" type="submit"  value="回复<%=fua.get(a).getNickname() %>" />
		</div>    

			
</form>
         </div>
     </div>
      <%
		}
		}		
		}
 		}
 
     %>
     
     <!--hui-->
 </div>
 <!--huifu2-->
  <p style="height: 300px;"></p>
  <!-- 回复楼主-->
 <form action="webForumtwosaveapp" method="post" >
 	<div class="pingnum pingnum3">	
	<!-- 获取论坛的id -->
	<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none""/>
	
	<!-- 获取回复信息 -->
  <input type="text" id="reply" class="shuru"  name="reply" /><input type="submit" class="btijiao"  value="回复楼主" />

	</div>
   
 </form>
</div>
<!--bbs
<div class="pingnum pingnum2" style="display: none">
    
</div>
-->
<!--pingnum-->
</div>
<!--cont-->
</body>
</html>