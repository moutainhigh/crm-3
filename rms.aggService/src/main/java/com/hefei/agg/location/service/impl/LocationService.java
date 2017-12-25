package com.hefei.agg.location.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.agg.location.dao.ILocationDao;
import com.hefei.agg.location.po.Location;
import com.hefei.agg.location.service.ILocationService;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service("locationService")
public class LocationService implements ILocationService{
	
	private static Logger log = Logger.getLogger(LocationService.class);

	@Autowired
	private ILocationDao locationDao;
	
	@Override
	public List<Location> getAllProvinceList() throws ServiceException {
		try {
			return locationDao.getAllProvinceList();
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + "getAllProvinceList error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public List<Location> getCityListByCode(String provinceCode) throws ServiceException {
		if(StringUtils.isBlank(provinceCode)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			return locationDao.getCityListByCode(provinceCode);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + "getCityListByCode error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public List<Location> getAreasListByCode(String cityCode) throws ServiceException {
		if(StringUtils.isBlank(cityCode)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			return locationDao.getAreasListByCode(cityCode);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + "getAreasListByCode error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public List<Location> getAllAddressList() throws ServiceException {
		try {
			return locationDao.getAllAddressList();
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + "getAllAddressList error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

}
