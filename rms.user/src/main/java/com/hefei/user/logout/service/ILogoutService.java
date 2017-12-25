package com.hefei.user.logout.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hefei.common.exception.BusinessException;

public interface ILogoutService {

	public void logout(HttpServletRequest request,HttpServletResponse response) throws BusinessException;
}
