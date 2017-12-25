<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.position.vo.PositionInfo" %>
<c:set var="DEL_FLAG_NO" value="<%=PositionInfo.DELFLAG_NO%>"></c:set>
<c:set var="DEL_FLAG_YES" value="<%=PositionInfo.DELFLAG_YES%>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="<%=RmsConstant.STAIC_SERVER_URL%>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=RmsConstant.STAIC_SERVER_URL%>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	<title>职位管理</title>

	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/style.css" />

	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/js/H-ui.admin.js"></script>
</head>
<body>
	<nav class="breadcrumb">
   		<i class="Hui-iconfont">&#xe67f;</i> 职位管理  
   		<span class="c-gray en">&gt;</span> 员工管理
   		<span class="c-red">部门：${department.departmentName }</span>
   	</nav>
   	<div class="page-container">
			<div class="text-c">
				 <dl class="colunm c3T4D8 cdg dl-tar colunmbotton mt15">
				    <dt></dt>
				    <dd style="padding-left: 10px;">
						<input class="btn buttonbgorange" type="button" onclick="addPosition('${department.id }')"  value="新增"/>
				    </dd>
				 </dl>
			</div>
			 <div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th class="ti2">ID</th>
							<th class="ti2">职位名称</th>
							<th class="ti2">职位状态</th>
							<th class="ti2">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list}">
							<c:forEach var="item" items="${ list}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.id }" name="chk" id="chk_${item.id }"></td>
								
								<td class="ti2"><c:out value="${item.id}" /></td>
								<td class="ti2"><c:out value="${item.positionName}"  /></td>
								<td class="ti2">
									<c:if test="${item.delFlag eq DEL_FLAG_NO}">正常</c:if>
									<c:if test="${item.delFlag eq DEL_FLAG_YES}">删除</c:if>
								</td>
								<td class="ti2"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="view(${item.id })" href="javascript:;" title="详情">详情</a>
									<a style="text-decoration:none" onClick="edit(${item.id })" href="javascript:;" title="更新">更新</a>
									<a style="text-decoration:none" onClick="del(${item.id })" href="javascript:;" title="删除">删除</a>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<jsp:include page="../common/pagination.jsp"></jsp:include>
				</table>
			</div>
	</div>
</body>

<script type="text/javascript">
//新增员工
function addPosition(departmentId){
	var titleContent ='<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>职位管理  <span class=\"c-gray en\">&gt;</span> 添加职位 <span class=\"c-red\">部门：'
	+'${department.departmentName }'+'</span></nav>';
	
	var index = layer.open({
		type: 2,
		title: titleContent,
		content: '<%=RmsConstant.RMS_URL%>'+'/position/toAdd.do?departmentId='+departmentId
	});
	layer.full(index);
}
function view(positionId){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/view.do?companyId='+companyId
	});
	layer.full(index);
}
function edit(positionId){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/toEdit.do?companyId='+companyId
	});
	layer.full(index);
}
function del(){
	var companyIds='';
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/del.do?companyIds='+companyIds
	});
	layer.full(index);
}
</script>
</html>
