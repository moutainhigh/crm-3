package com.hefei.api.rms.interview.manager;

import java.util.List;

import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.common.exception.ClientException;



public interface IInterviewManager {
	
	public Boolean saveInterviewInfo(InterviewInfo interviewInfo) throws ClientException;

	public Boolean updateInterview (Long id, String interviewStatus) throws ClientException;

	public List<InterviewInfo> findInterview (Long id, String interviewStatus) throws ClientException;
}
