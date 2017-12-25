package com.hefei.rms.physical.contorller;

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
import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.rms.physical.service.IPhysicalService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/physical", produces = "text/plain;charset=UTF-8")
public class PhysicalController {

	private static final Logger logger = Logger.getLogger(PhysicalController.class);
	
	@Autowired
	private IPhysicalService physicalService;
	
	@RequestMapping(value="/find",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findPhysical(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		
		Long employeeId = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
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
			
			PhysicalInfo physicalInfo = physicalService.findPhysical(employeeId);
			if(physicalInfo != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(physicalInfo);
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
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String savePhysical(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode dateNode = JacksonUtil.getJsonNode(plainString);
			JsonNode physicalJson = dateNode.get("physicalInfo");
			PhysicalInfo info = JacksonUtil.jsonToBean(physicalJson, PhysicalInfo.class);
			if(info == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				physicalService.savePhysical(info);
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
	
	@RequestMapping(value="/update",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updatePhysical(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode dateNode = JacksonUtil.getJsonNode(plainString);
			JsonNode physicalJson = dateNode.get("physicalInfo");
			PhysicalInfo info = JacksonUtil.jsonToBean(physicalJson, PhysicalInfo.class);
			if(info == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				physicalService.updatePhysical(info);
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
