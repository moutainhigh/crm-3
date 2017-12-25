package com.hefei.agg.log.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.agg.log.dao.IUserLogHistoryDao;
import com.hefei.agg.log.service.IUserLoginHistoryService;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class UserLoginHistoryService implements IUserLoginHistoryService{

	private Logger logger = Logger.getLogger(UserLoginHistoryService.class);
	
	@Autowired
	private IUserLogHistoryDao userLogHistoryDao;
	
	@Override
	public String saveUserLoginHistory(UserLoginHistory userLoginHistory)throws ServiceException {
		int i = 0;
		try {
			i = userLogHistoryDao.saveUserLoginHistory(userLoginHistory);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"error:", e);
			return e.getErrorCode();
		}
		if(i>0){
			return ReturnCode.RETURN_CODE_SUCCESS;
		}
		return ReturnCode.RETURN_CODE_ERROR;
	}

}
