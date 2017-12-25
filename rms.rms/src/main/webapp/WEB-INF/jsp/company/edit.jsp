<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.company.vo.CompanyInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>公司管理-更新公司信息</title>

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
	<!-- 
	<nav class="breadcrumb">
   		<i class="Hui-iconfont">&#xe67f;</i> 首页
   		<span class="c-gray en">&gt;</span> 公司管理
   		<span class="c-gray en">&gt;</span> 新增公司
   		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
   	</nav>
   	 -->
    <div class="page-container">
		<form method="post" action="<%=RmsConstant.RMS_URL%>/company/edit.do" id="editCompanyForm" class="form form-horizontal">
			<input  value="${company.id }"  id="companyId" name="companyId" type="hidden">
			<input  value="${company.provinceCode }"  id="selectedProvValue" name="selectedProvValue" type="hidden">
			<input  value="${company.cityCode }"  id="selectedCityValue" name="selectedCityValue" type="hidden">
			<input  value="${company.countyCode }"  id="selectedAreaValue" name="selectedAreaValue" type="hidden">
			<c:if test="${not empty msg }">
				<div class="row cl">
					<div class="errorblock" id="errorblock" >${msg }</div>
				</div>
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.companyName }" placeholder="" id="companyName" name="companyName" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>行业：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" id="btn_IndustryID" type="button" value="请选择行业" onclick="IndustrySelect()" />
					<input id="industryIds" type="hidden" name="industryIds" value="${industryIds}" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>增值税类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
					<select name="taxType" id="taxType" class="select">
						<c:forEach items="<%=CompanyInfo.taxTypeDict%>" var="item">
							<c:choose>
								<c:when test="${item.key == company.taxType }">
									<option value="${item.key }" selected="selected">${item.value }</option>
								</c:when>
								<c:otherwise>
									<option value="${item.key }">${item.value }</option>
								</c:otherwise>
							</c:choose>
                			
                		</c:forEach>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司地址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select id="provinceCode" name="provinceCode" onchange="doProvAndCityRelation();">
			  　　　　　　　　<option id="choosePro" value="">请选择您所在省份</option>
			  　　　　　　</select>
			  		<select id="cityCode" name="cityCode" onchange="doCityAndCountyRelation();">
			  　　　　　　　　<option id="chooseCity"value="">请选择您所在城市</option>
			  　　　　　　</select>
			 		<select id="countyCode" name="countyCode" >
			  　　　　　　　　<option id="chooseCounty"value="">请选择您所在区/县</option>
			  　　　　　　</select>
				</div>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.companyAddress }" placeholder="" id="companyAddress" name="companyAddress" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司电话：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.companyTel }" placeholder="" id="companyTel" name="companyTel" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>法人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.legalPerson }" placeholder="" id="legalPerson" name="legalPerson" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司规模：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
					<select name="companySize" id="companySize" class="select">
						<c:forEach items="<%=CompanyInfo.companySizeDict%>" var="item">  
							<c:choose>
								<c:when test="${item.key == company.companySize }">
									<option value="${item.key }" selected="selected">${item.value }</option>
								</c:when>
								<c:otherwise>
									<option value="${item.key }">${item.value }</option>
								</c:otherwise>
							</c:choose>
                		</c:forEach>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
					<select name="companyType" id="companyType" class="select">
						<c:forEach items="<%=CompanyInfo.companyTypeDict%>" var="item">  
							<c:choose>
								<c:when test="${item.key == company.companyType }">
									<option value="${item.key }" selected="selected">${item.value }</option>
								</c:when>
								<c:otherwise>
									<option value="${item.key }">${item.value }</option>
								</c:otherwise>
							</c:choose>
                		</c:forEach>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>社会信用码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.usccCode }" placeholder="" id="usccCode" name="usccCode" type="text">
				</div>
			</div>
			<!-- 
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>营业执照上传：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${company.businessLicensePic }" placeholder="" id="businessLicensePic" name="businessLicensePic" type="text">
				</div>
			</div>
			 -->
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司基本信息：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea rows="10" cols="120" id="companyInfo" name="companyInfo">${company.companyInfo }</textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="editCompanyForm();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../common/panel-pop.jsp"></jsp:include>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/industry_func.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/address/address.js"></script>
<script language="javascript">
function editCompanyForm(){
	document.forms['editCompanyForm'].submit();
}

initIndustryCheck($("#industryIds").val());

$(document).ready(function(){
	initLocationSelected();
});
</script>
</body>
</html>

