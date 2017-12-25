package com.hefei.user.logout.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.common.exception.BusinessException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.base.user.UserCookie;
import com.hefei.frontend.framework.cookie.util.CookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.logout.service.ILogoutService;

@Service
public class LogoutService implements ILogoutService{

	private Logger logger = Logger.getLogger(LogoutService.class);
	
	public void logout(HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		try {
			UserCookie userCookie = UserCookieUtil.getUserCookie(request, CookieConstants.COOKIE_NAME_WEB);
		
			//登出日志
//			saveLogoutHistory(userCookie.getUserId());
			
			//去除cookie用户信息
			CookieUtil.delCookieByName(request, response, CookieConstants.COOKIE_NAME_WEB);
			CookieUtil.delCookieByName(request, response, CookieConstants.COOKIE_NAME_USER_LOGIN_USERID);
			
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr()+"logOut error:", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
}
