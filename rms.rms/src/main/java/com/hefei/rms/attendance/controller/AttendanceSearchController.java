package com.hefei.rms.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.manager.impl.AttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping(value="/attendance",produces="text/plain;charset=UTF-8")
public class AttendanceSearchController {
	IAttendanceManager attendanceManager = new AttendanceManager();
	private Logger logger = Logger.getLogger(AttendanceController.class);
	
	@RequestMapping("find")
	public String findAttendance(HttpServletRequest request,HttpServletResponse response) {
		try{
			List<AttendanceInfo> attendanceInfo = attendanceManager.findAttendance(null);
			request.setAttribute("attendanceInfo", attendanceInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "create/list";
	}
}
