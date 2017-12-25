package com.hefei.rms.attendance.dao.impl;

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
import com.hefei.rms.attendance.dao.IAttendanceDao;
import com.hefei.rms.attendance.mapper.AttendanceMapper;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class AttendanceDao implements IAttendanceDao{
	private static final Logger logger = Logger.getLogger(AttendanceDao.class);
	@Autowired
	private AttendanceMapper mapper;

	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	/**
	 * @desc createAttendance
	 * @author zn
	 */
	@Override
	public void createAttendance(AttendanceInfo attendanceInfo) throws DaoException {
		if(attendanceInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
	    try{
			//Long id = idManager.getNextId();
			//attendanceInfo.setId(id);
			mapper.createAttendance(attendanceInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("AttendanceDao", "createAttendance", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public void updateAttendance(Long id, String delFlag) throws DaoException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("delFlag", delFlag);
			mapper.updateAttendance(map);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+" updateAttendance error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("AttendanceDao", "updateAttendance", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public List<AttendanceInfo> findAttendance(Long employeeId)throws DaoException {
		if(employeeId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		List<AttendanceInfo> list = null;
		try{
			list = mapper.findAttendance(employeeId);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" findAttendance error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("AttendanceDao", "findAttendance", null, (System.currentTimeMillis() - begintimer), begintimer);
		return list;
	}
}
