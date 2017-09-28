<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String hostPort = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Simple Times</title>
<link href="<%=basePath%>/style.css" rel="stylesheet" type="text/css" />

<style type="text/css"> 
	#header {height:185px; width:100%;background-image: url(../images/banner.png);background-repeat: no-repeat; background-color:white;}
	#navigation{list-style:none; text-align:left; font-size:20px; background:white; float:left} 
	#navigation li{width:160px; height:20px; line-height:0px; float:left; margin:2px; list-style:none;} 
	#navigation a{height:100%; display:block; text-decoration:none} 
	#navigation .curr{background:url(sub/images/nav_on.png); color:red;} /* 点击后的样式 */ 
	
	
</style>
</head>
<body>
 
<div id="main-container">	
<%-- <div id="header">
</div>
<div id="navigation">
	<ul>
		<li><a href="<%=path%>/index.jsp">Home</a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action"><font color="brown">Download</font></a></li>
		<li><a href="<%=path%>/post/Admin_toAdmin.action">Admin</a></li>  
		<li><a href="http://10.10.32.133/qa">Forum</a></li>		
	</ul>
</div> --%>

<s:include value="header_navigation.jsp"></s:include>
<div style="clear:both"></div>
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

<!-- <div id="content-div" > -->
<div style="width: 100%;">
	<div style="float:left; width: 25%">		
		<ul >
		<li style="list-style:none;"><h2>Software List</h2></li> 
		</ul>
		<%-- 
		<ul style="margin-left:30px;">
   		<s:iterator value="#request.all_post" id="allPost">
   		<li style=" text-indent:5px;">
   			<a href="#<s:property value="#allPost.pid"/>">
   				<font size="3px">
   					<s:property value="#allPost.title"/>
   				</font>
   			</a>
   		</li>   		
   		</s:iterator>
   		</ul> --%>
   		 
   		
 
   	
   	<ul style="margin-left:30px;">
	   	<s:iterator value="#request.catagories" id="catagories">	
	   		<s:if test="#catagories.subCategories.size() != 0">
		   		<li >
		   			<%-- <a href="#<s:property value="#catagories.cid"/>"> --%>
		   			<a href="<%=path%>/user/User_listCurrentSelectPost.action?pid=<s:property value="#catagories.cid"/>">
		   				<s:property value="#catagories.title"/>
		   			</a>
		   		</li>
	   		</s:if>	
	   		
	   		
	   		<s:iterator value="#catagories.subCategories" id="sc">
	   		
	   		<ul>
	   			<li >
	   				<%-- <a href="#<s:property value="#sc.sid"/>"> --%>
	   				<a href="<%=path%>/user/User_listCurrentSelectPost.action?pid=<s:property value="#sc.sid"/>">
	   					<s:property value="#sc.title"/>
	   				</a>	
	   			</li>
	   			
	   			
	   			<s:iterator value="#sc.subSubCategories" id="ssc">
	   			<ul>
	   				<li>
	   					<%-- <a href="#<s:property value="#ssc.ssid"/>"> --%>
	   					<a href="<%=path%>/user/User_listCurrentSelectPost.action?pid=<s:property value="#ssc.ssid"/>">
	   						<s:property value="#ssc.title"/>
	   					</a>
	   				</li>
	   			</ul>
	   			</s:iterator>
	   		</ul>	
	   		</s:iterator>
	   		
	   	</s:iterator>
   	</ul>
   		
   		
   		
	</div>
   <div style="float:right; width:74%">
			<h2><a id=<s:property value="#request.current_select_post.pid"/>></a><s:property value="#request.current_select_post.title"/> </h2>
			<p>Author: <s:property value="#request.current_select_post.author"/> </p>
			<p>Author Home: <a href="http://<s:property value="%{#request.current_select_post.authorHomePageURL}"/>" target="_blank"> <s:property value="#request.current_select_post.authorHomePageURL"/> </a></p>
			
			<p>
				<s:text name="%{#request.current_select_post.description}"/>
			</p>
			 <p><font size="5px">Download it,  please click &nbsp;<a href="<%=hostPort%><s:property value="#request.current_select_post.softwareURI"/>">Here.</a></font></p>
   </div>
</div>
<div style="clear:both"></div>
	




<s:include value="footer.jsp"></s:include>

<!-- <div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> 
</div> -->



</div>


</body>
</html>
