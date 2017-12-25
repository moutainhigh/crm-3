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
	
	<title>工资管理-调整社保</title>

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
   		<span class="c-gray en">&gt;</span>调整社保
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
   		<form method="post" action="<%=RmsConstant.RMS_URL%>/salary/adjustSSTransaction/saveAdjustSS.do" id="adjustSSTransactionForm" class="form form-horizontal">
   			<input type="hidden" value="${employeeSSTransactionDTO.id}" id="id" name="id">
   			<input type="hidden" value="${employeeSSTransactionDTO.employeeId}" id="employeeId" name="employeeId">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					${employeeSSTransactionDTO.employeeName }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>社保卡号</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${employeeSSTransactionDTO.ssCard }" id="ssCard" name="ssCard">
				</div>
			</div>
			
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>社保缴纳基数</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="${employeeSSTransactionDTO.ssBaseSalary }" id="ssBaseSalary" name="ssBaseSalary">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>养老金</label>
				<div class="formControls col-xs-8 col-sm-9">
					养老金公司缴存比例:<input value="${employeeSSTransactionDTO.ylaoComRate }" id="ylaoComRate" name="ylaoComRate" type="text">
					养老金公司缴存金额:<input value="${employeeSSTransactionDTO.ylaoComCash }" id="ylaoComCash" name="ylaoComCash" type="text">
					养老金个人缴存比例:<input value="${employeeSSTransactionDTO.ylaoPersonalRate }" id="ylaoPersonalRate" name="ylaoPersonalRate" type="text">
					养老金个人缴存金额:<input value="${employeeSSTransactionDTO.ylaoPersonalCash }" id="ylaoPersonalCash" name="ylaoPersonalCash" type="text">
					养老金总额:<input value="${employeeSSTransactionDTO.ylaoInsurance }" id="ylaoInsurance" name="ylaoInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>失业金</label>
				<div class="formControls col-xs-8 col-sm-9">
					失业金公司缴存比例:<input value="${employeeSSTransactionDTO.syeComRate }" id="syeComRate" name="syeComRate" type="text">
					失业金公司缴存金额:<input value="${employeeSSTransactionDTO.syeComCash }" id="syeComCash" name="syeComCash" type="text">
					失业金个人缴存比例:<input value="${employeeSSTransactionDTO.syePersonalRate }" id="syePersonalRate" name="syePersonalRate" type="text">
					失业金个人缴存金额:<input value="${employeeSSTransactionDTO.syePersonalCash }" id="syePersonalCash" name="syePersonalCash" type="text">
					失业金总额:<input value="${employeeSSTransactionDTO.syeInsurance }" id="syeInsurance" name="syeInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>工伤金</label>
				<div class="formControls col-xs-8 col-sm-9">
					工伤金公司缴存比例:<input value="${employeeSSTransactionDTO.gshComRate }" id="gshComRate" name="gshComRate" type="text">
					工伤金公司缴存金额:<input value="${employeeSSTransactionDTO.gshComCash }" id="gshComCash" name="gshComCash" type="text">
					工伤金个人缴存比例:<input value="${employeeSSTransactionDTO.gshPersonalRate }" id="gshPersonalRate" name="gshPersonalRate" type="text">
					工伤金个人缴存金额:<input value="${employeeSSTransactionDTO.gshPersonalCash }" id="gshPersonalCash" name="gshPersonalCash" type="text">
					工伤金总额:<input value="${employeeSSTransactionDTO.gshInsurance }" id="gshInsurance" name="gshInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>生育金</label>
				<div class="formControls col-xs-8 col-sm-9">
					生育金公司缴存比例:<input value="${employeeSSTransactionDTO.syuComRate }" id="syuComRate" name="syuComRate" type="text">
					生育金公司缴存金额:<input value="${employeeSSTransactionDTO.syuComCash }" id="syuComCash" name="syuComCash" type="text">
					生育金个人缴存比例:<input value="${employeeSSTransactionDTO.syuPersonalRate }" id="syuPersonalRate" name="syuPersonalRate" type="text">
					生育金个人缴存金额:<input value="${employeeSSTransactionDTO.syuPersonalCash }" id="syuPersonalCash" name="syuPersonalCash" type="text">
					生育金总额:<input value="${employeeSSTransactionDTO.syuInsurance }" id="syuInsurance" name="syuInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>医疗金</label>
				<div class="formControls col-xs-8 col-sm-9">
					医疗金公司缴存比例:<input value="${employeeSSTransactionDTO.yliaoComRate }" id="yliaoComRate" name="yliaoComRate" type="text">
					医疗金公司缴存金额:<input value="${employeeSSTransactionDTO.yliaoComCash }" id="yliaoComCash" name="yliaoComCash" type="text">
					医疗金个人缴存比例:<input value="${employeeSSTransactionDTO.yliaoPersonalRate }" id="yliaoPersonalRate" name="yliaoPersonalRate" type="text">
					医疗金个人缴存金额:<input value="${employeeSSTransactionDTO.yliaoPersonalCash }" id="yliaoPersonalCash" name="yliaoPersonalCash" type="text">
					医疗金总额:<input value="${employeeSSTransactionDTO.yliaoInsurance }" id="yliaoInsurance" name="yliaoInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公积金</label>
				<div class="formControls col-xs-8 col-sm-9">
					公积金公司缴存比例:<input value="${employeeSSTransactionDTO.gjjComRate }" id="gjjComRate" name="gjjComRate" type="text">
					公积金公司缴存金额:<input value="${employeeSSTransactionDTO.gjjComCash }" id="gjjComCash" name="gjjComCash" type="text">
					公积金个人缴存比例:<input value="${employeeSSTransactionDTO.gjjPersonalRate }" id="gjjPersonalRate" name="gjjPersonalRate" type="text">
					公积金个人缴存金额:<input value="${employeeSSTransactionDTO.gjjPersonalCash }" id="gjjPersonalCash" name="gjjPersonalCash" type="text">
					公积金补充金额(不从工资扣除):<input value="${employeeSSTransactionDTO.gjjAdd }" id="gjjAdd" name="gjjAdd" type="text">
					公积金总额:<input value="${employeeSSTransactionDTO.gjjInsurance }" id="gjjInsurance" name="gjjInsurance" type="text">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="saveAdjustSS();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
function saveAdjustSS(){
	document.forms['adjustSSTransactionForm'].submit();
}
</script>
</html>
