package com.hefei.api.agg.log.manager.impl;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.agg.client.AggClient;
import com.hefei.api.agg.client.AggUrlConstants;
import com.hefei.api.agg.log.manager.IUserLogManager;
import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.util.JacksonUtil;

public class UserLogManager implements IUserLogManager{
	private static Logger logger = Logger.getLogger(UserLogManager.class);
	
	public UserLogManager(){
		try {
			ClientFactory.init(AggClient.class, AggClient.CONFIG_FILE_NAME_CLIENT, AggUrlConstants.class, AggUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}
	
	@Override
	public BaseResponse saveUserLoginHistory(UserLoginHistory userLoginHistory)throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );
		RequestHead head;
		BaseResponse baseResponse = null;
		try {
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("userLoginHistory", userLoginHistory);
			String result = SendRequest.send(AggUrlConstants.AGG_SERVER_USER_LOGIN_HISTORY, dataJson, timer, head);
			logger.info("saveUserLoginHistory time:" + timer + " result:" + result);
			logger.info(head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
		} catch (Exception e) {
			logger.error("saveUserLoginHistory error", e);
		}
		return baseResponse;
	}

}
