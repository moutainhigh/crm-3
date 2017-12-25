package com.hefei.manager.common.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;
import com.hefei.frontend.framework.http.listener.FrontendStartupListener;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.manager.constants.ManagerConstants;

public class InitListener extends FrontendStartupListener {
	
	private Logger logger = Logger.getLogger(InitListener.class);

	public static final String CONFIG_FILE = "configure";
	
	public void contextInitialized(ServletContextEvent arg0) {
		
		initConstant();
	}

	protected void initConstant() {
		super.initConstant();
		try {
			ConfigReader reader = ConfigReader.read(CONFIG_FILE);
			ManagerConstants.STAIC_SERVER_URL = reader.getConstant("STAIC_SERVER_URL");
			logger.info(RequestThreadLocal.getRequestStr()+"STAIC_SERVER_URL:"+ManagerConstants.STAIC_SERVER_URL);
			
			ManagerConstants.DOMAIN = reader.getConstant("DOMAIN");
			logger.info(RequestThreadLocal.getRequestStr()+"DOMAIN:"+ManagerConstants.DOMAIN);
			
			ManagerConstants.DOMAIN_MANAGER = reader.getConstant("DOMAIN_MANAGER");
			logger.info(RequestThreadLocal.getRequestStr()+"DOMAIN_MANAGER:"+ManagerConstants.DOMAIN_MANAGER);
			
			ManagerConstants.DOMAIN_USER = reader.getConstant("DOMAIN_USER");
			logger.info(RequestThreadLocal.getRequestStr()+"DOMAIN_USER:"+ManagerConstants.DOMAIN_USER);
			
			
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}