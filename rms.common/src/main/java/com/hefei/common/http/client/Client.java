package com.hefei.common.http.client;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;

public abstract class Client {

	private static Logger logger = Logger.getLogger(Client.class);
	private String clientId = null;
	
	public void init(String configFileName){
		try{
			ConfigReader clientReader = ConfigReader.read(configFileName);
			if(clientReader != null) {
				clientId = clientReader.getConstant("client_id").trim();
			}else {
				logger.error(configFileName + " is not exist");
				System.exit(0);
			}
		}catch(Exception e){
			logger.error(configFileName + " error");
			System.exit(0);
		}
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}