package com.hefei.user.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.employee.manager.IEmployeeManager;
import com.hefei.api.rms.employee.manager.impl.EmployeeManager;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.user.auth.manager.IAuthManager;
import com.hefei.api.user.auth.manager.impl.AuthManager;
import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.base.CompanyCookieUtil;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.base.user.UserCookie;
import com.hefei.frontend.framework.cookie.util.CookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.auth.service.IAuthService;

@Service
public class AuthService implements IAuthService{
	private Logger logger = Logger.getLogger(AuthService.class);
	
	private IAuthManager authManager = new AuthManager();
	private ICompanyManager companyManager = new CompanyManager();
	private IEmployeeManager  employeeManager = new EmployeeManager();
	@Override
	public String login(String loginName,String password,HttpServletRequest request,HttpServletResponse response) {
		String returnCode = ReturnCode.RETURN_CODE_ERROR;
		try {
			AuthRequest authRequest = new AuthRequest();
			authRequest.setUsername(loginName);
			authRequest.setPlainPassword(password);
			authRequest.setIp(RequestThreadLocal.getIp());
			//TODO
			logger.info(RequestThreadLocal.getRequestStr()+ authRequest);
			Long userId  = authManager.login(authRequest);
			logger.info(RequestThreadLocal.getRequestStr()+ "login userId:"+userId);
			if(userId!=null){

				writeLoginCookie(userId, request, response);
				
				returnCode = ReturnCode.RETURN_CODE_SUCCESS;
			}
		
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getRequestStr()+"error:",e);
			return e.getErrorCode();
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr()+"error:",e);
		}
		
		return returnCode;
	}

	public void writeLoginCookie(Long userId, HttpServletRequest request,HttpServletResponse response)  throws BusinessException{
		try {
			UserCookie userCookie = new UserCookie();
			userCookie.setUserId(userId);
			
			EmployeeInfo employeeInfo = employeeManager.getEmployeeByUserId(userId);
			if(StringUtils.isBlank(employeeInfo.getName())){//新注册用户没有昵称
				userCookie.setNickname(userId.toString().substring(0, 6));
			}else{
				userCookie.setNickname(employeeInfo.getName());
			}
			userCookie.setEmployeeId(employeeInfo.getEmployeeId());
			
			List<CompanyInfo> companys = companyManager.getCompanyByUserId(userId);
			CompanyInfo company = companys.get(0);
			CompanyCookie companyCookie = new CompanyCookie();
			companyCookie.setCompanyId(company.getId());
			companyCookie.setCompanyName(company.getCompanyName());
			
			CompanyCookieUtil.setCurrentCompanyCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_CURRENT_COMPANY, companyCookie, CookieConstants.COOKIE_AGE_SESSION, true, false);
			UserCookieUtil.setUserCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_WEB, userCookie, CookieConstants.COOKIE_AGE_SESSION, true, false);
			CookieUtil.setCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_USER_LOGIN_USERID, userId.toString(), CookieConstants.COOKIE_AGE_SESSION, true, false);
		
			CookieUtil.setCookie(request, response, CookieConstants.COOKIE_DOMAIN, CookieConstants.COOKIE_NAME_USER_LOGIN_NICKNAME, userCookie.getNickname(), CookieConstants.COOKIE_AGE_ONE_WEEK, true, false);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr()+"error:",e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
}
