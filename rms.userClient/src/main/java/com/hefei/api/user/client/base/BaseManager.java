package com.hefei.api.user.client.base;

import org.apache.log4j.Logger;

import com.hefei.api.user.client.UserClient;
import com.hefei.api.user.client.UserUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;

public class BaseManager {
	private static Logger logger = Logger.getLogger(BaseManager.class);
	public BaseManager(){
		try {
			ClientFactory.init(UserClient.class, UserClient.CONFIG_FILE_NAME_CLIENT, UserUrlConstants.class, UserUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}
}
