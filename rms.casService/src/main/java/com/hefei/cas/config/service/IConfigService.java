package com.hefei.cas.config.service;

import com.hefei.service.framework.exception.ServiceException;

public interface IConfigService {

	Long getRMSSuperManagerRoleId() throws ServiceException;
	String getRMSSuperManagerRoleName() throws ServiceException;
	
	public Long getFMSSuperManagerRoleId() throws ServiceException;

	public String getFMSSuperManagerRoleName() throws ServiceException;
	
	Long getRMSUserRoleId() throws ServiceException;
	String getRMSUserRoleName() throws ServiceException;
	
	Long getSystemIdAdmin() throws ServiceException;
	
	Long getSystemIdRMS() throws ServiceException;
	
	Long getSystemIdFMS() throws ServiceException;
}
