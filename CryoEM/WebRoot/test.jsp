<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>/style.css" rel="stylesheet" type="text/css" />
</head>
<body> 


<div id="main-container">	
<div id="header"></div>
<div id="navigation">
	<ul>		
		<li><a href="<%=path%>/index.jsp">Home</font></a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action">Download</a></li>
		<li><a href="<%=path%>/post/Admin_toAdmin.action">Admin</a></li>  
		<li><a href="http://10.10.32.133/thunder/answers/">Forum</a></li>	
	</ul>
</div>
<script type="text/javascript">
		var index_plist = {
			obj : document.getElementById("navigation")
					.getElementsByTagName("li"),
			currObj : null,
			init : function() {
				for (i = 0; i < index_plist.obj.length; i++) {
					eval("index_plist.obj[i].getElementsByTagName('a')[0].onclick=function(){this.className='curr';if(index_plist.currObj){index_plist.currObj.className=''}index_plist.currObj=this}");
				}
			}
		};
		index_plist.init();
</script>
	

<br/>
<br/>

 <h3 align="center">
	 Post Success!
 </h3> 

<s:form action="/post/Test_login.action" enctype="multipart/form-data" method="post">
<s:textfield name="username1" required="true"></s:textfield>
<s:textfield name="username2"></s:textfield>
	<s:textfield name="username" required="true"></s:textfield>
	<s:submit></s:submit>
</s:form>





<div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> </div>
</div>


</body>
</html>
