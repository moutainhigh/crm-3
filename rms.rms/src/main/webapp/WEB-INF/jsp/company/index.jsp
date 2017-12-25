<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@ page import="com.hefei.api.rms.company.vo.CompanyInfo" %>

<c:set var="DELFLAG_NO" value="<%=CompanyInfo.DELFLAG_NO%>"></c:set>
<c:set var="DELFLAG_YES" value="<%=CompanyInfo.DELFLAG_YES%>"></c:set>
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
	
    <title>公司管理</title>

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
    		<span class="c-gray en">&gt;</span> 公司管理
    		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    	</nav>
    	
    	<div class="page-container">
    		<div class="cl pd-5 bg-1 bk-gray ml-20">
    			<form method="post" action="<%=RmsConstant.RMS_URL%>/company/search.do" id="companySearchForm">
					<div class="row cl">
						<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }">
						<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
						<ul class="inline1">
							<li>
							<input type="text" name="companyName" id="" placeholder="公司名称" style="width:250px" class="input-text" value="${companyName }">
							</li>
						</ul>
					</div>
					<div class="cl pd-5 mt-20"> 
						<span class="l">
							<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
							<a class="btn btn-primary radius" href="javascript:;" onclick="add();"><i class="Hui-iconfont">&#xe600;</i>添加公司</a>
						</span>
					</div>
				</form>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="80">ID</th>
							<th width="150">公司名</th>
							<th width="150">社会信用码</th>
							<th width="80">状态</th>
							<th width="80">创建信息</th>
							<th width="120">更新信息</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty pagination.items }">
							<c:forEach var="item" items="${ pagination.items}" varStatus="status">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.id }" name="chk" id="chk_${item.id }"></td>
								<td>${item.id }</td>
								<td class="text-l">${item.companyName }</td>
								<td class="text-l">${item.usccCode }</td>
								<td>
									<c:if test="${item.delFlag eq DELFLAG_NO }">正常</c:if>
									<c:if test="${item.delFlag eq DELFLAG_YES }">无效</c:if>
								</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd hh:MM:ss"  /></td>
								<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd hh:MM:ss"  /></td>
								<td class="f-14 td-manage">
									<!-- <a style="text-decoration:none" onClick="view(${item.id })" href="javascript:;" title="详情">详情</a> -->
									<a style="text-decoration:none" onClick="edit('${item.id }')" href="javascript:;" title="更新">更新</a>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<jsp:include page="../common/pagination.jsp"></jsp:include>
				</table>
			</div>
		</div>
<script type="text/javascript">
function submitForm(obj, pageIndex){
	$("#pageInde").val(pageIndex);
	$("#companySearchForm").submit();
}
function add(){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/toCreate.do'
	});
	layer.full(index);
}
function view(companyId){
	var index = layer.open({
		type: 2,
		title: "创建公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/view.do?companyId='+companyId
	});
	layer.full(index);
}
function edit(companyId){
	var index = layer.open({
		type: 2,
		title: "更新公司",
		content: '<%=RmsConstant.RMS_URL%>'+'/company/toEdit.do?companyId='+companyId
	});
	layer.full(index);
}
</script>
  </body>
</html>
