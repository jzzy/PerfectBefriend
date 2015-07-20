<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发表帖子</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/style.js"></script>
<script type="text/javascript" >
	function check(picForm)
{
	 var imgFile=picForm.file.value;    
	  var point = imgFile.lastIndexOf(".");   
	  var type = imgFile.substr(point); 
	  var title = document.getElementById("title").value;
	  
	  var content = document.getElementById("content").value;
	   if (title==""||title==null||title.length>40)
    	{
    		alert("请填写40个汉字以内标题！");
    	
    		return false;
  	}
	   if (content==""||content==null||title.length>1000)
   	{
   		alert("请填写1000个汉字以内！");
   	
   		return false;
 	}
    if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"&&type!=".PNG"&&type!=".png"&&type!=""){  
		  alert("图 请上传 正确格式的图片   您传的是"+type);
		  return false;
	  } 
	
	  

return true;
}
</script>
</head>
<%
// 获取当前登入的用户
User u = (User) session.getAttribute("u");
if (u == null) {
	// 跳到登入页
	System.out.println("请重新登入!");
	((HttpServletResponse) OpeFunction.response())
			.sendRedirect("/Befriend/SimulationApp/login.html");
	return;
}
%>
<body>
<div class="cont">
<h1 class="tit tit5"><!-- 返回上一页 --><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 发表帖子</span></a></h1>
<div class="publish">
<form action="webForumonesaveapp" method="post" enctype="multipart/form-data" name="picForm">   
    <div><label>标题：</label><input type="text" id="title" name="title"  class="shuru biaoti" /></div>
  
 	类别:
   <select name="model" id="model" >
							<!--  <option value="1">专家答疑</option>-->
							<option value="2">学前</option>
							<option value="3">小学</option>							
							<option value="4">中学</option>
							<option value="5">同城家长</option>	
					</select>
<
    <div><label>内容：</label><textarea name="content" id="content" class="shuru"></textarea></div>
    <div><label>上传图片：<input id="file" type="file" name="file" /></label></div>
    <div class="twobut"><input type="button" value="取消" /><input type="submit" onclick="return check(picForm);" value="确定" /></div>
</form>
</div>
 
<!--publish
<br/><input type="button" name="Submit" onclick="javascript:history.back(-1);" value="返回">

-->
</div>
<!--cont-->
</body>
</html>
