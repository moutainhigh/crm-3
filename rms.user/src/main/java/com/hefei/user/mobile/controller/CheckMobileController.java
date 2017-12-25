package com.hefei.user.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.api.mobile.manager.IMobileManager;
import com.hefei.user.api.mobile.manager.impl.MobileManager;
import com.hefei.user.api.mobile.vo.MobileInfo;

@Controller
@RequestMapping("/mobile")
@SuppressWarnings("all")
public class CheckMobileController {

	private Logger logger = Logger.getLogger(CheckMobileController.class);
	
	private IMobileManager mobileManager = new MobileManager();
	
	@RequestMapping(value="/checkMobileLoginExist",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkMobileLoginExist(HttpServletRequest request, HttpServletResponse response, String mobile){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<String, String> map= new HashMap<String, String>();
			 
			 MobileInfo mobileInfo = mobileManager.getByMobile(mobile, MobileInfo.TYPE_LOGIN);
			 if(mobileInfo == null){
				 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			 }else{
				 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			 }
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	@RequestMapping(value="/checkMobileLoginBind",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkMobileLoginBind(HttpServletRequest request, HttpServletResponse response, String mobile, Long userId){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<String, String> map= new HashMap<String, String>();
			 
			 MobileInfo mobileInfo = mobileManager.getByMobile(mobile, MobileInfo.TYPE_LOGIN);
			 if(mobileInfo == null){
				 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			 }else{
				 if(mobileInfo.getUserId().longValue() == userId){
					 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				 }
			 }
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
}
