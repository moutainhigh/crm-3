package com.hefei.rms.company.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeCompanyInfo;
import com.hefei.api.rms.employee.vo.UserCompanyInfo;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.company.dao.ICompanyDao;
import com.hefei.rms.company.mapper.CompanyMapper;
import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class CompanyDao implements ICompanyDao{
	private static final Logger logger = Logger.getLogger(CompanyDao.class);

	@Autowired
	private CompanyMapper companyMapper;
	
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	
	/**
	 * 创建公司
	 * @author zn
	 * 
	 */
	@Override
	public Company saveCompany(Company companyInfo) throws DaoException {
		if(companyInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			companyInfo.setId(id);
			companyMapper.saveCompany(companyInfo);
			Warning.warnFuntionTimer("CompanyDao", "createCompany", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void updateCompany(Company companyInfo) throws DaoException{
		if(companyInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			companyInfo.setUpdateTime(Calendar.getInstance().getTime());
			companyMapper.updateCompany(companyInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("CompanyDao", "updateCompany", null,(System.currentTimeMillis() - begintimer), begintimer);
	}
	public CompanyIndustry saveCompanyIndustry(CompanyIndustry companyIndustry) throws DaoException{
		if(companyIndustry == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			companyIndustry.setId(id);
			companyMapper.saveCompanyIndustry(companyIndustry);
			Warning.warnFuntionTimer("CompanyDao", "saveCompanyIndustry", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyIndustry;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void removeCompanyIndustry(Long companyId, List<Long> industryIds) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("industryIds", industryIds);
			map.put("companyId", companyId);
			map.put("updateTime", Calendar.getInstance().getTime());
			map.put("delFlag",  com.hefei.api.rms.company.vo.CompanyIndustryDTO.DEL_FLAG_YES);
			companyMapper.getCompanyByIdAndUser(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("CompanyDao", "getCompany", null,(System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public Company getCompany(Long companyId) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Company companyInfo = companyMapper.getCompany(companyId);
			Warning.warnFuntionTimer("CompanyDao", "getCompany", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public Company getCompany(Long userId, Long companyId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("companyId", companyId);
			Company companyInfo = companyMapper.getCompanyByIdAndUser(map);
			Warning.warnFuntionTimer("CompanyDao", "getCompany", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public Company getCompanyByName(String companyName) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Company companyInfo = companyMapper.getCompanyByName(companyName);
			Warning.warnFuntionTimer("CompanyDao", "getCompanyByName", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	@Override
	public List<Company> getCompanyByUserId(Long userId) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("userCompanyDelFlag", UserCompanyInfo.DEL_FLAG_NO);
			List<String> employeeCompanyStatus = new ArrayList<String>();
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PRACTICE);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PROBATION);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_ONBOARD);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			map.put("companyDelFlag", CompanyInfo.DELFLAG_NO);
			List<Company> companyInfo = companyMapper.getCompanyByUserId(map);
			Warning.warnFuntionTimer("CompanyDao", "getCompanyByUserId", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<Company> getCompanyByEmployeeId(Long employeeId)
			throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("employeeId", employeeId);
			List<String> employeeCompanyStatus = new ArrayList<String>();
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PRACTICE);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PROBATION);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_ONBOARD);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			map.put("companyDelFlag", CompanyInfo.DELFLAG_NO);
			List<Company> companyInfo = companyMapper.getCompanyByEmployeeId(map);
			Warning.warnFuntionTimer("CompanyDao", "getCompanyByEmployeeId", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public List<Company> getManagedPaginationItems(Long userId, String companyName, String delFlag, int pageIndex, int pageSize) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("companyName", companyName);
			map.put("companyDelFlag", delFlag);
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			map.put("userCompanyDelFlag", UserCompanyInfo.DEL_FLAG_NO);
			
			List<String> employeeCompanyStatus = new ArrayList<String>();
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PRACTICE);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PROBATION);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_ONBOARD);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			
			List<Company> companyInfo = companyMapper.getManagedPaginationItems(map);
			Warning.warnFuntionTimer("CompanyDao", "getPaginationItems", null,(System.currentTimeMillis() - begintimer), begintimer);
			return companyInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public int getManagedPaginationCount(Long userId, String companyName, String delFlag) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("companyName", companyName);
			map.put("companyDelFlag", delFlag);
			map.put("userCompanyDelFlag", UserCompanyInfo.DEL_FLAG_NO);
			
			List<String> employeeCompanyStatus = new ArrayList<String>();
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PRACTICE);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_PROBATION);
			employeeCompanyStatus.add(EmployeeCompanyInfo.STATUS_ONBOARD);
			map.put("employeeCompanyStatus", employeeCompanyStatus);
			int count = companyMapper.getManagedPaginationCount(map);
			Warning.warnFuntionTimer("CompanyDao", "getPaginationCount", null,(System.currentTimeMillis() - begintimer), begintimer);
			return count;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<CompanyIndustry> getCompanyIndustry(Long companyId) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("delFlag", com.hefei.api.rms.company.vo.CompanyIndustryDTO.DEL_FLAG_NO);
			List<CompanyIndustry> list = companyMapper.getCompanyIndustry(map);
			Warning.warnFuntionTimer("CompanyDao", "getCompanyIndustry", null,(System.currentTimeMillis() - begintimer), begintimer);
			return list;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
