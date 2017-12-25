<%@page import="com.hefei.admin.constants.AdminConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@page import="com.hefei.api.cas.module.vo.ModuleInfo"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="<%=AdminConstants.STAIC_SERVER_URL %>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=AdminConstants.STAIC_SERVER_URL %>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
    <title>添加系统</title>

    <!-- Le styles -->
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/style.css" />

    <script src="<%=AdminConstants.STAIC_SERVER_URL %>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=AdminConstants.STAIC_SERVER_URL %>/js/bootstrap.min.js"></script>
    <script src="<%=AdminConstants.STAIC_SERVER_URL %>/js/jquery.validate.js"></script>

  </head>

  <body>
	<article class="page-container">
	    <div class="errorblock" id="errorblock" style="display:none;">
	    </div>
	    <c:set var="TYPE_MENU"><%=ModuleInfo.TYPE_MENU %></c:set>
		<c:set var="TYPE_BUTTON"><%=ModuleInfo.TYPE_BUTTON %></c:set>
    	<form action="<%=AdminConstants.ADMIN_SERVER_URL %>/cas/module/create.do" method="post" class="form form-horizontal" id="moduleForm">
    		<input type="hidden" value="${module.id}" id="id" name="id">
		  	<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属系统</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="hidden" value="${system.id}" id="systemId" name="systemId">
					${system.systemName}
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>父菜单</label>
				<div class="formControls col-xs-8 col-sm-9">
					<c:if test="${empty parentModule }">
						顶级菜单
					</c:if>
					<c:if test="${not empty parentModule }">
						<input type="hidden" value="${parentModule.id}" id="parentId" name="parentId">
						${parentModule.name}
					</c:if>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="hidden" value="${type}" id="type" name="type">
					<c:if test="${TYPE_MENU == type}">菜单</c:if>
					<c:if test="${TYPE_BUTTON == type}">功能键</c:if>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>名称</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input  type="text" class="input-text" value="" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>路径</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  value="" id="url" name="url">
				</div>
			</div>
			<c:if test="${TYPE_MENU == type}">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>级别</label>
					<div class="formControls col-xs-8 col-sm-9">
						
						<c:if test="${empty parentModule }">
							<input type="text" class="input-text"  value="0" id="rank" name="rank">
						</c:if>
						<c:if test="${not empty parentModule }">
							<input type="text" class="input-text"  value="${parentModule.rank +1 }" id="rank" name="rank">
						</c:if>
					</div>
				</div>
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图标路径</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  value="" id="iconPath" name="iconPath">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>显示顺序</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"  value="" id="orders" name="orders">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>备注</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea id="remark" name="remark" rows="3" cols="20"></textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<button type="submmit" class="btn btn-success radius" id="module-create" name="module-save"><i class="icon-ok"></i> 确定</button>
				</div>
			</div>
		</form> 
      </article>
    </div>
  </body>
  <script type="text/javascript">
 	$("#moduleForm").validate({
		rules:{
			name:{
				required:true,
			},
			messages : {
				'name' : {
					required : '请输入系统名称'
				}
			}
		}
	});
  </script>
  
</html>
