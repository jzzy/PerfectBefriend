<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>后台管理员登入</title>
</head>

<body>

<div style="background:#adc;width:690px; height:110px;">

            <form action="SuperAdminLogin" method="post"   enctype="multipart/form-data">  
                 账户或者密码错误！
                <table cellpadding="0" cellspacing="0" style="width: 700px;">  
                <tr>
               
           
             
                        <td colspan="2" >  
                           账户：<input type="text" name="Admin"/> 
                        </td>  
                      
                         
                     
                    </tr>  
                    <tr>
                     
                    
                    
                           <td colspan="2">
                               密码：<input type="password" name="Pwd"/> 
                           </td>
                    </tr>  
            			<tr>
                        <td  >                
		<input type="submit" name="button" value="登入" /> 
		<a href="">联系管理员</a>
                        </td>  
                    </tr>  
                </table>  
                
            </form>  

</div>                                                                                                                                                                                                                                                                                                                                                                                                                                         
</body>
</html>
</html>