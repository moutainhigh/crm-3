package com.hefei.api.agg.location.manager;

import java.util.Map;

import com.hefei.api.agg.location.po.AddressInfo;
import com.hefei.common.exception.ClientException;

public interface ILocationManager {
	
	/**
	 * 根据地址信息获取对应编码
	 * @param provice 省名称
	 * @param city 市名称
	 * @param areas 区县名称
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getCodeByAddress(String province,String city,String areas) throws ClientException;
	
	/**
	 * 根据编码获取详细地址信息
	 * @param code 地址编码
	 * @return
	 * @throws Exception
	 */
	public AddressInfo getAddressByCode(String code) throws ClientException;
}
