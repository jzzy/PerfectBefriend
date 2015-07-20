<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看论坛</title>
</head>
<script type="text/javascript" >
function cka(){
	 if(confirm("确定删除？删除后不可恢复!"))
	 	{
		
		  return true;
	 	}
	 		else{
		   return false;
	 		}
}
</script>
<body>

<% 
List<List> fones=(List)request.getAttribute("fones");
List<List> us=(List)request.getAttribute("us");
for(int i=0;i<fones.size();i++){
	User u=(User)us.get(i);
	ForumOne f=(ForumOne) fones.get(i);
	int lc=i+1;
%>
<%
if(u!=null&&f!=null){
	String type=""; //论坛类型
	switch (f.getType()) {
	case 1:
		type="专家答疑";
		break;

	case 2:
		type="学前";
		break;
	case 3:
		type="小学";
		break;
	case 4:
		type="中学";
		break;
	case 5:
		type="本地论坛";
		break;
	default:
		type="未知";
		break;			
	}
%>
<div style="background:#adc">
<h3>第<%=lc %>个</h3>

<form action="ForumLook" method="post" enctype="multipart/form-data">
<table>

<tr>
<td>论坛创建者 :<%=u.getNickname()%></td>
</tr>
<tr>
<td>论坛标题 :<%=f.getTitle()%></td>

</tr>
<tr>
<td>论坛类别 :<%=type%></td>
</tr>
<tr>
<td>论坛地区 :<%=f.getArea()+f.getAreas()%></td>
</tr>
<tr>
<td>论坛内容:<%=f.getContent()%></td>

</tr>
<tr>
<td>创建时间:<%=f.getTime()%></td>

</tr>
<tr>
<td>回帖数:<%=f.getTotal()%></td>

</tr>
<tr>
<td>论坛被收藏次数 :<%=f.getFollectnum()%></td>
</tr>
<tr>
<td>论坛被点击次数 :<%=f.getfHits()%></td>
</tr>
<tr>
<input name="id" type="text" value="<%=f.getId()%>"style="display: none"/>
<td><input type="submit" value="1 点击查看论坛"/> <a href="webForumRemove?forumid=<%=f.getId()%>&model=<%=f.getType() %>" onclick="return cka();"><input type="button" value="2 点击删除论坛"/></a></td>
<!--  <td><a href="ForumLook?id=<%=f.getId()%>" >4 点击禁止当前论坛创建者发帖</a></td>-->
</tr>
</table>
</form>
<br>
<%
}
%>

<%
	
}
%>
<%
    	int currentPage=0;
    	int n=0;
    	currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());//当前页码
    	n=Integer.parseInt(request.getAttribute("cpe").toString());//获取 共有多少页
    	
     %>



		<br>共<%=n%>页</br> <br>第<%=currentPage%>页</br> <a
			href="ForumLookall?currentPage=1">首页</a> <a
			href="ForumLookall?currentPage=<%=currentPage-1%>">上一页</a> <a
			href="ForumLookall?currentPage=<%=currentPage+1%>">下一页</a> <a
			href="ForumLookall?currentPage=<%=n%>">末页</a>
			
		
			<br></br>
			<%
			for(int s=1;s<=n;s++){
			    
			%>
			<a href="ForumLookall?currentPage=<%=s%>"><%=s%>&nbsp&nbsp</a> 
			<%
			if(s%20==0){
			    %>
			    <br>
			    <% 
			}
			}
			%>
<a href=<%=request.getContextPath()%><%=session.getAttribute("home")%>>返回首页</a>
</div>
</body>
</html>