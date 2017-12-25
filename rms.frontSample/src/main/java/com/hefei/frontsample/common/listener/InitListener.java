package com.hefei.frontsample.common.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;
import com.hefei.frontend.framework.http.listener.FrontendStartupListener;
import com.hefei.frontsample.common.util.FrontSampleConstant;

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
			FrontSampleConstant.STAIC_SERVER_URL = reader.getConstant("STAIC_SERVER_URL");
			FrontSampleConstant.FRONTSAMPLE_URL = reader.getConstant("FRONTSAMPLE_URL");
			FrontSampleConstant.USER_URL = reader.getConstant("USER_URL");
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}