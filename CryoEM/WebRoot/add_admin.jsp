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
<%-- <div id="header"></div>
<div id="navigation">
	<ul>		
		<li><a href="<%=path%>/index.jsp">Home</font></a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action">Download</a></li>
		<li><a href="<%=path%>/post/Admin_toAdmin.action">Admin</a></li>  
		<li><a href="http://10.10.32.133/qa">Forum</a></li>	
	</ul>
</div> --%>

<s:include value="header_navigation.jsp"></s:include>
<script type="text/javascript">

		function validate()
        {
            var title = document.getElementsByName("title")[0].value;
            var description = document.getElementsByName("description")[0].value;
                
            if(title == " ")
            {
                alert("Title should not be empty!");
                
                return false;
            }
            
            if(description == " ")
            {
                alert("Description should not be empty!");
                
                return false;
            }
            return true;
        }

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

 
 
<div id="admin-login-form">
	<form action="post/Admin_addAdmin.action"	enctype="multipart/form-data" method="post" style="" title="" id="" class="">
	  
	  <p align="center"><font color="red"><s:property value="#request.adminLoginResult"/></font></p>
	  <p align="center">UserName: <input type="text" name="username"/>  
	  <br />
	  <br />	  
	  Password:&nbsp;&nbsp; <input type="password" name="password"/>
	  <br />	  
	  <br/>			  
	 			  
	  <input type="submit" value="add admin" />
	  </p>
	  
</form>


</div>
<div align="center">
<p><s:property value="#request.addAdminResult"/></p>
</div>
<div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> </div>
</div>


</body>
</html>
