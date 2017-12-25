package com.hefei.rms.socialsecurity.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.socialsecurity.dao.ISocialsecurityDao;
import com.hefei.rms.socialsecurity.mapper.SocialsecurityMapper;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SocialsecurityDao implements ISocialsecurityDao{

	private static final Logger logger = Logger.getLogger(SocialsecurityDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SocialsecurityMapper socialsecurityMapper;
	@Override
	public EmployeeSS getEmployeeSS(Long companyId, Long employeeId) throws DaoException {
		if(companyId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		EmployeeSS employeeSS = null;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			employeeSS = socialsecurityMapper.getEmployeeSS(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SocialsecurityDao", "getEmployeeSS", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return employeeSS;
	}
	@Override
	public EmployeeSS createEmployeeSS(EmployeeSS employeeSS) throws DaoException {
		if(employeeSS == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			employeeSS.setId(id);
			socialsecurityMapper.saveEmployeeSS(employeeSS);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SocialsecurityDao", "createEmployeeSS", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return employeeSS;
	}
	@Override
	public void updateEmployeeSS(EmployeeSS employeeSS) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			socialsecurityMapper.updateEmployeeSS(employeeSS);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SocialsecurityDao", "updateEmployeeSS", null, System.currentTimeMillis() - begintimer, begintimer);
	}

}
