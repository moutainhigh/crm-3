package com.hefei.cas.config.service.impl;

import org.springframework.stereotype.Service;

import com.hefei.cas.common.CasConstants;
import com.hefei.cas.config.service.IConfigService;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class ConfigService implements IConfigService{

	@Override
	public Long getRMSSuperManagerRoleId() throws ServiceException {
		return CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID;
	}

	@Override
	public String getRMSSuperManagerRoleName() throws ServiceException {
		return CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLENAME;
	}

	@Override
	public Long getFMSSuperManagerRoleId() throws ServiceException {
		return CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID;
	}

	@Override
	public String getFMSSuperManagerRoleName() throws ServiceException {
		return CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLENAME;
	}
	
	@Override
	public Long getRMSUserRoleId() throws ServiceException {
		return CasConstants.DEFAULT_RMS_USER_ROLEID;
	}

	@Override
	public String getRMSUserRoleName() throws ServiceException {
		return CasConstants.DEFAULT_RMS_USER_ROLENAME;
	}

	@Override
	public Long getSystemIdAdmin() throws ServiceException {
		return CasConstants.SYSTEM_ID_ADMIN;
	}

	@Override
	public Long getSystemIdRMS() throws ServiceException {
		return CasConstants.SYSTEM_ID_RMS;
	}
	public Long getSystemIdFMS() throws ServiceException {
		return CasConstants.SYSTEM_ID_FMS;
	}
}
