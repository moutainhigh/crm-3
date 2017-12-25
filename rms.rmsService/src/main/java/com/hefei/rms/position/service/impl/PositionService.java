package com.hefei.rms.position.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.position.dao.IPositionDao;
import com.hefei.rms.position.po.Position;
import com.hefei.rms.position.service.IPositionService;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class PositionService implements IPositionService {
	
	private static final Logger logger = Logger.getLogger(PositionService.class);

	@Autowired
	IPositionDao positionDao;
	
	@Override
	public Position savePositionInfo(Position info) throws ServiceException {
		if(info == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Date now = Calendar.getInstance().getTime();
			info.setCreateTime(now);
			info.setUpdateTime(now);
			info.setDelFlag(PositionInfo.DELFLAG_NO);
			info = positionDao.savePositionInfo(info);
			return info;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		
	}

	@Override
	public void updatePosition(Long id, String delFlag, Date updateTime) throws ServiceException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			positionDao.updatePosition(id,delFlag,updateTime);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Position getPositionById(Long id) throws ServiceException {
		if(id == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Position position = positionDao.getPositionById(id);
			return position;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public List<Position> getPositionByDepartment(Long departmentId) throws ServiceException{
		if(departmentId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			List<Position> list = positionDao.getPositionByDepartment(departmentId);
			return list;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
