package com.hefei.rms.department.dao;


import java.util.Date;
import java.util.List;

import com.hefei.rms.department.po.Department;
import com.hefei.service.framework.exception.DaoException;

public interface IDepartmentDao {

	/**
	 * 保存部门信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public Department saveDepartmentInfo(Department beInfo) throws DaoException;
	/**
	 * 更新部门信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public void updateDepartment(Long id, String delFlag, Date updateTime) throws DaoException;
	/**
	 * 查看部门信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public Department getDepartmentById(Long id) throws DaoException;
	public Department getDepartmentByName(Long companyId, String departmentName) throws DaoException; 
	
	public List<Department> findDepartmentPaginationByCompanyItems(Long companyId, String delFlag, int pageIndex, int pageSize) throws DaoException;
	
	public int findDepartmentPaginationByCompanyCount(Long companyId, String delFlag) throws DaoException;
	
}
