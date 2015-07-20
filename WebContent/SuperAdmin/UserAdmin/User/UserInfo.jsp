<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看用户注册信息</title>
<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" >

$(document).ready(function(){

  $("#b01").click(function(){
	  
  htmlobj=$.ajax({url:"/Befriend/GetUsertimeares?addcity="+$("#cars").val()+"&timeq="+$("#timeq").val()+"&timeh="+$("#timeh").val()+"",async:false});
  $("#myDiv").html(htmlobj.responseText);
  });
});

 
  
  
 </script>
</head>
<body>


<div style="background:#adc">
<%

//获取当前登入用户
User u=(User)session.getAttribute("useradmin");
if(u==null){
	%>
	请重新登入!
	<% 
	return;
}
%>
当前用户是:<%=u.getUsername() %>
<br>
<%



//获取市级信息
Registrationsa rsa=(Registrationsa)session.getAttribute("rg");
//获取省级信息
List<Registrationsa> lg=(List<Registrationsa>)session.getAttribute("lg");
String area="";
int c=u.getCompetence();
if(c==1){
	area=u.getAddress()+u.getAddcity();
}
if(c==2){
	area=u.getAddress();
}

%>
这是 <%=area %>的用户信息<br>
<%
if(u.getCompetence()==2&&lg!=null){
	%>
	<H3><a href="<%=request.getContextPath()%>/SuperAdmin/UserAdmin/Admin/forumhome.jsp">进入管理</a><br></H3>
	<%
	}
%>
<h3>按时间搜索用户注册数量</h3>
请输入开始日期:<input type="text"id="timeq" value="<%=OpeFunction.getNowTime() %>" name="timeq"  />请输入截止日期: <input type="text"id="timeh"  value="<%=OpeFunction.getNowTime() %>" name="timeh" />



<%
if(u.getCompetence()==2&&lg!=null){
	%>
	<h3>请选择地区</h3>
	
	<form> 
<select id="cars" name="cars"> 
<option value="nu">请选择地区</option> 
	<% 
for(int i=0;i<lg.size();i++){
	

%>

<option value="<%=lg.get(i).getAddcity() %>"><%=lg.get(i).getAddcity() %></option> 

<%
} 
%>
</select> 
</form> 
<% 
}
%>

<button id="b01" type="button">搜索</button>
<div id="myDiv"></div>

<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200">地区</td>
<td width="150">今天注册人数</td>

<td width="150">7天内注册人数</td>

<td width="150">30天内注册人数</td>

<td width="150">365天内注册人数</td>
<td width="150">全部注册人数</td>
<td width="300">更新时间</td>
</tr>
</table>
</form>
<%

if(u.getCompetence()==1&&rsa!=null){
%>
<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200"><%=rsa.getAddress()+rsa.getAddcity() %></td>
<td width="150"><%=rsa.getNote() %></td>
<td width="150"><%=rsa.getNote7() %></td>
<td width="150"><%=rsa.getNote30() %></td>
<td width="150"><%=rsa.getNote365()%></td>
<td width="150"><%=rsa.getNoteall()%></td>
<td width="300"><%=rsa.getTime() %></td>
</tr>
</table>
</form>
<%
}//if结束
//省级的显示
else if(u.getCompetence()==2&&lg!=null)
{
%>




<%

for(int i=0;i<lg.size();i++){
	

%>
<form action=""  method="post" enctype="multipart/form-data">
<table>
<tr>
<td width="200"><%=lg.get(i).getAddress()+lg.get(i).getAddcity() %></td>
<td width="150"><%=lg.get(i).getNote() %></td>
<td width="150"><%=lg.get(i).getNote7() %></td>
<td width="150"><%=lg.get(i).getNote30() %></td>
<td width="150"><%=lg.get(i).getNote365()%></td>
<td width="150"><%=lg.get(i).getNoteall()%></td>
<td width="300"><%=lg.get(i).getTime() %></td>
</tr>
<table>
</form>

<%

}//for结束

}//if结束
//销毁session

%>


</div>
</body>
</html>