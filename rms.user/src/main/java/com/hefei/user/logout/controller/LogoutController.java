package com.hefei.user.logout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.base.FrontendConstants;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.logout.service.ILogoutService;

@Controller
public class LogoutController {
	private Logger logger = Logger.getLogger(LogoutController.class);
	
	@Autowired
	private ILogoutService logoutService;
	/***
	 * 用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		try {
			logoutService.logout(request, response);
			return "redirect:" + FrontendConstants.HOME;
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getRequestStr()+"logOut error:", e);
			return "error";
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr()+"logOut error:", e);
			return "error";
		}
		
	}
	
}
