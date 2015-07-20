<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.befriend.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="utf-8" />
<title>本地新闻上传</title>
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

	

	function check(picForm)
	{
		 var imgFile=picForm.imgFile.value;   
		 var imgFilemax=picForm.imgFilemax.value;   
		  var title = document.getElementById("title").value;
		  var summary = document.getElementById("summary").value;
		  var imgFilemax = document.getElementById("imgFilemax").value;
		  var img = document.getElementById("imgFile").value;
		
		  var area = document.getElementById("area").value;
		  var areas = document.getElementById("areas").value;
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
	    if (area==""||area==null)
		{
			alert("请填写地区省级！");
		
			return false;
		}
	    if (areas==""||areas==null)
		{
			alert("请填写地区市级！");
		
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
		<h2>本地新闻上传</h2>
		<form action="UPtext" method="post" name="picForm"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" style="width: 1500px">
				<p></p>
				<tr>
					<td>文章名字： (20个汉字以内)</td>
					<td><input type="text" id="title" name="title" /></td>
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
					<td colspan="2">文章图片首页展示小图"规格为(180x140)(图片大小 0.5 MB内 )": <input
						id="imgFile" type="file" name="imgFile" />
					</td>

					<td></td>
				</tr>
				<p></p>


				<tr>
					<td>文章地区省(例如：省份写为 "山东"," 河北" 直辖市写为 "北京"，"上海"）</td>
					<td><input type="text" id="area" name="area" value="北京" /></td>
				</tr>

				<tr>
					<td>文章地区市(例如：省份的市写为 "长春"," 青岛" 直辖市的区写为: "海淀区" , "朝阳区"）</td>
					<td><input type="text" id="areas" name="areas" value="海淀区" />
					</td>
				</tr>

				<tr>
					<td></td>
					<td><input type="radio" id="rad" name="expert"
						value="1" />是专家<input type="radio" id="rad" checked="checked" name="expert"
						value="0" />不是专家</td>

				</tr>
				<tr>
					<td>文章发布时间：(例如 : 2014-12-09 12:12:32 )</td>
					<td><input type="datetime" id="timet" name="timet"
						value="<%=OpeFunction.getNowTime() %>" /></td>

				</tr>
				<tr>
					<td><br /> <input type="submit" name="button"
						onclick="return check(picForm);" value="OK" />---------<h3><a href="<%=request.getContextPath()%><%=OpeFunction.request().getSession().getAttribute("home") %>" onclick="return cka();">返回主页</a></h3></td>
				</tr>

			</table>

		</form>
	</div>

</body>
</html>
