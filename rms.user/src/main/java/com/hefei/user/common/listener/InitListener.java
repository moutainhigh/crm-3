package com.hefei.user.common.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.http.listener.FrontendStartupListener;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.user.constants.UserConstants;

public class InitListener extends FrontendStartupListener {
	
	private Logger logger = Logger.getLogger(InitListener.class);

	public static final String CONFIG_FILE = "configure";
	
	public void contextInitialized(ServletContextEvent arg0) {
		
		initConstant();
	}

	protected void initConstant() {
		super.initConstant();
		try {
			/**cookie user相关配置获取**/
			
			ConfigReader reader = ConfigReader.read(CONFIG_FILE);
			UserConstants.USER_SERVER_URL = reader.getConstant("USER_SERVER_URL");
			logger.info(RequestThreadLocal.getRequestStr()+"USER_SERVER_URL:"+UserConstants.USER_SERVER_URL);
			UserConstants.STAIC_SERVER_URL = reader.getConstant("STAIC_SERVER_URL");
			logger.info(RequestThreadLocal.getRequestStr()+"STAIC_SERVER_URL:"+UserConstants.STAIC_SERVER_URL);
			UserConstants.MANAGER_SERVER_URL = reader.getConstant("MANAGER_SERVER_URL");
			logger.info(RequestThreadLocal.getRequestStr()+"MANAGER_SERVER_URL:"+UserConstants.MANAGER_SERVER_URL);
			
			
			
			
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}