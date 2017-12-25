<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeInfo" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeCompanyInfo" %>
<%@ page import="com.hefei.common.returncode.ReturnCode" %>

<c:set var="HUKOU_TYPE_CITY" value="<%=EmployeeInfo.HUKOU_TYPE_CITY%>"></c:set>
<c:set var="HUKOU_TYPE_RURAL" value="<%=EmployeeInfo.HUKOU_TYPE_RURAL%>"></c:set>
<c:set var="HUKOU_TYPE_FOREIGN" value="<%=EmployeeInfo.HUKOU_TYPE_FOREIGN%>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>员工管理-更新员工信息</title>

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
	<div class="page-container">
		<form method="post" action="<%=RmsConstant.RMS_URL%>/employee/edit.do" id="editEmployeeForm" class="form form-horizontal">
			<input  value="${employee.employeeId }" id="employeeId" name="employeeId" type="hidden">
			<input  value="${employee.companyId }" id="companyId" name="companyId" type="hidden">
			<input  value="${employee.hukouProvinceCode }"  id="selectedProvValue1" name="selectedProvValue1" type="hidden">
			<input  value="${employee.hukouCityCode }"  id="selectedCityValue1" name="selectedCityValue1" type="hidden">
			<input  value="${employee.hukouAreaCode }"  id="selectedAreaValue1" name="selectedAreaValue1" type="hidden">
			<input  value="${employee.liveProvinceCode }"  id="selectedProvValue" name="selectedProvValue" type="hidden">
			<input  value="${employee.liveCityCode }"  id="selectedCityValue" name="selectedCityValue" type="hidden">
			<input  value="${employee.liveAreaCode }"  id="selectedAreaValue" name="selectedAreaValue" type="hidden">
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					${company.companyName }
				</div>
			</div>
			<c:if test="${not empty errorMsg}">
				<div class="row cl">
					<div class="errorblock" id="errorblock" >
					${errorMsg }
	    			</div>
	   			</div>
   			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工身份证：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.idNo }" id="idNo" name="idNo" type="text" onblur="fillEmployeeInfo();">
					<input  value="${employee.userId }" id="userId" name="userId" type="hidden">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.name }" placeholder="" id="name" name="name" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.email }" placeholder="" id="email" name="email" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机号码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.mobile }" placeholder="" id="mobile" name="mobile" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工卡号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.cardNo }" placeholder="" id="cardNo" name="cardNo" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>性别：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="sex" id="sex" class="select">
						<c:forEach items="<%=EmployeeInfo.sexDict%>" var="item">
							<c:choose>
								<c:when test="${item.key == employee.sex }">
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
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>生日：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" name="birthdayStr" id="birthdayStr" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'2050-01-01'})" 
			            			class="input-text Wdate" style="width:120px;" value="<fmt:formatDate value="${employee.birthday }" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>户籍：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select name="hukouType" id="hukouType">
                		<option value="${HUKOU_TYPE_CITY}" <c:if test="${hukouoType ==HUKOU_TYPE_CITY }">selected="selected"</c:if>>城镇</option>
                		<option value="${HUKOU_TYPE_RURAL}" <c:if test="${hukouoType ==HUKOU_TYPE_RURAL }">selected="selected"</c:if>>农村</option>
                		<option value="${HUKOU_TYPE_FOREIGN}" <c:if test="${hukouoType ==HUKOU_TYPE_FOREIGN }">selected="selected"</c:if>>国外</option>
					</select>
					<select id="provinceCode1" name="provinceCode1" onchange="doProvAndCityRelation1();">
			  　　　　　　　　<option id="choosePro1"value="">请选择您所在省份</option>
			  　　　　　　</select>
			  		<select id="cityCode1" name="cityCode1" onchange="doCityAndCountyRelation1();">
			  　　　　　　　　<option id="chooseCity1"value="">请选择您所在城市</option>
			  　　　　　　</select>
			 		<select id="countyCode1" name="countyCode1" >
			  　　　　　　　　<option id="chooseCounty1"value="">请选择您所在区/县</option>
			  　　　　　　</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>居住地：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select id="provinceCode" name="provinceCode" onchange="doProvAndCityRelation();">
			  　　　　　　　　<option id="choosePro"value="">请选择您所在省份</option>
			  　　　　　　</select>
			  		<select id="cityCode" name="cityCode" onchange="doCityAndCountyRelation();">
			  　　　　　　　　<option id="chooseCity"value="">请选择您所在城市</option>
			  　　　　　　</select>
			 		<select id="countyCode" name="countyCode" >
			  　　　　　　　　<option id="chooseCounty"value="">请选择您所在区/县</option>
			  　　　　　　</select>
				</div>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="${employee.liveAddress }" placeholder="" id="liveAddress" name="liveAddress" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>在职状态：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="status" id="status" class="select">
						<c:forEach items="<%=EmployeeCompanyInfo.employeeStatusDict%>" var="item">
							<c:choose>
								<c:when test="${item.key == employee.status }">
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
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>上级领导：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="superiorEmployeeName" name="superiorEmployeeName" type="hidden" value="${employee.superiorEmployeeName }">
					<span class="select-box" style="width:150px;">
					<select name="superior" id="superior" class="select">
						<option value="">无</option>
						<c:if test="${not empty  superviorList}">
							<c:forEach items="${superviorList }" var="item">
								<c:choose>
									<c:when test="${item.employeeId == employee.superior }">
										<option value="${item.employeeId }" selected="selected">${item.name }(卡号:${item.cardNo})</option>
									</c:when>
									<c:otherwise>
										<option value="${item.employeeId }">${item.name }(卡号:${item.cardNo})</option>
									</c:otherwise>
								</c:choose>
	                		</c:forEach>
						</c:if>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="editEmployee();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/industry_func.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/address/address.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/address/address1.js"></script>
