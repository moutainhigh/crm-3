package com.hefei.rms.physical.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.physical.dao.IPhysicalDao;
import com.hefei.rms.physical.mapper.PhysicalMapper;
import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class PhysicalDao implements IPhysicalDao{

	private static final Logger logger = Logger.getLogger(PhysicalDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private PhysicalMapper physicalMapper;
	
	@Override
	public PhysicalInfo findPhysical(Long employeeId) throws DaoException {
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		PhysicalInfo physicalInfo = null;
		try {
			physicalInfo = physicalMapper.findPhysical(employeeId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" findPhysical  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("PhysicalDao", "findPhysical", null, (System.currentTimeMillis() - begintimer), begintimer);
		return physicalInfo;
	}
	@Override
	public void savePhysical(PhysicalInfo physicalInfo) throws DaoException {
		if(physicalInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			physicalInfo.setId(id);
			physicalMapper.savePhysical(physicalInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" savePhysical  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("PhysicalDao", "savePhysical", null, (System.currentTimeMillis() - begintimer), begintimer);
		
	}
	@Override
	public void updatePhysical(PhysicalInfo physicalInfo) throws DaoException {
		if(physicalInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			physicalMapper.updatePhysical(physicalInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" updatePhysical  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("PhysicalDao", "updatePhysical", null, (System.currentTimeMillis() - begintimer), begintimer);
		
	}
	

}
