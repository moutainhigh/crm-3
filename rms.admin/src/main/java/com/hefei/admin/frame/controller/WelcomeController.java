package com.hefei.admin.frame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.admin.auth.controller.AuthController;

@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class WelcomeController {


	private Logger logger = Logger.getLogger(AuthController.class);
	
	
	@RequestMapping(value="/welcome",produces="text/plain;charset=UTF-8")
	public String index(String username , String plainPwd,HttpServletRequest request,HttpServletResponse response) {
		return "frame/welcome";
	}
}
