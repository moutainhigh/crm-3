package com.hefei.user.mobile.dao.impl;

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
import com.hefei.user.mobile.dao.IMobileDao;
import com.hefei.user.mobile.mapper.MobileMapper;
import com.hefei.user.mobile.po.Mobile;

@Repository
public class MobileDao implements IMobileDao{
	
	private static Logger logger= Logger.getLogger(MobileDao.class);

	private IdManager idManager = new IdManagerImpl();
	
	@Autowired
	private MobileMapper mobileMapper;
	
	@Override
	public Mobile saveMobile(Mobile mobile) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			mobile.setId(idManager.getNextId());
			mobileMapper.saveMobile(mobile);
			Warning.warnFuntionTimer("MobileDao", "saveMobile", null, (System.currentTimeMillis() - begintimer), begintimer);
			return mobile;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Mobile getByMobile(String mobile, String type) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("mobile", mobile);
			map.put("type", type);
			Mobile e = mobileMapper.getByMobile(map);
			Warning.warnFuntionTimer("MobileDao", "getByMobile", null, (System.currentTimeMillis() - begintimer), begintimer);
			return e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public Mobile getLoginMobileByUserId(Long userId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("type", EmailInfo.TYPE_LOGIN);
			Mobile e = mobileMapper.getLoginMobileByUserId(map);
			Warning.warnFuntionTimer("MobileDao", "getLoginMobileByUserId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
