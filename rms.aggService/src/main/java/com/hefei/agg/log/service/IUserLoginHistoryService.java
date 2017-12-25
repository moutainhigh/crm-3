package com.hefei.agg.log.service;

import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.service.framework.exception.ServiceException;

public interface IUserLoginHistoryService {

	/**
	 * 保存登录日志
	 * @param userLoginHistory
	 * @return
	 * @throws ServiceException
	 */
	public String saveUserLoginHistory(UserLoginHistory userLoginHistory) throws ServiceException;

}
