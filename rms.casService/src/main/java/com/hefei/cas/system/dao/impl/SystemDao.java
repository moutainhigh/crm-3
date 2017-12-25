package com.hefei.cas.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.cas.system.dao.ISystemDao;
import com.hefei.cas.system.mapper.SystemMapper;
import com.hefei.cas.system.po.Sys;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SystemDao implements ISystemDao{

	private static final Logger logger = Logger.getLogger(SystemDao.class);
	
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SystemMapper systemMapper;
	
	public void saveSystem(Sys system) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			system.setId(idManager.getNextId());
			systemMapper.saveSystem(system);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("SystemDao", "saveSystem", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	
	public Sys getById(Long id) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Sys sys= systemMapper.getById(id);
			Warning.warnFuntionTimer("SystemDao", "getById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return sys;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public Sys getBySystemName(String systemName) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Sys sys= systemMapper.getBySystemName(systemName);
			Warning.warnFuntionTimer("SystemDao", "getBySystemName", null, (System.currentTimeMillis() - begintimer), begintimer);
			return sys;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public int getTotalCount(String systemName) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("systemName", systemName);
			int count = systemMapper.getTotalCount(map);
			Warning.warnFuntionTimer("SystemDao", "getTotalCount", null, (System.currentTimeMillis() - begintimer), begintimer);
			return count;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public List<Sys> findSys(String systemName, int pageIndex, int pageSize) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			int startSize = (pageIndex -1) * pageSize;
			Map map = new HashMap();
			map.put("systemName", systemName);
			map.put("startSize", startSize);
			map.put("pageSize", pageSize);
			
			List<Sys> list = systemMapper.findSys(map);
			
			Warning.warnFuntionTimer("SystemDao", "findSys", null, (System.currentTimeMillis() - begintimer), begintimer);
			return list;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
