<%@page import="com.hefei.api.cas.role.vo.RoleInfo"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>

<c:set var="DEL_FLAG_NO" value="${RoleInfo.DEL_FLAG_NO}"></c:set>
<c:set var="DEL_FLAG_YES" value="${RoleInfo.DEL_FLAG_YES }"></c:set>
<!DOCTYPE html>
<html >
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
	
    <title>角色管理</title>

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
    		<span class="c-gray en">&gt;</span> 权限管理
    		<span class="c-gray en">&gt;</span> 角色管理 
    		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    	</nav>
    	
    	<div class="page-container">
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<a class="btn btn-primary radius" href="javascript:;" onclick="role_add('添加角色','<%=RmsConstant.RMS_URL%>/cas/role/toAdd.do','800')"><i class="Hui-iconfont">&#xe600;</i>添加角色</a>
				</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="80">ID</th>
							<th width="150">角色名称</th>
							<!-- <th width="80">状态</th> -->
							<th width="80">创建时间</th>
							<th width="120">更新时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items }">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.id }" name="chk" id="chk_${item.id }"></td>
								<td>${item.id }</td>
								<td class="text-l">${item.roleName }</td>
								<!-- 
								<td>
									<c:if test="${item.delFlag eq DEL_FLAG_NO }">有效</c:if>
									<c:if test="${item.delFlag eq DEL_FLAG_YES }">无效</c:if>
								</td>
								 -->
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd hh:MM:ss"  /></td>
								<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd hh:MM:ss"  /></td>
								<td class="f-14 td-manage">
									<c:if test="${companySuperManagerName != item.roleName}">
										<a style="text-decoration:none" onClick="roleAuthModule('${item.id }')" href="javascript:;" title="授权">授权</a>
									</c:if>
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
function role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
function roleAuthModule(roleId){
	var index = layer.open({
		type: 2,
		title: "授权",
		content: '<%=RmsConstant.RMS_URL%>'+'/cas/role/toAuthModule.do?roleId=' + roleId
	});
	layer.full(index);
}

</script>
  </body>
</html>
