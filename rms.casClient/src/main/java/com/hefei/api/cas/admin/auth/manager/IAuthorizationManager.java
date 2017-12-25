package com.hefei.api.cas.admin.auth.manager;

import com.hefei.common.exception.ClientException;

public interface IAuthorizationManager {

	public boolean adminHasAuthorization(Long adminId, String url) throws ClientException;
	
}
