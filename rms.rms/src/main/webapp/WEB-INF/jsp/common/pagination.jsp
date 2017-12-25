<%@page import="com.hefei.rms.common.util.RmsConstant"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${pagination.totalPage >0 }">
<div class="dataTables_wrapper">
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">
	共${pagination.totalCount }条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共${pagination.totalPage }页
	</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
		<c:choose>
			<c:when test="${pagination.pageIndex  ==1}"></c:when>
			<c:otherwise>
				<a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous" onClick="submitForm(this,${pagination.pageIndex -1})" href="javascript:;">
				上一页
				</a>
			</c:otherwise>
		</c:choose>
		<c:if test=""></c:if>
		
		<span>
			<a class="paginate_button current" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0">${ pagination.pageIndex}</a>
		</span>
		
		<c:choose>
			<c:when test="${pagination.pageIndex == pagination.totalPage}"></c:when>
			<c:otherwise>
				<a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next"> onClick="submitForm(this,${pagination.pageIndex +1})" href="javascript:;">
				下一页
				</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</c:if>