<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
		function validate()
        {
            var page = document.getElementsByName("pageNum")[0].value;
                
            if(page > <s:property value="#request.currPageData.totalPage"/>)
            {
                alert("你输入的页数大于最大页数，页面将跳转到首页！");
                
                window.document.location.href = "${param.actionURL}?pageNum=1";
                
                return false;
            }
            
            return true;
        }
</script>
<div align="center">
    
                            共<font color="red"><s:property value="#request.currPageData.totalPage"/></font>页&nbsp;&nbsp;
                           共<font color="red"><s:property value="#request.currPageData.allRows"/></font>条记录
        
        <s:if test="#request.currPageData.currentPage == 1">
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        </s:if>
        
        <s:else>
            <a href="${param.actionURL}?pageNum=0"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="${param.actionURL}?pageNum=<s:property value="#request.currPageData.currentPage - 1"/>"><font size=2>上一页</font></a>
        </s:else>
        
        <s:if test="#request.currPageData.currentPage != #request.currPageData.totalPage">
            <a href="${param.actionURL}?pageNum=<s:property value="#request.currPageData.currentPage + 1"/>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="${param.actionURL}?pageNum=<s:property value="#request.currPageData.totalPage"/>"><font size=2>尾页</font></a>
        </s:if>
        
        <s:else>
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        </s:else>
    
   </div>
   
   <div align="center">
        
        <form action="${param.actionURL}" onsubmit="return validate();">
            <font size="2">跳转至</font>            
            <input type="text" size="5" name="pageNum" >页
            <input type="submit" value="跳转">
        </form>
        
    </div>
