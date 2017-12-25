package com.hefei.manager.frame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.base.CompanyCookieUtil;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class WelcomeController {

		private Logger logger = Logger.getLogger(WelcomeController.class);
		
		/**
		 * 用户管理后台地址映射
		 * @param path
		 * @return
		 */
		@RequestMapping(value = "/welcome")
		public String welcome(HttpServletRequest request,HttpServletResponse response) {
			return "frame/welcome";
		}
	
}
