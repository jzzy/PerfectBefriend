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

<% 

ForumOneType fot=(ForumOneType)request.getAttribute("fot");
if(fot==null){
	return;
}
List<ForumTwoType> ftl=fot.getfTT();
if(fot!=null){
	
%>
<form action="forumupdateOneType" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>论坛模块名字 :  </td>
<td><input type="text" value="<%=fot.getTitle() %>" name="title"></td>
</tr>
<tr>
<td>创建时间 :<%=fot.getTime()%></td>
</tr>
<tr>
<td>创建者 :<%=fot.getAdminname()%></td>
</tr>
<tr>
<td>
<input type="text" name="id" value="<%=fot.getId()%>" style="display: none">
<input type="submit" onclick="return cka();" value="确定修改"></td>
</tr>


</table>
</form>
<%
}
%>
<form action="forumsaveTwoType" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>新增论坛小组名字 :  </td>
<td><input type="text"  name="title"></td>
</tr>
<tr>
<td>论坛板块Mini图片 :  </td>
<td><input type="file" name="file" ></td>
</tr>
<tr>
<td>论坛板块Max图片 :  </td>
<td><input type="file" name="fileMax" ></td>
</tr>
<tr>
<td>
<input type="text" name="id" value="<%=fot.getId()%>" style="display: none">
<input type="submit" onclick="return cka();" value="确定添加小组"></td>
</tr>


</table>
</form>
<%
for(int i=0;i<ftl.size();i++){
%>
<form action="forumupdateTwoType" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>论坛小组名字 :  </td>
<td><input type="text" value="<%=ftl.get(i).getTitle() %>" name="title"></td>
</tr>
<tr>
<td>创建时间 :<%=ftl.get(i).getTime()%></td>
</tr>
<tr>
<td>创建者 :<%=ftl.get(i).getAdminname()%></td>
<input name=id type="text" value="<%=ftl.get(i).getId()%>" style="display: none">
</tr>
<tr>
<td><input type="submit" onclick="return cka();" value="确定修改"></td>
</tr>


</table>
</form>
<form action="forumremoveTwoType" method="post" enctype="multipart/form-data">
<table>
<tr>
<input name=id type="text" value="<%=ftl.get(i).getId()%>" style="display: none">
<td><input type="submit" onclick="return cka();" value="删除小组"></td>
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