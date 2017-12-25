package com.hefei.user.auth.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.auth.service.IAuthService;

@Controller
@RequestMapping(value="/auth", produces = "text/plain;charset=UTF-8")
public class AuthController {
	
	private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IAuthService authService;
	
	/****
	 * 人事管理系统用户登录验证
	 * @return
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public String login() {
		
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		JsonNode authRequestNode = node.get("authRequest");
		AuthRequest authRequest = JacksonUtil.jsonToBean(authRequestNode, AuthRequest.class);
		logger.info("---------------------------------authRequest"+authRequest.toString());
		try {
			Long userId = authService.login(authRequest);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(userId);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
}
