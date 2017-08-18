<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>

    <script type="text/javascript">
        function selCity()
		{
            
			/* var arr = [["----select city----"]
                    ,['dongcheng', 'xicheng','chongwen']
                    ,['heping','hedong', 'hexi']
					,['pudong','lujiazui', 'xinshiji']
                    ]; */
			

			
			var arr = [];
			arr[0] = ["----select city----"];
			arr[1] = ['dongcheng', 'xicheng','chongwen'];
			arr[2] = ['pudong','lujiazui', 'xinshiji'];
			arr[3] = ['pudong','lujiazui', 'xinshiji'];
			
            /*拿到省份的索引*/
            var index = document.getElementById("selid").selectedIndex;
            /*拿到省份数组中对应的城市*/
            var citys = arr[index];
            /*拿到子菜单节点*/
            var subselNode = document.getElementById("subselid");
            /*移除子菜单中的城市
            注意：循环时不要“i++”,因为循环条件在递减，若在进行i++，会产生删除不干净的错误
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
    </script>
</head>
<body><br/>
    &nbsp;&nbsp;&nbsp;Home:&nbsp;
    <select id="selid" onchange="selCity()">
        <option selected>----select provice----</option>
        <option>beijing</option>
        <option>tianjin</option>
        <option>shanghai</option>        
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;
    <select id="subselid">
        <option selected>----qing xuan ze cheng shi----</option>
        <option></option>
    </select>
</body>
</html>