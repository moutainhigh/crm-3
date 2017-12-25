package com.hefei.user.api.mobile.manager.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.user.client.UserUrlConstants;
import com.hefei.api.user.client.base.BaseManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.user.api.email.vo.EmailInfo;
import com.hefei.user.api.mobile.manager.IMobileManager;
import com.hefei.user.api.mobile.vo.MobileInfo;

public class MobileManager  extends BaseManager implements IMobileManager{

	private static Logger logger = Logger.getLogger(MobileManager.class);
	
	@Override
	public MobileInfo getLoginMobileByUserId(Long userId) throws ClientException {
		if(userId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_MOBILE_GET_LOGIN_MOBILE_BY_USERID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			MobileInfo mobileInfo = (MobileInfo) JacksonUtil.jsonToBean(objStr, MobileInfo.class);
	 			return mobileInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public MobileInfo getByMobile(String mobile, String type) throws ClientException {
		if(mobile == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("mobile", mobile);
			dataJson.put("type", type);
			
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_MOBILE_GET_BY_MOBILE, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			MobileInfo mobileInfo = (MobileInfo) JacksonUtil.jsonToBean(objStr, MobileInfo.class);
	 			return mobileInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
