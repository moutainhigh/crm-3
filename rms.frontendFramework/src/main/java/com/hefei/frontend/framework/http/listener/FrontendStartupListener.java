package com.hefei.frontend.framework.http.listener;

import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.FrontendConstants;

public abstract class FrontendStartupListener implements ServletContextListener {
	
	private Logger logger = Logger.getLogger(FrontendStartupListener.class);

	public static final String CONFIG_FILE = "frontendstartup";
	
	protected void initConstant() {
		try {
			ConfigReader reader = ConfigReader.read(CONFIG_FILE);
			
			CookieConstants.COOKIE_NAME_WEB = reader.getConstant("COOKIE_NAME_WEB");
			logger.info(" COOKIE_NAME_WEB:" + CookieConstants.COOKIE_NAME_WEB);
			
			CookieConstants.COOKIE_DOMAIN = reader.getConstant("COOKIE_DOMAIN");
			logger.info(" COOKIE_DOMAIN:" + CookieConstants.COOKIE_DOMAIN);
			
			CookieConstants.COOKIE_NAME_USER_LOGIN_USERID = reader.getConstant("COOKIE_NAME_USER_LOGIN_USERID");
			logger.info(" COOKIE_NAME_USER_LOGIN_USERID:" + CookieConstants.COOKIE_NAME_USER_LOGIN_USERID);
			CookieConstants.COOKIE_NAME_USER_LOGIN_USERNAME = reader.getConstant("COOKIE_NAME_USER_LOGIN_USERNAME");
			logger.info(" COOKIE_NAME_USER_LOGIN_USERNAME:" + CookieConstants.COOKIE_NAME_USER_LOGIN_USERNAME);
			CookieConstants.COOKIE_NAME_USER_LOGIN_NICKNAME = reader.getConstant("COOKIE_NAME_USER_LOGIN_NICKNAME");
			logger.info(" COOKIE_NAME_USER_LOGIN_NICKNAME:" + CookieConstants.COOKIE_NAME_USER_LOGIN_NICKNAME);
			
			CookieConstants.COOKIE_NAME_CURRENT_COMPANY = reader.getConstant("COOKIE_NAME_CURRENT_COMPANY");
			logger.info(" COOKIE_NAME_CURRENT_COMPANY:" + CookieConstants.COOKIE_NAME_CURRENT_COMPANY);
			
			
			FrontendConstants.LOGIN_URL = reader.getConstant("LOGIN_URL");
			logger.info(" LOGIN_URL:" + FrontendConstants.LOGIN_URL);
			FrontendConstants.HOME = reader.getConstant("HOME");
			logger.info(" HOME:" + FrontendConstants.HOME);
			
			
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}

}
