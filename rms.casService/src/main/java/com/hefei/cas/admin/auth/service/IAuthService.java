package com.hefei.cas.admin.auth.service;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IAuthService {

	public AdminInfo auth(String username , String plainPwd) throws ServiceException;
}
