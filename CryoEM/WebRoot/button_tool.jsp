<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	function genURL()
	{
   		 <%-- window.open("<%=basePath%>gen_file_download_url.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=400"); --%>
   		 window.open("<%=basePath%>post/Admin_toGenDownloadURL.action", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=400");
	}
	
	function genCategory()
	 {
   		 <%-- window.open("<%=basePath%>add_category.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=600"); --%>
   		 window.open("<%=basePath%>post/Admin_toAddCategory.action", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=600");
	}
	
	function genSubCategory()
	 {
   		 window.open("<%=basePath%>post/Admin_toSubCategory.action", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=300,left=400,width=600,height=600");
	}

</script>
<button title="click to generate an URL for file downloading"onclick="genURL()">Generate File URL</button>
<button  title="click to create a new catagory" onclick="genCategory()">Create New Category</button>
<button  title="click to create a new sub catagory" onclick="genSubCategory()">Create New Sub Category</button>