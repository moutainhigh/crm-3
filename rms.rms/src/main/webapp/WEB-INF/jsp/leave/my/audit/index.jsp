<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.leave.dto.LeaveDTO" %>
<%@ page import="com.hefei.common.returncode.ReturnCode" %>
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
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/My97DatePicker/WdatePicker.js"></script> 
</head>
<body>
	<nav class="breadcrumb">
   		<i class="Hui-iconfont">&#xe67f;</i> 假期管理  
   		<span class="c-gray en">&gt;</span> 待审核假期
   	</nav>
   	<div class="page-container">
   			<div class="cl pd-5 bg-1 bk-gray ml-20">
				<form method="post" action="<%=RmsConstant.RMS_URL%>/leave/myaudit/index.do" id="leaveSearchForm">
					<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }">
					<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
					<div class="row cl">
						<ul class="inline1">
					         <li>
				            	<label class="tjname fw7">状态：</label>
				            	<input type="checkbox" id="status1" name="auditStatus" value="${ AUDIT_STATUS_TODO}" checked>待审核
				            	<input type="checkbox" id="status2" name="auditStatus" value="${ AUDIT_STATUS_PASS}" >通过
				            	<input type="checkbox" id="status3" name="auditStatus" value="${ AUDIT_STATUS_REJECT}" >拒绝
				          	</li>
						 </ul>
					 </div>
					<div class="cl pd-5  mt-20"> 
						<span class="l">
							<input class="btn btn-primary radius" type="submit"  value="查   询"/>
						</span>
					</div>
				</form>
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
									<a style="text-decoration:none" onClick="pass('${item.id }')" href="javascript:;" title="通过">通过</a>
									<a style="text-decoration:none" onClick="reject('${item.id }')" href="javascript:;" title="拒绝">拒绝</a>
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
function submitForm(obj, pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#leaveSearchForm").submit();
}

function view(leaveId){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/view.do?companyId='+companyId
	});
	layer.full(index);
}


function pass(leaveId){
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' +'/leave/myaudit/audit.do?leaveId=' + leaveId + '&auditStatus=' + '<%=LeaveDTO.AUDIT_STATUS_PASS%>',
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				alert("审核成功");
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
function reject(leaveId){
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' +'/leave/myaudit/audit.do?leaveId=' + leaveId + '&auditStatus=' + '<%=LeaveDTO.AUDIT_STATUS_REJECT %>',
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				alert("审核成功");
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
</script>
</html>
