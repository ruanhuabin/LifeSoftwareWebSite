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
	function editCategory()
	{
   		 window.open('<%=basePath%>post/Admin_editCategory.action?cid=<s:property value="#category.cid"/>&pageNum=<s:property value="#request.categoryPageData.currentPage"/>&categoryName=<s:property value="#category.categoryName"/>', "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=600");
	}
		
	function newPopup(url) {
		popupWindow = window.open(url, 'popUpWindow','height=300,width=400,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');
	}
</script>	
</head>
<body> 
<%-- <s:debug></s:debug> --%>

<div id="main-container">	

<s:include value="header_navigation.jsp"></s:include>

		<div style="width: 100%;">
			<div style="float:left; width: 18%">
				<s:include value="admin_left_menu.jsp"></s:include>
			</div>

			<div style="float:right; width:82%">

				<table border="1">
					<thead>
						<tr>
							<td>CategoryName</td>
							<td>Edit</td>
							<td>Delete</td>
							<!-- <td>Edit</td> -->
							
						</tr>
					</thead>

					<s:iterator value="#request.allL1Categories" id="category">

						<tr>
							<td><input type="text" style="border:none; background: transparent;" size="64" id='<s:property value="#category.categoryName"/>' name="categoryName" value='<s:property value="#category.categoryName"/>'> </input>	</td>

							<td><a
								href='<%=basePath%>post/Admin_editCategory.action?cid=<s:property value="#category.cid"/>&pageNum=<s:property value="#request.categoryPageData.currentPage"/>&categoryName=<s:property value="#category.categoryName"/>'
								target="_blank">Edit</a>
							</td>
							<td><a
								href='<%=basePath%>post/Admin_deleteCategory.action?cid=<s:property value="#category.cid"/>&pageNum=<s:property value="#request.categoryPageData.currentPage"/>'>Delete</a>
							</td>

							<!-- Following code is an good example to demonstrate how to open an new window through hyperlink  -->
							<%-- <td><a href="JavaScript:newPopup('<%=basePath%>post/Admin_editCategory.action?cid=<s:property value="#category.cid"/>&pageNum=<s:property value="#request.categoryPageData.currentPage"/>&categoryName=<s:property value="#category.categoryName"/>');">Edit</a></td> --%>
							
						</tr>
					</s:iterator>

				</table>
								
				<s:if test='#request.updateCategoryInfoSource.equals("from_update")'> 			 
	            	<p><s:property value="#request.updateCategoryResult"/></p>
	        	</s:if>

			</div>
		</div>


		<!-- <p><a href="JavaScript:newPopup('/javascript/examples/sample_popup.cfm');">Open a popup window</a></p> -->
<div style="clear:both"></div>

<!--要想这个set生效，必须在产生这个页面action中有postPageData这个属性，并且有这个属性的get方法  -->
<s:set var="currPageData" value="categoryPageData" scope="request"/>
<s:include value="npj_new.jsp">
	<s:param name="actionURL">Admin_getCategoryPreNextPageData.action</s:param>
</s:include>

<s:include value="footer.jsp"></s:include>

</div>


</body>
</html>
