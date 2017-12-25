package com.hefei.agg.address.service;

import java.util.Map;

import com.hefei.agg.location.po.Location;
import com.hefei.service.framework.exception.ServiceException;

public interface IAddressService {

	/**
	 * 根据地址获取地址编码
	 * @param province 省名称
	 * @param city 市名称
	 * @param areas 区县名称
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, String> getCodeByAddress(String province,String city,String areas) throws ServiceException;
	
	/**
	 * 根据地址编码获取地址信息
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public Location getAddressByCode(String code) throws ServiceException;
}
