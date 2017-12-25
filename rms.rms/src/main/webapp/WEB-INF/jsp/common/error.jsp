<%@page import="com.hefei.rms.common.util.RmsConstant"%>
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
	<LINK rel="Bookmark" href="<%=RmsConstant.STAIC_SERVER_URL%>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=RmsConstant.STAIC_SERVER_URL%>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	
    <title>Welcome</title>
  </head>

  <body>
    <div class="page-container">
		<div class="text-c">
			<c:choose>
				<c:when test="${not empty msg }">${msg }</c:when>
				<c:otherwise>
					系统暂时不能服务请稍后重试。
				</c:otherwise>
			</c:choose>
		</div>
	</div>
  </body>
</html>
