package com.hefei.cas.admin.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.cas.admin.auth.service.IAuthService;
import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.service.IAdminService;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.encrypt.SHA256;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class AuthService implements IAuthService{

	private static final Logger logger = Logger.getLogger(AuthService.class);
	
	
	@Autowired
	private IAdminService adminService;
	
	public AdminInfo auth(String username , String plainPwd) throws ServiceException{
		try{
			Admin admin = adminService.getByUsername(username);
			if(admin == null){
				logger.error(RequestThreadLocal.getLoggerStr() + " user not exist");
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_NOT_FOUND);
			}
			if(AdminInfo.DEL_FLAG_CLOSE.equals(admin.getDelFlag())){
				logger.error(RequestThreadLocal.getLoggerStr() + " user close");
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_PASSWD_ERROR);
			}
			if(AdminInfo.DEL_FLAG_FREEZE.equals(admin.getDelFlag())){
				logger.error(RequestThreadLocal.getLoggerStr() + " user freeze");
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_PASSWD_ERROR);
			}
			String pwdVersion = admin.getPwdVersion();
			String encPwd = SHA256.encrtypt(plainPwd, pwdVersion);
			if(!encPwd.equals(admin.getPassword())){
				logger.error(RequestThreadLocal.getLoggerStr() + " password error");
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_USER_PASSWD_ERROR);
			}
			AdminInfo adminInfo = new AdminInfo();
			adminInfo.setId(admin.getId());
			adminInfo.setRealName(admin.getRealName());
			return adminInfo;
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
}
