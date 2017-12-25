package com.hefei.agg.location.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.agg.location.dao.ILocationDao;
import com.hefei.agg.location.mapper.LocationMapper;
import com.hefei.agg.location.po.Location;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class LocationDao implements ILocationDao{
	
	private static final Logger logger = Logger.getLogger(LocationDao.class);

	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	public List<Location> getAllProvinceList() throws DaoException {
		try {
			long begintimer = System.currentTimeMillis();
			List<Location> list = locationMapper.getAllProvinceList();
			Warning.warnFuntionTimer("LocationDao", "getAllProvinceList", null, (System.currentTimeMillis() - begintimer), begintimer);
			
			return list;
			
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"getAllProvinceList error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public List<Location> getCityListByCode(String provinceCode) throws DaoException {
		try {
			long begintimer = System.currentTimeMillis();
			List<Location> list = locationMapper.getCityListByCode(provinceCode);
			Warning.warnFuntionTimer("LocationDao", "getCityListByCode", null, (System.currentTimeMillis() - begintimer), begintimer);
			
			return list;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"getCityListByCode error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public List<Location> getAreasListByCode(String cityCode) throws DaoException {
		try {
			long begintimer = System.currentTimeMillis();
			List<Location> list = locationMapper.getAreasListByCode(cityCode);
			Warning.warnFuntionTimer("LocationDao", "getAreasListByCode", null, (System.currentTimeMillis() - begintimer), begintimer);
			
			return list;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"getAreasListByCode error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public List<Location> getAllAddressList() throws DaoException {
		try {
			long begintimer = System.currentTimeMillis();
			List<Location> list = locationMapper.getAllAddressList();
			Warning.warnFuntionTimer("LocationDao", "getAllAddressList", null, (System.currentTimeMillis() - begintimer), begintimer);
			
			return list;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"getAllAddressList error " , e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

}
