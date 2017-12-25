<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@page import="com.hefei.common.returncode.ReturnCode"%>

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
    <title>添加部门</title>

    <!-- Le styles -->
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/style.css" />

    <script src="<%=RmsConstant.STAIC_SERVER_URL%>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=RmsConstant.STAIC_SERVER_URL%>/js/bootstrap.min.js"></script>
    <script src="<%=RmsConstant.STAIC_SERVER_URL%>/js/jquery.validate.js"></script>

  </head>

  <body>
	<div class="page-container">
	    添加成功
	    <script type="text/javascript">
	    	window.setInterval("refreshParent()",1000);
	    	
	    	function refreshParent(){
	    		window.parent.location.href = window.parent.location.href;
	    	}
	    </script>
     </div>
  </body>
</html>
