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
	
	<title>工资管理-工资调整历史</title>

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
   		<span class="c-gray en">&gt;</span>工资调整历史
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
   		<div class="cl pd-5 bg-1 bk-gray ml-20">
				<form method="post" action="<%=RmsConstant.RMS_URL%>/salary/adjusthistory/search.do" id="adjustSalaryHistorySubmitForm">
					<input type="hidden" name="pageIndex" id="pageIndex" value="${pagination.pageIndex }">
					<input type="hidden" name="pageSize" id="pageSize" value="${pagination.pageSize}">
					<div class="row cl">
						<ul class="inline1">
							 <li>
					            <label class="tjname fw7">员工编号：</label>
					            <input type="text" name="cardNo" id="cardNo" value="${cardNo}" />
					         </li>
					         <li>
				            	<label class="tjname fw7">员工姓名：</label>
				            	<input type="text" name="employeeName" id="employeeName" value="${employeeName}" />
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
							<th class="ti2">姓名</th>
							<th class="ti2">月基本工资调整(调整前-调整后)</th>
							<th class="ti2">月奖金调整(调整前-调整后)</th>
							<th class="ti2">季度奖金调整(调整前-调整后)</th>
							<th class="ti2">年度奖金调整(调整前-调整后)</th>
							<th class="ti2">生效时间</th>
							<th class="ti2">说明</th>
							<th class="ti2">调整人</th>
							<th class="ti2">调整时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items }">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.id }" name="chk" id="chk_${item.id }"></td>
								<td class="ti2"><c:out value="${item.employeeName}" /></td>
								<td class="ti2">
									<c:out value="${item.monthlyBaseSalaryBefore}" />--
									<c:out value="${item.monthlyBaseSalaryAfter}" />
								</td>
								<td class="ti2">
									<c:out value="${item.monthlyBonusBefore}" />--
									<c:out value="${item.monthlyBonusAfter}"  />
								</td>
								<td class="ti2">
									<c:out value="${item.quarterlyBonusBefore}" />--
									<c:out value="${item.quarterlyBonusAfter}" />
								</td>
								<td class="ti2">
									<c:out value="${item.yearlyBonusBefore}" />--
									<c:out value="${item.yearlyBonusAfter}" />
								</td>
								<td class="ti2"><fmt:formatDate value="${item.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="ti2">
									<c:out value="${item.remark}" />
								</td>
								<td class="ti2">
									<c:out value="${item.creator}" />
								</td>
								<td class="ti2"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="f-14 td-manage">
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<jsp:include page="../../common/pagination.jsp"></jsp:include>
				</table>
			</div>
	</div>
</body>

<script type="text/javascript">

function submitForm(obj, pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#adjustSalaryHistorySubmitForm").submit();
}
</script>
</html>
