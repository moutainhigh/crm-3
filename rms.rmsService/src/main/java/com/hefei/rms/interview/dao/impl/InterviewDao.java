package com.hefei.rms.interview.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.department.po.Department;
import com.hefei.rms.interview.dao.IInterviewDao;
import com.hefei.rms.interview.mapper.InterviewMapper;
import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class InterviewDao implements IInterviewDao{

	private static final Logger logger = Logger.getLogger(InterviewDao.class);
	
	@Autowired
	private InterviewMapper Mapper;
	
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Override
	public void saveInterviewInfo(InterviewInfo interviewInfo) throws DaoException {
		if(interviewInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try{
			//Long id = idManager.getNextId();
			//beInfo.setId(id);
			Mapper.saveInterviewInfo(interviewInfo);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("InterviewDao", "saveInterviewInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public void updateInterview(Long id, String interviewStatus, Date updateTime) throws DaoException {
		if(id == null || StringUtils.isBlank(interviewStatus) || updateTime == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("interviewStatus", interviewStatus);
			map.put("updateTime", updateTime);
			
			Mapper.updateInterview(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updateInterview error ",e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR);
		}
		Warning.warnFuntionTimer("InterviewDao", "updateInterview", null, System.currentTimeMillis() - begintimer, begintimer);
	}
	@Override
	public List<InterviewInfo> findInterview(Long id, String interviewStatus) throws DaoException {
		if(id == null || interviewStatus == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		List<InterviewInfo> list = null;
		try{
			list = Mapper.findInterview(id,interviewStatus);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" findInterview error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("InterviewDao", "findInterview", null, (System.currentTimeMillis() - begintimer), begintimer);
		return list;
	}
}
