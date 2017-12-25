package com.hefei.rms.salary.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.salary.dao.ISalaryHistoryDao;
import com.hefei.rms.salary.mapper.SalaryHistoryMapper;
import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SalaryHistoryDao implements ISalaryHistoryDao{

	private static final Logger logger = Logger.getLogger(SalaryHistoryDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SalaryHistoryMapper salaryHistoryMapper;
	@Override
	public void saveSalaryHistory(SalaryHistory salaryHistoryInfo) throws DaoException {
		if(salaryHistoryInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			salaryHistoryInfo.setId(id);
			
			salaryHistoryMapper.saveSalaryHistory(salaryHistoryInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveSalaryHistory error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryHistoryDao", "saveSalaryHistory", null, System.currentTimeMillis() - beginTimer, beginTimer);
		
		
	}
	@Override
	public List<SalaryHistory> findSalaryHistoryPaginationItems(Long companyId,
			Long employeeId, String cardNo, String employeeName, int pageIndex,int pageSize) throws DaoException {
		long beginTimer = System.currentTimeMillis();
		List<SalaryHistory> list = null;
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeId", employeeId);
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			list = salaryHistoryMapper.findSalaryHistoryPaginationItems(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryHistoryDao", "findSalaryHistoryPaginationItems", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return list;
	}
	@Override
	public int findSalaryHistoryPaginationCount(Long companyId, Long employeeId, String cardNo, String employeeName)
			throws DaoException {
		long beginTimer = System.currentTimeMillis();
		int count = 0;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeId", employeeId);
			count = salaryHistoryMapper.findSalaryHistoryPaginationCount(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryHistoryDao", "findSalaryHistoryPaginationCount", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return count;
	}

}
