package com.hefei.user.reg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hefei.common.exception.BusinessException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.constants.UserConstants;
import com.hefei.user.reg.service.IRegisterService;

@Controller
@RequestMapping("/")
@SuppressWarnings("all")
public class RegisterController {

	private Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private IRegisterService registerService;
	
	
	@RequestMapping("/reg")
	public String toRegister(){
		return "reg/register";
	}
	
	
	/**
	 * 系统用户登录
	 * @param loginName
	 * @param password
	 * @param backurl 登录拦截登录成功后返回url
	 * @return
	 */
	@RequestMapping(value="/doRegister",method=RequestMethod.POST)
	public String login(@RequestParam String loginName,@RequestParam String password,@RequestParam String companyName, String backurl,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("/login :loginName:"+loginName+",password:"+password);
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(password)){
			return "reg/register";
		}
		try {
			registerService.regByEmail(loginName,password,companyName,backurl, request,response);
			response.sendRedirect(UserConstants.MANAGER_SERVER_URL);
			return null;
		} catch (BusinessException e) {
			request.setAttribute("returnCode", e.getErrorCode());
			return "reg/register";
		}catch (Exception e) {
			request.setAttribute("returnCode", ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getRequestStr()+" error:", e);
			return "reg/register";
		}
		
	}
	
}
