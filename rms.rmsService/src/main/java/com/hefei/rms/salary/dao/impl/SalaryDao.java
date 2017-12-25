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
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.salary.dao.ISalaryDao;
import com.hefei.rms.salary.mapper.SalaryMapper;
import com.hefei.rms.salary.po.Salary;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class SalaryDao implements ISalaryDao {

	private static final Logger logger = Logger.getLogger(SalaryDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private SalaryMapper salaryMapper;
	
	@Override
	public Salary saveSalary(Salary salaryInfo) throws DaoException {
		if(salaryInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			salaryInfo.setId(id);
			salaryMapper.saveSalary(salaryInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryDao", "saveSalary", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return salaryInfo;
	}


	@Override
	public void updateSalary(Long id, Double monthlyBaseSalary,Double monthlyBonus, Double quarterlyBonus, Double yearlyBonus,Date updateTime) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("id", id);
			map.put("monthlyBaseSalary", monthlyBaseSalary);
			map.put("monthlyBonus", monthlyBonus);
			map.put("quarterlyBonus", quarterlyBonus);
			map.put("yearlyBonus", yearlyBonus);
			map.put("updateTime", updateTime);
			salaryMapper.updateSalary(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryDao", "updateSalary", null, System.currentTimeMillis() - begintimer, begintimer);
		
	}


	@Override
	public Salary getSalary(Long companyId, Long employeeId)throws DaoException {
		if(companyId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long beginTimer = System.currentTimeMillis();
		Salary salary = null;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			salary = salaryMapper.getSalary(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryDao", "getSalary", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return salary;
	}


	@Override
	public List<SalarySSDTO> findSalaryPaginationItems(Long companyId,String cardNo, String employeeName,
			List<String> employeeCompanyStatus, int pageIndex, int pageSize)throws DaoException {
		long beginTimer = System.currentTimeMillis();
		List<SalarySSDTO> list = null;
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			list = salaryMapper.findSalaryPaginationItems(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryDao", "findSalaryPaginationItems", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return list;
	}


	@Override
	public int findSalaryPaginationCount(Long companyId, String cardNo,
			String employeeName, List<String> employeeCompanyStatus)throws DaoException {
		long beginTimer = System.currentTimeMillis();
		int count = 0;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("cardNo", cardNo);
			map.put("employeeName", employeeName);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			count = salaryMapper.findSalaryPaginationCount(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("SalaryDao", "findSalaryPaginationCount", null, System.currentTimeMillis() - beginTimer, beginTimer);
		return count;
	}
	

}
