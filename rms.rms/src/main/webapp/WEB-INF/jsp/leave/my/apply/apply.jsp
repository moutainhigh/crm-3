<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.common.returncode.ReturnCode" %>
<%@ page import="com.hefei.api.rms.leave.dto.LeaveDTO" %>

<c:set var="TYPE_PRIVATE" value="<%=LeaveDTO.TYPE_PRIVATE%>"></c:set>
<c:set var="TYPE_SICK" value="<%=LeaveDTO.TYPE_SICK%>"></c:set>
<c:set var="TYPE_ANNUAL" value="<%=LeaveDTO.TYPE_ANNUAL%>"></c:set>
<c:set var="TYPE_OFF" value="<%=LeaveDTO.TYPE_OFF%>"></c:set>
<c:set var="TYPE_FUNERAL" value="<%=LeaveDTO.TYPE_FUNERAL%>"></c:set>
<c:set var="TYPE_MATERNITY" value="<%=LeaveDTO.TYPE_MATERNITY%>"></c:set>
<c:set var="TYPE_MARRIAGE" value="<%=LeaveDTO.TYPE_MARRIAGE%>"></c:set>
<c:set var="TYPE_PATERNITY" value="<%=LeaveDTO.TYPE_PATERNITY%>"></c:set>
<c:set var="TYPE_OTHER" value="<%=LeaveDTO.TYPE_OTHER%>"></c:set>
<c:set var="AUDIT_STATUS_TODO" value="<%=LeaveDTO.AUDIT_STATUS_TODO%>"></c:set>
<c:set var="AUDIT_STATUS_PASS" value="<%=LeaveDTO.AUDIT_STATUS_PASS%>"></c:set>
<c:set var="AUDIT_STATUS_REJECT" value="<%=LeaveDTO.AUDIT_STATUS_REJECT%>"></c:set>
<c:set var="DEL_FLAG_NO" value="<%=LeaveDTO.DEL_FLAG_NO%>"></c:set>
<c:set var="DEL_FLAG_YES" value="<%=LeaveDTO.DEL_FLAG_YES%>"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>假期管理-请假</title>

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
   		<i class="Hui-iconfont">&#xe67f;</i>假期管理
   		<span class="c-gray en">&gt;</span>请假
   	</nav>
	<div class="page-container">
		<form method="post" action="<%=RmsConstant.RMS_URL%>/leave/myapply/add.do" id="applyLeaveForm" class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司</label>
				<div class="formControls col-xs-8 col-sm-9">
					${companyName }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>假期类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width:150px;">
					<select name="type" id="type" class="select">
						<option value="${TYPE_PRIVATE }">事假</option>
						<option value="${TYPE_SICK }" >病假</option>
						<option value="${TYPE_ANNUAL }" >年假</option>
						<option value="${TYPE_OFF }" >调休</option>
						<option value="${TYPE_FUNERAL }" >丧假</option>
						<option value="${TYPE_MATERNITY }" >产假 </option>
						<option value="${TYPE_MARRIAGE }" >婚假</option>
						<option value="${TYPE_PATERNITY }" >陪产假</option>
						<option value="${TYPE_OTHER }" >其他</option>
					</select>
					</span> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>请假时间：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" name="startTimeStr" id="startTimeStr" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH',minDate:'2016-01-01',maxDate:'2050-01-01'})" 
            			class="input-text Wdate" style="width:120px;">
						-
					<input type="text" name="endTimeStr" id="endTimeStr" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH',minDate:'2016-01-01',maxDate:'2050-01-01'})" 
						class="input-text Wdate" style="width:120px;">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>时间总计</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input value="" id="totalTimeDay" name="totalTimeDay" type="text">天
					<input value="" id="totalTimeHour" name="totalTimeHour" type="text">小时
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>事由：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea rows="5" cols="100" name="content"></textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onclick="applyLeave();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存</button>
					<button onclick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script language="javascript">
function applyLeave(){
	document.forms['applyLeaveForm'].submit();
}
</script>
</body>
</html>

