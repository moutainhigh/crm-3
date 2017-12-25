package com.hefei.user.mobile.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.mobile.po.Mobile;
import com.hefei.user.mobile.service.IMobileService;

@Controller
@RequestMapping(value="/mobile", produces = "text/plain;charset=UTF-8")
public class MobileController {
	
	private static Logger logger = Logger.getLogger(MobileController.class);
	
	@Autowired
	private IMobileService mobileService;
	
	@RequestMapping(value="/getLoginMobileByUserId")
	@ResponseBody
	public String getLoginMobileByUserId() {
		
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		Long userId = JacksonUtil.getLong(node, "userId");
		try {
			Mobile mobile = mobileService.getLoginMobileByUserId(userId);
			if(mobile != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(mobile);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
	@RequestMapping(value="/getByMobile")
	@ResponseBody
	public String getByMobile() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		String mobile = JacksonUtil.getString(node, "mobile");
		String type = JacksonUtil.getString(node, "type");
		try {
			Mobile mobileInfo = mobileService.getByMobile(mobile, type);
			if(mobileInfo != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(mobileInfo);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
}
