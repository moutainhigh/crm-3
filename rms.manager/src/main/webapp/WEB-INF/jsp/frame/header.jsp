<%@page import="com.hefei.manager.constants.ManagerConstants"%>
<%@page import="com.hefei.common.returncode.ReturnCode"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
			<a class="logo navbar-logo f-l mr-30 ml-30 hidden-xs" href="#">企业一体化管理系统</a>
			<span class="logo navbar-slogan f-l  ml-30 hidden-xs">所有公司:</span>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover">
						<a href="javascript:;" class="dropDown_A">
							<c:if test="${not empty allCompanys }">
								<i class="Hui-iconfont">&#xe600;</i> 
							</c:if>
							${currentCompany.companyName }
							<c:if test="${not empty allCompanys }">
								<i class="Hui-iconfont">&#xe6d5;</i>
							</c:if>
							</a>
						<c:if test="${not empty allCompanys }">
							<ul class="dropDown-menu menu radius box-shadow">
								<c:forEach var="company" items="${allCompanys }" varStatus="status">
									<li><a href="javascript:;" onclick="changeCurrentCompany('${company.id}')"><i class="Hui-iconfont">&#xe616;</i> ${company.companyName }</a></li>
								</c:forEach>
							</ul>
						</c:if>
					</li>
				</ul>
			</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>${user.nickname }</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">操作 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="<%=ManagerConstants.DOMAIN_USER %>/logout.do">退出</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<script type="text/javascript">
/*资讯-添加*/
function changeCurrentCompany(companyId){
	 $.ajax({  
  	      type: "POST",  
  	      async: false,  
  	      url: '<%=ManagerConstants.DOMAIN_MANAGER %>/changeCompany.do',
  	      dataType: "json",
  	      data:"companyId=" + companyId,
  	      // dataType: "jsonp",
  	      //jsonp: "jsonCallback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)  
  	      //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据  
  	      success: function(result){
  				if(result.returnCode==<%=ReturnCode.RETURN_CODE_SUCCESS%>){
  					 location.reload(true);
  				} else if(result.returnCode==<%=ReturnCode.RETURN_CODE_ERROR_PARAM_NULL%> ){
  					 alert("请选择公司"); 
  				}else{
  					 alert("fail"); 
  				}
  	      },
  	      error: function(){  
  	          alert("fail");  
  	      }  
  	  });
}
</script>