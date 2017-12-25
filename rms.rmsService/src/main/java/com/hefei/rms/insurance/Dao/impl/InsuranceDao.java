package com.hefei.rms.insurance.Dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.insurance.Dao.IInsuranceDao;
import com.hefei.rms.insurance.mapper.InsuranceMapper;
import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class InsuranceDao implements IInsuranceDao {

	private static final Logger logger = Logger.getLogger(InsuranceDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	@Override
	public InsuranceInfo findInsurance(Long employeeId) throws DaoException {
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		InsuranceInfo insuranceInfo = null;
		try {
			insuranceInfo = insuranceMapper.findInsurance(employeeId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" saveInsurance  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("InsuranceDao", "findInsurance", null, (System.currentTimeMillis() - begintimer), begintimer);
		return insuranceInfo;
	}
	@Override
	public void saveInsurance(InsuranceInfo insuranceInfo) throws DaoException {
		if(insuranceInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			insuranceInfo.setId(id);
			insuranceMapper.saveInsurance(insuranceInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" saveInsurance  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("InsuranceDao", "saveInsurance", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public void updateInsurance(InsuranceInfo insuranceInfo) throws DaoException {
		if(insuranceInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			insuranceMapper.updateInsurance(insuranceInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" updateInsurance  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("InsuranceDao", "updateInsurance", null, (System.currentTimeMillis() - begintimer), begintimer);
		
	}
	

}
