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
	 if(confirm("确定？"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
}
</script>
<body>


<div style="background:#adc">

<form action="forumsaveOneType" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>论坛模块名字 :  </td>
<td><input type="text" name="title"></td>
</tr>
<tr>
<td><input type="submit" onclick="return cka();" value="确定"></td>
</tr>
</table>
</form>

<% 

List<ForumOneType> fotl=(List)request.getAttribute("fotl");
for(int i=0;i<fotl.size();i++){
%>
<form action="forumlookOneTwoType" method="post" enctype="multipart/form-data">
<table>

<tr>
<td>论坛模块创建者 :<%=fotl.get(i).getAdminname()%></td>
</tr>
<tr>
<td>论坛模块名字 :<%=fotl.get(i).getTitle()%></td>

</tr>
<tr>
<td>创建时间 :<%=fotl.get(i).getTime()%></td>
</tr>

<tr>
<input name="id" type="text" value="<%=fotl.get(i).getId()%>"style="display: none"/>
<td><input type="submit" value="点击查看小组"/> </td>

</tr>
</table>
</form>
<form action="forumremoveOneType" method="post" enctype="multipart/form-data">
<table>
<tr>
<input name=id type="text" value="<%=fotl.get(i).getId()%>" style="display: none">
<td><input type="submit" onclick="return cka();" value="删除模块"></td>
</tr>


</table>
</form>
<%
}
%>


<br>



<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%> onclick="return cka();">返回首页</a>
</div>
</body>
</html>