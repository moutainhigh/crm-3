<%@page import="com.hefei.user.constants.UserConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/userLogin.css" rel="stylesheet">
    <link href="<%=UserConstants.STAIC_SERVER_URL %>/css/bootstrap-responsive.css" rel="stylesheet">


  </head>

  <body>

    <div class="container">
    <c:if test="${not empty returnCode}">
	    <div class="errorblock">
	    <c:choose>
	    	<c:when test="${returnCode=='999710'}">
	    	用户名格式错误！
	    	</c:when>
	    	<c:when test="${returnCode=='999700'}">
	    	没有找到用户信息！
	    	</c:when>
	    	<c:when test="${returnCode=='999705'}">
	    	账户已冻结！
	    	</c:when>
	    	<c:when test="${returnCode=='999708'}">
	    	手机或邮箱未验证！
	    	</c:when>
	    	<c:when test="${returnCode=='999709'}">
	    	手机或邮箱未绑定！
	    	</c:when>
	    	<c:when test="${returnCode=='999702'}">
	    	用户密码错！
	    	</c:when>
	    	<c:when test="${returnCode=='999999'}">
	    	系统错误！
	    	</c:when>
	    </c:choose>
	    </div>
    </c:if>
    
      <form class="form-signin" action="<%=UserConstants.USER_SERVER_URL %>/vlogin.do" method= "POST" id="loginForm">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" class="input-block-level" placeholder="手机号/邮箱" name="loginName">
        <input type="password" class="input-block-level" placeholder="密码" name="password">
        <button class="btn btn-large btn-primary" type="submit">登录</button>
        <a href="<%=UserConstants.USER_SERVER_URL %>/reg.do">注册新账号</a>
      </form> 
 

    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/bootstrap.min.js"></script>
    <script src="<%=UserConstants.STAIC_SERVER_URL %>/js/jquery.validate.js"></script>

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
