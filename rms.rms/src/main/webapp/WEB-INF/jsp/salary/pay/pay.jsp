<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.common.returncode.ReturnCode" %>
<%@ page import="com.hefei.api.rms.salary.dto.SalaryTransactionDTO" %>
<%@ page import="com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeInfo" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeCompanyInfo" %>

<c:set var="STATUS_PRACTICE" value="<%=EmployeeCompanyInfo.STATUS_PRACTICE%>"></c:set>
<c:set var="STATUS_PROBATION" value="<%=EmployeeCompanyInfo.STATUS_PROBATION%>"></c:set>
<c:set var="STATUS_ONBOARD" value="<%=EmployeeCompanyInfo.STATUS_ONBOARD%>"></c:set>
<c:set var="STATUS_LEAVE" value="<%=EmployeeCompanyInfo.STATUS_LEAVE%>"></c:set>
<c:set var="STATUS_RETIRE" value="<%=EmployeeCompanyInfo.STATUS_RETIRE%>"></c:set>

<c:set var="STATUS_TO_PAY" value="<%=SalaryTransactionDTO.STATUS_TO_PAY%>"></c:set>
<c:set var="STATUS_PAYED" value="<%=SalaryTransactionDTO.STATUS_PAYED%>"></c:set>
<c:set var="SS_STATUS_TO_PAY" value="<%=EmployeeSSTransactionDTO.STATUS_TO_PAY%>"></c:set>
<c:set var="SS_STATUS_PAYED" value="<%=EmployeeSSTransactionDTO.STATUS_PAYED%>"></c:set>

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
	
	<title>工资管理-工资发放信息</title>

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
   		<span class="c-gray en">&gt;</span>工资发放
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray ml-20">
			<form method="post" action="<%=RmsConstant.RMS_URL%>/salary/pay/search.do" id="salayPaySubmitForm">
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
			          	 <li>
			            	<label class="tjname fw7">员工状态：</label>
			            	<input type="checkbox" id="employeeStatus1" name="employeeStatus" value="<%=EmployeeCompanyInfo.STATUS_PRACTICE%>" checked>实习
			            	<input type="checkbox" id="employeeStatus2" name="employeeStatus" value="<%=EmployeeCompanyInfo.STATUS_PROBATION%>" checked>试用
			            	<input type="checkbox" id="employeeStatus3" name="employeeStatus" value="<%=EmployeeCompanyInfo.STATUS_ONBOARD%>" checked>在职
			            	<input type="checkbox" id="employeeStatus4" name="employeeStatus" value="<%=EmployeeCompanyInfo.STATUS_LEAVE%>" >离职
			            	<input type="checkbox" id="employeeStatus5" name="employeeStatus" value="<%=EmployeeCompanyInfo.STATUS_RETIRE%>" >退休
			          	</li>
					</ul>
				</div>
				<div class="row cl">
					<ul class="inline1">
						<li>
			            	<label class="tjname fw7">工资发放状态：</label>
			            	<input type="checkbox" id="salaryPayStatus1" name="salaryPayStatus" value="<%=SalaryTransactionDTO.STATUS_TO_PAY%>" checked>未发
			            	<input type="checkbox" id="salaryPayStatus2" name="salaryPayStatus" value="<%=SalaryTransactionDTO.STATUS_PAYED%>" >已发
			          	</li>
			          	<li>
			            	<label class="tjname fw7">社保发放状态：</label>
			            	<input type="checkbox" id="ssPayStatus1" name="ssPayStatus" value="<%=EmployeeSSTransactionDTO.STATUS_TO_PAY%>" checked>未发
			            	<input type="checkbox" id="ssPayStatus2" name="ssPayStatus" value="<%=EmployeeSSTransactionDTO.STATUS_PAYED%>" >已发
			          	</li>
					</ul>
				</div>
				 <div class="cl pd-5 mt-20"> 
					<span class="l">
						<input class="btn btn-primary radius" type="submit"  value="查   询"/>
						<input class="btn btn-primary radius" type="button" onclick="generatePayInfo()" value="选择员工状态生成工资发放"/>
						<input class="btn btn-primary radius" type="button" onclick="paySalary()" value="发工资"/>
						<input class="btn btn-primary radius" type="button" onclick="paySS()" value="发社保"/>
					</span>
				</div>
			</form>
		</div>
		 <div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 40px;" aria-label="" width="40">
							<input name="checkboxTh" value="" type="checkbox">
						</th>
						<th width="ti2">月基本工资</th>
						<th class="ti2">奖金</th>
						<th class="ti2">扣减金额</th>
						<th class="ti2">个税金额(个税比例)</th>
						<th class="ti2">社保总金额</th>
						<th class="ti2">实际发放金额</th>
						<th class="ti2">是否已发放</th>
						<th class="ti2">发放时间</th>
						<th class="ti2"></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty pagination.items }">
						<c:forEach var="item" items="${ pagination.items}" varStatus="status">
						<tr class="text-c">
							<td>
								<input type="checkbox" value="${item.id }" name="checkboxTd" id="chk_${item.id }">
							</td>
							<td><c:out value="${item.monthlyBaseSalary}" /></td>
							<td class="ti2">
								月度奖金:<c:out value="${item.monthlyBonus}" /><br/>
								季度奖金:<c:out value="${item.quarterlyBonus}" /><br/>
								年度奖金:<c:out value="${item.yearlyBonus}" /><br/>
							</td>
							<td class="ti2"><c:out value="${item.deductAmount}"  /></td>
							<td class="ti2">
								<c:out value="${item.taxAmount}" />(<c:out value="${item.taxRate}" />)
							</td>
							<td class="ti2" id="${item.ssId }">
								<c:out value="${item.ssAmount}" />
								<div>
									<table>
										<tr>
											<td>社保缴纳基数:<c:out value="${item.ssBaseSalary}" />
											&nbsp;&nbsp;
											是否发放:<c:if test="${item.ssStatus eq SS_STATUS_TO_PAY}">未发</c:if>
												  <c:if test="${item.ssStatus eq SS_STATUS_PAYED}">已发</c:if>
											&nbsp;&nbsp;
											发放时间：<fmt:formatDate value="${item.ssPayedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
										</tr>
										<tr>
											<td>
												养老金总额:<c:out value="${item.ylaoInsurance}" />
												养老金公司金额(比率):<c:out value="${item.ylaoComCash}" />(<c:out value="${item.ylaoComRate}" />)
												养老金个人金额(比率):<c:out value="${item.ylaoPersonalRate}" />(<c:out value="${item.ylaoPersonalCash}" />)
											</td>
										</tr>
										<tr>
											<td>
												医疗金总额:<c:out value="${item.yliaoInsurance}" />
												医疗金公司金额(比率):<c:out value="${item.yliaoComCash}" />(<c:out value="${item.yliaoComRate}" />)
												医疗金个人金额(比率):<c:out value="${item.yliaoPersonalRate}" />(<c:out value="${item.yliaoPersonalCash}" />)
											</td>
										</tr>
										<tr>
											<td>
												工伤金总额:<c:out value="${item.gshInsurance}" />
												工伤金公司金额(比率):<c:out value="${item.gshComCash}" />(<c:out value="${item.gshComRate}" />)
												工伤金个人金额(比率):<c:out value="${item.gshPersonalRate}" />(<c:out value="${item.gshPersonalCash}" />)
											</td>
										</tr>
										<tr>
											<td>
												失业金总额:<c:out value="${item.syeInsurance}" />
												失业金公司金额(比率):<c:out value="${item.syeComCash}" />(<c:out value="${item.syeComRate}" />)
												失业金个人金额(比率):<c:out value="${item.syePersonalRate}" />(<c:out value="${item.syePersonalCash}" />)
											</td>
										</tr>
										<tr>
											<td>
												生育金总额:<c:out value="${item.syuInsurance}" />
												生育金公司金额(比率):<c:out value="${item.syuComCash}" />(<c:out value="${item.syuComRate}" />)
												生育金个人金额(比率):<c:out value="${item.syuPersonalRate}" />(<c:out value="${item.syuPersonalCash}" />)
											</td>
										</tr>
										<tr>
											<td>
												公积金金总额:<c:out value="${item.gjjInsurance}" />
												公积金金公司金额(比率):<c:out value="${item.gjjComCash}" />(<c:out value="${item.gjjComRate}" />)
												公积金金个人金额(比率):<c:out value="${item.gjjPersonalRate}" />(<c:out value="${item.gjjPersonalCash}" />)
												公积金补充:<c:out value="${item.gjjAdd}" />
											</td>
										</tr>
									</table>
								</div>
							</td>
							<td class="ti2">
								<c:out value="${item.payedAmount}" />
							</td>
							<td class="ti2">
								<c:if test="${item.status eq STATUS_TO_PAY}">未发</c:if>
								<c:if test="${item.status eq STATUS_PAYED}">已发</c:if>
							</td>
							<td class="ti2"><fmt:formatDate value="${item.payedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="adjustSalaryTransaction('${item.id }')" href="javascript:;" title="授权">调整本次工资</a>
									<a style="text-decoration:none" onClick="adjustSSTransaction('${item.ssId }')" href="javascript:;" title="详情">调整本次社保</a>
								</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<jsp:include page="../../common/pagination.jsp"></jsp:include>
			</table>
		</div>
	</div>

