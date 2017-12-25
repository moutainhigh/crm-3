<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.leave.dto.LeaveDTO" %>

<c:set var="TYPE_PRIVATE" value="<%=LeaveDTO.TYPE_PRIVATE%>"></c:set>
<c:set var="TYPE_SICK" value="<%=LeaveDTO.TYPE_SICK%>"></c:set>
<c:set var="TYPE_ANNUAL" value="<%=LeaveDTO.TYPE_ANNUAL%>"></c:set>
<c:set var="TYPE_OFF" value="<%=LeaveDTO.TYPE_OFF%>"></c:set>
<c:set var="TYPE_FUNERAL" value="<%=LeaveDTO.TYPE_FUNERAL%>"></c:set>
<c:set var="TYPE_MATERNITY" value="<%=LeaveDTO.TYPE_MATERNITY%>"></c:set>
<c:set var="TYPE_MARRIAGE" value="<%=LeaveDTO.TYPE_MARRIAGE%>"></c:set>
<c:set var="TYPE_PATERNITY" value="<%=LeaveDTO.TYPE_PATERNITY%>"></c:set>
<c:set var="TYPE_OTHER" value="<%=LeaveDTO.TYPE_OTHER%>"></c:set>
<c:set var="AUDIT_STATUS_TODO" value="<%=LeaveDTO.AUDIT_STATUS_TODO%>"></c:set>
<c:set var="AUDIT_STATUS_PASS" value="<%=LeaveDTO.AUDIT_STATUS_PASS%>"></c:set>
<c:set var="AUDIT_STATUS_REJECT" value="<%=LeaveDTO.AUDIT_STATUS_REJECT%>"></c:set>
<c:set var="DEL_FLAG_NO" value="<%=LeaveDTO.DEL_FLAG_NO%>"></c:set>
<c:set var="DEL_FLAG_YES" value="<%=LeaveDTO.DEL_FLAG_YES%>"></c:set>
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
	
	<title>假期管理</title>

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
   		<i class="Hui-iconfont">&#xe67f;</i> 假期管理  
   		<span class="c-gray en">&gt;</span> 我的假期
   	</nav>
   	<div class="page-container">
   			<div class="cl pd-5 bg-1 bk-gray ml-20">
				<span class="l">
					<input class="btn  btn-primary radius" type="button" onclick="applyLeave()"  value="请假"/>
				</span>
			</div>
			 <div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th class="ti2">员工姓名</th>
							<th class="ti2">请假类型</th>
							<th class="ti2">开始时间-结束时间-总时间</th>
							<th class="ti2">审核人-审核状态-审核时间</th>
							<th class="ti2">内容</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items}">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.id }" name="chk" id="chk_${item.id }"></td>
								
								<td class="ti2"><c:out value="${item.employeeName}" /></td>
								<td class="ti2">
									<c:if test="${item.type eq TYPE_PRIVATE }" >事假</c:if>
									<c:if test="${item.type eq TYPE_SICK }" >病假</c:if>
									<c:if test="${item.type eq TYPE_ANNUAL }" >年假</c:if>
									<c:if test="${item.type eq TYPE_OFF }" >调休</c:if>
									<c:if test="${item.type eq TYPE_FUNERAL }" >丧假</c:if>
									<c:if test="${item.type eq TYPE_MATERNITY }" >产假 </c:if>
									<c:if test="${item.type eq TYPE_MARRIAGE }" >婚假</c:if>
									<c:if test="${item.type eq TYPE_PATERNITY }" >陪产假</c:if>
									<c:if test="${item.type eq TYPE_OTHER }" >其他</c:if>
								</td>
								<td class="ti2">
									<fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH"/>
									-<fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH"/>
									-${item.totalTimeDay}天${item.totalTimeHour}小时
								</td>
								<td class="ti2">
									<c:out value="${item.auditEmployeeName}" />
									-
									<c:if test="${item.auditStatus eq AUDIT_STATUS_TODO }" >未审核</c:if>
									<c:if test="${item.auditStatus eq AUDIT_STATUS_PASS }" >通过</c:if>
									<c:if test="${item.auditStatus eq AUDIT_STATUS_REJECT }" >拒绝</c:if>
									-<fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td class="ti2">
									<c:out value="${item.content}" />
								</td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="view('${item.id }')" href="javascript:;" title="详情">详情</a>
									<a style="text-decoration:none" onClick="del('${item.id }')" href="javascript:;" title="取消">取消</a>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<jsp:include page="../../../common/pagination.jsp"></jsp:include>
				</table>
			</div>
	</div>
</body>

<script type="text/javascript">

function applyLeave(){
	var index = layer.open({
		type: 2,
		title: '请假',
		content: '<%=RmsConstant.RMS_URL%>'+'/leave/myapply/toAdd.do'
	});
	layer.full(index);
}
</script>
</html>
