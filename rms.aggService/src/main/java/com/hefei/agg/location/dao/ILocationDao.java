package com.hefei.agg.location.dao;

import java.util.List;

import com.hefei.agg.location.po.Location;
import com.hefei.service.framework.exception.DaoException;


public interface ILocationDao {
	public List<Location> getAllAddressList() throws DaoException;
	public List<Location> getAllProvinceList() throws DaoException;
	public List<Location> getCityListByCode(String provinceCode) throws DaoException;
	public List<Location> getAreasListByCode(String cityCode) throws DaoException;
}
