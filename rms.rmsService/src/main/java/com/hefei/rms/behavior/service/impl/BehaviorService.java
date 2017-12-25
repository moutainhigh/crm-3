package com.hefei.rms.behavior.service.impl;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.behavior.dao.IBehaviorDao;
import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.rms.behavior.service.IBehaviorService;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class BehaviorService implements IBehaviorService {

	@Autowired
	 IBehaviorDao behaviorDao;
	
	@Override
	public void saveBehaviorInfo(BehaviorInfo info) throws ServiceException {
		if(info == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Date now = Calendar.getInstance().getTime();
			info.setCreateTime(now);
			info.setUpdateTime(now);
			behaviorDao.saveBehaviorInfo(info);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		
	}
	@Override
	public void updateBehavior(Long id, String status, Date updateTime) throws ServiceException {
		if(id == null || StringUtils.isBlank(status)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			behaviorDao.updateBehavior(id,status,updateTime);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public List<BehaviorInfo> findBehavior(Long employeeId, String month) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			List<BehaviorInfo> list = behaviorDao.findBehavior(employeeId, month);
			return list;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
