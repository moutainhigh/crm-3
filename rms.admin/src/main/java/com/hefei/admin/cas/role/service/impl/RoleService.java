package com.hefei.admin.cas.role.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.admin.cas.role.service.IRoleService;
import com.hefei.api.cas.role.manager.IRoleManager;
import com.hefei.api.cas.role.manager.impl.RoleManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class RoleService implements IRoleService{
	private Logger logger = Logger.getLogger(RoleService.class);

	private IRoleManager roleManager = new RoleManager();
	@Override
	public Pagination<RoleInfo> find(Long systemId, String roleName, int pageSize, int pageIndex) throws BusinessException {
		try {
			return roleManager.find(systemId,null,roleName, pageSize, pageIndex);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}

	@Override
	public RoleInfo create(RoleInfo roleInfo) throws BusinessException {
		try {
			return roleManager.create(roleInfo);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public RoleInfo getById(Long id) throws BusinessException {
		try {
			return roleManager.getById(id);
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
