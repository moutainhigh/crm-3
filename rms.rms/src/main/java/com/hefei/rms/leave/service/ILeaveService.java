package com.hefei.rms.leave.service;

import java.util.Date;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;


public interface ILeaveService {
	
	public Pagination<LeaveDTO> findLeave(String employeeName, Long employeeId, 
			String cardNo, Long companyId, String leaveType, Long auditEmployeeId,String auditStatus, Date startTime, Date endTime, int pageIndex, int pageSize) throws BusinessException;
	
	public LeaveDTO applyLeave(LeaveDTO leaveDTO) throws BusinessException;
	
	public void audit(Long auditEmployeeId,Long leaveId, String auditStatus) throws BusinessException;
}
