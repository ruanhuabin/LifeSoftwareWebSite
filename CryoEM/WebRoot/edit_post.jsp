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
		function selCity()
		{
			var arr = new Array();
			var index = 0;
			<s:iterator value="#request.allL2Category" id="catagory">
			arr[index] = new Array();

			<s:iterator value="#catagory" id="item">
			arr[index].push("<s:property value="#item"/>");
			</s:iterator>
			index++;
			</s:iterator>

			/*拿到省份的索引*/
			var index = document.getElementById("categorySelect").selectedIndex;
			/*拿到省份数组中对应的城市*/
			var citys = arr[index];

			/*拿到子菜单节点*/
			var subselNode = document.getElementById("subCategoryID");
			/*移除子菜单中的城市， 注意：循环时不要“i++”,因为循环条件在递减，若在进行i++，会产生删除不干净的错误
			 */
			for ( var i = 1; i < subselNode.options.length;) {
				subselNode.removeChild(subselNode.options[i]);
			}
			for ( var i = 0; i < citys.length; i++) {
				/*创建节点*/
				var optNode = document.createElement("option");
				/*拿出数组对应的城市*/
				optNode.innerText = citys[i];

				if (optNode.innerText === "<s:property value='#request.postToEdit.subCatagory'/>") {
					optNode.defaultSelected = true;
				}
				/*将城市添加到子菜单中*/
				subselNode.appendChild(optNode);
			}
		}
	</script>
</head>
<body onload="selCity()"> 


<div id="main-container">	
<%-- <div id="header"></div>
<div id="navigation">
	<ul>		
		<li><a href="<%=path%>/index.jsp">Home</font></a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action">Download</a></li>
		<li><a href="<%=path%>/post/Admin_toAdmin.action">Admin</a></li>  
		<li><a href="http://10.10.32.133/thunder/answers/">Forum</a></li>	
	</ul>
</div>
<br/>
<br/> --%>
<s:include value="header_navigation.jsp"></s:include>
<div style="clear:both"></div>

<div align="center" >
<p><font color="red"><s:property value="#request.updatePostResult"/></font></p>
</div>

<div style="width: 100%;">
			<div style="float:left; width: 18%">
				<s:include value="admin_left_menu.jsp"></s:include>
			</div>

			<div style="float:right; width:82%">
						<p><s:include value="button_tool.jsp"></s:include></p>
						<form action="post/Admin_updatePost.action"	enctype="multipart/form-data" method="post" style="" title="" id="" class="">
							  <input type="hidden" name="pid" value="<s:property value="#request.postToEdit.pid"/>" />
							  Title:
							  <%-- <input type="text" name="title" required="true"  value="<s:property value='#request.postToEdit.title'/>"></input> --%>
							  <s:textfield name="title" required="true"  value="%{#request.postToEdit.title}"></s:textfield>
							  <!--  <input type="text" name="title"/>  -->
							  <br/>
							  <br/>
							  Category: 
							  <select id="categorySelect"  name="category" onclick="selCity()">

							<s:iterator value="#request.allL1Category" id="catagory">
							  <s:if test='#catagory.categoryName.equals(#request.postToEdit.catagory)'> 
							 
					            <option selected="selected" value="<s:property value="#catagory.categoryName"/>"><s:property value="#catagory.categoryName"/></option>
					        </s:if>        
					        <s:else>
					           <option value="<s:property value="#catagory.categoryName"/>"><s:property value="#catagory.categoryName"/></option>
					        </s:else> 
							</s:iterator>
							  </select>
							  
							  <br/>
							  <br/>
							  SubCategory: 
							  <select id="subCategoryID" name="subCategory">
					        	<option selected="selected">----Please Select Sub Category----</option>
					       		
					    	  </select>	
					    	  <br/>
					    	  <br/>
					    	  Author: <s:textfield name="author" required="true"  value="%{#request.postToEdit.author}"></s:textfield>
					    	  <br/>
					    	  <br/>
					    	  Author Home Page: <s:textfield name="authorHomePage" value="%{#request.postToEdit.authorHomePageURL}"></s:textfield>
					    	  <br/>
					    	  <br/>
					    	  
					    	  Discussing Page: <s:textfield name="forumPage" value="%{#request.postToEdit.forumURL}"></s:textfield>
					    	  <br/>
					    	  <br/>
					    	  <s:if test='#request.postToEdit.isWelcomePost.equals("1")'>
					    	  	<input type="checkbox" id="markWelcome" name="isMarkedWelcome" value="1" checked/>
    							<label for="markWelcome">Marked as welcome software</label>
					    	  </s:if>        
					          <s:else>
					          	<input type="checkbox" id="markWelcome" name="isMarkedWelcome" value="1"/>
    							<label for="markWelcome">Marked as welcome software</label>
					          </s:else> 
					    	  <br/>
					    	  <br/>
							  
							  <s:file name="yourFile"></s:file>
							  <br />
							  <br />
							  Description:
							  <br />    

							  <s:textarea rows="20" cols="80" required="true" name="description" value="%{#request.postToEdit.description}"> </s:textarea>
							  <br/>			  
							  <input type="submit" value="submit"/>
						</form>
					
				</div>
</div>
		
<div style="clear:both"></div>
<div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> </div>
</div>


</body>
</html>
