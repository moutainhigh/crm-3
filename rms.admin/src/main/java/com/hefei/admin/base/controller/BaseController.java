package com.hefei.admin.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class BaseController {

	private Logger logger = Logger.getLogger(BaseController.class);
	
	protected Long getAdminId(HttpServletRequest request,HttpServletResponse response){
		Long userId;
		try {
			userId = UserCookieUtil.getUserId(request, CookieConstants.COOKIE_NAME_WEB);
			return userId;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	
	protected String returnBaseResponse(BaseResponse baseResponse, String jsonCallback){
		String rtn = JacksonUtil.beanToJson(baseResponse);
		logger.info(RequestThreadLocal.getTimer() + " return:" + rtn);
		if(StringUtils.isNotBlank(jsonCallback))
			return jsonCallback + "(" + rtn + ")";
		return rtn;
	}
}
