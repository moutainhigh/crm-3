<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>

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
	
	<title>工资管理-我的工资信息</title>

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
   		<span class="c-gray en">&gt;</span>我的工资
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
   		<div class="cl pd-5 bg-1 bk-gray ml-20">
   			<div class="cl pd-5  mt-20"> 
				<span class="l">
   					<input class="btn btn-primary radius" type="button" value="查询工资调整历史" onclick="javascript:querySalaryAdjustHistory();" />
   					<input class="btn btn-primary radius" type="button" value="查询工资发放历史" onclick="javascript:querySalaryHistory();" />
				</span>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">基本工资：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.monthlyBaseSalary }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">奖金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.monthlyBonus }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">季度奖金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.quarterlyBonus }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">年度奖金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.yearlyBonus }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">我的社保卡号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.ssCard }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">社保基数：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<c:out value="${salarySSDTO.ssBaseSalary }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">养老金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				养老金总额：<c:out value="${salarySSDTO.ylaoInsurance }"></c:out>
				公司养老金金额(比例)：<c:out value="${salarySSDTO.ylaoComCash }"></c:out>
							(<c:out value="${salarySSDTO.ylaoComRate }"></c:out>)
				个人养老金金额(比例)：<c:out value="${salarySSDTO.ylaoPersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.ylaoPersonalRate }"></c:out>)
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">失业金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				失业金总额：<c:out value="${salarySSDTO.syeInsurance }"></c:out>
				公司失业金金额(比例)：<c:out value="${salarySSDTO.syeComCash }"></c:out>
							(<c:out value="${salarySSDTO.syeComRate }"></c:out>)
				个人失业金金额(比例)：<c:out value="${salarySSDTO.syePersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.syePersonalRate }"></c:out>)
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">工伤金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				工伤金总额：<c:out value="${salarySSDTO.gshInsurance }"></c:out>
				公司工伤金金额(比例)：<c:out value="${salarySSDTO.gshComCash }"></c:out>
							(<c:out value="${salarySSDTO.gshComRate }"></c:out>)
				个人工伤金金额(比例)：<c:out value="${salarySSDTO.gshPersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.gshPersonalRate }"></c:out>)
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">生育金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				生育金总额：<c:out value="${salarySSDTO.syuInsurance }"></c:out>
				公司生育金金额(比例)：<c:out value="${salarySSDTO.syuComCash }"></c:out>
							(<c:out value="${salarySSDTO.syuComRate }"></c:out>)
				个人生育金金额(比例)：<c:out value="${salarySSDTO.syuPersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.syuPersonalRate }"></c:out>)
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">医疗金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				医疗金总额：<c:out value="${salarySSDTO.yliaoInsurance }"></c:out>
				公司医疗金金额(比例)：<c:out value="${salarySSDTO.yliaoComCash }"></c:out>
							(<c:out value="${salarySSDTO.yliaoComRate }"></c:out>)
				个人医疗金金额(比例)：<c:out value="${salarySSDTO.yliaoPersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.yliaoPersonalRate }"></c:out>)
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">公积金金：</label>
			<div class="formControls col-xs-8 col-sm-9">
				公积金金总额：<c:out value="${salarySSDTO.gjjInsurance }"></c:out>
				公司公积金金金额(比例)：<c:out value="${salarySSDTO.gjjComCash }"></c:out>
							(<c:out value="${salarySSDTO.gjjComRate }"></c:out>)
				个人公积金金金额(比例)：<c:out value="${salarySSDTO.gjjPersonalCash }"></c:out>
							(<c:out value="${salarySSDTO.gjjPersonalRate }"></c:out>)
				补充公积金:<c:out value="${salarySSDTO.gjjAdd }"></c:out>
			</div>
		</div>
		<div class="row cl ml-20 mt-10">
			<label class="form-label col-xs-4 col-sm-2">纳税情况：</label>
			<div class="formControls col-xs-8 col-sm-9">
				扣除五险一金后(月基本工资和月奖金)：<c:out value="${afterSSAmount}"></c:out>
				纳税范围：<c:out value="${individualIncomeTaxRange.min}"></c:out>-<c:out value="${individualIncomeTaxRange.max}"></c:out>
				纳税比率：<c:out value="${individualIncomeTaxRange.rate}"></c:out>
				纳税扣减数：<c:out value="${individualIncomeTaxRange.deduct}"></c:out>
				纳税金额：<c:out value="${taxAmount}"></c:out>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

function querySalaryHistory(){		
	var index = layer.open({
		type: 2,
		title: "我的工资发放历史",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/my/history.do'
	});
	layer.full(index);
}
function querySalaryAdjustHistory(){		
	var index = layer.open({
		type: 2,
		title: "我的工资调整历史",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/my/adjustHistory.do'
	});
	layer.full(index);
}

</script>
</html>
