<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:include value="header.jsp"/>
<%-- <s:debug></s:debug> --%>
<div class="form-div">
<form action="<%=basePath%>post/Admin_addSubCategory.action" enctype="multipart/form-data" method="post">    
      Parent Category: 
      <select name="parentCategory" >
      	<s:iterator value="#request.parentCategories" id="categories">
      		<option><s:property value="#categories.categoryName"/></option>
      	</s:iterator>
      </select>
      <br/>
      Sub Category Name: <s:textfield type="text" required="true" name="subCategory" /> 
      <br/>
      <input type="submit" value="submit"></input>    
</form>
  
  <p><s:property value="#request.addSubCategoryResult"/></p>
  <br />
  
  
</div> 

</body>
</html>
