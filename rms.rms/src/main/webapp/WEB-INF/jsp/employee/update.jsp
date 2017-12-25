<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理-修改员工信息</title>

</head>
<body>
	<div class="mainInner">
		<ul class="crumbs">
			<li><a href="#" class="score-0">人事管理系统</a><span class="direct">&gt;</span></li>
			<li><a href="#">员工管理</a><span class="direct">&gt;</span></li>
			<li>修改员工</li>
		</ul>
		<div>
			<h3 class="Hs mb20 hsred">
				<span> 基本信息  </span>
			</h3>
		</div> 

		<form id="doSubmit" action="<%=RmsConstant.RMS_URL%>/employee/update.do" method="post" name="empFrom">
			<input id="id" name="id" type="hidden" value="${employee.id}"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableform">
				<tr>
					<td width="15%" style="white-space: nowrap;">员工姓名<font color='red'>*</font></td>
					<td width="20%">
						<input type="text" name="name" id="name" value="${employee.name}" maxlength="25" class="w200" />
					</td>
				</tr>
				
				<tr>
					<td width="15%" style="white-space: nowrap;">公司编号<font color='red'>*</font></td>
					<td width="20%">
						<input type="text" name="companyId" id="companyId" value="${employee.companyId}" maxlength="25" class="w200" />
					</td>
				</tr>
				
				<tr>
					<td width="15%" style="white-space: nowrap;">员工编号<font color='red'>*</font></td>
					<td width="20%">
						<input type="text" name="number" id="number" value="${employee.number}" maxlength="25" class="w200" />
					</td>
				</tr>
				
				<tr>
					<td width="15%" style="white-space: nowrap;">性别<font color='red'>*</font></td>
					<td width="20%">
						<input type=radio name=sex id=sex1 value=1 <c:if test="${fn:contains(employee.sex,'1')}">checked</c:if>>男  
						<input type=radio name=sex id=sex2 value=2 <c:if test="${fn:contains(employee.sex,'2')}">checked</c:if>>女<br>
				</tr>
				
				<tr>
					<td width="15%" style="white-space: nowrap;">状态<font color='red'>*</font></td>
					<td width="20%">
						<input type=radio name=status id=status1 value=0 <c:if test="${fn:contains(employee.status,'0')}">checked</c:if>>实习
						<input type=radio name=status id=status2 value=1 <c:if test="${fn:contains(employee.status,'1')}">checked</c:if>>试用
						<input type=radio name=status id=status3 value=2 <c:if test="${fn:contains(employee.status,'2')}">checked</c:if>>在职
						<input type=radio name=status id=status4 value=3 <c:if test="${fn:contains(employee.status,'3')}">checked</c:if>>离职
						<input type=radio name=status id=status5 value=4 <c:if test="${fn:contains(employee.status,'4')}">checked</c:if>>退休<br>
					</td>
				</tr>
			</table>
			
			<dl class="colunm c3T4D8 cdg dl-tar colunmbotton mt15">
		    <dt></dt>
		    <dd style="padding-left: 10px;">
		    	<input class="btn buttonbgorange" type="button" id="btnCreate" onclick="checkForm();" value="修     改"/>
		    </dd>
		 </dl>
		</form>
		<div>
			<c:choose>
			<c:when test="${!empty errMessage}">
				<h3 class="Hs mb20 hsred">
					<span> 创建结果  </span>
				</h3>
					<span class="msg">${errMessage}</span>
			</c:when>
			</c:choose>
		</div>

	</div>

<script language="javascript">

function checkName(id){
	return true;
}

function checkForm(){
	var checkFlag = true;
	if(!checkName('trueName')) checkFlag=false;
	if(checkFlag){
		document.forms['empFrom'].submit();
    }else{
    	jQuery("#btnCreate").removeAttr("disabled");
    }
}
	
</script>	
	
</body>
</html>