<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加论坛</title>
<script type="text/javascript" >
	function check(picForm)
{
	 var imgFile=picForm.file.value;    
	  var point = imgFile.lastIndexOf(".");   
	  var type = imgFile.substr(point); 
	  var title = document.getElementById("title").value;
	  
	  var content = document.getElementById("content").value;	
	  var reply = document.getElementById("reply").value;
	  if (reply==""||reply==null||reply.length>17)
  	{
  		alert("请填用户名！");
  	
  		return false;
	}
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
<body>
<div style="background:#adc">
<form action="Forumonesave" name="picForm" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>添加用户账号<input id="reply" type="text" name="reply"> </td>
</tr>
<tr>
<td>论坛标题<input id="title" type="text" name="title"> </td>
</tr>
<tr>
<td>论坛内容<input id="content" type="text" name="content"> </td>

</tr>
<tr>
<td> 	类别:
   <select name="model" id="model" >
							<option value="1">专家答疑</option>
							<option value="2">学前</option>
							<option value="3">小学</option>							
							<option value="4">中学</option>
							<option value="5">本地论坛</option>
							
					</select></td>
</tr>

<tr>
<td>选择图片<input type="file" name="file"> </td>

</tr>
<tr>

<td><input type="submit" onclick="return check(picForm);" value="提交"></td>
</tr>

</table>
</form>

<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
</div>
</body>
</html>