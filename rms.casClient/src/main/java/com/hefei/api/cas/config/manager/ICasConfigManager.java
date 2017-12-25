package com.hefei.api.cas.config.manager;

import com.hefei.common.exception.ClientException;

public interface ICasConfigManager {

	Long getRMSSuperManagerRoleId() throws ClientException;
	String getRMSSuperManagerRoleName() throws ClientException;
	
	public Long getFMSSuperManagerRoleId() throws ClientException;
	public String getFMSSuperManagerRoleName() throws ClientException;
	
	Long getRMSUserRoleId() throws ClientException;
	String getRMSUserRoleName() throws ClientException;
	
	Long getSystemIdAdmin() throws ClientException;
	
	Long getSystemIdRMS() throws ClientException;
	public Long getSystemIdFMS() throws ClientException;
}
