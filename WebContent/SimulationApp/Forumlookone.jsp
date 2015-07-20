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
<body>
<% 

ForumOne f=(ForumOne)request.getAttribute("fone");//论坛信息
User uu=(User)request.getAttribute("u");//论坛楼主信息
int ri=1;
if(f==null){
	return;
}
%>
<div style="background:#adc">
<form action="" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>楼主用户名:<%=uu.getUsername()%></td>
</tr>
<tr>
<td>论坛标题 :<%=f.getTitle()%></td>
</tr>
<tr>
<td>论坛类别 :<%=f.getType()%></td>
</tr>
<tr>
<td>论坛内容:<%=f.getContent()%></td>

</tr>
<tr>
<td>创建时间:<%=f.getTime()%></td>

</tr>
<tr>
<td>回帖数:<%=f.getTotal()%></td>

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
int r=0;
for(int i=0;i<fs.size();i++){
	User u=us.get(i);
	ForumTwo ft=fs.get(i);
	if(i==0){
		ri=1;	
	}
	ri=++ri;
	
%>


<div style="background:#bdb">


<form action="">
<table>
<tr>
<td> <%=ri%> 楼</td>

</tr>

<tr>


<td>用户名:<%=u.getUsername()%></td>

</tr>
<tr>
<td>评论内容 :<%=ft.getReply()%></td>
</tr>
<tr>
<td>回复时间 :<%=ft.getTime()%></td>

</tr>
</table>
</form>



<!显示各个楼层的用户互相回复信息>
<%	

	if(fl.get(i)!=null){
		List<ForumThree> fa=(List)fl.get(i);
		List<User> fua=(List)fu.get(i);
		List<User> futa=(List)fut.get(i);
	for(int a=0;a<fa.size();a++){
		
		if(fa.get(a)!=null){
			//获取第一个 用户id
			r=fa.get(a).getUserid();
	
%>


<form action="Forumthreesave" style="background:#bac">
<table>
<tr>
<td><%=fua.get(a).getUsername() %> 回复 <%=futa.get(a).getUsername() %> : <%=fa.get(a).getReply() %></td>

</tr>
<tr>
<td>回复时间：<%=fa.get(a).getTime() %></td>
</tr>
</table>
</form>


<% 
//获取最后一个 用户id
//r=fa.get(a).getTouserid();
		}
		
	}
	}
%>
<%

}
%>
</div>
</div>
</body>
</html>