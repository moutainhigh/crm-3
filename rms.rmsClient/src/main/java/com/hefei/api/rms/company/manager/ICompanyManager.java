package com.hefei.api.rms.company.manager;

import java.util.List;

import com.hefei.api.rms.company.vo.CompanyIndustryDTO;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;



public interface ICompanyManager {
	//创建公司
	public CompanyInfo regCreateCompanyEmployee(Long userId, String companyName,String email, String mobile,String username) throws ClientException;
	//创建公司
	public CompanyInfo createCompany(Long userId, String industryIds, CompanyInfo info) throws ClientException;
	
	public void updateCompany(Long userId, String industryIds, CompanyInfo info) throws ClientException;
	//查找公司
	public CompanyInfo getCompany(Long userId, Long companyId) throws ClientException;
	
	public List<CompanyInfo> getCompanyByUserId(Long userId) throws ClientException;
	
	public List<CompanyInfo> getCompanyByEmployeeId(Long employeeId) throws ClientException;
	
	public Pagination<CompanyInfo> findManagePagination(Long userId, String companyName, int pageIndex, int pageSize) throws ClientException;
	
	public List<CompanyIndustryDTO> getCompanyIndustry(Long companyId) throws ClientException;
}
