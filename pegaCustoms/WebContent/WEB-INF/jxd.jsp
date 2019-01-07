<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

#table{
border: 1px solid #ccc; 
border-collapse:collapse;
width: 80%;
margin-top: 11px;
}
#table td{
border: 1px solid #ccc; 
}
#table_divA{
width: 80%;
text-align: center;

}
#table_divA button{
background-color:#2067CC;
width: 8%;
height: 30px;
}
 #divA{
background-color:#8FBEE2; 
width: 80%;
height: 30px;
font-size: 18px;
}
.classA{
font-size: 16px;
font-weight: bold;

}
.classA div{
background-color: #43A043;
width: 3%;
display: inline; 
}
#tableA{
width: 80%;
border-collapse:collapse;
border: 1px solid #ccc;
margin-top: 11px;
}

#tableA th{
border: 1px solid #ccc;
background-color: #3B73BE;
height: 30px;
color: white; 

}
#tableA td{
border: 1px solid #ccc; 
height: 30px; 

}

.table_input{
width: 38px;height:18px;
}

.table_inputB{
width: 38px;height:18px;
}
#table_divA fout{

}
</style>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/demo/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-2.1.4/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">


function formSubmit(){
	var targetUrl = $("#form").attr("action");
	var data = $("#form").serialize();
     $.ajax({
    	 type:'post',
    	 url:targetUrl,
    	 cache:false,
    	 data:data,
    	 dataType:'text',
    	 success:function(result){
    		 alert("ok");
    		 alert(result);
    	 },
    	 error:function(result){
    		 alert("请求失败！！！");
    	 }
    	 
     });
	
}


function jsonData(){

//console.info($("#test").val())

	
	 $.ajax({
	url:"http://localhost:8080/pegaCustoms/login/hello6",
	contenType:'application/json;charset=utf-8',
	data:'{"name":"贾晓东"，"age":"18"}',
	
	type:'post',
	sucess:function(data){
		alert("1111");
		alert(data);
	},
	error:function(error){
		alert(111);
		alert(error);
	}	
	}) 
	
}

function reset(){
	$("input[type=checkbox]").attr("checked",false);
	$("input[type=radio]").attr("checked",false);
	$("#remark").val("");
}

</script>
</head>

<body>

<div id="divA" >
系统设置 > 选查参数设置
</div>
<br/>
<div class="classA" >
&nbsp;&nbsp;<div >&nbsp;</div>
&nbsp;增 选 查 参 数 列 表
</div>

	<form action="http://localhost:8080/pegaCustoms/maintenance/save" id="form">
<table   id="table" >
<tr style="height:39px;">
<td style="background-color:#EBF2FC;text-align: center;font-size:16px;">状态</td>

<td style="text-align: center;background-color:#FBFBFB" >

<input id="item1" class="table_inputB" type="radio" name="imex_status" value="0" >
<font size="3px;">申报地</font>
&nbsp;&nbsp;
<input id="item2" class="table_inputB" type="radio" name="imex_status" value="1" >
<font size="3px;">查验地</font>

</td>


</tr>
<tr style="height:160px;">
<td style="background-color:#EBF2FC;text-align: center;font-size:16px;">角色所在关区</td>
<td style="background-color:#FBFBFB">
<c:forEach var="p" items="${list }"  varStatus="status">
<c:if test="${status.index ==8 ||status.index ==16}">
<br/>
</c:if>
<input type="checkbox" name="role_area" class="table_input" value="${p.suoerior_sustoms}">
<font size="3px;"> ${p.customs_name}</font>


</c:forEach>

</td>
</tr>

<tr style="height:160px;">
<td style="background-color:#EBF2FC;text-align: center;font-size:16px; ">角色映射关区
</td>
<td style="background-color:#FBFBFB;">
<c:forEach var="p" items="${list }"  varStatus="status">
<c:if test="${status.index ==8 ||status.index ==16}">
<br/>
</c:if>
<input type="checkbox"  name="role_cast" class="table_input"  value="${p.suoerior_sustoms}">
<font  size="3px;"> ${p.customs_name}</font>

</c:forEach>

</td>

</tr>


<tr style="height:39px;">
<td style="background-color:#EBF2FC;text-align: center;font-size:16px;">备注</td>
<td style="background-color:#FBFBFB">
&nbsp;&nbsp;<input id="remark" name="remark" type="text" style="width: 80%;" /></td>
</tr>

</table>
</form>
<br/>
<div id="table_divA">
<button onclick="formSubmit();"><font size="3px;"  style="color: white">保存</font></button>

<button onclick="reset();"><font size="3px;" style="color: white">重置</font></button>

</div>


<br>
<div class="classA" style="margin-top: 20px;">
&nbsp;&nbsp;<div style="background-color: #FFA500">&nbsp;</div>
&nbsp;选查参数维护表
</div>

<table id="tableA" >
<thead>
<tr>
<th>序号</th><th>状态</th><th>角色所在关区</th><th>角色映射关区</th><th>备注</th><th>操作</th>
</tr>
</thead>
<tbody>

</tbody>
</table>

</body>


</html>