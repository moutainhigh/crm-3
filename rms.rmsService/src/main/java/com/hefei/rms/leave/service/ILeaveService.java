package com.hefei.rms.leave.service;

import java.util.Date;

import com.hefei.common.page.Pagination;
import com.hefei.rms.leave.po.Leave;
import com.hefei.service.framework.exception.ServiceException;

public interface ILeaveService {

	public Pagination<Leave> findLeave(String employeeName, Long employeeId, 
			String cardNo, Long companyId, String leaveType, Long auditEmployeeId,String auditStatus
			, Date startTime, Date endTime, int pageIndex, int pageSize) throws ServiceException;
	
	public Leave applyLeave(Leave leave) throws ServiceException;
	
	public void audit(Long auditEmployeeId,Long leaveId, String auditStatus) throws ServiceException;
	
	public Leave get(Long leaveId) throws ServiceException;
}
