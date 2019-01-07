<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; charset=UTF-8">
<title>测试</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/demo/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="../static/css/threeUniformPort.css">
<script type="text/javascript" src="../static/js/threeUniformPort.js"></script>
</head>
<body>
	<div id="left">
		<h4>
			<span></span> 岗位分类
		</h4>
		<ul>
			<c:forEach var="p" items="${rmap }" varStatus="status">
				<li data-num="${p.value}">${p.key}</li>
			</c:forEach>
		</ul>
	</div>
	<div id="content">
		<div id="divA" style="">系统设置 > 选查参数设置</div>
		<br />
		<div class="classA">
			&nbsp;&nbsp;
			<div>&nbsp;</div>
			&nbsp;增 选 查 参 数 列 表
		</div>
		<form action="${pageContext.request.contextPath }/maintenance/save"
			id="form">
			<input type="hidden" id="post" name="post" value="${post }" />
			<input type="hidden" id="userId" name="userId" value="${userId }"/>
			<input type="hidden" id="orgGuid" name="orgGuid" value="${orgGuid }"/>		
			<table id="table">
				<tr style="height: 39px;">
					<td style="background-color: #EBF2FC; text-align: center; font-size: 16px;">状态</td>
					<td>
						<div class="flexTd">
						<div>
							<input id="item1" class="table_inputB" type="radio" name="a"
								value="0" <c:if test="${post=='0' }"> checked="checked" </c:if>
								disabled="disabled"> <font size="3px;">申报地</font>
						</div>
						<div>
							<input id="item2" class="table_inputB" type="radio" name="a"
								value="1" <c:if test="${post=='1' }"> checked="checked" </c:if>
								disabled="disabled"> <font size="3px;">查验地</font>
						</div>
						</div>
						
					</td>
				</tr>
				<tr id="RoleTR" style="height: 140px;">
					<td style="background-color: #EBF2FC; text-align: center; font-size: 16px;">角色所在关区</td>
					<td>
						<div class="flexTd">
						<c:forEach
							var="p" items="${list}" varStatus="status">
							<c:if test="${status.index ==8 ||status.index ==16}">
								<br />
							</c:if>
							<div>
								<input type="radio" name="roleArea" class="table_input"	value="${p.suoeriorSustoms}">
								<font size="3px;">${p.customsName}</font>
							</div>
						</c:forEach>
						</div>
						
					</td>
				</tr>
				<tr style="height: 140px;">
					<td style="background-color: #EBF2FC; text-align: center; font-size: 16px;">角色映射关区</td>
					<td>
					<div class="flexTd">
					<c:forEach var="p" items="${list}" varStatus="status">
							<c:if test="${status.index ==8 ||status.index ==16}">

							</c:if>
							<div>
								<input type="checkbox" name="role_cast" class="table_input"	value="${p.suoeriorSustoms}${p.customsName}">
								<font size="3px;"> ${p.customsName}</font>
							</div>
						</c:forEach>
						</div>
						
					</td>
				</tr>
				<tr style="height: 39px;">
					<td style="background-color: #EBF2FC; text-align: center; font-size: 16px;">备注</td>
					<td style="background-color: #FBFBFB">
					<textarea id="remark" name="remark" type="text"></textarea></td>
				</tr>
			</table>
		</form>
		<br />
		<div id="table_divA">
			<button onclick="formSubmit();">
				<font size="3px;" style="color: white">保存</font>
			</button>
			<button onclick="reset();">
				<font size="3px;" style="color: white">重置</font>
			</button>
		</div>
		<br>
		<div class="classA">
			&nbsp;&nbsp;
			<div style="background-color: #FFA500">&nbsp;</div>
			&nbsp;选查参数维护表
		</div>
		<table id="tableA">
			<thead>
				<tr>
					<th>序号</th>
					<th>状态</th>
					<th>角色所在关区</th>
					<th>角色映射关区</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbodyB">
				<c:forEach items="${mlist }" var="p" varStatus="s">
					<tr>
						<td>${s.index }</td>
						<td><c:if test="${p.post=='0' }">
		申报地
		</c:if> <c:if test="${p.post=='1' }">
		查验地
		</c:if></td>
						<td>${p.roleAreaName }</td>
						<td>${p.roleRaeaCast }</td>
						<td>${p.remark }</td>
						<td style="text-align: center;"><a
							href="javascript:deleteInfo(${p.id})"
							style="text-decoration: none;"><font color="red">X</font></a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div id="pageUtil">
			<a href="javascript:getPage(1,'${pageContext.request.contextPath }')">首页</a>
			<a
				href="javascript:getPage(${page.page-1<=0?1:page.page-1},'${pageContext.request.contextPath }')">上一页</a>
			<span>当前页:${page.page} </span> <a
				href="javascript:getPage(${page.page+1>page.endPage?page.endPage:page.page+1},'${pageContext.request.contextPath }')">下一页</a>
			<a
				href="javascript:getPage(${page.endPage},'${pageContext.request.contextPath }')">尾页</a>
			共计: <span>${page.endPage} </span>页—<span>${page.sumRows} </span>条
		</div>
	</div>
	<input type="hidden" id="path"
		value="${pageContext.request.contextPath }" />
		<script>
		$('#left ul li:first-child').addClass('active');	
		//左侧标签栏点击事件
		$('#left ul').on('click','li',function(){
			var a=$(this).attr('data-num');
			changePost(a);
		$("#post").val(a);
			$(this).siblings().removeClass('active');
			$(this).addClass('active');
			var tit='系统设置 > '+$(this).html();
			$('#divA').html(tit);
		})
		</script>
</body>
</html>