package com.hefei.rms.attendance.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.service.framework.exception.DaoException;


public interface IAttendanceDao {
	
	public void createAttendance(AttendanceInfo attendanceInfo) throws DaoException;

	public void updateAttendance(Long id, String delFlag) throws DaoException;

	public List<AttendanceInfo> findAttendance(Long employeeId) throws DaoException;
}
