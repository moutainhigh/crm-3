package com.hefei.api.rms.client.base;

import org.apache.log4j.Logger;

import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;

public class BaseManager {
	private static Logger logger = Logger.getLogger(BaseManager.class);
	public BaseManager(){
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}
}
