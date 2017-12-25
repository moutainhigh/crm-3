package com.hefei.common.http.request;

import org.apache.log4j.Logger;

import com.hefei.common.http.client.Client;
import com.hefei.common.util.StringUtils;
import com.hefei.common.util.UUIDGenerator;

public class RequestHead implements java.io.Serializable{

	private static final long serialVersionUID = 8445638702588692859L;
	private static Logger logger = Logger.getLogger(RequestHead.class);
	
	private String clientId;
	private long timer;
	private String token;
	
	public String getLoggerStr(){
		return "  clientId:" + clientId + " timer:" + timer + " token:" + token;
	}
	public static RequestHead get(Long timer, Client client) throws Exception{
		if(client == null || StringUtils.isEmpty(client.getClientId())){
			logger.error(" client id is empty!");
			throw new Exception(" client id is empty!");
		}
		
		RequestHead head = new RequestHead();
		head.setClientId(client.getClientId());
		head.setTimer(timer);
		head.setToken(UUIDGenerator.getUUID());
		logger.info(head.getLoggerStr());
		return head;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public long getTimer() {
		return timer;
	}
	public void setTimer(long timer) {
		this.timer = timer;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
