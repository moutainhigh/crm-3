package com.hefei.rms.salary.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.salary.dao.ISalaryTransactionDao;
import com.hefei.rms.salary.mapper.SalaryTransactionMapper;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SalaryTransactionDao implements ISalaryTransactionDao{

	private static final Logger logger = Logger.getLogger(SalaryTransactionDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SalaryTransactionMapper salaryTransactionMapper;
	@Override
	public SalaryTransaction getSalaryTransaction(Long transactionId)throws DaoException {
		if(transactionId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		SalaryTransaction salaryTransaction = null;
		try {
			salaryTransaction = salaryTransactionMapper.getSalaryTransaction(transactionId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "getSalaryTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return salaryTransaction;
	}
	@Override
	public SalaryTransaction saveSalaryTransaction(SalaryTransaction salaryTransaction) throws DaoException {
		if(salaryTransaction == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			salaryTransaction.setId(id);
			salaryTransactionMapper.saveSalaryTransaction(salaryTransaction);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "saveSalaryTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return salaryTransaction;
	}
	@Override
	public void saveSalaryTransaction(List<SalaryTransaction> list)throws DaoException {
		long beginTimer = System.currentTimeMillis();
		try {
			salaryTransactionMapper.saveSalaryTransactions(list);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "saveSalaryTransaction", null, System.currentTimeMillis() - beginTimer, beginTimer);
	}
	@Override
	public void updateSalaryTransactionPayed(List<Long> transactionIds,String status, Long userId, Date payedTime) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("transactionIds", transactionIds);
			map.put("status", status);
			map.put("payedTime", payedTime);
			map.put("updateTime", payedTime);
			salaryTransactionMapper.updateSalaryTransactionPayed(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "updateSalaryTransactionPayed", null, System.currentTimeMillis() - begintimer, begintimer);
		
	}
	@Override
	public void updateSalaryTransaction(Long id, Double monthlyBaseSalary,
			Double monthlyBonus, Double quarterlyBonus, Double yearlyBonus,
			Double deductAmount, Double taxRate, Double taxAmount,
			Double payedAmount, Date updateTime) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("id", id);
			map.put("monthlyBaseSalary", monthlyBaseSalary);
			map.put("monthlyBonus", monthlyBonus);
			map.put("quarterlyBonus", quarterlyBonus);
			map.put("yearlyBonus", yearlyBonus);
			map.put("deductAmount", deductAmount);
			map.put("taxRate", taxRate);
			map.put("taxAmount", taxAmount);
			map.put("payedAmount", payedAmount);
			map.put("updateTime", updateTime);
			salaryTransactionMapper.updateSalaryTransaction(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "updateSalaryTransactionPayed", null, System.currentTimeMillis() - begintimer, begintimer);
		
	}
	@Override
	public List<SalaryPayTransactionDTO> getSalaryPayTransactionPaginationItems(
			Long companyId, Long employeeId, List<String> employeeStatus,List<String> salaryPayStatus, List<String> ssPayStatus,
			String cardNo, String employeeName, int pageIndex, int pageSize)throws DaoException {
		long beginTimer = System.currentTimeMillis();
		List<SalaryPayTransactionDTO> list = null;
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeCompanyStatus", employeeStatus);
			map.put("salaryPayStatus", salaryPayStatus);
			map.put("ssPayStatus", ssPayStatus);
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			list = salaryTransactionMapper.getSalaryPayTransactionPaginationItems(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "getSalaryPayTransactionPaginationItems", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return list;
	}
	@Override
	public int getSalaryPayTransactionPaginationCount(Long companyId,Long employeeId, List<String> employeeStatus,
			List<String> salaryPayStatus, List<String> ssPayStatus,String cardNo, String employeeName) throws DaoException {
		long beginTimer = System.currentTimeMillis();
		int count = 0;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeCompanyStatus", employeeStatus);
			map.put("salaryPayStatus", salaryPayStatus);
			map.put("ssPayStatus", ssPayStatus);
			count = salaryTransactionMapper.getSalaryPayTransactionPaginationCount(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryTransactionDao", "getSalaryPayTransactionPaginationCount", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return count;
	}
	

}
