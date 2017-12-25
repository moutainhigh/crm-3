package com.hefei.user.info.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.info.dao.IUserDao;
import com.hefei.user.info.po.User;
import com.hefei.user.info.service.IUserService;

@Service
public class UserService implements IUserService{

	private static Logger logger= Logger.getLogger(UserService.class);
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User saveUser(User user) throws ServiceException {
		try {
			return userDao.saveUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	public User getById(Long id) throws ServiceException{
		try {
			return userDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public User getByIdNo(String idNo) throws ServiceException {
		try {
			return userDao.getByIdNo(idNo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	public void updateUser(User user)throws ServiceException{
		try {
			userDao.updateUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}

}
