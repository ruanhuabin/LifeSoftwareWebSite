<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	/* #header {height:185px; width:100%;background-image: url(../images/banner.png);background-repeat: no-repeat; background-color:white;} */
	#navigation{list-style:none; text-align:left; font-size:20px; background:white; float:left} 
	#navigation li{width:160px; height:20px; line-height:0px; float:left; margin:2px; list-style:none;} 
	#navigation a{height:100%; display:block; text-decoration:none} 
	#navigation .curr{background:url(sub/images/nav_on.png); color:red;} /* 点击后的样式 */ 
</style>
</head>
<body onload="selCity()">

<div id="main-container">
	
<%-- <div id="header">
</div>

<div id="navigation">
	<ul>
		<li><a href="<%=path%>/index.jsp">Home</a></li> 
		<li><a href="<%=path%>/user/User_listSoftware.action">Download</a></li> 
		<li><a href="<%=path%>/post/Admin_toAdmin.action"> <font color="brown">Admin</font></a></li>
		<li><a href="http://10.10.32.133/qa">Forum</a></li>		
	</ul>
</div> --%>

<s:include value="header_navigation.jsp"></s:include>
<script type="text/javascript">
	
	function selCity()
	{
		var arr= new Array();
		/* <s:iterator value="#request.allL1Category" id="catagory">
			arr.push(["<s:property value="#catagory.categoryName"/>"]);
		</s:iterator> */
		var index = 0;
		<s:iterator value="#request.allL2Category" id="catagory">
			arr[index] = new Array();
			
			<s:iterator value="#catagory" id="item">
				arr[index].push("<s:property value="#item"/>");
			</s:iterator>
			index ++;
			/* arr.push(["<s:property value="#catagory"/>"]); */
		</s:iterator>
        
           /*拿到省份的索引*/
           var index = document.getElementById("categorySelect").selectedIndex;
           /*拿到省份数组中对应的城市*/
           var citys = arr[index]; 
           
           /*拿到子菜单节点*/
           var subselNode = document.getElementById("subCategoryID");
           /*移除子菜单中的城市， 注意：循环时不要“i++”,因为循环条件在递减，若在进行i++，会产生删除不干净的错误
           */
           for(var i=1; i<subselNode.options.length; )
		   {
               subselNode.removeChild(subselNode.options[i]);
           }
           for(var i=0; i<citys.length; i++)
		   {	
               /*创建节点*/
               var optNode = document.createElement("option");
               /*拿出数组对应的城市*/
               optNode.innerText = citys[i];
               /*将城市添加到子菜单中*/
               subselNode.appendChild(optNode);
           }
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


<div style="clear:both"></div>


<br/>
 
<div style="width: 100%;">
	<div style="float:left; width: 18%">
		<s:include value="admin_left_menu.jsp"></s:include>		
	</div>
	
	<div style="float:right; width:82%">
		<!-- <button title="click to generate an URL for file downloading"onclick="genURL()">Generate File URL</button>
		<button  title="click to create a new catagory" onclick="genCategory()">Create New Category</button>
		<button  title="click to create a new sub catagory" onclick="genSubCategory()">Create New Sub Category</button> -->
		<s:include value="button_tool.jsp"></s:include>
		<div class="form-div">
			<form action="post/Admin_postSoftware.action"	enctype="multipart/form-data" method="post" style="" title="" id="" class="">
				  Title:<s:textfield name="title" required="true"></s:textfield>
				  <!--  <input type="text" name="title"/>  -->
				  <br/>
				  <br/>
				  Category: 
				  <select id="categorySelect"  name="category" onclick="selCity()">
				  <s:iterator value="#request.allL1Category" id="catagory">
				  
				  <s:if test="#catagory.categoryName.equals('General')">
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
		    	  <div>
   					 <input type="checkbox" id="markWelcome" name="isMarkedWelcome" value="1"/>
    				<label for="markWelcome">Marked as welcome software</label>
  				</div>
  				 <br/>
  				 <div>
  				 <label>Author: </label><s:textfield name="author" required="true"></s:textfield>
  				 </div>
  				 <br/>
  				 <div>
  				 <label>Author Home Page: </label><s:textfield name="authorHomePage"></s:textfield>
  				 </div>
  				 
  				 <br/>
  				 <div>
  				 <label>Discussing Forum: </label><s:textfield name="forumPage" ></s:textfield>
  				 </div>
  				 
  				 <br/>
  				 <br/>
  				 
				  <!-- <input type="file" name="yourFile" />  -->
				  <s:file name="yourFile" required="true"></s:file>
				  <br />
				  <br />
				  Description:
				  <br />    
				  <!-- <textarea rows="20" cols="80" name="description"></textarea> -->
				  <s:textarea rows="20" cols="80" required="true" name="description"> </s:textarea>
				  <br/>			  
				  <input type="submit" value="submit" onclick="return validate1()"/>
			</form>
		</div>

	</div>
</div> 

<div style="clear:both"></div>

<div id="footer">&copy; 2017 <a href="mailto:ruanhuabin@tsinghua.edu.cn">Huabin Ruan</a> | Design By <a href="#">Huabin Ruan</a> </div>
</div>


</body>
</html>
