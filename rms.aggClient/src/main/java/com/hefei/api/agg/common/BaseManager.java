package com.hefei.api.agg.common;

import org.apache.log4j.Logger;

import com.hefei.api.agg.client.AggClient;
import com.hefei.api.agg.client.AggUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;

public class BaseManager {
	private static Logger logger = Logger.getLogger(BaseManager.class);
	public BaseManager(){
		try{
			ClientFactory.init(AggClient.class, AggClient.CONFIG_FILE_NAME_CLIENT, AggUrlConstants.class, AggUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}
}
