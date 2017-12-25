package com.hefei.rms.physical.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.physical.dao.IPhysicalDao;
import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.rms.physical.service.IPhysicalService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class PhysicalService implements IPhysicalService {

	private static final Logger logger = Logger.getLogger(PhysicalService.class);
	@Autowired
	private IPhysicalDao physicalDao;
	
	@Override
	public PhysicalInfo findPhysical(Long employeeId) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			return physicalDao.findPhysical(employeeId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findPhysical error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public void savePhysical(PhysicalInfo physicalInfo) throws ServiceException {
		if(physicalInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			physicalInfo.setCreateTime(now);
			physicalInfo.setUpdateTime(now);
			
			physicalDao.savePhysical(physicalInfo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " savePhysical error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public void updatePhysical(PhysicalInfo physicalInfo) throws ServiceException {
		if(physicalInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			physicalInfo.setUpdateTime(now);
			
			physicalDao.updatePhysical(physicalInfo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updatePhysical error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	

}
