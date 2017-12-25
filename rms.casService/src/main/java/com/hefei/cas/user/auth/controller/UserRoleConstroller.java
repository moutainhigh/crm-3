package com.hefei.cas.user.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.user.auth.service.IUserAuthService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/user/", produces = "text/plain;charset=UTF-8")
public class UserRoleConstroller {
private static final Logger logger = Logger.getLogger(AuthDefaultController.class);
	
	@Autowired
	private IUserAuthService userAuthService;
	
	@RequestMapping(value="/getRoleByUserId")
	@ResponseBody
	public String getRoleByUserId() {
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
			List<Role> roles = userAuthService.getRoleByUserId(userId);
			if(roles != null && roles.size() > 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(roles);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
			
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
	@RequestMapping(value="/authRole")
	@ResponseBody
	public String authRole() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		try {
			JsonNode node = JacksonUtil.getJsonNode(plain);
			Long userId = JacksonUtil.getLong(node, "userId");
			String roleIdAndCheckString = JacksonUtil.getString(node, "roleIdAndCheck");
			Map<String, String> roleIdAndCheckMap = JacksonUtil.jsonToBean(roleIdAndCheckString, HashMap.class);
			Map<Long, String> roleIdAndCheck = new HashMap<Long, String>();
			for(String roleId:roleIdAndCheckMap.keySet()){
				roleIdAndCheck.put(Long.valueOf(roleId), roleIdAndCheckMap.get(roleId));
			}
			userAuthService.authUserRole(userId, roleIdAndCheck);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
}
