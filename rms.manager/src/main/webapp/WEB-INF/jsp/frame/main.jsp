<%@page import="com.hefei.manager.constants.ManagerConstants"%>
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
	<LINK rel="Bookmark" href="<%=ManagerConstants.STAIC_SERVER_URL %>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=ManagerConstants.STAIC_SERVER_URL %>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	
    <title>管理后台</title>

	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/style.css" />

	<script type="text/javascript" src="<%=ManagerConstants.STAIC_SERVER_URL %>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=ManagerConstants.STAIC_SERVER_URL %>/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=ManagerConstants.STAIC_SERVER_URL %>/static/h-ui.admin/js/H-ui.admin.js"></script> 
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="menu.jsp"></jsp:include>
    <section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="<%=ManagerConstants.STAIC_SERVER_URL %>/welcome.do">我的桌面</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="<%=ManagerConstants.DOMAIN_MANAGER %>/welcome.do"></iframe>
			</div>
		</div>
	</section>
  </body>
</html>
