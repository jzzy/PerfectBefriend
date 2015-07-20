<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>八大类新闻上传</title>
<link rel="stylesheet" href="../themes/default/default.css" />
<link rel="stylesheet" href="../plugins/code/prettify.css" />
<script charset="utf-8" src="../kindeditor.js"></script>
<script charset="utf-8" src="../lang/zh_CN.js"></script>
<script charset="utf-8" src="../plugins/code/prettify.js"></script>



<script type="text/javascript">

function cka(){
	 if(confirm("确定放弃当前操作返回主页？"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
}


	function ck(){
		
		 var type = document.getElementById("type").value;
		
		 if(type!="升学指南"){
			 document.getElementById("types").value="";
			 document.getElementById("types").style.display="none"; 
			 
		 }else{
			 
			 document.getElementById("types").style.display="block"; 
		 }
		 
	}
	
	
	
	function check(picForm)
	{
		 var imgFile=picForm.imgFile.value;   
		 var imgFilemax=picForm.imgFilemax.value;   
		  var title = document.getElementById("title").value;
		  var summary = document.getElementById("summary").value;
		  var imgFilemax = document.getElementById("imgFilemax").value;
		  var img = document.getElementById("imgFile").value;
		  var type = document.getElementById("type").value;
		  var types = document.getElementById("types").value;
		  
	
		  
		  var timet = document.getElementById("timet").value;
		  var point = imgFile.lastIndexOf(".");   
		  var type = imgFile.substr(point); 
		 var p= imgFilemax.lastIndexOf("."); 
		 var typemax = imgFilemax.substr(p); 
		 
		
		 

		
		   if (title==""||title==null||title.length>20)
	    	{
	    		alert("请填写20个汉字以内标题！");
	    	
	    		return false;
	  	}
	    if (summary==""||summary==null||summary.length>30)
	    	{
	    		alert("请正确填写概要30字以内！");
	    	
	    		return false;
	  	}
	    if (timet==""||timet==null)
		{
			alert("请输入时间！");
		
			return false;
		} 
	    if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"){  
			  alert("小图 请上传 正确格式的图片   您传的是"+type);
			  return false;
		  } 
		  if(typemax!=".jpg"&&typemax!=".gif"&&typemax!=".JPG"&&typemax!=".GIF"){  
			  alert("大图 请上传 正确格式的图片   您传的是"+typemax);
			  return false;
		  } 
		  
	
return true;
	}
	
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>

	<div style="background: #adc; width: 1500px; height: 1000px;">
	<h2>八大类新闻上传</h2>
		<form action="UPtext" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				<p></p>
				<tr>
					<td>文章名字： (20个汉字以内)</td>
					<td><input type="text" id="a" name="A" style="display: none"
						value="2" /> <input type="text" id="title" name="title" /></td>
				</tr>
				<p></p>
				<tr>
					<td>文章概要:(30个汉字以内)</td>
					<td><input type="text" id="summary" name="summary"
						style="width: 300px; height: 70px" /></td>
				</tr>

				<tr>
					<td>文章内容：</td>
					<td colspan="2"><textarea name="content1" id="content1"
							cols="100" rows="8"
							style="width: 150px; height: 500px; visibility: hidden;"></textarea>

					</td>
				</tr>
				<tr>
					<td colspan="2">文章图片首页展示大图"规格为(720x360)(图片大小 1 MB内 )":<input
						type="file" id="imgFilemax" name="imgFilemax" />
					</td>

					<td></td>
				</tr>
				<tr>
					<td colspan="2">文章图片首页展示小图"规格为(180x140)(图片大小 0.5MB内 )": <input
						id="imgFile" type="file" name="imgFile" />
					</td>

					<td></td>
				</tr>
				<p></p>
				<tr>
					<td>文章类别 八大类</td>
					<td><select name="type" id="type" disable=true onclick="ck()">
							<option value="教子经验">教子经验</option>
							<option value="升学指南">升学指南</option>
							<option value="成长路上">成长路上</option>
							<option value="出国留学">出国留学</option>
							<option value="兴趣特长">兴趣特长</option>
							<option value="名人教子">名人教子</option>
							<option value="健康导航">健康导航</option>
							<option value="轻松驿站">轻松驿站</option>
							<option value="社会广角">社会广角</option>
					</select></td>
				</tr>
				<p></p>
				<tr>
					<td>文章类别 四小类(只有升学指南 有这个4小类)</td>
					<td><select name="types" id="types" disable=true
						style="display: none">
							<option value="幼升小">幼升小</option>
							<option value="小升初">小升初</option>
							<option value="中考">中考</option>
							<option value="高考">高考</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="radio" id="rad" name="expert"
						value="1" />是专家<input type="radio" id="rad" checked="checked" name="expert"
						value="0" />不是专家</td>

				</tr>

				<tr>
					<td>文章发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="timet" name="timet"
						value="<%=OpeFunction.getNowTime() %>"/></td>

				</tr>
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="OK" /></td>
				</tr>

			</table>

		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
