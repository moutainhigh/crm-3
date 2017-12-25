package com.hefei.user.email.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.email.dao.IEmailDao;
import com.hefei.user.email.mapper.EmailMapper;
import com.hefei.user.email.po.Email;

@Repository
public class EmailDao implements IEmailDao{
	private static Logger logger= Logger.getLogger(EmailDao.class);
	
	private IdManager idManager = new IdManagerImpl();
	
	@Autowired
	private EmailMapper emailMapper;
	
	public Email saveEmail(Email email) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			email.setId(idManager.getNextId());
			emailMapper.saveEmail(email);
			Warning.warnFuntionTimer("EmailDao", "saveEmail", null, (System.currentTimeMillis() - begintimer), begintimer);
			return email;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Email getByEmail(String email, String type) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("email", email);
			map.put("type", type);
			Email e = emailMapper.getByEmail(map);
			Warning.warnFuntionTimer("EmailDao", "getByEmail", null, (System.currentTimeMillis() - begintimer), begintimer);
			return e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public Email getLoginEmailByUserId(Long userId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("type", EmailInfo.TYPE_LOGIN);
			Email e = emailMapper.getLoginEmailByUserId(map);
			Warning.warnFuntionTimer("EmailDao", "getLoginEmailByUserId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
