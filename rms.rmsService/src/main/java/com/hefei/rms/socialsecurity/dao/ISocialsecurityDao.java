package com.hefei.rms.socialsecurity.dao;

import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.service.framework.exception.DaoException;


public interface ISocialsecurityDao {

	public EmployeeSS getEmployeeSS(Long companyId, Long employeeId) throws DaoException;
	
	public EmployeeSS createEmployeeSS(EmployeeSS employeeSS)throws DaoException;
	
	public void updateEmployeeSS(EmployeeSS employeeSS)throws DaoException;
}
