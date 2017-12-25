package com.hefei.user.info.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;
import com.hefei.user.info.dao.IUserDao;
import com.hefei.user.info.mapper.UserMapper;
import com.hefei.user.info.po.User;

@Repository
public class UserDao implements IUserDao{
	
	private static final Logger logger = Logger.getLogger(UserDao.class);
	
	@Autowired
	private UserMapper userMapper;
	private IdManager idManager = new IdManagerImpl();
	
	@Override
	public User saveUser(User user) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			user.setId(idManager.getNextId());
			userMapper.saveUser(user);
			Warning.warnFuntionTimer("UserDao", "saveUser", null, (System.currentTimeMillis() - begintimer), begintimer);
			return user;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public User getById(Long id) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			User user = userMapper.getById(id);
			Warning.warnFuntionTimer("UserDao", "getById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return user;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	@Override
	public User getByIdNo(String idNo) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			User user = userMapper.getByIdNo(idNo);
			Warning.warnFuntionTimer("UserDao", "getByIdNo", null, (System.currentTimeMillis() - begintimer), begintimer);
			return user;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void updateUser(User user)throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			userMapper.updateUser(user);
			Warning.warnFuntionTimer("UserDao", "updateUser", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
