package com.hefei.api.user.auth.manager;

import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.common.exception.ClientException;

public interface IAuthManager {
	/**
	 * 登录,loginType:1.手机登录;2.邮箱登录
	 * @param username
	 * @param password
	 * @param loginType
	 * @return userId
	 * @throws ClientException
	 */
	public Long login(AuthRequest authRequest)throws ClientException;
}
