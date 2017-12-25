package com.hefei.api.rms.attendance.manager;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.behavior.vo.BehaviorInfo;
import com.hefei.common.exception.ClientException;

/**
 * 出勤
 * @author dell
 *
 */
public interface IAttendanceManager {
	
	public Boolean createAttendance(AttendanceInfo attendanceInfo) throws ClientException;

	public Boolean updateAttendance(Long id, String delFlag) throws ClientException;
	
	public List<AttendanceInfo> findAttendance(Long employeeId) throws ClientException;


}
