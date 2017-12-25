package com.hefei.rms.employee.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.employee.dao.IEmployeeDao;
import com.hefei.rms.employee.mapper.EmployeeMapper;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.po.EmployeePosition;
import com.hefei.rms.employee.po.UserEmployee;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;
@SuppressWarnings({"unchecked","rawtypes"})
@Repository
public class EmployeeDao implements IEmployeeDao{

	private static final Logger logger = Logger.getLogger(EmployeeDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private EmployeeMapper employeeMapper;
	@Override
	public Employee saveEmployee(Employee employee) throws DaoException {
		if(employee == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			employee.setId(id);
			employeeMapper.saveEmployee(employee);
			Warning.warnFuntionTimer("EmployeeDao", "saveEmployee", null, System.currentTimeMillis() - begintimer, begintimer);
			return employee;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveEmployee error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	public EmployeeCompany getEmployeeCompany(Long id) throws DaoException{
		if(id == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		EmployeeCompany employeeCompany = null;
		try {
			employeeCompany = employeeMapper.getEmployeeCompanyById(id);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployeeCompany", null, System.currentTimeMillis() - begintimer, begintimer);
		return employeeCompany;
	}
	
	public EmployeeCompany getEmployeeCompany(Long companyId, Long employeeId) throws DaoException{
		if(companyId == null || employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		EmployeeCompany employeeCompany = null;
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			employeeCompany = employeeMapper.getEmployeeCompany(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployeeCompany", null, System.currentTimeMillis() - begintimer, begintimer);
		return employeeCompany;
	}
	public EmployeeCompany saveEmployeeCompany(EmployeeCompany employeeCompany) throws DaoException{
		if(employeeCompany == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			employeeCompany.setId(id);
			employeeMapper.saveEmployeeCompany(employeeCompany);
			Warning.warnFuntionTimer("EmployeeDao", "saveEmployeeCompany", null, System.currentTimeMillis() - begintimer, begintimer);
			return employeeCompany;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	public void updateEmployeeCompany(EmployeeCompany employeeCompany) throws DaoException{
		if(employeeCompany == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			employeeMapper.updateEmployeeCompany(employeeCompany);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "updateEmployeeCompany", null, System.currentTimeMillis() - begintimer, begintimer);
		
	}
	public UserEmployee getUserEmployeeByUserId(Long userid) throws DaoException{
		if(userid == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		UserEmployee userEmployee = null;
		try {
			userEmployee = employeeMapper.getUserEmployeeByUserId(userid);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getUserEmployeeByUserId", null, System.currentTimeMillis() - begintimer, begintimer);
		return userEmployee;
	}
	public UserEmployee getUserEmployeeByEmployeeId(Long employeeId) throws DaoException{
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		UserEmployee userEmployee = null;
		try {
			userEmployee = employeeMapper.getUserEmployeeByEmployeeId(employeeId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getUserEmployeeByEmployeeId", null, System.currentTimeMillis() - begintimer, begintimer);
		return userEmployee;
	}
	public UserEmployee saveUserEmployee(UserEmployee userEmployee) throws DaoException{
		if(userEmployee == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			userEmployee.setId(id);
			employeeMapper.saveUserEmployee(userEmployee);
			Warning.warnFuntionTimer("EmployeeDao", "saveUserEmployee", null, System.currentTimeMillis() - begintimer, begintimer);
			return userEmployee;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public EmployeePosition saveEmployeePosition(EmployeePosition employeePosition) throws DaoException{
		if(employeePosition == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			employeePosition.setId(id);
			employeeMapper.saveEmployeePosition(employeePosition);
			Warning.warnFuntionTimer("EmployeeDao", "saveEmployeePosition", null, System.currentTimeMillis() - begintimer, begintimer);
			return employeePosition;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public void updateEmployee(Employee employeeInfo) throws DaoException {
		if(employeeInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			employeeMapper.updateEmployee(employeeInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updateEmployee error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "updateEmployee", null, System.currentTimeMillis() - begintimer, begintimer);
		
	}
	
	public List<EmployeeInfo> getEmployeePaginationItems(String cardNo, List<String> sex, List<String> status, Long companyId, Integer pageIndex, Integer pageSize) throws DaoException{
		long begintimer = System.currentTimeMillis();
		List<EmployeeInfo> list = null;
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("cardNo", cardNo);
			map.put("companyId", companyId);
			map.put("employeeSex", sex);
			map.put("employeeCompanyStatus", status);
			
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			
			list = employeeMapper.getEmployeePaginationItems(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployeePaginationItems", null, System.currentTimeMillis() - begintimer, begintimer);
		return list;
	}
	public Integer getEmployeePaginationCount(String cardNo, List<String> sex, List<String> status, Long companyId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("cardNo", cardNo);
			map.put("companyId", companyId);
			map.put("employeeSex", sex);
			map.put("employeeCompanyStatus", status);
			int count = employeeMapper.getEmployeePaginationCount(map);
			Warning.warnFuntionTimer("EmployeeDao", "getEmployeePaginationCount", null,(System.currentTimeMillis() - begintimer), begintimer);
			return count;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	
	@Override
	public EmployeeInfo getEmployee(Long id) throws DaoException {
		if(id == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		EmployeeInfo employee = null;
		try {
			employee = employeeMapper.getEmployee(id);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployee", null, System.currentTimeMillis() - begintimer, begintimer);
		return employee;
	}
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws DaoException{
		long begintimer = System.currentTimeMillis();
		EmployeeInfo employee = null;
		try {
			employee = employeeMapper.getEmployeeByIdNo(idNo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployeeByIdNo", null, System.currentTimeMillis() - begintimer, begintimer);
		return employee;
	}
	public EmployeeInfo getEmployeeByUserId(Long userId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		EmployeeInfo employee = null;
		try {
			employee = employeeMapper.getEmployeeByUserId(userId);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("EmployeeDao", "getEmployeeByUserId", null, System.currentTimeMillis() - begintimer, begintimer);
		return employee;
	}
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			List<EmployeeDepartPositionDTO> list = employeeMapper.getEmployeeDepartPosition(map);
			Warning.warnFuntionTimer("EmployeeDao", "getEmployeeDepartPosition", null,(System.currentTimeMillis() - begintimer), begintimer);
			return list;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId,Long departmentId, Long positionId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("companyId", companyId);
			map.put("employeeId", employeeId);
			map.put("departmentId", departmentId);
			map.put("positionId", positionId);
			employeeMapper.updateEmployeeDepartPosition(map);
			Warning.warnFuntionTimer("EmployeeDao", "updateEmployeeDepartPosition", null,(System.currentTimeMillis() - begintimer), begintimer);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
