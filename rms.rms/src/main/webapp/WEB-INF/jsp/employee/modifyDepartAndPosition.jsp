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
	<title>员工管理-修改员工部门职位</title>

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
		<form method="post" action="<%=RmsConstant.RMS_URL%>/employee/saveDepartAndPosition.do" id="editEmployeeForm" class="form form-horizontal">
			<input  value="${employeeId }" id="employeeId" name="employeeId" type="hidden">
			<input  value="${companyId }" id="companyId" name="companyId" type="hidden">
			<input  value="${employeeDepartPosition.positionId }"  id="selectedPositionValue" name="selectedPositionValue" type="hidden">
			<input  value="${employeeDepartPosition.departmentId }"  id="selectedDepartmentValue" name="selectedDepartmentValue" type="hidden">
			
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
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>部门：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="departmentId" id="departmentId" class="select" onchange="departmentChange()">
						<option value="-1">请选择公司部门</option>
						<c:forEach items="${departments }" var="item">
							<c:choose>
								<c:when test="${item.id == employeeDepartPosition.departmentId }">
									<option value="${item.id }" selected="selected">${item.departmentName }</option>
								</c:when>
								<c:otherwise>
									<option value="${item.id }">${item.departmentName }</option>
								</c:otherwise>
							</c:choose>
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
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="modifyDepartAndPosition();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script language="javascript">
function modifyDepartAndPosition(){
	document.forms['editEmployeeForm'].submit();
}

function departmentChange(){
	var val = $("#departmentId").val();
	$("#positionId").empty();
	$.ajax({
	     type: "POST",  
	     async: false,  
	     url: '<%=RmsConstant.RMS_URL%>' + '/position/getPositionByDepartment.do?departmentId='+val,  
	     dataType: "json",
	     // dataType: "jsonp",
	     //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	     //jsonpCallback:"flightHandler",//自定义的jsZzypayConstant.RMS_URL动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	     success: function(result){
			if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
				var positions = result.returnObject;
				var selectedPositionValue = $("#selectedPositionValue").val();
				for(var i=0; i<positions.length; i++){
					var pid = '' + positions[i].id;
					if(selectedPositionValue == pid ){
						$("#positionId").prepend("<option selected='selected' value='" +pid +"'>"+positions[i].positionName+"</option>");
					}else{
						$("#positionId").prepend("<option value='" +pid +"'>"+positions[i].positionName+"</option>");
					}
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

$(document).ready(function(){
	$("#departmentId").trigger("change");
});
</script>
</body>
</html>

