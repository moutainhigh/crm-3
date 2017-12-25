package com.hefei.rms.socialsecurity.dao.impl;

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
import com.hefei.rms.socialsecurity.dao.ISSTransactionDao;
import com.hefei.rms.socialsecurity.mapper.SSTransactionMapper;
import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SSTransactionDao implements ISSTransactionDao{

	private static final Logger logger = Logger.getLogger(SSTransactionDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SSTransactionMapper ssTransactionMapper;
	
	@Override
	public EmployeeSSTransaction getEmployeeSSTransaction(Long ssTransactionId) throws DaoException {
		if(ssTransactionId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		EmployeeSSTransaction employeeSSTransaction = null;
		try {
			employeeSSTransaction = ssTransactionMapper.getEmployeeSSTransaction(ssTransactionId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SSTransactionDao", "getEmployeeSSTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return employeeSSTransaction;
	}
	@Override
	public void saveSSTransaction(List<EmployeeSSTransaction> employeeSSTransactionList) throws DaoException {
		long beginTimer = System.currentTimeMillis();
		try {
			ssTransactionMapper.saveSSTransaction(employeeSSTransactionList);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SSTransactionDao", "saveSSTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
		
	}
	@Override
	public void updateSSTransaction(EmployeeSSTransaction ssTransaction)throws DaoException {
		long beginTimer = System.currentTimeMillis();
		try {
			ssTransactionMapper.updateSSTransaction(ssTransaction);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SSTransactionDao", "updateSSTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
	}
	@Override
	public void updateSSTransactionPayed(List<Long> transactionIds, String status, Long userId, Date payedTime) throws DaoException {
		long beginTimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("transactionIds", transactionIds);//是T_SALARY_TRANSACTION.ID
			map.put("status", status);
			map.put("payedTime", payedTime);
			map.put("updateTime", payedTime);
			ssTransactionMapper.updateSSTransactionPayed(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SSTransactionDao", "updateSSTransactionPayed", null, System.currentTimeMillis() - beginTimer, beginTimer);
	}

}
