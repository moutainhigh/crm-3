package com.hefei.api.cas.admin.auth.manager;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.common.exception.ClientException;

public interface IAdminAuthManager {

	public AdminInfo auth(String username, String password) throws ClientException;
}
