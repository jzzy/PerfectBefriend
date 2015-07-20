<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>这是您查看的论坛</title>
</head>
<script type="text/javascript">
function showdiv(targetid,btn){
	   
    var target=document.getElementById(targetid);
    var bt=document.getElementById(btn);

          if (target.style.display=="block"){
              target.style.display="none";
              bt.value="修改";


          } else {
              target.style.display="block";
              bt.value="放弃修改";
          }
 
}


function cka()
{
 if(confirm("是否删除?"))
 {
	
  return true;
  
 }
 else{
	   return false;
 }

}

</script>
<body>
<% 
//当前登入用户信息
User useradmin=(User)session.getAttribute("useradmin");
//论坛信息
ForumOne f=(ForumOne)request.getAttribute("fone");
//论坛楼主信息
User u=(User)request.getAttribute("u");

if(f==null){
	return;
}
String type="";
switch (f.getType()) {
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
default:
	type="未知";
	break;			
}
%>
<div style="background:#bdb">
<a  href="<%=request.getContextPath()%><%=session.getAttribute("home")%>"><h3>返回首页</h3> </a>
</div>
<div style="background:#adc">
<form action="webForumtwosaveapp" method="post" enctype="multipart/form-data">
<table>
<tr>
<%
if(u.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=u.getImg() %>" width="100" />
</td>
<td><td>楼主昵称:<%=u.getNickname()%></td></td>
<%
}else{
%>
<td>
<img src="<%=request.getContextPath()%>/SimulationApp/images/logod.png" width="100" />
</td>
<td><td>楼主昵称:<%=u.getNickname()%></td></td>
<%
}
%>
</tr>

<tr>
<td>论坛标题 :<%=f.getTitle()%></td>
</tr>
<tr>
<td>论坛类别 :<%=type%></td>
</tr>
<tr>
<td>论坛内容:<%=f.getContent()%></td>

</tr>
<tr>
<td>创建时间:<%=f.getTime()%></td>

</tr>
<tr>
<td>地区:<%=f.getArea()+f.getAreas()%></td>

</tr>
<tr>
<td>回帖数:<%=f.getFrs()%></td>

</tr>
<tr>


<%
if(f.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=f.getImg() %>" width="300" />
</td>
<%
}
%>
</tr>
</table>
</form >
</div>
<!显示论坛的各个楼层的评论>
<%
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<User> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<User> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
List fl=(List)request.getAttribute("fl");//论坛用户之间的回复信息
int ri=1;//楼层信息
for(int i=0;i<fs.size();i++){
	User uy=us.get(i);
	ForumTwo ft=fs.get(i);	
	ri=++ri;
	
%>


<div style="background:#bdb">


<form action="" method="post">
<table>
<tr>
<td> <%=ri%> 楼</td>

</tr>
<tr>
<%
if(uy.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=uy.getImg() %>" width="100" />
</td>
<%
}else{
%>
<img src="<%=request.getContextPath()%>/SimulationApp/images/logod.png" width="100" />
<%
}
%>
</tr>
<tr>


<td>昵称:<%=uy.getNickname()%></td>

</tr>


<tr>
<td width="">评论内容 :<%=ft.getReply()%></td>
</tr>
<%
//只有本人可以删除修改
if(useradmin.getId().equals(uy.getId())){
%>
<tr>
<td ><input type="button" id="b<%=ri%><%=ft.getId()%>" onclick="showdiv('<%=ri%><%=ft.getId()%>','b<%=ri%><%=ft.getId()%>')" value="修改"/></td>
<td ><a onclick="return cka();" href="<%=request.getContextPath() %>/webProfessorRemoveForumTwo?id=<%=ft.getId()%>"><input type="button" value="删除"></a></td>
</tr>
<%
}
%>






<tr>
<td>回复时间 :<%=ft.getTime()%></td>

</tr>
</table>
</form>









<!-- 修改回复 -->
<form action="webProfessorUpdateForumTwo" method="post" name="<%=ri%><%=ft.getId()%>">

<div id="<%=ri%><%=ft.getId()%>" style="display: none;">
<input type="" name="id" value="<%=ft.getId()%>" style="display: none;"/>
<textarea rows="" cols="" value="<%=ft.getId()%>" style="display: none;"></textarea>
修改内容 :<textarea rows="8" cols="50" name="reply" ><%=ft.getReply()%></textarea>

<input type="submit"  onclick="return check('<%=ri%><%=ft.getId()%>')" value="确定修改" />

</div>
</form>













<!显示各个楼层的用户互相回复信息>
<%	

	if(fl.get(i)!=null){
		List<ForumThree> fa=(List)fl.get(i);
		List<User> fua=(List)fu.get(i);
		List<User> futa=(List)fut.get(i);
	for(int a=0;a<fa.size();a++){
		
		if(fa.get(a)!=null&&fua.get(a)!=null&&futa.get(a)!=null){		
	
%>


<form action="" style="background:#bac">
<table>
<tr>
<td><%=fua.get(a).getNickname() %> 回复 <%=futa.get(a).getNickname() %> : <%=fa.get(a).getReply() %></td>

</tr>
<tr>
<td><a href="webProfessorRemoveForumThree?id=<%=fa.get(a).getId()%>"><input type="button" value="删除回复"></a></td>
</tr>

<tr>
<td>回复时间：<%=fa.get(a).getTime() %></td>
</tr>
</table>
</form>


<% 
		}
		
	}
	}
%>



<%

}
%>
 <form action="webForumtwosaveProfessor" method="post" style="background:#adc" > 
	回复楼主！
	<!-- 获取论坛的id -->
	<input type="text" name="forumid" value="<%=f.getId() %>" style="display: none""/>
	
	<!-- 获取回复信息 -->
  
  <textarea rows="8" cols="50" name="reply" ></textarea>
  <input type="submit" class="btijiao"  value="回复楼主" />

</form>

</div>

</div>
</body>
</html>