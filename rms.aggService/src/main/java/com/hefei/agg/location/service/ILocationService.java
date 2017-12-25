package com.hefei.agg.location.service;

import java.util.List;

import com.hefei.agg.location.po.Location;
import com.hefei.service.framework.exception.ServiceException;

public interface ILocationService {

	/**
	 * 获取所有地址信息
	 * @return
	 * @throws Exception
	 */
	public List<Location> getAllAddressList() throws ServiceException;
	
	/**
	 * 获取所有省份列表
	 * @return
	 * @throws Exception
	 */
	public List<Location> getAllProvinceList() throws ServiceException;
	
	/**
	 * 根据省份编码获取对应市列表
	 * @param provinceCode 省份编码
	 * @return
	 * @throws Exception
	 */
	public List<Location> getCityListByCode(String provinceCode) throws ServiceException;
	
	/**
	 * 根据城市编号获取对应区县列表
	 * @param cityCode 市编码
	 * @return
	 * @throws Exception
	 */
	public List<Location> getAreasListByCode(String cityCode) throws ServiceException;
}
