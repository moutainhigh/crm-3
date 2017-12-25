package com.hefei.rms.leave.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.leave.dao.ILeaveDao;
import com.hefei.rms.leave.mapper.LeaveMapper;
import com.hefei.rms.leave.po.Leave;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class LeaveDao implements ILeaveDao{

	private static final Logger logger = Logger.getLogger(LeaveDao.class);
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	private LeaveMapper leaveMapper;
	
	@Override
	public Leave saveLeave(Leave leave) throws DaoException {
		if(leave == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Long id = idManager.getNextId();
			leave.setId(id);
			leaveMapper.saveLeave(leave);
			Warning.warnFuntionTimer("LeaveDao", "saveLeave", null, System.currentTimeMillis() - begintimer, begintimer);
			return leave;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void saveLeaveAudit(Long auditEmployeeId, Long leaveId,
			String auditStatus)  throws DaoException{
		if(auditEmployeeId == null || leaveId == null || auditStatus == null ){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Date now = Calendar.getInstance().getTime();
			Map map = new HashMap();
			map.put("auditEmployeeId", auditEmployeeId);
			map.put("leaveId", leaveId);
			map.put("auditStatus", auditStatus);
			map.put("auditTime", now);
			map.put("updateTime", now);
			leaveMapper.saveLeaveAudit(map);
			Warning.warnFuntionTimer("LeaveDao", "saveLeaveAudit", null, System.currentTimeMillis() - begintimer, begintimer);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public Leave getLeave(Long id) throws DaoException {
		if(id == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		Leave leave = null;
		try {
			leave = leaveMapper.getLeave(id);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("LeaveDao", "getLeave", null, System.currentTimeMillis() - begintimer, begintimer);
		return leave;
	}

	@Override
	public List<Leave> getLeavePaginationItems(String employeeName,
			Long employeeId, String cardNo, Long companyId, List<String> leaveType,
			Long auditEmployeeId, List<String> auditStatus, Date startTime,
			Date endTime, int pageIndex, int pageSize) throws DaoException {
		long begintimer = System.currentTimeMillis();
		List<Leave> list = null;
		try {
			int startIndex = (pageIndex -1)*pageSize;
			Map map = new HashMap();
			map.put("employeeName", employeeName);
			map.put("employeeId", employeeId);
			map.put("cardNo", cardNo);
			map.put("companyId", companyId);
			
			map.put("leaveType", leaveType);
			map.put("auditEmployeeId", auditEmployeeId);
			map.put("auditStatus", auditStatus);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			
			map.put("startIndex", startIndex);
			map.put("pageSize", pageSize);
			
			list = leaveMapper.getLeavePaginationItems(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("LeaveDao", "getLeavePaginationItems", null, System.currentTimeMillis() - begintimer, begintimer);
		return list;
	}

	@Override
	public Integer getLeavePaginationCount(String employeeName,
			Long employeeId, String cardNo, Long companyId, List<String> leaveType,
			Long auditEmployeeId, List<String> auditStatus, Date startTime,
			Date endTime) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try {
			Map map = new HashMap();
			map.put("employeeName", employeeName);
			map.put("employeeId", employeeId);
			map.put("cardNo", cardNo);
			map.put("companyId", companyId);
			map.put("leaveType", leaveType);
			map.put("auditEmployeeId", auditEmployeeId);
			map.put("auditStatus", auditStatus);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			int count = leaveMapper.getLeavePaginationCount(map);
			Warning.warnFuntionTimer("LeaveDao", "getLeavePaginationCount", null,(System.currentTimeMillis() - begintimer), begintimer);
			return count;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

}
