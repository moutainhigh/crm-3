<%@page import="com.hefei.user.constants.UserConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="<%=UserConstants.STAIC_SERVER_URL%>/css/p/reset.css" rel="stylesheet">
<link href="<%=UserConstants.STAIC_SERVER_URL%>/css/p/login/v1.css" rel="stylesheet">

</head>

<body>
	<div class="container">
		<div class="pageleft">
		
		</div>
		<div class="pageright">
			<div class="content">
				<form class="form-signin" action="<%=UserConstants.USER_SERVER_URL%>/vlogin.do" method="POST" id="loginForm">
					<h2 class="form-signin-heading">请登录</h2>
					<input type="text" class="input-block-level" placeholder="手机号/邮箱" name="loginName" id="loginName">
					<input type="password" class="input-block-level" placeholder="密码" name="password" id="password">
					<c:if test="${not empty returnCode}">
						<div class="errorblock">
						${errorMessage}
						</div>
					</c:if>
					<button class="btn" type="submit">登录</button>					
					<a href="<%=UserConstants.USER_SERVER_URL%>/findPwd.do">忘记密码</a>
					<a href="<%=UserConstants.USER_SERVER_URL%>/reg.do">注册新账号</a>
				</form>
			</div>
		</div>
	</div>
	
	
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=UserConstants.STAIC_SERVER_URL%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=UserConstants.STAIC_SERVER_URL%>/js/jquery.validate.js"></script>
</body>
<script type="text/javascript">
	$("#loginForm").validate({
		wrapper : "li",// 使用"li"标签再把上边的errorELement包起来
		rules : {
			loginName : {
				required : true
			},
			password : {
				required : true
			}
		},
		messages : {
			'loginName' : {
				required : '请输入用户名！'
			},
			'password' : {
				required : '请输入密码'
			}
		}

	});
</script>

</html>
