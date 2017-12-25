package com.hefei.rms.department.service;

import java.util.List;

import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.rms.department.vo.DepartmentTreeNode;

public interface IDepartmentService {
	public DepartmentInfo createDepartment(DepartmentInfo info) throws BusinessException;

	public DepartmentInfo getDepartment(Long departmentId) throws BusinessException;
	
	public List<DepartmentInfo> getDepartmentsByCompany(Long companyId) throws BusinessException;
	
	public Pagination<DepartmentInfo> findDeparmentPaginationByCompanyId(Long companyId, int pageIndex, int pageSize) throws BusinessException;
	
	public List<DepartmentTreeNode> treeNode(Long companyId) throws BusinessException;
}
