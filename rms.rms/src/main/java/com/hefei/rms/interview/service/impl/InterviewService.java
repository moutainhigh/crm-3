package com.hefei.rms.interview.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.interview.manager.IInterviewManager;
import com.hefei.api.rms.interview.manager.impl.InterviewManager;
import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.rms.interview.service.IInterviewService;


@Service
public class InterviewService implements IInterviewService{
	private IInterviewManager interviewManager = new InterviewManager();
	
	@Override
	public void createInterview(InterviewInfo info) throws Exception {
		InterviewInfo interviewInfo = new InterviewInfo();
		BeanUtils.copyProperties(info, interviewInfo);
		interviewManager.saveInterviewInfo(interviewInfo);
	}
}
