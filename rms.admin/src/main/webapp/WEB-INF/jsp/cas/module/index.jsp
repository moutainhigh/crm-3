<%@page import="com.hefei.admin.constants.AdminConstants"%>
<%@page import="com.hefei.api.cas.system.vo.SystemInfo"%>
<%@page import="com.hefei.admin.cas.module.vo.TreeNode"%>
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
	<LINK rel="Bookmark" href="<%=AdminConstants.STAIC_SERVER_URL %>/img/favicon.ico" >
	<LINK rel="Shortcut Icon" href="<%=AdminConstants.STAIC_SERVER_URL %>/img/favicon.ico" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	
    <title>管理后台</title>

	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" href="<%=AdminConstants.STAIC_SERVER_URL %>/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

	<script type="text/javascript" src="<%=AdminConstants.STAIC_SERVER_URL %>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=AdminConstants.STAIC_SERVER_URL %>/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=AdminConstants.STAIC_SERVER_URL %>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="<%=AdminConstants.STAIC_SERVER_URL %>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
  </head>

  <body>
    	<nav class="breadcrumb">
    		<i class="Hui-iconfont">&#xe67f;</i> 首页
    		<span class="c-gray en">&gt;</span> 权限管理
    		<span class="c-gray en">&gt;</span> 模块管理管理 
    		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    	</nav>
    	<table class="table">
			<tr>
				<td width="200" class="va-t"><ul id="systemModuleTree" class="ztree"></ul></td>
				<td class="va-t"><IFRAME ID="moduleIFrame" Name="moduleIFrame" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC=""></IFRAME></td>
			</tr>
		</table>
		<p>
		<span style="background-color: #fafafa;"><!-- 右键菜单div -->  
		     <div id="rMenu" style="position:absolute; display:none;">  
		     <li>  
		     <ul id="m_addModule" onclick="addModule();" url="<%=AdminConstants.ADMIN_SERVER_URL%>/cas/module/toAddModule.do"><li>增加菜单</li></ul>
		     <ul id="m_addButton" onclick="addButton();" url="<%=AdminConstants.ADMIN_SERVER_URL%>/cas/module/toAddButton.do"><li>增加功能</li></ul>  
		     <ul id="m_del" onclick="delModule();" url="<%=AdminConstants.ADMIN_SERVER_URL%>/cas/module/delModule.do"><li>删除</li></ul>  
		     <ul id="m_edit" onclick="editModule();" url="<%=AdminConstants.ADMIN_SERVER_URL%>/cas/module/toEditModule.do"><li>编辑</li></ul>  
		     <ul id="m_view" onclick="queryModule();" url="<%=AdminConstants.ADMIN_SERVER_URL%>/cas/module/viewModule.do"><li>查看</li></ul>  
		     </li>  
		     </div>
	     </span>
	    </p>  
<script type="text/javascript">
var NODE_TYPE_SYSTEM = <%=TreeNode.NODE_TYPE_SYSTEM %>;
var NODE_TYPE_MENU = <%=TreeNode.NODE_TYPE_MENU %>;
var NODE_TYPE_BUTTON = <%=TreeNode.NODE_TYPE_BUTTON %>;
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,//是否显示节点间的连线 
		selectedMulti: false,   
	    checkable: true
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
		onRightClick : zTreeOnRightClick   //右键事件  
	}
};

var zNodes = ${zTreeNodes};
var zTree;
$(document).ready(function(){
	var t = $("#systemModuleTree");
	t = $.fn.zTree.init(t, setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("systemModuleTree");
     $("body").bind(//鼠标点击事件不在节点上时隐藏右键菜单  
             "mousedown",  
             function(event) {  
                 if (!(event.target.id == "rMenu" || $(event.target)  
                         .parents("#rMenu").length > 0)) {  
                     $("#rMenu").hide();  
                 }  
    });  
});

//显示右键菜单  
function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    
    var selectedNodes = zTree.getSelectedNodes()[0];
	var nodeType = selectedNodes.nodeType;
	if(nodeType == NODE_TYPE_SYSTEM){
		$("#m_addButton").hide();  
	}
	if(nodeType == NODE_TYPE_MENU){
	}
	if(nodeType == NODE_TYPE_BUTTON){
		$("#m_addModule").hide();
	//	$("#m_addButton").hide();
	}
	
    $("#rMenu").css({"top":y+"px", "left":x+"px", "display":"block","background-color":"#5eb95e","border-color":"#5eb95e"});  
}  
//隐藏右键菜单  
function hideRMenu() {  
    $("#rMenu").hide();  
}
function zTreeOnRightClick(event, treeId, treeNode){
	if (!treeNode) {
        zTree.cancelSelectedNode();  
        showRMenu("root", event.clientX, event.clientY);  
    } else if (treeNode && !treeNode.noR) { //noR属性为true表示禁止右键菜单  
        if (treeNode.newrole && event.target.tagName != "a" && $(event.target).parents("a").length == 0) {  
            zTree.cancelSelectedNode();  
            showRMenu("root", event.clientX, event.clientY);  
        } else {  
            zTree.selectNode(treeNode);  
            showRMenu("node", event.clientX, event.clientY);  
        }  
    }  
}

function addModule(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	//alert(selectedNodes.id + "_" +selectedNodes.pId + "_"  +selectedNodes.nodeType+ "_"+selectedNodes.systemId );
	var url = $("#m_addModule").attr("url");
	url = url+'?moduleId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
	
}
function addButton(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_addButton").attr("url");
	url = url+'?moduleId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function delModule(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_del").attr("url");
	url = ulr+'?moduleId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function editModule(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_edit").attr("url");
	url = url+'?moduleId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function queryModule(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_view").attr("url");
	url = url+'?moduleId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
</script>
</body>
</html>
