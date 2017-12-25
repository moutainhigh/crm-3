package com.hefei.rms.company.dao;

import java.util.List;

import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.service.framework.exception.DaoException;


public interface ICompanyDao {

	public Company saveCompany(Company companyInfo) throws DaoException;
	public void updateCompany(Company companyInfo) throws DaoException;
	
	public CompanyIndustry saveCompanyIndustry(CompanyIndustry companyIndustry) throws DaoException;
	public void removeCompanyIndustry(Long companyId, List<Long> industryIds) throws DaoException;
	public Company getCompany(Long companyId) throws DaoException;
	public Company getCompany(Long userId, Long companyId) throws DaoException;
	public Company getCompanyByName(String companyName) throws DaoException;
	public List<Company> getCompanyByUserId(Long userId) throws DaoException;
	
	public List<Company> getCompanyByEmployeeId(Long employeeId) throws DaoException;
	
	public List<Company> getManagedPaginationItems(Long userId, String companyName, String delFlag, int pageIndex, int pageSize) throws DaoException;
	
	public int getManagedPaginationCount(Long userId, String companyName, String delFlag) throws DaoException;
	
	public List<CompanyIndustry> getCompanyIndustry(Long companyId) throws DaoException;
}
