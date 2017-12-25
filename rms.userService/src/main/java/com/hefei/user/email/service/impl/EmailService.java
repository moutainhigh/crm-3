package com.hefei.user.email.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.email.dao.IEmailDao;
import com.hefei.user.email.po.Email;
import com.hefei.user.email.service.IEmailService;

@Service
public class EmailService implements IEmailService{
	private static Logger logger= Logger.getLogger(EmailService.class);
	@Autowired
	private IEmailDao emailDao;
	
	@Override
	public Email saveEmail(Email email) throws ServiceException {
		try {
			return emailDao.saveEmail(email);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public Email getByEmail(String email, String type) throws ServiceException {
		try {
			return emailDao.getByEmail(email, type);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}

	public Email getLoginEmailByUserId(Long userId) throws ServiceException {
		try {
			return emailDao.getLoginEmailByUserId(userId);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "error", e);
			throw new ServiceException(e.getErrorCode());
		}
	}
}
