<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeInfo" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeCompanyInfo" %>

<c:set var="SEX_MAILE" value="<%=EmployeeInfo.SEX_MAILE%>"></c:set>
<c:set var="SEX_FEMAILE" value="<%=EmployeeInfo.SEX_FEMAILE%>"></c:set>
<c:set var="SEX_UNKNOWN" value="<%=EmployeeInfo.SEX_UNKNOWN%>"></c:set>
<c:set var="STATUS_PRACTICE" value="<%=EmployeeCompanyInfo.STATUS_PRACTICE%>"></c:set>
<c:set var="STATUS_PROBATION" value="<%=EmployeeCompanyInfo.STATUS_PROBATION%>"></c:set>
<c:set var="STATUS_ONBOARD" value="<%=EmployeeCompanyInfo.STATUS_ONBOARD%>"></c:set>
<c:set var="STATUS_LEAVE" value="<%=EmployeeCompanyInfo.STATUS_LEAVE%>"></c:set>
<c:set var="STATUS_RETIRE" value="<%=EmployeeCompanyInfo.STATUS_RETIRE%>"></c:set>

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
	
	<title>员工管理-员工信息查询</title>

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
   		<i class="Hui-iconfont">&#xe67f;</i> 首页
   		<span class="c-gray en">&gt;</span> 员工管理
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
			<div class="cl pd-5 bg-1 bk-gray ml-20">
				<form method="post" action="<%=RmsConstant.RMS_URL%>/employee/search.do" id="employeeSubmitForm">
					<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }">
					<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
					<div class="row cl">
						<ul class="inline1">
							<li>
							<label class="tjname fw7">员工编号：</label>
				       		<input type="text" name="cardNo" id="cardNo" value="${cardNo}" />
				       		</li>
				       		<li>
				       			<label class="tjname fw7">性别：</label>
				            	<input type="checkbox" id="sex1" name="sex" value="<%=EmployeeInfo.SEX_MAILE%>" >男
				            	<input type="checkbox" id="sex2" name="sex" value="<%=EmployeeInfo.SEX_FEMAILE%>" >女
				            	<input type="checkbox" id="sex2" name="sex" value="<%=EmployeeInfo.SEX_UNKNOWN%>" >未知
				       		</li>
				       		<li>
				       			<label class="tjname fw7">状态：</label>
				            	<input type="checkbox" id="status1" name="status" value="<%=EmployeeCompanyInfo.STATUS_PRACTICE%>" >实习
				            	<input type="checkbox" id="status2" name="status" value="<%=EmployeeCompanyInfo.STATUS_PROBATION%>" checked>试用
				            	<input type="checkbox" id="status3" name="status" value="<%=EmployeeCompanyInfo.STATUS_ONBOARD%>" checked>在职
				            	<input type="checkbox" id="status4" name="status" value="<%=EmployeeCompanyInfo.STATUS_LEAVE%>" >离职
				            	<input type="checkbox" id="status5" name="status" value="<%=EmployeeCompanyInfo.STATUS_RETIRE%>" >退休
				       		</li>
						</ul>
					</div>
					<div class="cl pd-5 mt-20"> 
						<span class="l">
							<input class="btn btn-primary radius" type="submit"  value="查   询"/>
							<input class="btn btn-primary radius" style="margin-left: 15px;" type="button" value="新增员工" onclick="javascript:addEmployee();" />
						</span>
					</div>
				</form>
			</div>
			 <div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th class="ti2">ID</th>
							<th class="ti2">姓名</th>
							<th class="ti2">员工编号</th>
							<th class="ti2">性别</th>
							<th class="ti2">类型</th>
							<th class="ti2">创建人</th>
							<th class="ti2">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items }">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.employeeId }" name="chk" id="chk_${item.employeeId }"></td>
								
								<td class="ti2"><c:out value="${item.employeeId}" /></td>
								<td class="ti2"><c:out value="${item.name}"  /></td>
								<td class="ti2"><c:out value="${item.cardNo}" /></td>
								<td class="ti2">
									<c:if test="${item.sex eq SEX_MAILE}">男</c:if>
									<c:if test="${item.sex eq SEX_FEMAILE}">女</c:if>
									<c:if test="${item.sex eq SEX_UNKNOWN}">未知</c:if>
								</td>
								<td class="ti2">
									<c:if test="${item.status eq STATUS_PRACTICE}">实习</c:if>
									<c:if test="${item.status eq STATUS_PROBATION}">试用</c:if>
									<c:if test="${item.status eq STATUS_ONBOARD}">在职</c:if>
									<c:if test="${item.status eq STATUS_LEAVE}">离职</c:if>
									<c:if test="${item.status eq STATUS_RETIRE}">退休</c:if>
								</td>
								<td class="ti2"><c:out value="${item.creator}" /></td>
								<td class="ti2"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="userAuthRole('${item.employeeId }')" href="javascript:;" title="授权">授权</a>
								<!-- <a style="text-decoration:none" onClick="view('${item.employeeId }')" href="javascript:;" title="详情">详情</a> -->
									<a style="text-decoration:none" onClick="edit('${item.employeeId }')" href="javascript:;" title="更新">更新</a>
									<a style="text-decoration:none" onClick="modifyDepartAndPosition('${item.employeeId }')" href="javascript:;" title="更新">调整部门职位</a>
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
function submitForm(obj, pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#employeeSubmitForm").submit();
}
//新增员工
function addEmployee(){		
	var index = layer.open({
		type: 2,
		title: "添加员工",
		content: '<%=RmsConstant.RMS_URL%>'+'/employee/toAdd.do'
	});
	layer.full(index);
}
function view(companyId){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/view.do?companyId='+companyId
	});
	layer.full(index);
}
function edit(employeeId){
	var index = layer.open({
		type: 2,
		title: "更新员工",
		content: '<%=RmsConstant.RMS_URL%>'+'/employee/toEdit.do?employeeId='+employeeId
	});
	layer.full(index);
}
function modifyDepartAndPosition(employeeId){
	var index = layer.open({
		type: 2,
		title: "修改部门职位",
		content: '<%=RmsConstant.RMS_URL%>'+'/employee/modifyDepartAndPosition.do?employeeId='+employeeId
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
function userAuthRole(employeeId){
	var index = layer.open({
		type: 2,
		title: "授权",
		content: '<%=RmsConstant.RMS_URL%>'+'/cas/role/toAuthRole.do?employeeId=' + employeeId
	});
	layer.full(index);
}
</script>
</html>
