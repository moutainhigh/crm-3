<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	
	<title>部门职位管理</title>

	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
	<nav class="breadcrumb">
   		<i class="Hui-iconfont">&#xe67f;</i> 首页
   		<span class="c-gray en">&gt;</span> 部门职位管理
   	</nav>
   	<div class="page-container">
		<div style="width:200px;float:left;position: relative;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
				右击鼠标管理部门与职位<br/><br/>
			<ul id="departmentTree" class="ztree"></ul>
		</div>
		<div style="float:left;position: relative;width:800px;">
			<IFRAME ID="moduleIFrame" Name="moduleIFrame" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC=""></IFRAME>
		</div>
	</div>
	<p>
	<span style="background-color: #fafafa;"><!-- 右键菜单div -->  
	     <div id="rMenu" style="position:absolute; display:none;">  
		     <li>  
		     <ul id="m_addDepartment" onclick="addDepartment();" url="<%=RmsConstant.RMS_URL%>/department/toAdd.do"><li>增加部门</li></ul>
		     <ul id="m_delDepartment" onclick="delDepartment();" url="<%=RmsConstant.RMS_URL%>/department/delete.do"><li>删除部门</li></ul>
		     <ul id="m_editDepartment" onclick="editDepartment();" url="<%=RmsConstant.RMS_URL%>/department/toEdit.do"><li>编辑部门</li></ul>
		     <ul id="m_viewDepartment" onclick="viewDepartment();" url="<%=RmsConstant.RMS_URL%>/department/view.do"><li>查看部门</li></ul>
		     <ul id="m_managePosition" onclick="managePosition();" url="<%=RmsConstant.RMS_URL%>/position/index.do"><li>管理职位</li></ul>
		     </li>  
	     </div>
     </span>
    </p>
</body>

<script type="text/javascript">
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
	var t = $("#departmentTree");
	t = $.fn.zTree.init(t, setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("departmentTree");
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
    var id = selectedNodes.id;
    if (id=="0") {
        $("#m_delDepartment").hide();  
        $("#m_editDepartment").hide();  
        $("#m_viewDepartment").hide();
        $("#m_managePosition").hide();
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

function addDepartment(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	//alert(selectedNodes.id + "_" +selectedNodes.pId + "_"  +selectedNodes.nodeType+ "_"+selectedNodes.systemId );
	var url = $("#m_addDepartment").attr("url");
	url = url+'?departmentId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
	
}
function delDepartment(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_delDepartment").attr("url");
	url = url+'?departmentId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function editDepartment(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_editDepartment").attr("url");
	url = ulr+'?departmentId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function viewDepartment(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_viewDepartment").attr("url");
	url = url+'?departmentId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
function managePosition(){
	var selectedNodes = zTree.getSelectedNodes()[0];
	var url = $("#m_managePosition").attr("url");
	url = url+'?departmentId='+selectedNodes.id;
	$("#moduleIFrame").attr("src",url);
	
	hideRMenu();
}
</script>
</html>
