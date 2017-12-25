package com.hefei.user.email.controller;

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
import com.hefei.user.api.email.manager.IEmailManager;
import com.hefei.user.api.email.manager.impl.EmailManager;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.email.controller.CheckEmailController;

@Controller
@RequestMapping("/email")
@SuppressWarnings("all")
public class CheckEmailController {

	private Logger logger = Logger.getLogger(CheckEmailController.class);
	
	private IEmailManager emailManager = new EmailManager();
	
	@RequestMapping(value="/checkEmailLoginExist",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkEmailLoginExist(HttpServletRequest request, HttpServletResponse response, String email){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<String, String> map= new HashMap<String, String>();
			 
			 EmailInfo emailInfo = emailManager.getByEmail(email, EmailInfo.TYPE_LOGIN);
			 
			 if(emailInfo == null){
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
	
	@RequestMapping(value="/checkEmailLoginBind",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String checkEmailLoginBind(HttpServletRequest request, HttpServletResponse response, String email, Long userId){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<String, String> map= new HashMap<String, String>();
			 
			 EmailInfo emailInfo = emailManager.getByEmail(email, EmailInfo.TYPE_LOGIN);
			 
			 if(emailInfo == null){
				 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			 }else{
				 if(emailInfo.getUserId().longValue() == userId){
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
