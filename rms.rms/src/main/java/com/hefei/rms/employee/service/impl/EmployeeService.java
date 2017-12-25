package com.hefei.rms.employee.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.employee.manager.IEmployeeManager;
import com.hefei.api.rms.employee.manager.impl.EmployeeManager;
import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.user.api.email.manager.IEmailManager;
import com.hefei.user.api.email.manager.impl.EmailManager;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.api.mobile.manager.IMobileManager;
import com.hefei.user.api.mobile.manager.impl.MobileManager;
import com.hefei.user.api.mobile.vo.MobileInfo;

@Service
public class EmployeeService implements IEmployeeService{

	private static final Logger logger = Logger.getLogger(EmployeeService.class);
	
	private IEmployeeManager employeeManager = new EmployeeManager();
	
	private IMobileManager mobileManager = new MobileManager();
	private IEmailManager emailManager = new EmailManager();
	
	@Override
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws BusinessException {
		if(salaryInfo == null){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(employeeInfo == null){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(companyId == null){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(departmentId == null){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(positionId == null){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			return employeeManager.createEmployee(salaryInfo, employeeInfo, companyId, departmentId, positionId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	public void updateEmployee(EmployeeInfo employeeInfo) throws BusinessException{
		try {
			employeeManager.updateEmployee(employeeInfo);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws BusinessException{
		try {
			return employeeManager.getEmployeeDepartPosition(companyId, employeeId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId, Long departmentId,Long positionId)  throws BusinessException{
		try {
			employeeManager.updateEmployeeDepartPosition(companyId, employeeId, departmentId,positionId);;
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
	}
	
	@Override
	public Pagination<EmployeeInfo> findEmployeePagination(String cardNo,String sex, String status, Long companyId, int pageIndex,int  pageSize) throws BusinessException {
		try {
			return employeeManager.findEmployeePagination(cardNo, sex, status, companyId, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	
	public EmployeeInfo getEmployee(Long employeeId) throws BusinessException{
		try {
			return employeeManager.getEmployee(employeeId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	public EmployeeInfo getEmployee(Long companyId,Long employeeId) throws BusinessException{
		try {
			return employeeManager.getEmployee(companyId,employeeId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	@Override
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws BusinessException {
		try {
			EmployeeInfo employeeInfo = employeeManager.getEmployeeByIdNo(idNo);
			if(employeeInfo == null)
				return null;
			try {
				MobileInfo mobileInfo = mobileManager.getLoginMobileByUserId(employeeInfo.getUserId());
				if(mobileInfo != null){
					employeeInfo.setMobile(mobileInfo.getMobile());
				}
			} catch (ClientException e) {
				logger.error(RequestThreadLocal.getTimer() + " error", e);
			}
			try {
				EmailInfo emailInfo = emailManager.getLoginEmailByUserId(employeeInfo.getUserId());
				if(emailInfo != null){
					employeeInfo.setEmail(emailInfo.getEmail());
				}
			} catch (ClientException e) {
				logger.error(RequestThreadLocal.getTimer() + " error", e);
			}
			return employeeInfo;
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	@Override
	public void grantEmployeePosition(Long employeeId, String positionIds) throws BusinessException {
		try {
			employeeManager.grantEmployeePosition(employeeId, positionIds);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
		
	}
}
