package com.hefei.user.reg.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hefei.common.exception.BusinessException;

public interface IRegisterService {

	public void regByEmail(String loginName,String password,String companyName,String backurl, HttpServletRequest request,HttpServletResponse response)throws BusinessException;
}
