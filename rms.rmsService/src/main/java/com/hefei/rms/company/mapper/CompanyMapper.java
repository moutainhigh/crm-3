package com.hefei.rms.company.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.service.framework.base.mapper.Mapper;

public interface CompanyMapper extends Mapper{
	//添加公司
	public void saveCompany(Company companyInfo);
	public void updateCompany(Company companyInfo);
	
	public void saveCompanyIndustry(CompanyIndustry companyIndustry);
	
	public void  removeCompanyIndustry(Map map);
	public Company getCompany(Long id);
	public Company getCompanyByIdAndUser(Map<String, Object> map );
	public Company getCompanyByName(String companyName);
	public List<Company> getCompanyByUserId(Map map);
	
	public List<Company> getCompanyByEmployeeId(Map map);
	
	public List<Company> getManagedPaginationItems(Map map);
	public List<CompanyIndustry> getCompanyIndustry(Map map);
	public int getManagedPaginationCount(Map map);
}
