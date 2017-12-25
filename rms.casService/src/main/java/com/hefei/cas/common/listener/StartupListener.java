package com.hefei.cas.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hefei.cas.common.CasConstants;
import com.hefei.common.util.ConfigReader;

public class StartupListener implements ServletContextListener {
	
	private Logger logger = Logger.getLogger(StartupListener.class);

	public static final String CONFIG_FILE = "configure";
	
	protected void initConstant() {
		try {
			ConfigReader reader = ConfigReader.read(CONFIG_FILE);
			
			CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID = Long.valueOf(reader.getConstant("DEFAULT_RMS_SUPER_MANAGER_ROLEID"));
			CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLENAME = reader.getConstant("DEFAULT_RMS_SUPER_MANAGER_ROLENAME");
			logger.info(" DEFAULT_RMS_SUPER_MANAGER_ROLEID:" + CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID);
			
			CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID = Long.valueOf(reader.getConstant("DEFAULT_FMS_SUPER_MANAGER_ROLEID"));
			CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLENAME = reader.getConstant("DEFAULT_FMS_SUPER_MANAGER_ROLENAME");
			logger.info(" DEFAULT_FMS_SUPER_MANAGER_ROLEID:" + CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID);
			
			
			CasConstants.DEFAULT_RMS_USER_ROLEID = Long.valueOf(reader.getConstant("DEFAULT_RMS_USER_ROLEID"));
			CasConstants.DEFAULT_RMS_USER_ROLENAME = reader.getConstant("DEFAULT_RMS_USER_ROLENAME");
			logger.info(" DEFAULT_RMS_USER_ROLEID:" + CasConstants.DEFAULT_RMS_USER_ROLEID);
			
			CasConstants.SYSTEM_ID_ADMIN = Long.valueOf(reader.getConstant("SYSTEM_ID_ADMIN"));
			CasConstants.SYSTEM_ID_RMS = Long.valueOf(reader.getConstant("SYSTEM_ID_RMS"));
			CasConstants.SYSTEM_ID_FMS = Long.valueOf(reader.getConstant("SYSTEM_ID_FMS"));
			
		} catch (Exception e) {
			logger.error(" error", e);
			System.exit(0);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initConstant();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
