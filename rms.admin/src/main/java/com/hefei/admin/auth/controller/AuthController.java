package com.hefei.admin.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.admin.auth.service.IAuthService;
import com.hefei.common.exception.BusinessException;

@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class AuthController {
	
	private Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IAuthService authService;
	
	@RequestMapping(value="/login",produces="text/plain;charset=UTF-8")
	public String login() {
		return "auth/login";
	}
	
	@RequestMapping(value="/vlogin",produces="text/plain;charset=UTF-8")
	public String validateLogin(String loginName , String password,HttpServletRequest request,HttpServletResponse response) {
		try {
			authService.auth(loginName, password, request, response);
			return "redirect:/frame/index.do";
		} catch (BusinessException e) {
			request.setAttribute("returnCode", e.getErrorCode());
			return "auth/login";
		}
	}
}