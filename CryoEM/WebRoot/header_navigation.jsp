<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div id="header"></div>
<div id="navigation" align="right">
	<ul>		
		 
		<li><a href="<%=path%>/user/User_listCurrentSelectPost.action">Home</a></li>
		<%-- <li><a href="<%=path%>/user/User_listSoftware.action">Test</a></li> --%>
		
		<li><a href="http://10.10.32.133/thunder/answers/">Q&A Forum</a></li>	
	</ul>
</div>
<div style="clear:both"></div>