package com.hefei.rms.socialsecurity.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.socialsecurity.dao.ISSTransactionDao;
import com.hefei.rms.socialsecurity.dao.ISocialsecurityDao;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.rms.socialsecurity.service.ISocialsecurityService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;
@Service
public class SocialsecurityService implements ISocialsecurityService {

	private static final Logger logger = Logger.getLogger(SocialsecurityService.class);
	@Autowired
	private ISocialsecurityDao socialsecurityDao;
	@Autowired
	private ISSTransactionDao ssTransactionDao;
	
	@Override
	public EmployeeSS getEmployeeSS(Long companyId, Long employeeId) throws ServiceException {
		try {
			return socialsecurityDao.getEmployeeSS(companyId, employeeId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public EmployeeSSTransaction getEmployeeSSTransaction(Long ssTransactionId) throws ServiceException {
		try {
			return ssTransactionDao.getEmployeeSSTransaction(ssTransactionId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	
	public EmployeeSS createEmployeeSS(EmployeeSS employeeSS) throws ServiceException{
		try {
			Date now = Calendar.getInstance().getTime();
			employeeSS.setCreateTime(now);
			employeeSS.setUpdateTime(now);
			return socialsecurityDao.createEmployeeSS(employeeSS);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public void saveAdjustSS(EmployeeSSDTO employeeSSDTO) throws ServiceException {
		try {
			EmployeeSS employeeSS = new EmployeeSS();
			BeanUtils.copyProperties(employeeSS, employeeSSDTO);
			employeeSS.setUpdateTime(new Date());
			socialsecurityDao.updateEmployeeSS(employeeSS);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (IllegalAccessException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveSalary error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		} catch (InvocationTargetException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveSalary error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public void saveAdjustEmployeeSSTransaction(EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark,Long currentUsrId) throws ServiceException {
		try {
			EmployeeSSTransaction employeeSSTransaction = new EmployeeSSTransaction();
			BeanUtils.copyProperties(employeeSSTransaction, employeeSSTransactionDTO);
			employeeSSTransaction.setUpdateTime(new Date());
			employeeSSTransaction.setRemark(remark);
			ssTransactionDao.updateSSTransaction(employeeSSTransaction);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (IllegalAccessException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveSalary error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		} catch (InvocationTargetException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveSalary error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public void paySS(Long companyId, Long employeeId, Long userId,String transactionIds) throws ServiceException {
		try{
			List<Long> transactionIdList = new ArrayList<Long>();
			if(StringUtils.isNotBlank(transactionIds)){
				String[] transactionIdsStr = transactionIds.split(",");
				for(String id: transactionIdsStr){
					if(StringUtils.isNotBlank(id)){
						transactionIdList.add(Long.valueOf(id));
					}
				}
			}
			if(transactionIdList.size() > 0)
				ssTransactionDao.updateSSTransactionPayed(transactionIdList, EmployeeSSTransactionDTO.STATUS_PAYED, userId, new Date());
		}catch(DaoException e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DAO);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
