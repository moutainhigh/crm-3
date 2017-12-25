package com.hefei.api.cas.common;

import org.apache.log4j.Logger;

import com.hefei.api.cas.client.CasClient;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;

public class BaseManager {
	private static Logger logger = Logger.getLogger(BaseManager.class);
	public BaseManager(){
		try {
			ClientFactory.init(CasClient.class, CasClient.CONFIG_FILE_NAME_CLIENT, CasUrlConstants.class, CasUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}
}
