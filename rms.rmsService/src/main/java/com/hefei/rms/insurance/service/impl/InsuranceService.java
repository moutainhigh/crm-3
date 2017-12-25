package com.hefei.rms.insurance.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.insurance.Dao.IInsuranceDao;
import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.rms.insurance.service.IInsuranceService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class InsuranceService implements IInsuranceService{

	private static final Logger logger = Logger.getLogger(InsuranceService.class);
	
	@Autowired
	private IInsuranceDao insuranceDao;
		
	@Override
	public InsuranceInfo findInsurance(Long employeeId) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			return insuranceDao.findInsurance(employeeId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findInsurance error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	@Override
	public void saveInsurance(InsuranceInfo insuranceInfo) throws ServiceException {
		if(insuranceInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			insuranceInfo.setCreateTime(now);
			insuranceInfo.setUpdateTime(now);
			
			insuranceDao.saveInsurance(insuranceInfo);
			
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveInsurance error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public void updateInsurance(InsuranceInfo insuranceInfo) throws ServiceException {
		if(insuranceInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			insuranceInfo.setUpdateTime(now);
			
			insuranceDao.updateInsurance(insuranceInfo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveInsurance error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

}
