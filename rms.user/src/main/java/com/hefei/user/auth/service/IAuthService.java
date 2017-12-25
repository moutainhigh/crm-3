package com.hefei.user.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hefei.common.exception.BusinessException;



public interface IAuthService {
	
	public String login(String loginName,String password,HttpServletRequest request,HttpServletResponse response);

	public void writeLoginCookie(Long userId, HttpServletRequest request,HttpServletResponse response) throws BusinessException;
}
