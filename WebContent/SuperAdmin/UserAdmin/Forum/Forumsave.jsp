<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加论坛</title>
</head>
<body>
<div style="background:#adc">
<form action="Forumonesave" method="post" enctype="multipart/form-data">
<table>
<tr>
<td>论坛标题<input type="text" name="title"> </td>
</tr>
<tr>
<td> 	类别:
   <select name="model" id="model" >
							<option value="1">专家答疑</option>
							<option value="2">学前</option>
							<option value="3">小学</option>							
							<option value="4">中学</option>
							
					</select></td>
<tr>
<td>论坛内容<input type="text" name="content"> </td>

</tr>
<tr>
<td>论坛地区省<input type="text" name="area"> </td>

</tr>
<tr>
<td>论坛地区市<input type="text" name="areas"> </td>

</tr>
<tr>

<td><input type="submit" value="提交"></td>
</tr>

</table>
</form>

<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
</div>
</body>
</html>