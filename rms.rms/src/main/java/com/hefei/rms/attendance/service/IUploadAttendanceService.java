package com.hefei.rms.attendance.service;

import java.util.List;

import com.hefei.api.rms.attendance.vo.AttendanceInfo;

public interface IUploadAttendanceService {
	
	public void uploadAttendance(List<AttendanceInfo> attendanceInfo, List<String> erroReturnMessage);

}
