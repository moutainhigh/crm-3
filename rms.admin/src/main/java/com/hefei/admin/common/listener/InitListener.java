package com.hefei.admin.common.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import com.hefei.admin.constants.AdminConstants;
import com.hefei.common.util.ConfigReader;
import com.hefei.frontend.framework.http.listener.FrontendStartupListener;

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
			AdminConstants.STAIC_SERVER_URL = reader.getConstant("STAIC_SERVER_URL");
			
			AdminConstants.ADMIN_DOMAIN = reader.getConstant("ADMIN_DOMAIN");
			AdminConstants.ADMIN_SERVER_URL = reader.getConstant("ADMIN_SERVER_URL");
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}