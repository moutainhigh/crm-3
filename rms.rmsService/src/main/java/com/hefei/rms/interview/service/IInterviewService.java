package com.hefei.rms.interview.service;

import java.util.Date;
import java.util.List;

import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.exception.ServiceException;

public interface IInterviewService {
	/**
	 * 保存面试信息
	 * @param info
	 * @throws ServiceException
	 */
	public void saveInterviewInfo(InterviewInfo interviewInfo) throws ServiceException;
	/**
	 * 更新面试信息
	 * @param info
	 * @throws ServiceException
	 */
	public void updateInterview(Long id, String interviewStatus) throws ServiceException;
	/**
	 * 查看面试信息
	 * @param info
	 * @throws ServiceException
	 */
	public List<InterviewInfo> findInterview(Long id,String interviewStatus) throws ServiceException;

}
