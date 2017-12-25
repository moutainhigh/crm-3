package com.hefei.rms.salary.dao;

import java.util.List;

import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.service.framework.exception.DaoException;

public interface ISalaryHistoryDao {

	public void saveSalaryHistory(SalaryHistory salaryHistory) throws DaoException;
	
	public List<SalaryHistory> findSalaryHistoryPaginationItems(Long companyId, Long employeeId, String cardNo, String employeeName, int pageIndex, int pageSize)throws DaoException;
	public int findSalaryHistoryPaginationCount(Long companyId, Long employeeId, String cardNo, String employeeName)throws DaoException;
	
}
