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
<script type="text/javascript">
function viewPost()
{
	alert("value:" + arguments[0]);
	var mypid = arguments[0];
 	window.open("<%=basePath%>post/Admin_viewPost.action?pid=mypid.text", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=600");
}

</script>

</head>
<body> 
<%-- <s:debug></s:debug> --%>

<div id="main-container">	
<%-- <div id="header"></div>
<div id="navigation">
	<ul>		
		<li><a href="<%=path%>/index.jsp">Home</font></a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action">Download</a></li>
		<li><a href="<%=path%>/post/Admin_toAdmin.action">Admin</a></li>  
		<li><a href="http://10.10.32.133/thunder/answers/">Forum</a></li>	
	</ul>
</div> --%>
<s:include value="header_navigation.jsp"></s:include>
<div style="clear:both"></div>

<div style="width: 100%;">
	<div style="float:left; width: 18%">
		<s:include value="admin_left_menu.jsp"></s:include>		
	</div>
	
	<div style="float:right; width:82%">
	
	<p><s:include value="button_tool.jsp"></s:include></p>
	
	<table border="1">
	<thead>
	<tr>
	<td>Title</td>
	<td>Description</td>
	<td>Attachment</td>
	<td>Author</td>	
	<td>Edit</td>
	<td>Delete</td>
	</tr>
	</thead>
		<s:iterator value="#request.postPageData.list" id="pageData"> 
			<tr>
				<td><s:property value="#pageData.title"/></td>
				<td><input type="text" style="border: none;background: transparent;" disabled="disabled" size="48" maxlength="48" value='<s:property value="#pageData.description"/>'> </input></td>
				
				
				<td><s:property value="#pageData.softwareFileName"/></td>
				<td><s:property value="#pageData.author"/></td>
				<td><a href="<%=basePath%>post/Admin_editPost.action?pid=<s:property value="#pageData.pid"/>" target="_blank" >Edit</a></td>
				<td><a href='<%=basePath%>post/Admin_deletePost.action?pid=<s:property value="#pageData.pid"/>&pageNum=<s:property value="#request.postPageData.currentPage"/>'>Delete</a></td>
			</tr>
		</s:iterator>
	</table>
	</div>
</div>


<div style="clear:both"></div>

<%-- <s:include value="npj.jsp">	
	<s:param name="currPageData">
	totalPage=<s:property value="#request.postPageData.totalPage"/>, 
	allRows=<s:property value="#request.postPageData.allRows"/>,
	currentPage=<s:property value="#request.postPageData.currentPage"/>,
	action=Admin_getPageData.action
	</s:param>
</s:include>
 --%>

<!--要想这个set生效，必须在产生这个页面action中有postPageData这个属性，并且有这个属性的get方法  -->
<s:set var="currPageData" value="postPageData" scope="request"/>
<s:include value="npj_new.jsp">
	<s:param name="actionURL">Admin_getPostPreNextPageData.action</s:param>
</s:include>



<s:include value="footer.jsp"></s:include>

</div>


</body>
</html>
