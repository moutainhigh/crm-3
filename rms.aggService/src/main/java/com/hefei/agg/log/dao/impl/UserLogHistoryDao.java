package com.hefei.agg.log.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.agg.log.dao.IUserLogHistoryDao;
import com.hefei.agg.log.mapper.UserLogHistoryMapper;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class UserLogHistoryDao implements IUserLogHistoryDao{

	private Logger logger = Logger.getLogger(UserLogHistoryDao.class);
	
	@Autowired
	private UserLogHistoryMapper userLogHistoryMapper;
	
	@Override
	public int saveUserLoginHistory(UserLoginHistory userLoginHistory)throws DaoException{
		try {
			long begintimer = System.currentTimeMillis();
			int i =  userLogHistoryMapper.saveUserLoginHistory(userLoginHistory);
			Warning.warnFuntionTimer("UserLogHistoryDao", "saveUserLoginHistory", null, (System.currentTimeMillis() - begintimer), begintimer);
			
			return i;
			
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"saveUserLoginHistory error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

}
