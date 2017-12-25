<%@page import="com.hefei.user.constants.UserConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/userLogin.css" rel="stylesheet">
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/bootstrap.min.js"></script>
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/jquery.validate.js"></script>

  </head>

  <body>

    <div class="container">
    <c:if test="${not empty returnCode}">
	    <div class="errorblock">
	    ${returnCode}
	    </div>
    </c:if>
    
      <form class="form-signin" action="<%=UserConstants.USER_SERVER_URL %>/doRegister.do" method= "POST" id="registerForm">
        <h2 class="form-signin-heading">注册新账号</h2>
        <input type="text" class="input-block-level" placeholder="手机号/邮箱" name="loginName">
        <input type="password" class="input-block-level" placeholder="密码" name="password">
        <input type="text" class="input-block-level" placeholder="公司名称" name="companyName">
        <button class="btn btn-large btn-primary" type="submit">登录</button>
        <a href="<%=UserConstants.USER_SERVER_URL %>/login.do">已有账号</a>
      </form> 
    </div>
  </body>
  <script type="text/javascript">
 	$("#registerForm").validate({
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
