package com.hefei.rms.interview.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.employee.service.impl.EmployeeService;
import com.hefei.rms.interview.dao.IInterviewDao;
import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.rms.interview.service.IInterviewService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class InterviewService implements IInterviewService {
	private static final Logger logger = Logger.getLogger(InterviewService.class);
	@Autowired
	IInterviewDao interviewDao;
	
	
	
	@Override
	public void saveInterviewInfo(InterviewInfo interviewInfo) throws ServiceException {
		if(interviewInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			interviewInfo.setCreateTime(now);
			interviewInfo.setUpdateTime(now);
			interviewDao.saveInterviewInfo(interviewInfo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	@Override
	public void updateInterview(Long id, String interviewStatus) throws ServiceException {
		if(id == null || StringUtils.isBlank(interviewStatus)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			
			interviewDao.updateInterview(id, interviewStatus, now);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updateEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public List<InterviewInfo> findInterview(Long id,String interviewStatus) throws ServiceException {
		if(id == null || interviewStatus == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			List<InterviewInfo> list = interviewDao.findInterview(id,interviewStatus);
			return list;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
