<%@ page import="com.hefei.rms.common.util.RmsConstant" %>
<%@page import="com.hefei.rms.cas.vo.ModuleTreeNode"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="<%=RmsConstant.STAIC_SERVER_URL %>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=RmsConstant.STAIC_SERVER_URL %>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	
    <title>管理后台</title>

	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" href="<%=RmsConstant.STAIC_SERVER_URL %>/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL %>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
  </head>

  <body>
    	<nav class="breadcrumb">
    		<i class="Hui-iconfont">&#xe67f;</i> 首页
    		<span class="c-gray en">&gt;</span> 权限管理
    		<span class="c-gray en">&gt;</span> 角色授权
    		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    	</nav>
    	<table class="table">
    		<input type="hidden" id="roleId" name="roleId" value="${role.id }">
			<tr>
				<td width="200" class="va-t">
					<div class="row cl">
						<div class="errorblock" id="errorblock" style="display:none;">
		    			</div>
	    			</div>
					<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
							<button type="button" class="btn btn-success radius" id="role-auth" name="role-auth"><i class="icon-ok"></i> 确定</button>
						</div>
					</div>
					<ul id="systemModuleTree" class="ztree"></ul>
				</td>
				<td class="va-t">
					<div id="treeCheckMessage"></div>
				</td>
			</tr>
		</table>
<script type="text/javascript">
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,//是否显示节点间的连线 
		selectedMulti: false,   
	    checkable: true
	},
	check: {  
        enable: true,  
        nocheckInherit: false  
    },
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		onCheck: zTreeOnCheck
	}
};

var zNodes = ${zTreeNodes};
var zTree;
$(document).ready(function(){
	var t = $("#systemModuleTree");
	t = $.fn.zTree.init(t, setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("systemModuleTree");
	
	$("#role-auth").click(function(){submitAuth();});
});

function zTreeOnCheck(event, treeId, treeNode){
	//alert(treeNode.id + "_" +treeNode.pId + "_"  +treeNode.nodeType+ "_"+treeNode.systemId );
}

function submitAuth(){
	var idAndcheck='';
	var changedNodes = zTree.getChangeCheckedNodes();
    for (var i=0 ; i < changedNodes.length ; i++ ){
        var treeNode = changedNodes[i];
        idAndcheck= idAndcheck + treeNode.id;
        if(treeNode.checked){
        	idAndcheck= idAndcheck + "-Y";
        }else{
        	idAndcheck= idAndcheck + "-N";
        }
        idAndcheck= idAndcheck + "|";
    }
   	var roleId=$('#roleId').val();
    if(idAndcheck != ''){
   	 $.ajax({  
  	      type: "POST",  
  	      async: false,  
  	      url: '<%=RmsConstant.RMS_URL %>' + '/cas/role/authModule.do?roleId=' + roleId +'&idAndCheck='+idAndcheck,
  	      dataType: "json",
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
}
</script>
</body>
</html>
