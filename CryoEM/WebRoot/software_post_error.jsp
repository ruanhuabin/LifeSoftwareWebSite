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
	#navigation{list-style:none; text-align:left; font-size:20px; background:white; float:left} 
	#navigation li{width:160px; height:20px; line-height:0px; float:left; margin:2px; list-style:none;} 
	#navigation a{height:100%; display:block; text-decoration:none} 
	#navigation .curr{background:url(sub/images/nav_on.png); color:red;} /* 点击后的样式 */ 
</style>
</head>
<body>
 


<div id="main_container_x">
	
<%-- <div id="header">
</div>
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
	

<!-- 
<div id="navigation2"><a href="#">Home</a> <a href="#">Clock</a> <a href="software_publish.jsp">Time</a> <a href="#">Simple</a> <a href="#">Links</a> <a href="#">Contact</a> </div>
 --> 

<br/>
<br/>
 <h3 align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 Post Failed!
 </h3> 
<!-- <h2>Lorem Ipsum </h2> -->




<!-- <p>Before you dive deep into lorem ipsum, please note that if you have any issues with this design (be it coding issues, an unresolved hatred, whatever) open up the <a href="#">readme.txt</a> file included with this design to find out how to contact me. </p> -->
<!-- <p>This design is open source, so feel free to edit it as you wish, but you must keep &quot;design by jeremy d&quot; at the bottom. </p> -->
<!-- <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed id felis sit amet dolor sollicitudin gravida. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce in quam. Pellentesque vel eros eu sapien auctor vehicula. Ut bibendum urna. Sed scelerisque augue eu tellus. Cras nibh mauris, porttitor a, nonummy tempus, hendrerit eu, leo. In ut urna feugiat nisl fermentum dictum. Aliquam quis lacus vel enim elementum lacinia. Sed nisi erat, fermentum at, dictum quis, ullamcorper at, dui. Phasellus ante mi, auctor sed, molestie eu, rutrum eu, augue. Phasellus at orci. Maecenas ut velit. Donec posuere odio nec sem. Praesent leo neque, varius sit amet, vulputate id, lobortis sed, leo. Mauris luctus. Aliquam elementum, tellus et lobortis rhoncus, sem ipsum blandit pede, quis vulputate leo erat eu purus. Praesent sapien. Ut auctor, nibh non dapibus eleifend, justo ligula dignissim ligula, sed elementum purus magna quis elit.</p> -->
<!-- <h2>Lorem Ipsum Strikes Back!</h2> -->
<!-- <p>Lorem ipsum dolor sit amet, etiam eu magna. Aliquam vulputate tempor magna. Duis lobortis ultricies dolor. Sed eros. Curabitur porta facilisis est. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque sem neque, bibendum ut, pulvinar vitae, cursus eget, elit. Nam consequat. Sed posuere lectus tempus massa. Cras iaculis nonummy augue. Integer laoreet turpis at velit. Vivamus vehicula ligula et elit vulputate ullamcorper. Donec lacus.</p> -->
<!-- <p>Before you dive deep into lorem ipsum, please note that if you have any issues with this design (be it coding issues, an unresolved hatred, whatever) open up the <a href="#">readme.txt</a> file included with this design to find out how to contact me. </p> -->
<!-- <p>This design is open source, so feel free to edit it as you wish, but you must keep &quot;design by jeremy d&quot; at the bottom. </p> -->
<!-- <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed id felis sit amet dolor sollicitudin gravida. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce in quam. Pellentesque vel eros eu sapien auctor vehicula. Ut bibendum urna. Sed scelerisque augue eu tellus. Cras nibh mauris, porttitor a, nonummy tempus, hendrerit eu, leo. In ut urna feugiat nisl fermentum dictum. Aliquam quis lacus vel enim elementum lacinia. Sed nisi erat, fermentum at, dictum quis, ullamcorper at, dui. Phasellus ante mi, auctor sed, molestie eu, rutrum eu, augue. Phasellus at orci. Maecenas ut velit. Donec posuere odio nec sem. Praesent leo neque, varius sit amet, vulputate id, lobortis sed, leo. Mauris luctus. Aliquam elementum, tellus et lobortis rhoncus, sem ipsum blandit pede, quis vulputate leo erat eu purus. Praesent sapien. Ut auctor, nibh non dapibus eleifend, justo ligula dignissim ligula, sed elementum purus magna quis elit.</p> -->


<div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> </div>
</div>


</body>
</html>
