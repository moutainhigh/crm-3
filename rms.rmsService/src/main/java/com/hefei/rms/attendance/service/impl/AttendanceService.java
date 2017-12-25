package com.hefei.rms.attendance.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.DateUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.attendance.dao.IAttendanceDao;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.attendance.service.IAttendanceService;
import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class AttendanceService implements IAttendanceService{
	
	@Autowired
	private IAttendanceDao attendanceDao;

	@Override
	public void createAttendance(AttendanceInfo info) throws ServiceException {
		if(info == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date date =Calendar.getInstance().getTime();
			info.setCreateTime(date);
			info.setUpdateTime(date);
			attendanceDao.createAttendance(info);
		}catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void updateAttendance(Long id, String delFlag) throws ServiceException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			attendanceDao.updateAttendance(id,delFlag);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}		
	}

	@Override
	public List<AttendanceInfo> findAttendance(Long employeeId) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			List<AttendanceInfo> list = attendanceDao.findAttendance(employeeId);
			return list;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}