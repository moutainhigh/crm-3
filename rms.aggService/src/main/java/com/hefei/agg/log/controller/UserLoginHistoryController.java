package com.hefei.agg.log.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.agg.log.service.IUserLoginHistoryService;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@RequestMapping(value="/log",produces="text/plain;charset=UTF-8")
@Controller
public class UserLoginHistoryController {

	private static final Logger logger = Logger.getLogger(UserLoginHistoryController.class);
	
	
	@Autowired
	private IUserLoginHistoryService ulhService;
	/***
	 * 记录用户登录登出日志
	 * @param ulh
	 * @return
	 */
	@RequestMapping(value="/saveUserLoginHistory",method=RequestMethod.POST)
	@ResponseBody
	public String saveUserLoginHistory(){
		BaseResponse bresp = new BaseResponse();
		bresp.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		
		UserLoginHistory userLoginHistory = new UserLoginHistory();
		
		String plainString = RequestThreadLocal.getPlain();
		if(StringUtils.isBlank(plainString)){
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			bresp.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			return JacksonUtil.beanToJson(bresp);
		}
		
		JsonNode node = JacksonUtil.getJsonNode(plainString);
		logger.info("userLoginHistory:"+node.get("userLoginHistory").toString());
		
		userLoginHistory = JacksonUtil.jsonToBean(node.get("userLoginHistory"), UserLoginHistory.class);
		String returnCode = null;
		try {
			//TODO 记录日志
			returnCode = ulhService.saveUserLoginHistory(userLoginHistory);
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr()+"error:", e);
			bresp.setReturnCode(e.getErrorCode());
			return JacksonUtil.beanToJson(bresp);
		}
		bresp.setReturnCode(returnCode);
		return JacksonUtil.beanToJson(bresp);
	}
	
}
