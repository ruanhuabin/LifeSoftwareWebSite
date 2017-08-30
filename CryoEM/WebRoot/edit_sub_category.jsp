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
 <form action="<%=basePath%>post/Admin_updateSubCategory.action" enctype="multipart/form-data" method="post">   
 	
 	Parent Category Name:
 	  <select name="parentCategoryName">
 	  	<s:iterator value="#request.parentCategories" id="category">
 	  		<s:if test='#category.categoryName.equals(#request.parentCategoryName)'> 			 
	            <option selected="selected" value="<s:property value="#category.categoryName"/>"><s:property value="#category.categoryName"/></option>
	        </s:if>        
	        <s:else>
	           <option value="<s:property value="#category.categoryName"/>"><s:property value="#category.categoryName"/></option>
	        </s:else>
 	  	</s:iterator>
 	  </select>
  
      <%-- Parent Category Name:<br/>
       <s:textfield required="true"  value="%{#request.parentCategoryName}" name="parentCategoryName"/> --%>
       <br/>
      Enter New Category Name:<br/> 
      <s:textfield required="true"  value="%{#request.categoryName}" name="categoryName"/> 
      <input type="hidden" name="scid" value="<s:property value="#request.scid"/>" />
      <input type="hidden" name="pageNum" value="<s:property value="#request.pageNum"/>" />
      <input type="submit" id="id_submit" name="nm_submit" value="Modify" />    
  </form>
  
  <p><s:property value="#request.addCategoryResult"/></p>
  
</div> 

</body>
</html>
