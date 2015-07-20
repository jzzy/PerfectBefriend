<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.*"%>
<%
//获取app信息
List<App> la=(List)request.getAttribute("la");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>精彩内容</title>
<link href="<%=request.getContextPath()%>/SimulationApp/css/style.css" rel="stylesheet" type="text/css" />
<script  src="<%=request.getContextPath()%>SimulationApp/js/TouchSlide.1.1.js"></script>
</head>

<body style="background-color:#35b67a">
<div class="cont">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="<%=request.getContextPath()%>/SimulationApp/images/xw_03.png" height="40" /></a>
      <p class="set fr"><a href="<%=request.getContextPath()%>/SimulationApp/about.html"></a><a href="<%=request.getContextPath()%>/SimulationApp/about.html"><img src="SimulationApp/images/xw_07.png" height="40" /></a></p>
         </div>
   <!--top-->
   <ul class="nav">
      <li><a href="<%=request.getContextPath()%>/webNewsA10">今日必读</a></li>
      <li><a href="<%=request.getContextPath()%>/SimulationApp/exchange2.jsp">社区互动</a></li>
      <li class="current"><a href="<%=request.getContextPath()%>/webGetapp">精彩内容</a></li>
      <li><a href="<%=request.getContextPath()%>/SimulationApp/personal_information.jsp">我的信息</a></li>
   </ul>
   <!--nav <a href="/Befriend/JzFoIosAndAndroid"></a>-->
<div   id="slideBox" class="slideBox slideBox2">
<div class="jing">
<img src="<%=request.getContextPath()%>/SimulationApp/images/imma_02.png" width="100%" />
<img src="<%=request.getContextPath()%>/SimulationApp/images/imma_03.png" width="100%" />
<img src="<%=request.getContextPath()%>/SimulationApp/images/imma_05.png" width="100%" />
<div><img src="<%=request.getContextPath()%>/SimulationApp/images/imma_06.png" width="100%" /><p>进入微信添加《家长之友》
公众微信号“jztdzk”，或
者扫描下方二维码，然后点
击“关注”即可。</p></div>
<img src="<%=request.getContextPath()%>/SimulationApp/images/imma_07.png" width="100%" />
</div>
			 
             
   </div>
   <!--slideBox-->
   <!--  
   <div class="applist">
       <ul>
       
         <li>
         
              <a href="http://www.chaimiyouxi.com/wap/cmyy.jsp">
                <p><img src="SimulationApp/images/3guanyume.png" /></p>
                <div class="xinxi">
                  <p class="atit">柴米游言</p>
                  <p class="adown">下载2569次</p>
                </div>
              </a>
          </li>
       <%
       if(la==null){
    	return;	
       }
       for(int i=0;i<la.size();i++){
       %>
          <li>
         
              <a href="<%=request.getContextPath()%><%=la.get(i).getPathapk()%>">
                <p><img src="<%=request.getContextPath()%><%=la.get(i).getPathimg() %>" /></p>
                <div class="xinxi">
                  <p class="atit"><%=la.get(i).getName() %></p>
                  <p class="adown">下载<%=la.get(i).getDownloads() %>次</p>
                </div>
              </a>
          </li>
         <%
       }
         %>
       </ul>
   </div>
   -->
  <!--applist-->
  
</div>
<!--cont-->
 <!-- 
<div class="dmyGD"><table width="100%" height="36" border="0" align="center" cellpadding="0" cellspacing="0" id="Table_01">
<tr>
<td width="100%" height="36" valign="bottom"><a href="#">更多应用下载</a></td>
</tr>
</table>
</div>
-->
   <script  type="text/javascript">
				TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"leftLoop", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放
				});
			</script>
</body>
</html>