<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" Content="text/html; charset=UTF-8">
	<title>测试</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/demo/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/css/threeUniformPort.css">
	<script type="text/javascript" src="../static/js/threeUniformPort.js"></script>
</head>
<body>
	<!-- <h2>信息展示</h2> -->
	<div id="info" style="margin:20px 0;"></div>
	<div class="easyui-layout" style="width:80%;height:350px;">
		<div data-options="region:'west',split:true" title="菜单栏" style="width:15%;"></div>
		<div data-options="region:'center',title:'系统设置 >选查参数设置',iconCls:'icon-ok'">
		</div>
	</div>
	
	
	<div class="classA" >&nbsp;&nbsp;<div >&nbsp;</div>&nbsp;选查参数列表</div>

	<form action="http://localhost:8080/pegaCustoms/maintenance/save" id="form">
		<table  id="table" title="选查参数列表" style="width:80%">
		<tbody>
			<tr style="height:39px;">
				<td style="background-color:#EBF2FC;text-align: center;font-size:12px;">状态</td>
				<td style="text-align: center;background-color:#FBFBFB" >
					<input id="item1" class="table_inputB" type="radio" name="imex_status" value="0" >
					<font size="3px;">申报地</font>&nbsp;&nbsp;
					<input id="item2" class="table_inputB" type="radio" name="imex_status" value="1" >
					<font size="3px;">查验地</font>
				</td>
			</tr>
			<tr style="height:160px;">
				<td style="background-color:#EBF2FC;text-align: center;font-size:12px;">角色所在关区</td>
				<td style="background-color:#FBFBFB">
					<c:forEach var="p" items="${list }"  varStatus="status">
						<c:if test="${status.index ==8 ||status.index ==16}"><br/>
						</c:if>
						<input type="checkbox" name="role_area" class="table_input" value="${p.suoerior_sustoms}">
						<font size="3px;"> ${p.customs_name}</font>
					</c:forEach>
				</td>
			</tr>
			<tr style="height:160px;">
				<td style="background-color:#EBF2FC;text-align: center;font-size:12px; ">角色映射关区</td>
				<td style="background-color:#FBFBFB;">
					<c:forEach var="p" items="${list }"  varStatus="status">
						<c:if test="${status.index ==8 ||status.index ==16}"><br/>
						</c:if>
						<input type="checkbox"  name="role_cast" class="table_input"  value="${p.suoerior_sustoms}">
						<font  size="3px;"> ${p.customs_name}</font>
					</c:forEach>
				</td>
			</tr>
			<tr style="height:39px;">
				<td style="background-color:#EBF2FC;text-align: center;font-size:12px;">备注</td>
				<td style="background-color:#FBFBFB">&nbsp;&nbsp;<input id="remark" name="remark" type="text" style="width: 80%;" /></td>
			</tr>
			</tbody>
		</table>
	</form><br/>
	<div id="table_divA">
		<button onclick="formSubmit();"><font size="3px;"  style="color: white;font-size: 14px;">保存</font></button>
		<button onclick="reset();"><font size="3px;" style="color: white;font-size: 14px;">重置</font></button>
	</div><br>
	<table id="table_dg" class="easyui-datagrid" title="选查参数维护表" style="width:80%;height:auto;" fitColumns="true" toolbar="#tb"
			data-options="  singleSelect:true,
							rownumbers:true,
							collapsible:true,
							autoRowHeight:false,
							pagination:true,
							pageSize:10,
							url:'${pageContext.request.contextPath}/threeUniformPort/listInfo'">
		<thead>
			<tr>
				<th field='userId',width="10",align="center">序号</th>
				<th field='userIda',width="10",align="center">状态</th>
				<th field='userIdb',width="10",align="center">角色所在关区</th>
				<th field='userIdc',width="10",align="center">角色映射关区</th>
				<th field='userIdd',width="10",align="center">备注</th>
				<th field='userIdf',width="10",align="center">操作</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>