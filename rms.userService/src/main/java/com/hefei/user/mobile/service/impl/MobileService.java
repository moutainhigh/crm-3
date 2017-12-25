package com.hefei.user.mobile.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.email.po.Email;
import com.hefei.user.mobile.dao.IMobileDao;
import com.hefei.user.mobile.po.Mobile;
import com.hefei.user.mobile.service.IMobileService;

@Service
public class MobileService implements IMobileService{

	private static Logger logger= Logger.getLogger(MobileService.class);
	
	@Autowired
	private IMobileDao mobileDao;
	
	@Override
	public Mobile saveMobile(Mobile mobile) throws ServiceException {
		try {
			return mobileDao.saveMobile(mobile);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public Mobile getByMobile(String mobile, String type) throws ServiceException {
		try {
			return mobileDao.getByMobile(mobile, type);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}
	public Mobile getLoginMobileByUserId(Long userId) throws ServiceException {
		try {
			return mobileDao.getLoginMobileByUserId(userId);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}
}
