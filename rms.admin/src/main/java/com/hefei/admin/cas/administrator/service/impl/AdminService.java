package com.hefei.admin.cas.administrator.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.admin.cas.administrator.service.IAdminService;
import com.hefei.api.cas.admin.manager.IAdminManager;
import com.hefei.api.cas.admin.manager.impl.AdminManager;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class AdminService implements IAdminService{
	private Logger logger = Logger.getLogger(AdminService.class);
	private IAdminManager adminManager = new AdminManager();
	
	@Override
	public Pagination<AdminInfo> find(String username, String mobile,
			String email, int pageSize, int pageIndex) throws BusinessException {
		try {
			return adminManager.find(username, mobile, email, pageSize, pageIndex);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}

	@Override
	public AdminInfo create(AdminInfo adminInfo) throws BusinessException {
		try {
			return adminManager.create(adminInfo);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public AdminInfo getById(Long id) throws BusinessException {
		try {
			return adminManager.getById(id);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	public List<RoleInfo>  getRoleByAdmin(Long adminId) throws BusinessException{
		try {
			return adminManager.getRoleByAdmin(adminId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck)  throws BusinessException{
		try {
			adminManager.adminAuthRole(adminId, roleIdAndCheck);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
	}
}
