package com.hefei.rms.salary.dao;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.rms.salary.po.Salary;
import com.hefei.service.framework.exception.DaoException;

public interface ISalaryDao {

	public Salary saveSalary(Salary salaryInfo) throws DaoException;
	
	public void updateSalary(Long id, Double monthlyBaseSalary,Double monthlyBonus,Double quarterlyBonus,Double yearlyBonus, Date updateTime) throws DaoException;
	
	public Salary getSalary(Long companyId, Long employeeId) throws DaoException;
	
	public List<SalarySSDTO>  findSalaryPaginationItems(Long companyId, String cardNo, String employeeName, List<String> employeeCompanyStatus,int pageIndex, int pageSize) throws DaoException;
	public int  findSalaryPaginationCount(Long companyId, String cardNo, String employeeName,List<String> employeeCompanyStatus) throws DaoException;
}
