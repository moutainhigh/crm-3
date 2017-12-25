package com.hefei.admin.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hefei.common.exception.BusinessException;


public interface IAuthService {
	
	public void auth(String username , String plainPwd,HttpServletRequest request,HttpServletResponse response) throws BusinessException;
}
