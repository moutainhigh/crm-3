package com.hefei.rms.department.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.department.dao.IDepartmentDao;
import com.hefei.rms.department.po.Department;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class DepartmentService implements IDepartmentService {
	
	private Logger logger = Logger.getLogger(DepartmentService.class);
	
	@Autowired
	private IDepartmentDao departmentDao;
	@Override
	public Department saveDepartmentInfo(Department info) throws ServiceException {
		if(info == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Department oldDepartment = getDepartmentByName(info.getCompanyId(), info.getDepartmentName());
			if(oldDepartment != null){
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			}
			Date now = Calendar.getInstance().getTime();
			info.setCreateTime(now);
			info.setUpdateTime(now);
			info.setDelFlag(DepartmentInfo.DELFLAG_NO);
			return departmentDao.saveDepartmentInfo(info);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
			
	}

	@Override
	public void updateDepartment(Long id, String delFlag, Date updateTime) throws ServiceException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			departmentDao.updateDepartment(id,delFlag,updateTime);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Department getDepartmentById(Long id) throws ServiceException {
		if(id == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Department department = departmentDao.getDepartmentById(id);
			return department;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public Department getDepartmentByName(Long companyId, String departmentName) throws ServiceException {
		if(companyId == null || StringUtils.isBlank(departmentName)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Department department = departmentDao.getDepartmentByName(companyId, departmentName);
			return department;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public Pagination<Department> findDepartmentPaginationByCompany(Long companyId, int pageIndex, int pageSize) throws ServiceException{
		Pagination<Department> pagination = new Pagination<Department>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			List<Department> departments = departmentDao.findDepartmentPaginationByCompanyItems(companyId, DepartmentInfo.DELFLAG_NO, pageIndex, pageSize);
			int count = departmentDao.findDepartmentPaginationByCompanyCount(companyId, DepartmentInfo.DELFLAG_NO);
			pagination.setItems(departments);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
