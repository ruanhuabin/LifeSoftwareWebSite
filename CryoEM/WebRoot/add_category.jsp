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

<style type="text/css">
		
</style>
</head>
<body>
<div class="form-div">
 <form action="<%=basePath%>post/Admin_addCategory.action" enctype="multipart/form-data" method="post">    
      
      Category Name: <input type="text" name="category" /> 
      <input type="submit" id="id_submit" name="nm_submit" value="submit" />    
  </form>
  
  <p><s:property value="#request.addCategoryResult"/></p>
  <br />
  
  
</div> 

</body>
</html>
