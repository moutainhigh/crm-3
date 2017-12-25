package com.hefei.rms.attendance.service;

import java.util.Date;
import java.util.List;

import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IAttendanceService {
	/**
	 * 保存出勤信息
	 * @param info
	 * @throws ServiceException
	 */
	public void createAttendance(AttendanceInfo attendanceInfo) throws ServiceException;
	/**
	 * 更新出勤信息
	 * @param info
	 * @throws ServiceException
	 */
	public void updateAttendance(Long id, String delFlag ) throws ServiceException;
	/**
	 * 查看出勤信息
	 * @param info
	 * @throws ServiceException
	 */
	public List<AttendanceInfo> findAttendance(Long employeeId) throws ServiceException;

	
}
