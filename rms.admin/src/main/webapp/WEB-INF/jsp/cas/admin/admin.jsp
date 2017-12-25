<%@page import="com.hefei.admin.constants.AdminConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
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
    	<form action="<%=AdminConstants.ADMIN_SERVER_URL %>/cas/admin/create.do" method="post" class="form form-horizontal" id="adminForm">
		  	<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户登录名</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="username" name="username" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户登录密码</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="password" name="password" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户真实名</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="realName" name="realName" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户手机</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="mobileNo" name="mobileNo" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户邮箱</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="email" name="email" >
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<button type="button" class="btn btn-success radius" id="admin-create" name="admin-save"><i class="icon-ok"></i> 确定</button>
				</div>
			</div>
		</form> 
      </article>
    </div>

  </body>
  <script type="text/javascript">
  $(document).ready(function(){
	  $("#admin-create").click(function(){adminCreateSubmit();});
  });
  function adminCreateSubmit(){
	  $.ajax({  
	      type: "POST",  
	      async: false,  
	      url: $('#adminForm').attr("action"),  
	      dataType: "json",
	      data: $('#adminForm').serialize(), 
	      // dataType: "jsonp",
	      //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
	      //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
	      success: function(result){
				if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
					$('#errorblock').html(result.returnCode);
					$('#errorblock').css("display", "block");
					setTimeout(function(){
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},3000);
				}else{
					$('#errorblock').html(result.returnCode);
					$('#errorblock').css("display", "block");
				}
	      },
	      error: function(){  
	          alert("fail");  
	      }  
	  });
  }

 	$("#adminForm").validate({
		rules:{
			username:{
				required:true,
			},
			messages : {
				'username' : {
					required : '请输入系统名称'
				}
			}
		}
	});
  </script>
  
</html>
