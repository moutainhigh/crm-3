package com.hefei.rms.company.service;

import java.util.List;

import com.hefei.common.page.Pagination;
import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.service.framework.exception.ServiceException;

public interface ICompanyService {
	//创建公司
	public Company regCreateCompanyEmployee(Long userId, String companyName, String email, String mobile, String username) throws ServiceException;
	public Company createCompany(Long userId, String industryIds, Company company) throws ServiceException; 
	public void updateCompany(Long userId, String industryIds, Company company) throws ServiceException; 
	
	//创建公司
	public Company createCompany(Company company) throws ServiceException;
	//查找公司
	public Company getCompany(Long userId, Long companyId) throws ServiceException;
	
	public Company getCompany(Long companyId) throws ServiceException;
	public Company getCompanyByName(String companyName) throws ServiceException;
	public List<Company> getCompanyByUserId(Long userId) throws ServiceException;
	
	public List<Company> getCompanyByEmployeeId(Long employeeId) throws ServiceException;
	
	public List<CompanyIndustry> getCompanyIndustry(Long companyId) throws ServiceException;
	public Pagination<Company> findManagedPagination(Long userId, String companyName, int pageIndex, int pageSize)throws ServiceException;
}
