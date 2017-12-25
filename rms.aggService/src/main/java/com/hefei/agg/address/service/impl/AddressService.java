package com.hefei.agg.address.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.agg.address.service.IAddressService;
import com.hefei.agg.address.util.AddressUtil;
import com.hefei.agg.location.po.Location;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class AddressService implements IAddressService {

	private static Logger logger = Logger.getLogger(AddressService.class);
	@Override
	public Map<String, String> getCodeByAddress(String province, String city, String areas) throws ServiceException {
		
		if(StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(areas)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			String proviceCode = AddressUtil.getProvinceCodeByName(province);
			String cityCode = AddressUtil.getCityIndex(city, proviceCode).get("cityCode");
			String areasCode = AddressUtil.getAreaIndex(areas, cityCode).get("areasCode");
			
			map.put("proviceCode", proviceCode);
			map.put("cityCode", cityCode);
			map.put("areasCode", areasCode);
			
			return map;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getCodeByAddress error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public Location getAddressByCode(String code) throws ServiceException {
		if(StringUtils.isBlank(code)){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			Location location = AddressUtil.getAddressByCode(code);
			return location;
			
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getAddressByCode error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	
	
}
