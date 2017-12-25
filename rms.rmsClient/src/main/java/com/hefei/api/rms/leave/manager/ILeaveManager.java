package com.hefei.api.rms.leave.manager;

import java.util.Date;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public interface ILeaveManager {

	public Pagination<LeaveDTO> findLeave(String employeeName, Long employeeId, 
			String cardNo, Long companyId, String leaveType, Long auditEmployeeId,String auditStatus, Date startTime, Date endTime, int pageIndex, int pageSize) throws ClientException;
	
	public LeaveDTO applyLeave(LeaveDTO leaveDTO) throws ClientException;
	
	public void audit(Long auditEmployeeId,Long leaveId, String auditStatus) throws ClientException;
	
	public LeaveDTO get(Long leaveId) throws ClientException;
}
