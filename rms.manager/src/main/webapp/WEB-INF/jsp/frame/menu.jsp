<%@page import="com.hefei.manager.constants.ManagerConstants"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="<%=ManagerConstants.STAIC_SERVER_URL %>/css/p/menu/s1-v1.css" />

<div class="menu-container">
	<ul class="nav1">
		<c:forEach items="${menuTree }" var="menu" varStatus="status">
			<li>
				<a href="javascript:void(0);">${menu.systemName}</a>
				<c:if test="${not empty menu.moduleTrees }">
					<ul class="nav2">
						<c:forEach items="${menu.moduleTrees }" var="moduleMenu" varStatus="status">
							<li>
								<c:choose>
									<c:when test="${not empty moduleMenu.module.url}">
										<a _href="<%=ManagerConstants.DOMAIN %>${moduleMenu.module.url}" data-title="${moduleMenu.module.name}" href="javascript:void(0);">
											${moduleMenu.module.name}
										</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0);">${moduleMenu.module.name}</a>
									</c:otherwise>
								</c:choose>
								
								<c:if test="${not empty moduleMenu.children }">
									<ul class="nav3">
										<c:forEach items="${moduleMenu.children }" var="moduleChildMenu" varStatus="status">
											<li>
												<a _href="<%=ManagerConstants.DOMAIN %>${moduleChildMenu.url}" data-title="${moduleChildMenu.name}" href="javascript:void(0);">
													${moduleChildMenu.name}
												</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript" src="<%=ManagerConstants.STAIC_SERVER_URL %>/js/p/menu/s1-v1.js"></script> 
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>