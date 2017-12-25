package com.hefei.rms.cas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.module.manager.IModuleManager;
import com.hefei.api.cas.module.manager.impl.ModuleManager;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.manager.IRoleManager;
import com.hefei.api.cas.role.manager.impl.RoleManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.cas.user.auth.manager.IUserAuthorizationManager;
import com.hefei.api.cas.user.auth.manager.impl.UserAuthorizationManager;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.cas.service.ICasRoleService;

@Service
public class CasRoleService implements ICasRoleService{

	private Logger logger = Logger.getLogger(CasRoleService.class);

	private IRoleManager roleManager = new RoleManager();
	private IModuleManager moduleManager = new ModuleManager();
	private IUserAuthorizationManager userAuthorizationManager= new UserAuthorizationManager();
	
	public List<RoleInfo> getRoleByUserId(Long userId) throws BusinessException{
		try {
			return userAuthorizationManager.getRoleByUserId(userId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	
	public void userAuthRole(Long userId, Map<Long, String> roleIdAndCheck) throws BusinessException{
		try {
			userAuthorizationManager.userAuthRole(userId, roleIdAndCheck);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	
	public Pagination<RoleInfo> find(Long systemId, Long companyId, String roleName, int pageSize, int pageIndex) throws BusinessException {
		try {
			return roleManager.find(systemId,companyId,roleName, pageSize, pageIndex);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}

	public RoleInfo create(RoleInfo roleInfo) throws BusinessException {
		try {
			return roleManager.create(roleInfo);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	public RoleInfo getById(Long id) throws BusinessException {
		try {
			return roleManager.getById(id);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	/**
	 * 通过公司ID 查找当前公司的超级管理员角色ID
	 * @param companyId
	 */
	public List<RoleInfo> getManagerRoleByCompanyId(Long companyId) throws BusinessException{
		try {
			return roleManager.getManagerRoleByCompanyId(companyId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	
	public List<ModuleInfo> getModuleByRole(List<RoleInfo> roleInfos) throws BusinessException{
		try {
			if(roleInfos == null || roleInfos.size() == 0)
				return null;
			
			List<Long> roleIds = new ArrayList<Long>();
			for(RoleInfo roleInfo: roleInfos)
				roleIds.add(roleInfo.getId());
			return moduleManager.getModuleByRole(roleIds);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	public List<ModuleInfo> getModuleByRole(Long roleId) throws BusinessException{
		try {
			List<Long> roleIds = new ArrayList<Long>();
			roleIds.add(roleId);
			return moduleManager.getModuleByRole(roleIds);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck) throws BusinessException{
		try {
			roleManager.roleAuthModule(roleId, moduleIdAndCheck);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
}
