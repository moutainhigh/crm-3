package com.hefei.rms.insurance.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.rms.insurance.service.IInsuranceService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/insurance", produces = "text/plain;charset=UTF-8")
public class InsuranceController {

	private static final Logger logger = Logger.getLogger(InsuranceController.class);
	
	@Autowired
	private IInsuranceService insuranceService;
	
	@RequestMapping(value="/find",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findInsurance(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			Long employeeId = null;
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("employeeId")) {
				String employeeIdString = jsonNode.get("employeeId").asText();
				if(StringUtils.isNotBlank(employeeIdString)){
					employeeId = Long.valueOf(employeeIdString);
				}
			}
			
			if(employeeId == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				insuranceService.findInsurance(employeeId);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}
			
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
	/**
	 * 保存保险信息
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String saveInsurance(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode dataJson = JacksonUtil.getJsonNode(plainString);
			JsonNode insuranceJson = dataJson.get("insuranceInfo");
			InsuranceInfo info = JacksonUtil.jsonToBean(insuranceJson, InsuranceInfo.class);
			if(info == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				insuranceService.saveInsurance(info);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}
			
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
	/**
	 * 更新保险信息
	 * @return
	 */
	@RequestMapping(value="/update",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateInsurance(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode dataJson = JacksonUtil.getJsonNode(plainString);
			JsonNode insuranceJson = dataJson.get("insuranceInfo");
			InsuranceInfo info = JacksonUtil.jsonToBean(insuranceJson, InsuranceInfo.class);
			if(info == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				insuranceService.updateInsurance(info);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}
			
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
}
