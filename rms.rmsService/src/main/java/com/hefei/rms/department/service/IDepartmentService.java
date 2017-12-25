package com.hefei.rms.department.service;


import java.util.Date;

import com.hefei.common.page.Pagination;
import com.hefei.rms.department.po.Department;
import com.hefei.service.framework.exception.ServiceException;

public interface IDepartmentService {
	/**
	 * 保存部门信息
	 * @param info
	 * @throws ServiceException
	 */
	public Department saveDepartmentInfo(Department departmentInfo) throws ServiceException;
	/**
	 * 更新部门信息
	 * @param info
	 * @throws ServiceException
	 */
	public void updateDepartment(Long id, String delFlag, Date updateTime) throws ServiceException;
	/**
	 * 查看部门信息
	 * @param info
	 * @throws ServiceException
	 */
	public Department getDepartmentById(Long id) throws ServiceException;
	
	public Pagination<Department> findDepartmentPaginationByCompany(Long companyId, int pageIndex, int pageSize) throws ServiceException;

}
