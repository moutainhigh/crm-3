package com.hefei.cas.user.auth.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.cas.user.auth.service.IUserAuthService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/user/", produces = "text/plain;charset=UTF-8")
public class UserCheckAuthController {
	private static final Logger logger = Logger.getLogger(UserCheckAuthController.class);
	
	@Autowired
	private IUserAuthService userAuthService;
	
	@RequestMapping(value="/checkAuth")
	@ResponseBody
	public String checkAuth() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		String url = JacksonUtil.getString(node, "url");
		Long userId = JacksonUtil.getLong(node, "userId");
		Long companyId = JacksonUtil.getLong(node, "companyId");
		try {
			boolean r = userAuthService.checkAuth(userId, companyId, url);
			if(r){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_CAS_FAIL);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
}
