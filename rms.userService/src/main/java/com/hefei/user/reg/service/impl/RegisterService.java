package com.hefei.user.reg.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.user.auth.manager.IUserAuthorizationManager;
import com.hefei.api.cas.user.auth.manager.impl.UserAuthorizationManager;
import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.api.user.reg.vo.RegisterRequestByEmailMobile;
import com.hefei.api.user.reg.vo.RegisterRequestByMobile;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.PatternUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.common.util.encrypt.SHA256;
import com.hefei.common.util.encrypt.SHA256Version;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.api.info.vo.UserInfo;
import com.hefei.user.api.mobile.vo.MobileInfo;
import com.hefei.user.email.po.Email;
import com.hefei.user.email.service.IEmailService;
import com.hefei.user.info.po.User;
import com.hefei.user.info.service.IUserService;
import com.hefei.user.mobile.po.Mobile;
import com.hefei.user.mobile.service.IMobileService;
import com.hefei.user.reg.service.IRegisterService;

@Service
public class RegisterService implements IRegisterService {

	private static Logger logger= Logger.getLogger(RegisterService.class);
	
	private IUserAuthorizationManager userAuthorizationManager = new UserAuthorizationManager();
	private ICompanyManager companyManager = new CompanyManager();
	
	@Autowired
	private IMobileService mobileService;
	@Autowired
	private IEmailService emailService;
	@Autowired
	private IUserService userService;
	
