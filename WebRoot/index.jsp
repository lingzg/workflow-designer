<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>流程模型列表</title>
	<script type="text/javascript"
		src="${contextPath}/editor-app/libs/jquery_1.11.0/jquery.min.js"></script>
	<style type="text/css">
	button {
		margin: 5px;
	}
	table {
		border-collapse: collapse;
	}
	table, th, td {
		border: 1px solid black;
	}
	td {
  		text-align:left;
  		vertical-align:bottom;
  		padding:10px;
  	}
  	th{
  		background-color:gray;
  	}
	</style>
</head>
<body>
	<h1>流程模型列表</h1>
	<button onclick="add();">新增</button>
	<table>
		<thead>
			<tr>
				<th>名称</th>
				<th>描述</th>
				<th>版本</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
</body>

<script type="text/javascript">
	$(function() {
		loadData();
	});
	function loadData() {
		$.getJSON('${contextPath}/model/findModels', function(data) {
			console.log(data);
			var i, len, tr, description = '', status = '';
			if (data && data.length > 0) {
				for (i = 0, len = data.length; i < len; i++) {
					if (data[i].metaInfo) {
						description = JSON.parse(data[i].metaInfo).description;
					}
					status = data[i].deploymentId ? '已部署' : '未部署';
					tr = '<tr>' +
							'<td>' + data[i].name + '</td>' +
							'<td>' + description + '</td>' +
							'<td>' + data[i].version + '</td>' +
							'<td>' + data[i].createTime + '</td>' +
							'<td>' + data[i].lastUpdateTime + '</td>' +
							'<td>'+ status + '</td>' +
							'<td><a href="javascript:;" onclick="edit('+ data[i].id + ');" title="编辑">编辑</a></td>' +
						 '</tr>';
					$('tbody').append(tr);
				}
			}
		});
	}
	function add() {
		window.location.href = "${contextPath}/model/create?name=process&key=process2&description=process";
	}
	function edit(id) {
		window.location.href = "${contextPath}/modeler.html?modelId=" + id;
	}
</script>
</html>