<script type="text/javascript">
function submitForm(obj, pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#salayPaySubmitForm").submit();
}

function generatePayInfo(){
	var employeeStatus='';
	$("input[name='employeeStatus']:checkbox:checked").each(function(){
		employeeStatus = employeeStatus+$(this).val() + ",";
	});
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' + '/salary/pay/generatePayInfo.do?employeeStatus='+employeeStatus,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				alert("生成成功，请查询需要发放工资和社保");  
			}else{
				alert("生成失败，请稍后重试");  
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
function paySalary(){	
	var transactionIds = getCheckboxTd();
	alert(transactionIds);
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' + '/salary/pay/paySalary.do?transactionIds='+transactionIds,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				alert("工资发放成功");  
			}else{
				alert("工资发放失败，请稍后重试");  
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
function paySS(){	
	var transactionIds = getCheckboxTd();
	alert(transactionIds);
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' + '/salary/pay/paySS.do?transactionIds='+transactionIds,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				alert("工资发放成功");  
			}else{
				alert("工资发放失败，请稍后重试");  
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
function getCheckboxTd(){
	var employeeIds='';
	$("input[name='checkboxTd']:checkbox:checked").each(function(){
		employeeIds = employeeIds+$(this).val() + ",";
	});
	return employeeIds;
}

function adjustSalaryTransaction(transactionId){
	var index = layer.open({
		type: 2,
		title: "调整本次工资发放",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/adjustSalaryTransaction/toAdjustSalary.do?transactionId='+transactionId
	});
	layer.full(index);
}
function adjustSSTransaction(ssTransactionId){
	var index = layer.open({
		type: 2,
		title: "调整本次社保发放",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/adjustSSTransaction/toAdjustSS.do?ssTransactionId='+ssTransactionId
	});
	layer.full(index);
}
</script>
</body>
</html>
