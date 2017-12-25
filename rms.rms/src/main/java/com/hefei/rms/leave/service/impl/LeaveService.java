package com.hefei.rms.leave.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.api.rms.leave.manager.ILeaveManager;
import com.hefei.api.rms.leave.manager.impl.LeaveManager;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.leave.service.ILeaveService;


@Service
public class LeaveService implements ILeaveService{
	
	private Logger logger = Logger.getLogger(LeaveService.class);

	private ILeaveManager leaveManager =  new LeaveManager();
	
	@Override
	public Pagination<LeaveDTO> findLeave(String employeeName, Long employeeId,
			String cardNo, Long companyId, String leaveType,Long auditEmployeeId,
			String auditStatus, Date startTime, Date endTime, int pageIndex,
			int pageSize) throws BusinessException {
		try {
			return leaveManager.findLeave(employeeName, employeeId,
					cardNo, companyId, leaveType,auditEmployeeId,
					auditStatus, startTime, endTime, pageIndex,
					pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public LeaveDTO applyLeave(LeaveDTO leaveDTO) throws BusinessException {
		try {
			return leaveManager.applyLeave(leaveDTO);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public void audit(Long auditEmployeeId, Long leaveId, String auditStatus) throws BusinessException {
		try {
			leaveManager.audit(auditEmployeeId, leaveId, auditStatus);;
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	
	
}
