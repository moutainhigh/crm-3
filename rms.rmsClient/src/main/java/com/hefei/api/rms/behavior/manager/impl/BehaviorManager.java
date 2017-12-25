package com.hefei.api.rms.behavior.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.behavior.manager.IBehaviorManager;
import com.hefei.api.rms.behavior.vo.BehaviorInfo;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;


public class BehaviorManager implements IBehaviorManager{

	private static final Logger logger = Logger.getLogger(BehaviorManager.class);
	
	public BehaviorManager() throws ClientException{
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e) {
			logger.error("error", e);
		}
	}
	
	@Override
	public Boolean saveBehavior(BehaviorInfo behaviorInfo) throws ClientException {
		if(behaviorInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveBehavior begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("behaviorInfo", behaviorInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_BEHAVIOR_SAVE, dataJson, timer, head);
			logger.info("saveBehavior end time:" + timer + " result:" + result);
			logger.info("saveBehavior " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("saveBehavior error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}

	@Override
	public Boolean updateBehavior(Long id, String status) throws ClientException {
		if(id == null || StringUtils.isBlank(status)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("updateBehavior begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("status", status);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_BEHAVIOR_UPDATE, dataJson, timer, head);
			logger.info("updateBehavior end time:" + timer + " result:" + result);
			logger.info("updateBehavior " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("updateBehavior error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}

	@Override
	public List<BehaviorInfo> findBehavior(Long employeeId,String month) throws ClientException {
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("findBehavior begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			dataJson.put("month", month);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_BEHAVIOR_FIND, dataJson, timer, head);
			logger.info("findBehavior end time:" + timer + " result:" + result);
			logger.info("findBehavior " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<BehaviorInfo> list =  (List<BehaviorInfo>)JacksonUtil.jsonToCollections(listStr, List.class, BehaviorInfo.class);
	 			return list;
			}else {
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findBehavior error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
