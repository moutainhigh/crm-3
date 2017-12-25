package com.hefei.cas.user.auth.service;

import java.util.List;
import java.util.Map;

import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.service.framework.exception.ServiceException;

public interface IUserAuthService {

	public void createCompanySuperManager(Long userId,Long companyId) throws ServiceException;
	public void createCompanyCommonRole(Long userId,Long companyId) throws ServiceException;
	
//	public void authDefault(Long userId,Long companyId,Long roleId) throws ServiceException;
	
	public boolean checkAuth(Long userId, Long companyId, String url)throws ServiceException;
	
	public List<Module> getModuleMenus(Long userId, Long companyId) throws ServiceException;
	public List<Role>  getRoleByUserId(Long userId) throws ServiceException;
	
	public void authUserRole(Long userId, Map<Long, String> roleIdAndCheck) throws ServiceException;
}
