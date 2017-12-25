package com.hefei.agg.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hefei.agg.address.util.AddressUtil;


public class InitListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(InitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		initAddressInfo();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	} 

	/**
	 * 加载省市县信息
	 */
	private void initAddressInfo() {
		try {
			AddressUtil.initAddressData();
		} catch (Exception e) {
			log.error("initAddressInfo error : ",e);
			System.exit(0);
		}
	}
}