<script language="javascript">
function editEmployee(){
	var superiorObj = $("#superior").find("option:selected");
	if(superiorObj){
		var txt = $("#superior").find("option:selected").text();
		if(txt){
			txt = txt.split("(")[0];
			$("#superiorEmployeeName").val(txt);
		}
	}
	
	var checkResult1 = checkEmailBind();
	if(checkResult1){
		checkResult1 = checkMobileBind();
	}
	if(checkResult1){
		document.forms['editEmployeeForm'].submit();
	}
}

function checkMobileBind(){
	var idNo = $("#idNo").val();
	if(idNo == ''){
		alert("请填写身份证号");
		return false;
	}
	var userId = $("#userId").val();
	var mobile = $("#mobile").val();
	if(userId == '' || mobile == ''){
		return true;
	}
	var checkResult = false;
	$.ajax({
	     type: "POST",  
	     async: false,
	     url: '<%=RmsConstant.USER_URL%>' + '/mobile/checkMobileLoginBind.do?userId='+userId + "&mobile="+mobile,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				checkResult = true;
			}else{
				alert("此手机号码被绑定到了其他用户");
			}
	     },
	     error: function(){  
	         alert("手机号码检查失败");  
	     }  
	 });
	return checkResult;
}
function checkEmailBind(){
	var idNo = $("#idNo").val();
	if(idNo == ''){
		alert("请填写身份证号");
		return false;
	}
	var userId = $("#userId").val();
	var email = $("#email").val();
	if(userId == '' || email == ''){
		return true;
	}
	var checkResult = false;
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.USER_URL%>' + '/email/checkEmailLoginBind.do?userId='+userId + "&email="+email,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				checkResult= true;
			}else{
				alert("此邮箱被绑定到了其他用户");
			}
	     },
	     error: function(){  
	         alert("邮箱检查失败");  
	     }  
	 });
	return checkResult;
}
function fillEmployeeInfo(){
	var idNo = $("#idNo").val();
	if(idNo != ''){
		$.ajax({
		     type: "POST",  
		     async: false,  
		     url: '<%=RmsConstant.RMS_URL%>' + '/employee/getInfoByIdNo.do?idNo='+idNo,  
		     dataType: "json",
		     // dataType: "jsonp",
		     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
		     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
		     success: function(result){
				if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
					var info = result.returnObject;
					$("#name").val(info.name);
					$("#mobile").val(info.mobile);
					$("#email").val(info.email);
					$("#sex").val(info.sex);
					$("#userId").val(info.userId);
				}else if(result.returnCode==<%=ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS%> ){
					$("#userId").val('');
				}else{
					$("#userId").val('');
				}
		     },
		     error: function(){  
		         alert("fail");  
		     }  
		 });
	}
}
$(document).ready(function(){
	initLocationSelected();
	initLocationSelected1();
	
	$("#departmentId").trigger("change");
});
</script>
</body>
</html>

