package com.hefei.api.agg.location.manager.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.agg.client.AggUrlConstants;
import com.hefei.api.agg.common.BaseManager;
import com.hefei.api.agg.location.manager.ILocationManager;
import com.hefei.api.agg.location.po.AddressInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class LoactionManager extends BaseManager implements ILocationManager{

	private Logger logger = Logger.getLogger(LoactionManager.class);
	
	@Override
	public Map<String, String> getCodeByAddress(String province, String city,String areas) throws ClientException {
		if(StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(areas)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("getCodeByAddress begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("province", province);
			dataJson.put("city",city);
			dataJson.put("areas",areas);
			
			String result = SendRequest.send(AggUrlConstants.AGG_SERVER_LOCATION_GETCODE, dataJson, timer, head);
			logger.info("getCodeByAddress end time:" + timer + " result:" + result);
			logger.info("getCodeByAddress " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			Map<String, String> map = (LinkedHashMap<String, String>)resp.getReturnObject();
	 			return map;
			}
		} catch (Exception e) {
			logger.error("getCodeByAddress error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		
 		return null;
	}

	@Override
	public AddressInfo getAddressByCode(String code) throws ClientException {
		if(StringUtils.isBlank(code)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			AddressInfo addressInfo = null;
			
			long timer = System.currentTimeMillis();
			logger.info("getAddressByCode begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("code", code);
			
			String result = SendRequest.send(AggUrlConstants.AGG_SERVER_LOCATION_GETADDRESS, dataJson, timer, head);
			logger.info("getCodeByAddress end time:" + timer + " result:" + result);
			logger.info("getCodeByAddress " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			addressInfo = (AddressInfo) JacksonUtil.jsonToBean(objStr, AddressInfo.class);
	 		}
	 		
			return addressInfo;
		} catch (Exception e) {
			logger.error("getAddressByCode error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

}
