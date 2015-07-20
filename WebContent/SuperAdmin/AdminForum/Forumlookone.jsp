<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
     <%@page import="com.befriend.util.*"%>
     <%@page import="com.befriend.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>这是您查看的论坛</title>
<script type="text/javascript">
function cka()
{
 if(confirm("是否删除?"))
 {
	
  return true;
  
 }
 else{
	   return false;
 }

}
</script>
</head>
<body>
<% 
//论坛信息
ForumOne f=(ForumOne)request.getAttribute("fone");
//论坛楼主信息
User uu=(User)request.getAttribute("u");
String type="";

if(f==null){
	return;
}
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
<div style="background:#bdb">
<a  href="<%=request.getContextPath()%><%=session.getAttribute("home")%>"><h3>返回首页</h3> </a>
</div>
<div style="background:#adc">
<form action="" method="post" enctype="multipart/form-data">
<table>
<tr>
<%
//判断楼主头像
if(uu.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=uu.getImg() %>" width="100" />
</td>
<td><td>楼主昵称:<%=uu.getNickname()%></td></td>
<%
}else{
%>
<td>
<img src="<%=request.getContextPath()%>/SimulationApp/images/logod.png" width="100" />
</td>
<td><td>楼主昵称:<%=uu.getNickname()%></td></td>
<%
}
%>
</tr>

<tr>
<td>论坛标题 :<%=f.getTitle()%></td>
</tr>
<tr>
<td>论坛类别 :<%=type %></td>
</tr>
<tr>
<td>论坛内容:<%=f.getContent()%></td>

</tr>
<tr>
<td>创建时间:<%=f.getTime()%></td>

</tr>
<tr>
<td>地区:<%=f.getArea()+f.getAreas()%></td>

</tr>
<tr>
<td>回帖数:<%=f.getTotal()%></td>

</tr>
<tr>


<%
//论坛图片
if(f.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=f.getImg() %>" width="300" />
</td>
<%
}
%>
</tr>
</table>
</form >
</div>







<!--显示论坛的各个楼层的评论 循环在此开始 -->
<%
List<ForumTwo> fs=(List)request.getAttribute("ftwos");//论坛评论信息
List<User> us=(List)request.getAttribute("us");//论坛评论的用的用户信息
List<User> fu=(List)request.getAttribute("fu");// 论坛用户之间 回复的 用户信息
List<User> fut=(List)request.getAttribute("fut");// 论坛用户之间 被回复 用户信息
List fl=(List)request.getAttribute("fl");//论坛用户之间的回复信息
//设置楼层
int ri=1;
//遍历评论信息
for(int i=0;i<fs.size();i++){
	//评论者
	User u=us.get(i);
	//评论内容
	ForumTwo ft=fs.get(i);
	//判断是否为空 
	if(u==null||ft==null){
		continue;
	}
	
	//ri自己先自己在+1
	ri=++ri;
	
%>


<div style="background:#bdb">


<form action="">
<table>
<tr>

<!-- 显示楼层数 -->

<td> <%=ri%> 楼</td>

</tr>
<tr>
<%
//判断评论者是否有头像
if(u.getImg()!=null){
%>
<td>
<img src="<%=request.getContextPath()%><%=u.getImg() %>" width="100" />
</td>
<%
}else{
%>
<img src="<%=request.getContextPath()%>/SimulationApp/images/logod.png" width="100" />
<%
}
%>
</tr>
<tr>


<td>昵称:<%=u.getUsername()%></td>

</tr>
<tr>
<td>评论内容 :<%=ft.getReply()%></td>
</tr>
<tr>
<td>回复时间 :<%=ft.getTime()%></td>

</tr>
<tr>
<td><a onclick="return cka();" href="webAdminRemoveForumTwo?id=<%=ft.getId()%>"><input type="button" value="删除<%=ri %>楼评论"></a></td>
</tr>
</table>
</form>



<!-- 显示各个楼层的用户互相回复信息-->
<%	
	//获取回复信息
	if(fl.get(i)!=null){
		//回复内容
		List<ForumThree> fa=(List)fl.get(i);
		//主动回复者
		List<User> fua=(List)fu.get(i);
		//被回复者
		List<User> futa=(List)fut.get(i);
		//遍历回复信息
	for(int a=0;a<fa.size();a++){
		//判断3者是否有空
		if(fa.get(a)!=null&&fua.get(a)!=null&&futa.get(a)!=null){	
				
%>

<!-- 用户回复用户 -->

<form action="Forumthreesave" style="background:#bac">
<table>
<tr>
<td><%=fua.get(a).getUsername() %> 回复 <%=futa.get(a).getUsername() %> : <%=fa.get(a).getReply() %></td>

</tr>
<tr>
<td>回复时间：<%=fa.get(a).getTime() %></td>
</tr>
<!-- 删除回复内容 -->
<tr>
<td><a onclick="return cka();" href="webAdminRemoveForumThree?id=<%=fa.get(a).getId()%>"><input type="button" value="删除回复"></a></td>
</tr>
</table>
</form>


<% 
					}	//判断3者是否有空    if结束
					
			}//遍历回复信息 for结束
			
		}//获取回复信息 if结束
		
}//遍历评论信息   for结束
%>
</div>
</div>

</body>
</html>