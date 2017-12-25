package com.hefei.user.auth.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.agg.log.manager.IUserLogManager;
import com.hefei.api.agg.log.manager.impl.UserLogManager;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.api.user.auth.manager.IAuthManager;
import com.hefei.api.user.auth.manager.impl.AuthManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.cookie.util.CookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.auth.service.IAuthService;
import com.hefei.user.constants.UserConstants;
import com.hefei.user.util.GetMacAddress;

@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class AuthController {
	
	private Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IAuthService authService;
	
	
	@RequestMapping("/login")
	public String toLogin(){
		return "auth/login";
	}
	
	
	/**
	 * 系统用户登录
	 * @param loginName
	 * @param password
	 * @param backurl 登录拦截登录成功后返回url
	 * @return
	 */
	@RequestMapping(value="/vlogin",method=RequestMethod.POST)
	public String login(@RequestParam String loginName,@RequestParam String password,String backurl,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("/login :loginName:"+loginName+",password:"+password);
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(password)){
			return "auth/login";
		}
		try {
			String returnCode = authService.login(loginName,password,request,response);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(returnCode)){
			    if(backurl!=null){
			    	response.sendRedirect( backurl);
			    }else{
			    	//登录成功到人事管理中心首页
			    	response.sendRedirect(UserConstants.MANAGER_SERVER_URL);
			    }
			    return null;
			}
			request.setAttribute("errorMessage", ReturnCode.MESSAGES.get(returnCode));
			return "auth/login";
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr()+"Exception error:", e);
			request.setAttribute("errorMessage", ReturnCode.MESSAGES.get(ReturnCode.RETURN_CODE_ERROR));
			return "auth/login";
		}
		
	}
	
	
}