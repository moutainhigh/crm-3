package com.hefei.rms.position.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.position.dao.IPositionDao;
import com.hefei.rms.position.mapper.PositionMapper;
import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class PositionDao implements IPositionDao{

	private static final Logger logger = Logger.getLogger(PositionDao.class);
	
	@Autowired
	private PositionMapper mapper;
	
	//获取唯一编号
	private IdManager idManager = new IdManagerImpl();
	@Override
	public Position savePositionInfo(Position beInfo) throws DaoException {
		if(beInfo == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long begintimer = System.currentTimeMillis();
		try{
			Long id = idManager.getNextId();
			beInfo.setId(id);
			mapper.savePositionInfo(beInfo);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("PositionDao", "savePositionInfo", null, (System.currentTimeMillis() - begintimer), begintimer);
		return beInfo;
	}
	@Override
	public void updatePosition(Long id, String delFlag, Date updateTime) throws DaoException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("delFlag", delFlag);
		map.put("updateTime", updateTime);
		long begintimer = System.currentTimeMillis();
		mapper.updatePosition(map);
		Warning.warnFuntionTimer("PositionDao", "updatePosition", null, (System.currentTimeMillis() - begintimer), begintimer);
	}
	@Override
	public Position getPositionById(Long id) throws DaoException {
		if(id == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		Position position = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("delFlag", PositionInfo.DELFLAG_NO);
			position = mapper.getPositionById(map);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+" findPosition error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("PositionDao", "getPositionById", null, (System.currentTimeMillis() - begintimer), begintimer);
		return position;
	}
	
	public List<Position> getPositionByDepartment(Long departmentId) throws DaoException{
		if(departmentId == null){
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		long begintimer = System.currentTimeMillis();
		List<Position> list = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("departmentId", departmentId);
			map.put("delFlag", PositionInfo.DELFLAG_NO);
			list = mapper.getPositionByDepartment(map);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr()+"  error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		Warning.warnFuntionTimer("PositionDao", "getPositionByDepartment", null, (System.currentTimeMillis() - begintimer), begintimer);
		return list;
	}
}
