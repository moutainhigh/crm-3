package com.hefei.rms.leave.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.rms.leave.dao.ILeaveDao;
import com.hefei.rms.leave.po.Leave;
import com.hefei.rms.leave.service.ILeaveService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class LeaveService implements ILeaveService{

	private static final Logger logger = Logger.getLogger(LeaveService.class);
	
	@Autowired
	private ILeaveDao leaveDao;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Override
	public Pagination<Leave> findLeave(String employeeName, Long employeeId,
			String cardNo, Long companyId, String leaveType,
			Long auditEmployeeId, String auditStatus, Date startTime,
			Date endTime, int pageIndex, int pageSize) throws ServiceException {
		Pagination<Leave> pagination = new Pagination<Leave>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			List<String> leaveTypeList =  null;
			if(StringUtils.isNotBlank(leaveType)){
				leaveTypeList = new ArrayList<String>();
				String[] leaveTypeArray = leaveType.split(",");
				for(int i=0; i<leaveTypeArray.length; i++){
					if(StringUtils.isNotBlank(leaveTypeArray[i])){
						leaveTypeList.add(leaveTypeArray[i]);
					}
				}
			}
			
			List<String> auditStatusList =  null;
			if(StringUtils.isNotBlank(auditStatus)){
				auditStatusList = new ArrayList<String>();
				String[] auditStatusArray = auditStatus.split(",");
				for(int i=0; i<auditStatusArray.length; i++){
					if(StringUtils.isNotBlank(auditStatusArray[i])){
						auditStatusList.add(auditStatusArray[i]);
					}
				}
			}
			List<Leave> leaves = leaveDao.getLeavePaginationItems(employeeName, employeeId, cardNo, companyId, leaveTypeList, auditEmployeeId, auditStatusList, startTime, endTime, pageIndex, pageSize);
			int count = leaveDao.getLeavePaginationCount(employeeName, employeeId, cardNo, companyId, leaveTypeList, auditEmployeeId, auditStatusList, startTime, endTime);
			pagination.setItems(leaves);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Leave applyLeave(Leave leave) throws ServiceException {
		if(leave == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeInfo employee = employeeService.getEmployeeInfo(leave.getEmployeeId());
			
			Date now = Calendar.getInstance().getTime();
			leave.setCreateTime(now);
			leave.setUpdateTime(now);
			leave.setDelFlag(LeaveDTO.DEL_FLAG_NO);
			if(employee.getSuperior() != null){
				leave.setAuditEmployeeId(employee.getSuperior());
				leave.setAuditEmployeeName(employee.getSuperiorEmployeeName());
				leave.setAuditStatus(LeaveDTO.AUDIT_STATUS_TODO);
			}else{
				leave.setAuditStatus(LeaveDTO.AUDIT_STATUS_PASS);
			}
			
			leave = leaveDao.saveLeave(leave);
			return leave;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void audit(Long auditEmployeeId, Long leaveId, String auditStatus)
			throws ServiceException {
		if(auditEmployeeId == null || leaveId == null|| auditStatus == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			leaveDao.saveLeaveAudit(auditEmployeeId, leaveId, auditStatus);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public Leave get(Long leaveId) throws ServiceException {
		if(leaveId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Leave leave = leaveDao.getLeave(leaveId);
			return leave;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
