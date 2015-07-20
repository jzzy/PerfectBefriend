<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
User u=(User)session.getAttribute("u");
if(u==null){
	//跳转到登入页
	((HttpServletResponse) OpeFunction.response())
	.sendRedirect("/Befriend/SimulationApp/login.html");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改信息</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/style.js"></script>
<script type="text/javascript" src="js/jquery.cityselect.js"></script> 
<script type="text/javascript" src="js/city.min.js"></script> 
<script type="text/javascript" >
	function check(creator)
{
	 var imgFile=creator.file.value;    
	  var point = imgFile.lastIndexOf(".");   
	  var type = imgFile.substr(point); 
	  	  	  
    if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"&&type!=".PNG"&&type!=".png"&&type!=""){  
		  alert("图 请上传 正确格式的图片   您传的是"+type);
		  return false;
	  } 
	
	  

return true;
}
</script>
</head>

<body style="background:#f9f9f9"  bgcolor=#FFFFFF alink=#333333 vlink=#333333 link=#333333 topmargin=0 leftmargin=0 onload="init()">

<form action="webModification" method="post" name="creator" enctype="multipart/form-data">
<script language="javascript" src="js/cityselect.js"></script>
<div class="cont">
<h1 class="tit tit5"><a style="float:left;" href="javascript:history.back(-1);"><span>&lt; 我的资料</span></a>
</h1>
<div class="recont">
 
    </div>
   
    <div class="pnum2"> 上传头像：<input id="file" type="file" name="file" /></div>
    <div class="pnum2">用户名:<%=u.getUsername() %></div>
    <div class="pnum2">昵称:<input type="text" name="nickname" value="<%=u.getNickname() %>" class="shuru"/></div>
      <div class="pnum2">所在地区:<%=u.getAddress()+u.getAddcity() %></div>  
     <div class="pnum2 pnum2s">修改：<select name="province"  onChange = "select()">
     
     </select> 
     <select name="city"   onChange = "select()">
    
     </select>
     </div>
     
     <div class="pnum2" >
   	  孩子阶段:
    <select class="shuru" name="stage" id="type" disable=true >
   							 <option value="<%=u.getStage()%>"><%=u.getStage()%></option>
							<option value="未入园">未入园</option>
							<option value="幼儿园">幼儿园</option>
							<option value="一年级">一年级</option>
							<option value="二年级">二年级</option>
							<option value="三年级">三年级</option>
							<option value="四年级">四年级</option>
							<option value="五年级">五年级</option>
							<option value="六年级">六年级</option>
							<option value="初一">初一</option>
							<option value="初二">初二</option>
							<option value="初三">初三</option>
							<option value="高一">高一</option>
							<option value="高二">高二</option>
							<option value="高三">高三</option>
							<option value="大学以上">大学以上</option>
					</select>   
					</div>
					<%
					if(u.getCome()==null){
						if(u.getPhone()==null||u.getPhone().equals("")){
							u.setPhone("未设置");
						}
					%>
					
	<div class="pnum2">手机号:<%=u.getPhone() %></div>
	<%
						}
					
	%>
    <div class="pnum2">所在学校:<input type="text" name="school" value="<%=u.getSchool() %>"  class="shuru"/></div>
    <div class="next"><input type="submit" onclick="return check(creator);" value="确定" /></div>

</div>
<!--recont-->
</div>
<!--cont-->
</form>
 
</body>
</html>