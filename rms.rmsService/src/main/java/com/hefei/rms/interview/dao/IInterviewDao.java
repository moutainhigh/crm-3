package com.hefei.rms.interview.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.exception.DaoException;

public interface IInterviewDao {

	/**
	 * 保存面试信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public void saveInterviewInfo(InterviewInfo interviewInfo) throws DaoException;

	public void updateInterview(Long id, String interviewStatus, Date updateTime) throws DaoException;

	public List<InterviewInfo> findInterview(Long id, String interviewStatus) throws DaoException;
}
