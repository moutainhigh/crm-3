package com.hefei.rms.interview.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.interview.service.IInterviewService;

/**
 * 面试
 * @author dell
 *
 */
@Controller
@RequestMapping(value="/interview",produces="text/plain;charset=UTF-8")
public class InterviewController {
	
	private Logger logger = Logger.getLogger(InterviewController.class);
	
	@Autowired
	private IInterviewService interviewService;
	
	@RequestMapping("create")
	public String listFile(HttpServletRequest request, HttpServletResponse response){
		return "interview/create";
	}
	
	@RequestMapping(value="/createInterview",method=RequestMethod.POST)
	public String createInterview(HttpServletRequest request){
		try{
			Long companyId = Long.parseLong(request.getParameter("companyId"));
			Long employeeId = Long.parseLong(request.getParameter("employeeId"));
			Long positionId = Long.parseLong(request.getParameter("positionId"));
			Long userId = Long.parseLong(request.getParameter("userId"));
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String interviewStatus = request.getParameter("interviewStatus");
			String interviewYuyueTimeString = request.getParameter("interviewYuyueTime");
			Date interviewYuyueTime = DateUtil.string2date(interviewYuyueTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			String interviewActualTimeString = request.getParameter("interviewActualTime");
			Date interviewActualTime = DateUtil.string2date(interviewActualTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			
			InterviewInfo info = new InterviewInfo();
			info.setCompanyId(companyId);
			info.setEmployeeId(employeeId);
			info.setPositionId(positionId);
			info.setUserId(userId);
			if(name.length()>0){
			info.setName(name);
			} else {
				throw new Exception();
			}
			if(phone.length()>0){
			info.setPhone(phone);
			}else{
				throw new Exception();
			}
			info.setEmail(email);
			info.setAddress(address);
			info.setInterviewYuyueTime(interviewYuyueTime);
			info.setInterviewActualTime(interviewActualTime);
			info.setInterviewStatus(interviewStatus);
			interviewService.createInterview(info);
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "common/error";
	}
		return null;
	}
}