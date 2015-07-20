<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>家长天地 APP更新</title>
<script type="text/javascript">
	function check()
	{
		  var valuea = document.getElementById("tv").value;
		  var valuep = document.getElementById("tp").value;
    if (valuea==""||valuea==null)
    	{
    		alert("请填版本号！");
    	
    		return false;
  	}
    if (valuep==""||valuep==null)
    	{
    		alert("请填更新内容！");
    	
    		return false;
  	}
return true;

	}
	function chk() {
		 var valuea = document.getElementById("av").value;
		  var valuep = document.getElementById("us").value;
		  if (valuea==""||valuea==null)
	    	{
	    		alert("请填版本号！");
	    	
	    		return false;
	  	}
		  if (valuep.length==""||valuep==null)
	    	{
	    		alert("请填更新内容！");
	    	
	    		return false;
	  	}
	return true;
		
	}

</script>
</head>
<body>
<div style="background: #adc; width: 690px; height: 320px;">

		<form action="JztdApp" method="post" enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 350px;">
				<tr>
					<td>app版本号 :</td>
					<td><input type="text" name="downloads" id="tv" style="width: 40"/></td>
				</tr>
				<tr>
					<td>app更新内容:</td>
					
					<td><textarea name="type" id="tp"
							
							style="width: 400px; height:200px; "></textarea>

					</td>
				</tr>
				
				<tr>
					<td colspan="2">上传appapk.....................................</td>
				</tr>

				<tr>
					<td>apk包 名字 必为："FamilyGroup.apk"</td>
					<td><input type="file" name="appFile" /></td>
				</tr>
			
				<tr>
					<td colspan="2"><input type="submit" value="确认上传"  onclick="return check();"/> <input
						type="reset" value="确认重置" /></td>
				</tr>
			</table>
		</form>
		</div >
		<div style="background: #abd; width: 900px; height: 300px;">
		<h2>修改错误信息</h2>
		<form action="JztdAppm" method="post" enctype="multipart/form-data">
		<tr>
		<td>家长之友的版本号:</td>
		<td><input type="text" name="apptv" id="av"/></td>
		<tr>
		<td>家长之友更新内容:</td>
		
		<td><textarea name="updates" id="us"  required
							
							style="width: 400px; height:200px; "></textarea>

					</td>
		</tr>
		<tr>
		
		<td><input type="submit" value="确认修改" onclick="return chk();"/></td>
		</tr>
		</form>


	<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
	</div>
</body>
</html>