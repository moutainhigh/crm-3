package com.hefei.rms.leave.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.leave.po.Leave;
import com.hefei.service.framework.exception.DaoException;

public interface ILeaveDao {

	public Leave saveLeave(Leave leave) throws DaoException;
	
	public void saveLeaveAudit(Long auditEmployeeId, Long leaveId, String auditStatus) throws DaoException;

	public Leave getLeave(Long id) throws DaoException;
	
	public List<Leave> getLeavePaginationItems(String employeeName, Long employeeId,
			String cardNo, Long companyId, List<String> leaveType,
			Long auditEmployeeId, List<String> auditStatus, Date startTime,
			Date endTime, int pageIndex, int pageSize) throws DaoException;
	public Integer getLeavePaginationCount(String employeeName, Long employeeId,
			String cardNo, Long companyId, List<String> leaveType,
			Long auditEmployeeId, List<String> auditStatus, Date startTime,
			Date endTime) throws DaoException;
	
}
