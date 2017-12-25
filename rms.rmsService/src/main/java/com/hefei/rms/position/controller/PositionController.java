package com.hefei.rms.position.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.hefei.rms.position.po.Position;
import com.hefei.rms.position.service.IPositionService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/position", produces = "text/plain;charset=UTF-8")
public class PositionController {
	
	private static final Logger logger = Logger.getLogger(PositionController.class);
	
	@Autowired
	private IPositionService positionService;
	
	/**
	 * 保存岗位信息
	 * @return
	 */
	@RequestMapping(value="/savePosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String savePosition(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try{
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode dataJson = JacksonUtil.getJsonNode(plainString);
			JsonNode positionJson = dataJson.get("positionInfo");
			Position positionInfo = JacksonUtil.jsonToBean(positionJson, Position.class);
			
			if(positionInfo == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				positionInfo = positionService.savePositionInfo(positionInfo);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(positionInfo);
			}
			
		}catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
		
	@RequestMapping(value="/updatePosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updatePosition(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		String delFlag = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("id") && jsonNode.has("delFlag")){
				id = Long.valueOf(jsonNode.get("id").asText());
				delFlag = jsonNode.get("delFlag").asText();
				if(id == null && StringUtils.isBlank(delFlag)){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
				
				Date updateTime = Calendar.getInstance().getTime();
				
				positionService.updatePosition(id,delFlag,updateTime);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
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
	@RequestMapping(value="/getPosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getPosition(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("id")){
				id = Long.valueOf(jsonNode.get("id").asText());
				if(id == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			Position position = positionService.getPositionById(id);
			if(position != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(position);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
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
	
	@RequestMapping(value="/getPositionByDepartment",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getPositionByDepartment(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long departmentId = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("departmentId")){
				departmentId = Long.valueOf(jsonNode.get("departmentId").asText());
				if(departmentId == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			List<Position> list = positionService.getPositionByDepartment(departmentId);
			if(list != null && list.size() > 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(list);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
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
