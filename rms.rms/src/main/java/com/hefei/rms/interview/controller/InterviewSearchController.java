package com.hefei.rms.interview.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.interview.manager.IInterviewManager;
import com.hefei.api.rms.interview.manager.impl.InterviewManager;
import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping(value="/interview",produces="text/plain;charset=UTF-8")
public class InterviewSearchController {
	IInterviewManager interviewManager = new InterviewManager();
	private Logger logger = Logger.getLogger(InterviewController.class);
	
	@RequestMapping("find")
	public String findInterview(HttpServletRequest request,HttpServletResponse response) {
		try{
			List<InterviewInfo> interviewInfo = interviewManager.findInterview(null, null);
			request.setAttribute("interviewInfo", interviewInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "create/list";
	}
}
