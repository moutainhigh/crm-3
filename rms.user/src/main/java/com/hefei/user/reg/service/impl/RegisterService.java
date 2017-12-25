package com.hefei.user.reg.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.user.reg.manager.IRegisterManager;
import com.hefei.api.user.reg.manager.impl.RegisterManager;
import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.api.info.vo.UserInfo;
import com.hefei.user.auth.service.IAuthService;
import com.hefei.user.reg.service.IRegisterService;

@Service
public class RegisterService implements IRegisterService{

	private Logger logger = Logger.getLogger(RegisterService.class);
	
	private IRegisterManager registerManager =  new RegisterManager();

	@Autowired
	private IAuthService authService;
	@Override
	public void regByEmail(String loginName,String password,String companyName,String backurl, HttpServletRequest request,HttpServletResponse response)
			throws BusinessException {
		try {
			RegisterRequestByEmail registerRequestByEmail = new RegisterRequestByEmail();
			registerRequestByEmail.setBrowser("");
			registerRequestByEmail.setEmail(loginName);
			registerRequestByEmail.setIp(RequestThreadLocal.getIp());
			registerRequestByEmail.setMac("");
			registerRequestByEmail.setPlainPassword(password);
			registerRequestByEmail.setRegisterPosition("");
			registerRequestByEmail.setRegisterType("");
			registerRequestByEmail.setRegisterSubType("");
			registerRequestByEmail.setCompanyName(companyName);
			registerRequestByEmail.setType(UserInfo.TYPE_MANAGER);
			Long userId = registerManager.regByEmail(registerRequestByEmail);
			
			authService.writeLoginCookie(userId, request, response);
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		} 
	}

}
