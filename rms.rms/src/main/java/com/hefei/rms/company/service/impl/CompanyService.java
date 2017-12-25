package com.hefei.rms.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyIndustryDTO;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;

@Service
public class CompanyService implements ICompanyService{
	
	private static Logger logger = Logger.getLogger(CompanyService.class);
	
	private ICompanyManager companyManager = new CompanyManager();

	
	public Pagination<CompanyInfo> findManagePagination(Long userId, String companyName, int pageIndex, int pageSize) throws BusinessException{
		try {
			return companyManager.findManagePagination(userId, companyName, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	
	public CompanyInfo getCompany(Long userId,Long companyId) throws BusinessException{
		try {
			return companyManager.getCompany(userId, companyId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}

	@Override
	public void editCompany(Long userId, String industryIds, CompanyInfo info) throws BusinessException {
		try {
			companyManager.updateCompany(userId, industryIds, info);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	@Override
	public CompanyInfo createCompany(Long userId, String industryIds, CompanyInfo info) throws BusinessException {
		try {
			info = companyManager.createCompany(userId,industryIds, info);
			return info;
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public List<CompanyIndustryDTO> getCompanyIndustry(Long companyId) throws BusinessException {
		try {
			return  companyManager.getCompanyIndustry(companyId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

}
