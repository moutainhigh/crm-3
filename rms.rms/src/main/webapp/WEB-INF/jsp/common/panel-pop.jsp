<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.hefei.rms.common.util.RmsConstant" %>

<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/css/base/panel-pop/css.css" />
<link rel="stylesheet" type="text/css" href="<%=RmsConstant.STAIC_SERVER_URL%>/css/base/panel-pop/alpha.css" />

<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/drag.js"></script>
<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/city_arr.js"></script>
<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/nationality_arr.js"></script>
<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/funtype_arr.js"></script>
<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/industry_arr.js"></script>
<script type="text/javascript" src="<%=RmsConstant.STAIC_SERVER_URL%>/js/base/panel-pop/major_arr.js"></script>
<script type="text/javascript">
// 是否在数组内
function in_array(needle, haystack) {
	if(typeof needle == 'string' || typeof needle == 'number') {
		for(var i in haystack) {
			if(haystack[i] == needle) {
					return true;
			}
		}
	}
	return false;
}
</script>
<!-- alpha div -->
<div id="maskLayer" style="display:none">
<iframe id="maskLayer_iframe" frameBorder=0 scrolling=no style="filter:alpha(opacity=50)"></iframe>
<div id="src/main/webapp/WEB-INF/jsp/common/panel-pop.jsp""alphadiv" style="filter:alpha(opacity=50);-moz-opacity:0.5;opacity:0.5"></div>
	<div id="drag">
		<h3 id="drag_h"></h3>
		<div id="drag_con"></div><!-- drag_con end -->
	</div>
</div><!-- maskLayer end -->
</div>
