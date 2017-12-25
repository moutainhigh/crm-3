package com.hefei.user.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.PatternUtil;
import com.hefei.common.util.encrypt.SHA256;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.api.info.vo.UserInfo;
import com.hefei.user.api.mobile.vo.MobileInfo;
import com.hefei.user.auth.service.IAuthService;
import com.hefei.user.email.po.Email;
import com.hefei.user.email.service.IEmailService;
import com.hefei.user.info.po.User;
import com.hefei.user.info.service.IUserService;
import com.hefei.user.mobile.po.Mobile;
import com.hefei.user.mobile.service.IMobileService;

@Service
public class AuthService implements IAuthService{
	
	private static Logger logger= Logger.getLogger(AuthService.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IMobileService mobileService;
	
	public Long login(AuthRequest authRequest) throws ServiceException{
		User user = null;
		if(PatternUtil.isMobileNO(authRequest.getUsername())){
			
		}else if(PatternUtil.isEmail(authRequest.getUsername())){
			Email email = emailService.getByEmail(authRequest.getUsername(), EmailInfo.TYPE_LOGIN);
			if(email ==null){
				throw new ServiceException(ReturnCode.RETURN_CODE_LOGINNAME_ERROR);
			}
			
			String encPwd = SHA256.encrtypt(authRequest.getPlainPassword(), email.getPwdVersion());
			if(!encPwd.equals(email.getPassword())){
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_PASSWD_ERROR);
			}
			user = userService.getById(email.getUserId());
		}else if(PatternUtil.isMobileNO(authRequest.getUsername())){
			Mobile mobile = mobileService.getByMobile(authRequest.getUsername(), MobileInfo.TYPE_LOGIN);
			if(mobile ==null){
				throw new ServiceException(ReturnCode.RETURN_CODE_LOGINNAME_ERROR);
			}
			
			String encPwd = SHA256.encrtypt(authRequest.getPlainPassword(), mobile.getPwdVersion());
			if(!encPwd.equals(mobile.getPassword())){
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_PASSWD_ERROR);
			}
			user = userService.getById(mobile.getUserId());
		}else{
			throw new ServiceException(ReturnCode.RETURN_CODE_LOGINNAME_ERROR);
		}
		if(user ==null){
			throw new ServiceException(ReturnCode.RETURN_CODE_LOGINNAME_ERROR);
		}
		if(UserInfo.DEL_FLAG_CLOSE.equals(user.getDelFlag())){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_FREEZE);
		}
		if(UserInfo.DEL_FLAG_FREEZE.equals(user.getDelFlag())){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_FREEZE);
		}
		
		return user.getId();
	}

}
