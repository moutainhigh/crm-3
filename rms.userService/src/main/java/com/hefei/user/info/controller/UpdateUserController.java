package com.hefei.user.info.controller;

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
import com.hefei.user.auth.controller.AuthController;
import com.hefei.user.info.po.User;
import com.hefei.user.info.service.IUserService;

@Controller
@RequestMapping(value="/userinfo", produces = "text/plain;charset=UTF-8")
public class UpdateUserController {

private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/updateById")
	@ResponseBody
	public String updateById() {
		
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		JsonNode userinfoNode = node.get("userinfo");
		User user = JacksonUtil.jsonToBean(userinfoNode, User.class);
		try {
			userService.updateUser(user);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
}
