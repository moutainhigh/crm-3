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
	
	<title>工资管理-调整工资</title>

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
   		<i class="Hui-iconfont">&#xe67f;</i> 首页
   		<span class="c-gray en">&gt;</span>调整工资
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
   		<form method="post" action="<%=RmsConstant.RMS_URL%>/salary/adjustSalaryTransaction/saveAdjustSalary.do" id="adjustSalaryTransactionForm" class="form form-horizontal">
   			<input type="hidden" value="${salaryTransactionDTO.id}" id="id" name="id">
   			<input type="hidden" value="${salaryTransactionDTO.employeeId}" id="employeeId" name="employeeId">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					${salaryTransactionDTO.employeeName }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>月基本工资</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${salaryTransactionDTO.monthlyBaseSalary }" id="monthlyBaseSalary" name="monthlyBaseSalary">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>月奖金</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${salaryTransactionDTO.monthlyBonus }" id="monthlyBonus" name="monthlyBonus">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>年奖金</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${salaryTransactionDTO.quarterlyBonus }" id="quarterlyBonus" name="quarterlyBonus">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>季度奖金</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${salaryTransactionDTO.yearlyBonus }" id="yearlyBonus" name="yearlyBonus">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>当月惩罚(扣除)金额</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${salaryTransactionDTO.deductAmount }" id="deductAmount" name="deductAmount">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>当月社保(扣除)金额</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" readOnly="readonly" value="${salaryTransactionDTO.ssAmount }" id="ssAmount" name="ssAmount">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>纳税(扣除)金额比率</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text"value="${salaryTransactionDTO.taxAmount }" id="taxAmount" name="taxAmount">
					(<input  type="text"value="${salaryTransactionDTO.taxRate }" id="taxRate" name="taxRate">)
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>实发工资</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text"value="${salaryTransactionDTO.payedAmount }" id="payedAmount" name="payedAmount">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>说明</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name="remark" rows="10" cols="50">${remark }"</textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="saveAdjustSalary();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script language="javascript">
function saveAdjustSalary(){
	document.forms['adjustSalaryTransactionForm'].submit();
}
</script>
</html>
