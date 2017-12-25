<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeInfo" %>
<%@ page import="com.hefei.api.rms.employee.vo.EmployeeCompanyInfo" %>
<%@ page import="com.hefei.common.returncode.ReturnCode" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>员工管理-增加部门信息</title>

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
	<div class="page-container">
		<form method="post" action="<%=RmsConstant.RMS_URL%>/department/add.do" id="createDepartmentForm" class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					${company.companyName }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>父级部门</label>
				<div class="formControls col-xs-8 col-sm-9">
					<c:if test="${empty parentDepartment }">
						顶级菜单
					</c:if>
					<c:if test="${not empty parentDepartment }">
						<input type="hidden" value="${parentDepartment.id}" id="parentId" name="parentId">
						${parentDepartment.departmentName}
					</c:if>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>部门名称</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="" id="departmentName" name="departmentName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>备注</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea id="departmentInfo" name="departmentInfo" rows="3" cols="80"></textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="addDepartment();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script language="javascript">
function addDepartment(){
	document.forms['createDepartmentForm'].submit();
}
</script>
</body>
</html>

