<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.*"%>
<%@page import="com.befriend.entity.App"%>
<%@page import="com.befriend.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>上传app</title>

</head>
<script type="text/javascript" src="/Befriend/Jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript" language="javascript">

$(document).ready(function(){

	  $("#b01").click(function(){
		 
	  htmlobj=$.ajax({url:"/Befriend/Appset",async:false});
	  $("#myDiv").html(htmlobj.responseText);
	  });
	});
  function ck()
  {
   if(confirm("确定要删除App么？"))
   {
	
    return true;
   }
   else{
	   return false;
   }
  
  }
	function check()
	{
		  var valuea = document.getElementById("name").value;
		  var valuep = document.getElementById("type").value;
    if (valuea==""||valuea==null)
    	{
    		alert("app名！");
    	
    		return false;
  	}
    if (valuep==""||valuep==null)
    	{
    		alert("app概要！");
    	
    		return false;
  	}
return true;

	}
 </script>
<body>

<!--<div id="myDiv"></div>  -->
	<div id="die" style="background: #adc; width: 690px; height: 420px;">

		<form   action="AUP" method="post" enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 690px;">
				<tr>
					<td>app名字：</td>
					<td><input type="text" name="name"  id="name"/></td>
				</tr>
				<tr>
					<td>app概要：</td>
					<td><input type="text" name="summary" id="summary" /></td>
				</tr>
				<tr>
					<td>app logo：</td>
					<td><input type="file" name="imgFile" /></td>
				</tr>
				<tr>
					<td>应用截图1：</td>
					<td><input type="file" name="imgFile1" /></td>
				</tr>
				<tr>
					<td>应用截图2：</td>
					<td><input type="file" name="imgFile2" /></td>
				</tr>
				<tr>
					<td>应用截图3：</td>
					<td><input type="file" name="imgFile3" /></td>
				</tr>
				<tr>
					<td>应用类别：</td>
					<td><input type="text" name="type" /></td>
				</tr>
				
				
				<tr>
					<td>版本号：</td>
					<td><input type="text" name="vnum" /></td>
				</tr>
				
				<tr>
					<td>应用描述:</td>
					
					<td><textarea name="dpt" id="tp"
							
							style="width: 400px; height:100px; "></textarea>

					</td>
				</tr>
				
				
				
			
				<tr>
					<td>上传apk包：</td>
					<td><input type="file" name="appFile" /></td>
				</tr>
				<tr>
					<td>app推荐程度：</td>
					<td><input type="text" name="num" /></td>
				</tr>
				<tr>
					<td>app初始下载数：</td>
					<td><input type="text" name="downloads" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="上传"  onclick="return check();"/>
					 <input type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>

	</div>
	
	<div   style="background: #ada; width: 690px; height: 1000px;">
	<!-- <input id="b01" type="button" value="刷新" /> -->
		<%
	
	List<List> l=(List)session.getAttribute("l");
	
	int i=0;
	
	for(;i<l.size();i++){
		App a= (App) l.get(i);
		
	%>
		<!--
			<br>上传时间    :<%=a.getName() %></br>
			
			<br>图片ID  &nbsp&nbsp:<%=a.getId() %></br>
				
			-->
		<br>App名字 :<%=a.getName()%></br> <a id="b01"
			href="Deleteapp?id=<%= a.getId() %>"  onclick="return ck()"> <img
			height="70" width="100"
			src="<%=request.getContextPath()+a.getPathimg() %>" />
		</a> <br></br>





		<%
		
		
	}	
	
	
	
		%>

		<%
    	int currentPage=0;
    	int n=0;
    	currentPage=Integer.parseInt(session.getAttribute("currentPage").toString());//当前页码
    	n=Integer.parseInt(session.getAttribute("n").toString());//获取 共有多少页
    	
     %>



		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="Appset?currentPage=1">首页</a> <a
			href="Appset?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="Appset?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="Appset?currentPage=<%=n%>">末页</a><br>
			<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
			
	</div>








</body>
</html>