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

<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" >

$(document).ready(function(){

  $("#b01").click(function(){
	  
  htmlobj=$.ajax({url:"/Befriend/getUsermhh?username="+$("#tet").val(),async:false});
  $("#myDiv").html(htmlobj.responseText);
  });
});

 function ck()
  {
   if(confirm("确定赋予权限？"))
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

请输入用户名为:<input type="text"id="tet" name="tet" />

<button id="b01" type="button"  >搜索</button>
<div id="myDiv"></div>
<div style="background: #adc">
<form action="">
<h3>没有填写省市地区的 用户不会出现在这里 也搜索不到！</h3>
<table>
<tr>
<td width="300"><h4>------用户账号</h4></td>
<td width="300"><h4>用户地址</h4></td>

<td width="180"><h4>给予当前市级管理权限</h4></td>
<td width="180"><h4>注册时间</h4></td>
</tr>
</table>
</form>
<%
List<User> us=(List)request.getAttribute("us");
for(int i=0;i<us.size();i++){
%>
<form action="SetQU"  method="post" enctype="multipart/form-data">
<table>
<tr>
<td><input type="text" id="id" name="id" value="<%=us.get(i).getId()%>"  style="display: none;"/></td>
<td width="300">用户名是:<a href="getUserine?username=<%=us.get(i).getUsername() %>"><%=us.get(i).getUsername() %></a></td>
<td width="300"><%=us.get(i).getAddress() %><%=us.get(i).getAddcity()%></td>

<td width="180"><a href="SetQU?competence2=1&id=<%=us.get(i).getId()%>" onclick="return ck();"> 授权市级级管理员</a></td>
<td width="">注册时间是:<%=us.get(i).getTime() %></td>
</tr>
</table>
</form>

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
			href="getUsera?currentPage=1">首页</a> <a
			href="getUsera?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="getUsera?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="getUsera?currentPage=<%=n%>">末页</a>
			
		
			<br></br>
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<a href="getUsera?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
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