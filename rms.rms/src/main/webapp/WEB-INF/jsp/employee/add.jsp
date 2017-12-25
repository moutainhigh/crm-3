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
	<title>员工管理-增加员工信息</title>

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
		<form method="post" action="<%=RmsConstant.RMS_URL%>/employee/add.do" id="createEmployeeForm" class="form form-horizontal">
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
					<input class="input-text" value="" placeholder="" id="idNo" name="idNo" type="text" onblur="fillEmployeeInfo();">
					<input  value="" id="userId" name="userId" type="hidden">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="" placeholder="" id="name" name="name" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="" placeholder="" id="email" name="email" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机号码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="" placeholder="" id="mobile" name="mobile" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>员工卡号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input class="input-text" value="" placeholder="" id="cardNo" name="cardNo" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>性别：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="sex" id="sex" class="select">
						<c:forEach items="<%=EmployeeInfo.sexDict%>" var="item">  
                			<option value="${item.key }">${item.value }</option>
                		</c:forEach>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>生日：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" name="birthdayStr" id="birthdayStr" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'2050-01-01'})" 
			            			class="input-text Wdate" style="width:120px;">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>户籍：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<select name="hukouType" id="hukouType">
                		<option value="${HUKOU_TYPE_CITY}">城镇</option>
                		<option value="${HUKOU_TYPE_RURAL}">农村</option>
                		<option value="${HUKOU_TYPE_FOREIGN}">国外</option>
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
					<input class="input-text" value="" placeholder="" id="liveAddress" name="liveAddress" type="text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>在职状态：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="status" id="status" class="select">
						<c:forEach items="<%=EmployeeCompanyInfo.employeeStatusDict%>" var="item">  
                			<option value="${item.key }">${item.value }</option>
                		</c:forEach>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>上级领导：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="superiorEmployeeName" name="superiorEmployeeName" type="hidden">
					<span class="select-box" style="width:150px;">
					<select name="superior" id="superior" class="select">
						<option value="">无</option>
						<c:if test="${not empty  superviorList}">
							<c:forEach items="${superviorList }" var="item">  
	                			<option value="${item.employeeId }">${item.name }(卡号:${item.cardNo})</option>
	                		</c:forEach>
		                </c:if>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>部门：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="departmentId" id="departmentId" class="select" onchange="departmentChange()">
						<option value="-1">请选择公司部门</option>
						<c:forEach items="${departments }" var="item">  
                			<option value="${item.id }">${item.departmentName }</option>
                		</c:forEach>
					</select>
					</span>
					<button onclick="openAddDepartment();" class="btn btn-primary radius" type="button">添加部门</button>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>职位：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="positionId" id="positionId" class="select">
					</select>
					</span>
					<button onclick="openAddDepartment();" class="btn btn-primary radius" type="button">添加职位</button>
				</div>
				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>工资：</label>
				<div class="formControls col-xs-8 col-sm-9">
					月基本工资:<input value="0" placeholder="" id="monthlyBaseSalary" name="monthlyBaseSalary" type="text">
					月奖金:<input value="0" placeholder="" id="monthlyBonus" name="monthlyBonus" type="text">
					季度奖金:<input value="0" placeholder="" id="quarterlyBonus" name="quarterlyBonus" type="text">
					年奖金:<input value="0" placeholder="" id="yearlyBonus" name="yearlyBonus" type="text">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="addEmployee();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/js/base/panel-pop/industry_func.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/js/base/address/address.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/js/base/address/address1.js"></script>
<script language="javascript">
function addEmployee(){
	var superiorObj = $("#superior").find("option:selected");
	if(superiorObj){
		var txt = $("#superior").find("option:selected").text();
		if(txt){
			txt = txt.split("(")[0];
			$("#superiorEmployeeName").val(txt);
		}
	}
	var checkResult = checkEmailBind();
	if(checkResult){
		checkResult = checkMobileBind();
	}
	if(checkResult){
		document.forms['createEmployeeForm'].submit();
	}
}

function departmentChange(){
	var val = $("#departmentId").val();
	$("#positionId").empty();
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL %>' + '/position/getPositionByDepartment.do?departmentId='+val,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				var positions = result.returnObject;
				for(var i=0; i<positions.length; i++){
					$("#positionId").prepend("<option value='" +positions[i].id +"'>"+positions[i].positionName+"</option>");
				}
			}
	     },
	     error: function(){  
	         alert("fail");  
	     }  
	 });
}
function openAddDepartment(){
	var index = layer.open({
		type: 2,
		title: "",
		content: '<%=RmsConstant.RMS_URL %>'+'/department/index.do'
	});
	layer.full(index);
}
function checkMobileBind(){
	var idNo = $("#idNo").val();
	if(idNo == ''){
		alert("请填写身份证号");
		return true;
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
	     url: '<%=RmsConstant.USER_URL %>' + '/mobile/checkMobileLoginBind.do?userId='+userId + "&mobile="+mobile,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				 checkResult =  true;
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
		return true;
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
	     url: '<%=RmsConstant.USER_URL %>' + '/email/checkEmailLoginBind.do?userId='+userId + "&email="+email,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				checkResult = true;
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
		     url: '<%=RmsConstant.RMS_URL %>' + '/employee/getInfoByIdNo.do?idNo='+idNo,  
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
</script>
</body>
</html>

