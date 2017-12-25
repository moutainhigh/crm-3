package com.hefei.rms.attendance.service;

import java.util.List;

import com.hefei.api.rms.attendance.vo.AttendanceInfo;




public interface IAttendanceService {
	
	public void createAttendance(AttendanceInfo info) throws Exception;
	
	public void uploadAttendance(List<AttendanceInfo> attendanceInfo, List<String> erroReturnMessage);
}
