package com.hefei.rms.behavior.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.behavior.dao.IBehaviorDao;
import com.hefei.rms.behavior.mapper.BehaviorMapper;
import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class BehaviorDao implements IBehaviorDao{

	private static final Logger logger = Logger.getLogger(BehaviorDao.class);
	
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private BehaviorMapper beMapper;
	@Override
	public void saveBehaviorInfo(BehaviorInfo beInfo) throws DaoException {
		if(beInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try{
			Long id = idManager.getNextId();
			beInfo.setId(id);
			beMapper.saveBehaviorInfo(beInfo);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" saveBehaviorInfo error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("BehaviorDao", "saveBehaviorInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public void updateBehavior(Long id, String status, Date updateTime) throws DaoException {
		if(id == null || StringUtils.isBlank(status)){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("status", status);
			map.put("updateTime", updateTime);
			beMapper.updateBehavior(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" updateBehavior error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("BehaviorDao", "updateBehavior", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public List<BehaviorInfo> findBehavior(Long employeeId, String month) throws DaoException {
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		List<BehaviorInfo> list = null;
		try{
			list = beMapper.findBehavior(employeeId, month);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" saveBehaviorInfo error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("BehaviorDao", "saveBehaviorInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
		return list;
	}

	
}
