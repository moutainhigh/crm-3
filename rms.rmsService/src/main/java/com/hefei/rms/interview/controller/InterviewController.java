package com.hefei.rms.interview.controller;

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
import com.hefei.rms.attendance.po.AttendanceInfo;
import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.rms.interview.service.IInterviewService;
import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/interview", produces = "text/plain;charset=UTF-8")
public class InterviewController {
	
	private static final Logger logger = Logger.getLogger(InterviewController.class);
	
	@Autowired
	private IInterviewService interviewService;
	
	/**
	 * 保存面试信息
	 * @return
	 */
	@RequestMapping(value="/saveInterview",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String saveInterview(){
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
			JsonNode interviewJson = dataJson.get("interviewInfo");
			InterviewInfo interviewInfo = JacksonUtil.jsonToBean(interviewJson, InterviewInfo.class);
			
			if(interviewInfo == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				interviewService.saveInterviewInfo(interviewInfo);
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
	@RequestMapping(value="/updateInterview",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateInterview(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		String interviewStatus = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("id") &&jsonNode.has("interviewStatus")) {
				String idString = jsonNode.get("id").asText();
				if(StringUtils.isNotBlank(idString)){
					id = Long.valueOf(idString);
				}
				interviewStatus = jsonNode.get("interviewStatus").asText();
				if(id == null || interviewStatus == null ){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}
			
			interviewService.updateInterview(id,interviewStatus);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			
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
	
	@RequestMapping(value="/findInterview",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findInterview(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		String interviewStatus = null;
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
			if(jsonNode.has("interviewStatus")){
				interviewStatus = jsonNode.get("interviewStatus").asText();
				if(interviewStatus == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			List<InterviewInfo> list = interviewService.findInterview(id,interviewStatus);
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
