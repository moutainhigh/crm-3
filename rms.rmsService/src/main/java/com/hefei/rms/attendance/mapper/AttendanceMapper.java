package com.hefei.rms.attendance.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.service.framework.base.mapper.Mapper;

public interface AttendanceMapper extends Mapper{

	public void createAttendance(AttendanceInfo attendanceInfo);

	public void updateAttendance(Map<String, Object> map);

	public List<AttendanceInfo> findAttendance(Long employeeId);
}
