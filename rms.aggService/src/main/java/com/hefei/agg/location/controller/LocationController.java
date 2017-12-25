package com.hefei.agg.location.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.agg.address.service.IAddressService;
import com.hefei.agg.location.po.Location;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/location", produces = "text/plain;charset=UTF-8")
public class LocationController {
	
	private static final Logger logger = Logger.getLogger(LocationController.class);
	
	@Autowired
	IAddressService addressService;
	
	/**
	 * 根据地址获取对应编码
	 * @param province 省名称
	 * @param city 市名称
	 * @param areas 区县名称
	 * 
	 * @return Map<String,String>  name,code
	 */
	@RequestMapping("/getCodeByAddress")
	@ResponseBody
	public String getCodeByAddress(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		String province = null;
		String city = null;
		String areas = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("province") && jsonNode.has("city") && jsonNode.has("areas")) {
				province = jsonNode.get("province").asText();
				city = jsonNode.get("city").asText();
				areas = jsonNode.get("areas").asText();
				if(StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(areas)){
					baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(baseResponse);
				}
			}
			
			Map<String, String> map = addressService.getCodeByAddress(province, city, areas);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			baseResponse.setReturnObject(map);
			
		} catch (ServiceException e){
			baseResponse.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getCodeByAddress error",e);
		}
		
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	/**
	 * 根据地址编码获取地址信息
	 * @param code 编号
	 * 
	 * @return Location
	 */
	@RequestMapping("/getAddressByCode")
	@ResponseBody
	public String getAddressByCode(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		String code = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("code")) {
				code = jsonNode.get("code").asText();
				if(StringUtils.isBlank(code)){
					baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(baseResponse);
				}
			}
			
			Location location = addressService.getAddressByCode(code);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			baseResponse.setReturnObject(location);
			
		} catch (ServiceException e){
			baseResponse.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getAddressByCode error",e);
		}
		
		return JacksonUtil.beanToJson(baseResponse);
	}
	
}
