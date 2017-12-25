package com.hefei.rms.attendance.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.attendance.service.IAttendanceService;

/**
 * 考勤
 * @author zn
 *
 */
@Controller
@RequestMapping(value="/attendance",produces="text/plain;charset=UTF-8")
public class AttendanceController {
	
	private Logger logger = Logger.getLogger(AttendanceController.class);
	
	@Autowired
	private IAttendanceService attendanceService;	
	
	@RequestMapping(value="/createAttendance",method=RequestMethod.POST)
	public String createAttendance(HttpServletRequest request) {
		try{
			Long employeeId = Long.parseLong(request.getParameter("employeeId"));
			String note = request.getParameter("note");
			String goworkTimeString = request.getParameter("goworkTime");
			Date goworkTime = DateUtil.string2date(goworkTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			String offworkTimeString = request.getParameter("offworkTime");
			Date offworkTime = DateUtil.string2date(offworkTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			
			AttendanceInfo info = new AttendanceInfo();
			info.setEmployeeId(employeeId);
			if(note.length() > 0){
			info.setNote(note);
			}else{
				throw new Exception();
			}
			if(offworkTime.compareTo(goworkTime)>0){
			info.setGoworkTime(goworkTime);
			info.setOffworkTime(offworkTime);
			}else{
				throw new Exception();
			}
			attendanceService.createAttendance(info);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "common/error";
		}
		return "Attendance/create";
	}
	
	
}