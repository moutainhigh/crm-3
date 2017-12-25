package com.hefei.rms.company.service;

import java.util.List;

import com.hefei.api.rms.company.vo.CompanyIndustryDTO;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface ICompanyService {
	
	public CompanyInfo createCompany(Long userId, String industryIds, CompanyInfo info) throws BusinessException;
	public void editCompany(Long userId, String industryIds, CompanyInfo info) throws BusinessException;
	
	
	public Pagination<CompanyInfo> findManagePagination(Long userId, String companyName, int pageIndex, int pageSize) throws BusinessException;
	
	public CompanyInfo getCompany(Long userId,Long companyId) throws BusinessException;
	
	public List<CompanyIndustryDTO> getCompanyIndustry(Long companyId) throws BusinessException;
}
