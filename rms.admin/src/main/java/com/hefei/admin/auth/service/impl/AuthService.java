package com.hefei.admin.auth.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.admin.auth.service.IAuthService;
import com.hefei.api.cas.admin.auth.manager.IAdminAuthManager;
import com.hefei.api.cas.admin.auth.manager.impl.AdminAuthManager;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.base.user.UserCookie;
import com.hefei.frontend.framework.cookie.util.CookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class AuthService implements IAuthService{

	private Logger logger = Logger.getLogger(AuthService.class);
	
	private IAdminAuthManager adminAuthManager = new AdminAuthManager();
	
	public void auth(String username , String plainPwd,HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		try {
			AdminInfo adminInfo = adminAuthManager.auth(username, plainPwd);
			
			writeLoginCookie(adminInfo, request, response);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		} catch(Exception e){
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	private void writeLoginCookie(AdminInfo adminInfo, HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserCookie userCookie = new UserCookie();
		userCookie.setUserId(adminInfo.getId());
		userCookie.setNickname(adminInfo.getRealName());
		UserCookieUtil.setUserCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_WEB, userCookie, CookieConstants.COOKIE_AGE_SESSION, true, false);
		CookieUtil.setCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_USER_LOGIN_USERID, adminInfo.getId().toString(), CookieConstants.COOKIE_AGE_SESSION, true, false);
		CookieUtil.setCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_USER_LOGIN_NICKNAME, adminInfo.getRealName(), CookieConstants.COOKIE_AGE_ONE_WEEK, true, false);
	}
}
