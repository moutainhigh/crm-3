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
   			<form method="post" action="<%=RmsConstant.RMS_URL%>/salary/company/search.do" id="employeeSubmitForm">
				<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }">
				<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
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
							<th class="ti2">员工姓名</th>
							<th class="ti2">月基本工资</th>
							<th class="ti2">月奖金</th>
							<th class="ti2">季度奖金</th>
							<th class="ti2">年度奖金</th>
							<th class="ti2">我的社保卡号</th>
							<th class="ti2">社保信息</th>
							<th class="ti2"></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items }">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.employeeId }" name="chk" id="chk_${item.employeeId }"></td>
								
								<td class="ti2"><c:out value="${item.employeeName}" /></td>
								<td class="ti2"><c:out value="${item.monthlyBaseSalary}"  /></td>
								<td class="ti2"><c:out value="${item.monthlyBonus}" /></td>
								<td class="ti2">
									<c:out value="${item.quarterlyBonus}" />
								</td>
								<td class="ti2">
									<c:out value="${item.yearlyBonus}" />
								</td>
								<td class="ti2"><c:out value="${item.ssCard}" /></td>
								<td class="ti2">
									<table>
										<tr>
											<td>社保缴纳基数:<c:out value="${item.ssBaseSalary}" /></td>
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
								</td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="adjustSalary('${item.employeeId }')" href="javascript:;" title="调整工资">调整工资</a>
									<a style="text-decoration:none" onClick="adjustSS('${item.employeeId }')" href="javascript:;" title="调整社保">调整社保</a>
									
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

function adjustSalary(employeeId){		
	var index = layer.open({
		type: 2,
		title: "调整工资",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/company/employee/adjustSalary.do?employeeId='+employeeId
	});
	layer.full(index);
}
function adjustSS(employeeId){		
	var index = layer.open({
		type: 2,
		title: "调整社保",
		content: '<%=RmsConstant.RMS_URL%>'+'/salary/company/employee/adjustSS.do?employeeId='+employeeId
	});
	layer.full(index);
}

</script>
</html>
