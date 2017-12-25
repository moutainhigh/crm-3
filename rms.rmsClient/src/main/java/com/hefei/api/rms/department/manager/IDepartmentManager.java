package com.hefei.api.rms.department.manager;

import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;


public interface IDepartmentManager {
	public DepartmentInfo saveDepartment(DepartmentInfo departmentInfo) throws ClientException;

	public void updateDepartment(Long id,String delFlag) throws ClientException;
	
	public DepartmentInfo getDepartmentById(Long id) throws ClientException;
	
	public Pagination<DepartmentInfo> findDepartmentPaginationByCompany(Long companyId, int pageIndex, int pageSize) throws ClientException;
}