	public Long regByEmail(RegisterRequestByEmail registerRequestByEmail)throws ServiceException{
		Email email = emailService.getByEmail(registerRequestByEmail.getEmail(),EmailInfo.TYPE_LOGIN);
		if(email != null)
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
		try{
			User user = createUser(registerRequestByEmail.getType(), PatternUtil.formatEmail(registerRequestByEmail.getEmail()));
			email = createEmail(user, registerRequestByEmail.getEmail(), registerRequestByEmail.getPlainPassword());
			
			CompanyInfo company = companyManager.regCreateCompanyEmployee(user.getId(), registerRequestByEmail.getCompanyName(), registerRequestByEmail.getEmail(), null, user.getName());
			
			userAuthorizationManager.createCompanySuperManager(user.getId(), company.getId());;
			return user.getId();
		}catch(ClientException e){
			throw new ServiceException(e.getErrorCode(), e.getErrorMessage());
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	public Long regByMobile(RegisterRequestByMobile registerRequestByMobile)throws ServiceException{
		Mobile mobile = mobileService.getByMobile(registerRequestByMobile.getMobile(),MobileInfo.TYPE_LOGIN);
		if(mobile != null)
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
		try{
			User user = createUser(registerRequestByMobile.getType(),PatternUtil.formatMobile(registerRequestByMobile.getMobile()));
			createMobile(user, registerRequestByMobile.getMobile(), registerRequestByMobile.getPlainPassword());
			
			CompanyInfo company = companyManager.regCreateCompanyEmployee(user.getId(), registerRequestByMobile.getCompanyName(), null, registerRequestByMobile.getMobile(),user.getName());
			userAuthorizationManager.createCompanySuperManager(user.getId(), company.getId());;
			return user.getId();
		}catch(ClientException e){
			throw new ServiceException(e.getErrorCode(), e.getErrorMessage());
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public Long companyAddEmployee(RegisterRequestByEmailMobile registerRequestByEmailMobile)throws ServiceException{
		try{
			if(StringUtils.isNotBlank(registerRequestByEmailMobile.getIdNo())){
				User existUser = userService.getByIdNo(registerRequestByEmailMobile.getIdNo());
				if(existUser != null)
					return existUser.getId();
			}
			User user = null;
			if(StringUtils.isNotBlank(registerRequestByEmailMobile.getMobile())){
				Mobile mobile = mobileService.getByMobile(registerRequestByEmailMobile.getMobile(), MobileInfo.TYPE_LOGIN);
				if(mobile != null)
					throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			}
			if(StringUtils.isNotBlank(registerRequestByEmailMobile.getEmail())){
				Email email = emailService.getByEmail(registerRequestByEmailMobile.getEmail(), EmailInfo.TYPE_LOGIN);
				if(email != null)
					throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			}

			
			Date now = Calendar.getInstance().getTime();
			if(StringUtils.isNotBlank(registerRequestByEmailMobile.getEmail())){
				user = new User();
				user.setType(registerRequestByEmailMobile.getType());
				user.setName(registerRequestByEmailMobile.getName());
				user.setIdNo(registerRequestByEmailMobile.getIdNo());
				user.setSex(registerRequestByEmailMobile.getSex());
				user.setBirthday(registerRequestByEmailMobile.getBirthday());
				user.setLiveProvinceCode(registerRequestByEmailMobile.getLiveProvinceCode());
				user.setLiveCityCode(registerRequestByEmailMobile.getLiveCityCode());
				user.setLiveAreaCode(registerRequestByEmailMobile.getLiveAreaCode());
				user.setLiveAddress(registerRequestByEmailMobile.getLiveAddress());
				user.setHukouType(registerRequestByEmailMobile.getHukouType());
				user.setHukouProvinceCode(registerRequestByEmailMobile.getHukouProvinceCode());
				user.setHukouCityCode(registerRequestByEmailMobile.getHukouCityCode());
				user.setHukouAreaCode(registerRequestByEmailMobile.getHukouAreaCode());
				user.setDelFlag(UserInfo.DEL_FLAG_NORMAL);
				user.setCreateTime(now);
				user.setUpdateTime(now);
				user = userService.saveUser(user);
				
				createEmail(user, registerRequestByEmailMobile.getEmail(), registerRequestByEmailMobile.getPlainPassword());
			}
			if(StringUtils.isNotBlank(registerRequestByEmailMobile.getMobile())){
				if(user == null){
					user = new User();
					user.setType(registerRequestByEmailMobile.getType());
					user.setName(registerRequestByEmailMobile.getName());
					user.setIdNo(registerRequestByEmailMobile.getIdNo());
					user.setSex(registerRequestByEmailMobile.getSex());
					user.setBirthday(registerRequestByEmailMobile.getBirthday());
					user.setLiveProvinceCode(registerRequestByEmailMobile.getLiveProvinceCode());
					user.setLiveCityCode(registerRequestByEmailMobile.getLiveCityCode());
					user.setLiveAreaCode(registerRequestByEmailMobile.getLiveAreaCode());
					user.setLiveAddress(registerRequestByEmailMobile.getLiveAddress());
					user.setHukouType(registerRequestByEmailMobile.getHukouType());
					user.setHukouProvinceCode(registerRequestByEmailMobile.getHukouProvinceCode());
					user.setHukouCityCode(registerRequestByEmailMobile.getHukouCityCode());
					user.setHukouAreaCode(registerRequestByEmailMobile.getHukouAreaCode());
					user.setDelFlag(UserInfo.DEL_FLAG_NORMAL);
					user.setCreateTime(now);
					user.setUpdateTime(now);
					user = 	userService.saveUser(user);
				}
				createMobile(user, registerRequestByEmailMobile.getMobile(), registerRequestByEmailMobile.getPlainPassword());
			}
			userAuthorizationManager.createCompanyCommonRole(user.getId(), registerRequestByEmailMobile.getCompanyId());;
			return user.getId();
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
//	private void authrize(Long userId, Long companyId, Long roleId) {
//		try {
//			userAuthorizationManager.authDefault(userId, companyId, roleId);
//		} catch (ClientException e) {
//			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
//		}
//	}
	
	private Mobile createMobile(User user, String mobileInfo, String plainPwd) throws ServiceException{
		Date now = Calendar.getInstance().getTime();
		Mobile mobile = new Mobile();
		mobile.setUserId(user.getId());
		mobile.setMobile(mobileInfo);
		mobile.setPwdVersion(SHA256Version.V1.getVersion());
		mobile.setPassword(encPwd(plainPwd, mobile.getPwdVersion()));
		mobile.setIsCheck(MobileInfo.IS_CHECK_TODO);
		mobile.setType(MobileInfo.TYPE_LOGIN);
		mobile.setDelFlag(MobileInfo.DEL_FLAG_NO);
		mobile.setCreateTime(now);
		mobile.setUpdateTime(now);
		mobileService.saveMobile(mobile);
		return mobile;
	}
	
	private Email createEmail(User user, String emailInfo, String plainPwd) throws ServiceException{
		Date now = Calendar.getInstance().getTime();
		Email email = new Email();
		email.setUserId(user.getId());
		email.setEmail(emailInfo);
		email.setPwdVersion(SHA256Version.V1.getVersion());
		email.setPassword(encPwd(plainPwd, email.getPwdVersion()));
		email.setIsCheck(EmailInfo.IS_CHECK_TODO);
		email.setType(EmailInfo.TYPE_LOGIN);
		email.setDelFlag(EmailInfo.DEL_FLAG_NO);
		email.setCreateTime(now);
		email.setUpdateTime(now);
		emailService.saveEmail(email);
		return email;
	}
	private User createUser(String type, String nickname) throws ServiceException{
		Date now = Calendar.getInstance().getTime();
		User user = new User();
		user.setType(type);
		user.setName(nickname);
		user.setDelFlag(UserInfo.DEL_FLAG_NORMAL);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userService.saveUser(user);
		return user;
	}
	
	private String encPwd(String plainPwd, String pwdVersion){
		return SHA256.encrtypt(plainPwd, pwdVersion);
	}
}
