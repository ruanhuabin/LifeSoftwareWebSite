<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<%!

public Map<String, String> convert(String str) 
{
    String[] tokens = str.split(",|=");
    Map<String, String> map = new HashMap<String, String>();
    for (int i=0; i<tokens.length-1; ) 
    	map.put(tokens[i++].trim(), tokens[i++].trim());
    return map;
}

 %>

<%
	String str = request.getParameter("currPageData");
	Map<String, String> pageInfo = convert(str);
	out.println(pageInfo);
 %>

 
<br/> 
newpagedata in npj.jsp: <s:property value="#request.mypagedata"/>
<div align="center">
    
                            共<font color="red"><%=pageInfo.get("totalPage")%></font>页&nbsp;&nbsp;
                           共<font color="red"><%=pageInfo.get("allRows")%></font>条记录      
       
        <% 
        if(pageInfo.get("currentPage").equals("1"))
        {
        %>
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        <%
        }
        else
        {
         %>
            <a href="<%=pageInfo.get("action") %>?pageNum=1"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="<%=pageInfo.get("action") %>?pageNum=<%=Integer.parseInt(pageInfo.get("currentPage")) - 1 %>"><font size=2>上一页</font></a>
       
       <%
       }
        %>
        
        <%-- <s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage"> --%>
        <%
        	if(!pageInfo.get("currentPage").equals(pageInfo.get("totalPage")))
        	{
         %>
            <a href="<%=pageInfo.get("action") %>?pageNum=<%=Integer.parseInt(pageInfo.get("currentPage")) + 1 %>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="<%=pageInfo.get("action") %>?pageNum=<%=pageInfo.get("totalPage") %>"><font size=2>尾页</font></a>
        
        <%
        }
        else
        {
         %>
        
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        <%
        }
         %>
    
   </div>
   
   <div align="center">
        
        <form action="<%=pageInfo.get("action") %>" onsubmit="return validate();">
            <font size="2">跳转至</font>            
            <input type="text" size="5" name="pageNum" >页
            <input type="submit" value="跳转">
        </form>
        
    </div>
