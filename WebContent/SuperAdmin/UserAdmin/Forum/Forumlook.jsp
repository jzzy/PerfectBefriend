<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看论坛</title>
</head>
<script type="text/javascript" >
function cka(){
	 if(confirm("确定删除？删除后不可恢复!"))
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
//获取论坛信息
List<List> fones=(List)request.getAttribute("fones");
//获取用户信息
User u=(User)session.getAttribute("useradmin");
//获取 页
int cpe=(Integer)request.getAttribute("cpe");
//获取当前
int currentPage=(Integer)request.getAttribute("currentPage");
if(u==null){
	return;
}
%>
当前那用户是:<%=u.getUsername()%>
<br>

<%
String type=null;
if(u.getCompetence()==1){
	//市级
	type=u.getAddcity();
}
if(u.getCompetence()==2){
	//省级
	type=u.getAddress();
}
if(u.getCompetence()==4){
	//专家答疑
	type="专家答疑";
}
%>
这是 <%=type %> 所有的论坛

<%

for(int i=0;i<fones.size();i++){
	ForumOne f=(ForumOne) fones.get(i);
	int lc=i+1;
//	String type=""; //论坛类型
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
<div style="background:#adc">
<h3>第<%=lc %>个</h3>
<form action="ForumLookiduser" method="post" enctype="multipart/form-data">
<table>
<%
if(f.getFrs()>=1){
	
%>
<tr>
<td style="color: blue;">已解决</td>

</tr>
<%
}else{
%>
<tr>
<td style="color: red;" >未解决</td>

</tr>
<%

}%>
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
<td>回帖数:<%=f.getFrs()%></td>

</tr>
<tr>
<td>点击数:<%=f.getfHits()%></td>

</tr>

<tr>
<input name="id" type="text" value="<%=f.getId()%>"style="display: none"/>
<td><input type="submit" value="点击查看论坛"/> <a href="webUserForumRemove?forumid=<%=f.getId()%>" onclick="return cka();"><input type="button" value="2 点击删除论坛"/></a></td>

</tr>
</table>
</form>
<br>

<%
}
%>
	<br>共<%=cpe%>页</br> <br>第<%=currentPage%>页</br> <a
			href="ForumLookuser?currentPage=1">首页</a> <a
			href="ForumLookuser?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="ForumLookuser?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="ForumLookuser?currentPage=<%=cpe%>">末页</a>
			
		
			<br></br>
			<%
			for(int s=1;s<=cpe;s++){
			    
			%>
			<a href="ForumLookuser?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
			<%
			if(s%20==0){
			    %>
			    <br>
			    <% 
			}
			}
			%>
<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
</div>
</body>
</html>