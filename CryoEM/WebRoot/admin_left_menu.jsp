<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<p><a href="<%=path%>/post/Admin_toAdmin.action">Create New Post</a></p>
<p><a href="<%=path%>/post/Admin_toPostManage.action">Manage Posts</a></p>
<p><a href="<%=path%>/post/Admin_toCategoryManage.action">Manage Categories</a></p>
<p><a href="<%=path%>/post/Admin_toSubCategoryManage.action">Manage Sub Categories</a></p>
