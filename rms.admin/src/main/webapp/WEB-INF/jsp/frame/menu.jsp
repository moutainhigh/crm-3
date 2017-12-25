<%@page import="com.hefei.admin.constants.AdminConstants"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<c:if test="${not empty menuTree }">
			<c:forEach items="${menuTree }" var="menu" varStatus="status">
				<dl id="${menu.module.id }">
					<dt>
						<c:if test="${not empty menu.module.iconPath }">
							<i class="Hui-iconfont"> <img alter="" src="${menu.module.iconPath }"/></i>
						</c:if>
						<c:out value="${menu.module.name}"></c:out>
						<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
					</dt>
					<c:if test="${not empty menu.children }">
						<dd>
							<ul>
								<c:forEach var="childMenu" items="${menu.children }" varStatus="childStatus">
								<li><a _href="<%=AdminConstants.ADMIN_DOMAIN %>${childMenu.url}" data-title="${childMenu.name}" href="javascript:void(0)">${childMenu.name}</a></li>
								</c:forEach>
							</ul>
						</dd>
					</c:if>
				</dl>
			</c:forEach>
		</c:if>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>