package com.hefei.rms.behavior.controller;

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
import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.agg.location.manager.ILocationManager;
import com.hefei.api.agg.location.manager.impl.LoactionManager;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.rms.behavior.service.IBehaviorService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/behavior", produces = "text/plain;charset=UTF-8")
public class BehaviorController {
	
	private static final Logger logger = Logger.getLogger(BehaviorController.class);
	
	@Autowired
	private IBehaviorService behaviorService;
	
	/**
	 * 保存奖惩信息
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String saveBehavior(){
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
			JsonNode behaviorJson = dataJson.get("behaviorInfo");
			BehaviorInfo behaviorInfo = JacksonUtil.jsonToBean(behaviorJson, BehaviorInfo.class);
			
			if(behaviorInfo == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				behaviorService.saveBehaviorInfo(behaviorInfo);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
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
	
	@RequestMapping(value="/update",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateBehavior(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		String status = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("id") && jsonNode.has("status")){
				id = Long.valueOf(jsonNode.get("id").asText());
				status = jsonNode.get("status").asText();
				if(id == null && StringUtils.isBlank(status)){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
				
				Date updateTime = Calendar.getInstance().getTime();
				
				behaviorService.updateBehavior(id,status,updateTime);
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
	
	@RequestMapping(value="/find",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findBehavior(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long employeeId = null;
		String month = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("employeeId")){
				employeeId = Long.valueOf(jsonNode.get("employeeId").asText());
				if(employeeId == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			
			if(jsonNode.has("month")){
				month=jsonNode.get("employeeId").asText();
			}
			List<BehaviorInfo> list = behaviorService.findBehavior(employeeId, month);
			if(list != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(list);
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
