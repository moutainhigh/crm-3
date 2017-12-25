package com.hefei.user.auth.service;

import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.service.framework.exception.ServiceException;

public interface IAuthService {

	/****
	 * 用户登录
	 * @param loginName
	 * @param password
	 */
	public Long login(AuthRequest authRequest) throws ServiceException;
}
