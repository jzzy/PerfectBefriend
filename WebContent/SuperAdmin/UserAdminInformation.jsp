<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>家长天地后台论坛管理登入</title>

<script type="text/javascript">
	function check()
	{
		  var valuea = document.getElementById("username").value;
		  var valuep = document.getElementById("password").value;
    if (valuea==""||valuea==null)
    	{
    		alert("请填写用户名！");
    	
    		return false;
  	}
    if (valuep==""||valuep==null)
    	{
    		alert("请填写密码！");
    	
    		return false;
  	}
return true;

	}

</script>

</head>

<body>

	<div style="background: #adc; width: 690px; height: 110px;">

		<form action="RegionalAdministrator" method="post" enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 700px;">
				<tr>
					<td colspan="2">账户：<input type="text" id="username" name="username" />
					</td>



				</tr>
				<tr>



					<td colspan="2">密码：<input type="password" id="password" name="password" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" name="button" value="登入"
						onclick="return check();" /> <input type="reset" value="重置">
						<a href="">联系管理员</a></td>
				</tr>
				<tr>
					<td>根据简单评测！推荐使用  “Google浏览器” 和  “火狐浏览器 ”  ！</td>
					
				</tr>
				<tr>
					<td>IE会出现,不能正常返回错误信息!所以导致,不能正确识别错误信息!</td>
				</tr>
			</table>

		</form>

	</div>
</body>
</html>
