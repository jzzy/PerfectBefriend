<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看用户</title>
<script type="text/javascript" language="javascript">
 function ck()
  {
   if(confirm("确定取消已赋予权限？"))
   {
	
    return true;
   }
   else{
	   return false;
   }
  
  }
 </script>
</head>
<body>
<div style="background: #adc">
<form action="">
<table>
<tr>
<td width="300"><h4>------用户账号</h4></td>
<td width="300"><h4>用户地址</h4></td>
<td width="300"><h4>管理权限</h4></td>
<td width="180"><h4>撤销管理员权限</h4></td>
</tr>
</table>
</form>
<%
List<User> us=(List)request.getAttribute("usa");

for(int i=0;i<us.size();i++){
	
		
%>
<% if(us.get(i).getCompetence()==1){%>

<form action="SetQU"  method="post" enctype="multipart/form-data">
<table>
<tr>
<td><input type="text" id="id" name="id" value="<%=us.get(i).getId()%>"  style="display: none;"/></td>
<td width="300">用户名是:<a href="getUserine?username=<%=us.get(i).getUsername() %>"><%=us.get(i).getUsername() %></a></td>
<td width="300"><%=us.get(i).getAddress() %><%=us.get(i).getAddcity() %></td>
<td width="300">市级管理员</td>
<td><input type="text" name="competence2" value="3" style="display: none;"/></td>
<td width="180"><a href="SetQU?competence2=3&id=<%=us.get(i).getId()%>" onclick="return ck();"> 取消授权市级管理员</a></td>

</tr>
</table>
</form>

<%} %>
<% if(us.get(i).getCompetence()==2){%>

<form action="SetQU"  method="post" enctype="multipart/form-data">
<table>
<tr>
<td><input type="text" id="id" name="id" value="<%=us.get(i).getId()%>"  style="display: none;"/></td>
<td width="300">用户名是:<a href="getUserine?username=<%=us.get(i).getUsername() %>"><%=us.get(i).getUsername() %></a></td>
<td width="300"><%=us.get(i).getAddress() %><%=us.get(i).getAddcity() %></td>
<td width="300">省级管理员</td>
<td><input type="text" name="competence2" value="3" style="display: none;"/></td>
<td width="180"><a href="SetQU?competence2=3&id=<%=us.get(i).getId()%>" onclick="return ck();"> 取消授权省级管理员</a></td>

</tr>
</table>
</form>
<%} %>
<%
	
	
	}

%>


<%
    	int currentPage=0;
    	int n=0;
    	currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());//当前页码
    	n=Integer.parseInt(request.getAttribute("competence2").toString());//获取 共有多少页
    	
     %>

 <h3><a href=<%=request.getContextPath()%>/SuperAdmin/UserAdmin/User/UserInfo.jsp>返回首页</a></h3>

		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="getUadmin?currentPage=1">首页</a> <a
			href="getUadmin?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="getUadmin?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="getUadmin?currentPage=<%=n%>">末页</a>
			
		
			<br></br>
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<a href="getUadmin?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
			<%
			if(s%50==0){
			    %>
			    <br>
			    <% 
			}
			}
			%>

 <br>



</div>
</body>
</html>