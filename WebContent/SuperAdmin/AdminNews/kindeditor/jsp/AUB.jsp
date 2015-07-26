<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>新闻上传(自定义时间)</title>
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
		  
		 //var imgFilemax=picForm.imgFilemax.value;   
		  var title = document.getElementById("title").value;
		//  var summary = document.getElementById("summary").value;
		 // var imgFilemax = document.getElementById("imgFilemax").value;
		 // var img = document.getElementById("imgFile").value;
		//  var type = document.getElementById("type").value;
		  var content1 = document.getElementById("content1").value;

		   if (title.length<5||title.length>40)
	    	{
	    		alert("请填写5-40个汉字以内标题！");
	    	
	    		return false;
	  	}
		 
	    
	   
	
		  
		//  var timet = document.getElementById("timet").value;
		 var imgFile1=picForm.imgFile1.value; 
		 var imgFile2=picForm.imgFile2.value; 
		 var imgFile3=picForm.imgFile3.value; 
		
		 if(imgFile1!=null){
			 var point = imgFile1.lastIndexOf(".");   
			  var type = imgFile1.substr(point); 
			 if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"&&type!=""){  
				  alert("图1 请上传 正确格式的图片   您传的是"+type);
				  return false;
			  } 
		 }
		 if(imgFile2!=null){
			 var point = imgFile1.lastIndexOf(".");   
			  var type = imgFile1.substr(point); 
			 if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"&&type!=""){  
				  alert("图2 请上传 正确格式的图片   您传的是"+type);
				  return false;
			  } 
		 }
		 if(imgFile3!=null){
			 var point = imgFile1.lastIndexOf(".");   
			  var type = imgFile1.substr(point); 
			 if(type!=".jpg"&&type!=".gif"&&type!=".JPG"&&type!=".GIF"&&type!=""){  
				  alert("图3 请上传 正确格式的图片   您传的是"+type);
				  return false;
			  } 
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

	<div style="background: #adc; width: 100%; height: 1000px;">
	<h2>新闻上传(自定义时间)</h2>
		<form action="UPtext" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 100%">
				<p></p>
				<tr>
					<td>文章名字： (20个汉字以内)</td>
					<td>
					<textarea name="title" id="title" 
							cols="100" rows="8"
							style="width: 646px; height: 70px; "></textarea>
					</td>
				</tr>
				<p></p>
				<!-- 
				<tr>
					<td>文章概要:(30个汉字以内)</td>
					<td>
					<textarea name="summary" id="summary"
							cols="100" rows="8"
							style="width: 646px; height: 70px; "></textarea>
					
						</td>
				</tr>
 -->
				<tr>
					<td>文章内容：</td>
					<td colspan="2"><textarea name="content1" id="content1" 
							cols="100" rows="8"
							style="width: 150px; height: 500px; visibility: hidden;"></textarea>

					</td>
				</tr>
				<!--  
				<tr>
					<td colspan="2">文章图片1: <input
						type="file" id="imgFile1" name="imgFile1" />
					</td>

					<td></td>
				</tr>
				<tr>
					<td colspan="2">文章图片2: <input
						id="imgFile2" type="file" name="imgFile2" />
					</td>

					<td></td>
				</tr>
				<tr>
					<td colspan="2">文章图片3: <input
						id="imgFile13" type="file" name="imgFile3" />
					</td>

					<td></td>
				</tr>
				-->
				<p></p>
				<tr>
					<td>文章类别 </td>
					<td><select name="itype" id="type" disable=true onclick="ck()">
							<option value="1">育经</option>
							<option value="2">本地</option>
							<option value="3">升学</option>
							<option value="4">学校</option>
							<option value="5">健康</option>							
					</select></td>
				</tr>
				<p></p>
				
				<tr style="display: none">
					<td></td>
					<td><input type="radio" id="rad"  name="expert"
						value="1" />随机时间<input checked="checked" type="radio" id="rad"  name="expert"
						value="0" />不随机</td>

				</tr>
				<tr>
					<td>文章地区省(例如：省份写为 "山东"," 河北" 直辖市写为 "北京"，"上海"）</td>
					<td><input type="text" id="area" name="area" /></td>
				</tr>

				<tr>
					<td>文章地区市(例如：省份的市写为 "长春"," 青岛" 直辖市的区写为: "海淀区" , "朝阳区"）</td>
					<td><input type="text" id="areas" name="areas"  />
					</td>
				</tr>
				
				<tr>
					<td>文章发布时间：( 例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="text" id="timet" name="timet"
						value="<%=OpeFunction.getNowTime() %>"/></td>

				</tr>
				
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="确认发布" /></td>
				</tr>

			</table>

		</form>
		
		---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3>
	</div>

</body>
</html>
