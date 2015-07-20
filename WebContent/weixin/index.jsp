<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="java.util.*"%>
    <%@page import="com.befriend.util.*"%>
<%@page import="com.befriend.entity.News"%>
<%
//获取前8个新闻
List<News> nl=(List)request.getAttribute("nl");

//获取地区
Object Address=request.getAttribute("Address");
//省级
Object province=request.getAttribute("province");
//市级
Object city=request.getAttribute("city");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本地新闻</title>
<link href="<%=request.getContextPath()%>/weixin/css/style.css" rel="stylesheet" type="text/css" />
<script  src="<%=request.getContextPath()%>/weixin/js/TouchSlide.1.1.js"></script>
<script src="<%=request.getContextPath()%>/weixin/js/jweixin-1.0.0.js"></script>    
<script>  
  document.querySelector('#getLocation').onclick = function () {
    wx.getLocation({
      success: function (res) {
        alert(JSON.stringify(res));
      },
      cancel: function (res) {
        alert('用户拒绝授权获取地理位置');
      }
    });
  };
  </script>
</head>

<body>
<div class="cont">
   <div class="top">
       <a href="<%=request.getContextPath()%>/webNewsA10" class="fl"><img src="<%=request.getContextPath()%>/weixin/images/xw_03.png" height="40" /></a>
       <p class="set fr"><a href="<%=request.getContextPath()%>/webNewsA10" title="返回主页"><img src="<%=request.getContextPath()%>/weixin/images/xw_07s.png" height="40" /></a></p>
   </div>
   <!--top-->

   
   <%
   if(province==null||province.equals("省份名")){
   %>
   <h1 class="tit8"><span class="fl">本地新闻</span>
   <span class="fr">湖南 常德<a href="<%=request.getContextPath()%>/weixin/change_city.html" title="切换城市">
   <img src="<%=request.getContextPath()%>/weixin/images/ez-switch-user.png" width="23">
   </a>
   </span>
   </h1>
  
   <%
   }else{
	   
   if(nl.size()==0){
	   %>
	 <h1 class="tit8"><span class="fl">本地新闻</span>
   <span class="fr"><%=province+""+city %><a href="<%=request.getContextPath()%>/weixin/change_city.html" title="切换城市">
   <img src="<%=request.getContextPath()%>/weixin/images/ez-switch-user.png" width="23">
   </a>
   </span>
   </h1>
   <h1>该地区尚未上传新闻!</h1>
	   <%
	   return;
	   }else{
		   
		   %>
		    <h1 class="tit8"><span class="fl">本地新闻</span>
   <span class="fr"><%=province+""+city %><a href="<%=request.getContextPath()%>/weixin/change_city.html" title="切换城市">
   <img src="<%=request.getContextPath()%>/weixin/images/ez-switch-user.png" width="23">
   </a>
   </span>
   </h1>
		   <%
	   }
   }
   %>
   
   
   <%
   if(nl.size()>4){
   %>
 
   <div  id="slideBox"  class="slideBox">
			  <div  class="bd">
					<div  class="tempWrap"  style="overflow:hidden; position:relative;">
                        <ul  style="width: 3360px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition: 200ms; -webkit-transition: 200ms; -webkit-transform: translate(-1680px, 0px) translateZ(0px);">
								<%
								for(int i=0;i<4;i++){
									
							%>
							
							<li  style="display: table-cell; vertical-align: top; width:100%;">
								
								<a  class="pic" href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><img  src="<%=request.getContextPath()+nl.get(i).getImgmax()%>"></a>															
								
							</li>
                           <%
                           } 
                           %>                         
                       </ul>
                    </div>
				</div>
                <!--bd-->
				<div  class="hd">
					<ul><li  class="">1</li><li  class="">2</li><li  class="on">3</li><li  class="">4</li></ul>
				</div>
   </div>
   
   <%
   }
   %>
   <!--slideBox-->
    <%
    		int i=0;
    		if(nl.size()!=8){
    			i=0;
    			}else{
    				i=4;
    			}
				for(;i<nl.size();i++){
			%>
				<dl class="list">
    				<dt><a href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><img src="<%=request.getContextPath()+nl.get(i).getImg() %>"/></a></dt>
     			    <dd>
     			    <p class="subtit"> <a  class="pic" href="<%=request.getContextPath()%>/webNewsId?id=<%=nl.get(i).getId()%>"><%=nl.get(i).getTitle() %></a></p>
       				
        		      <p><%=nl.get(i).getSummary() %></p>
     		       </dd>
  				 </dl>	
							
           <%
			}
            %>  
   <!--list-->  
   <div class="more4">
     <dl>
      <dt><a href="<%=request.getContextPath()%>/webWeiXinHotarea"><img src="<%=request.getContextPath()%>/weixin/images/xia.png" width="23"></a></dt>
      <dd><a href="<%=request.getContextPath()%>/webWeiXinHotarea">查看更多</a></dd>
      <a  href="#getLocation">地理位置接口</a>
    </dl>
  </div>
  <!--more4-->
</div>
<!--cont-->

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