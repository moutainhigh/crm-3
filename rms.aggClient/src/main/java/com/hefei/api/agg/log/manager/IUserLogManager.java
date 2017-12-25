package com.hefei.api.agg.log.manager;

import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;

public interface IUserLogManager {
	/****
	 * 系统用户登录登出日志
	 * @param ulh
	 * @return
	 * @throws ClientException
	 */
	public BaseResponse saveUserLoginHistory(UserLoginHistory ulh)throws ClientException;
}
