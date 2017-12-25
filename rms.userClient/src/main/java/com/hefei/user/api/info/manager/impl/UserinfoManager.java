package com.hefei.user.api.info.manager.impl;

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
import com.hefei.user.api.info.manager.IUserinfoManager;
import com.hefei.user.api.info.vo.UserInfo;

public class UserinfoManager  extends BaseManager implements IUserinfoManager{

	private static Logger logger = Logger.getLogger(UserinfoManager.class);

	@Override
	public void updateUser(UserInfo userinfo) throws ClientException {
		if(userinfo == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("userinfo", userinfo);
			
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_USERINFO_UPDATE_BY_ID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public UserInfo getById(Long id) throws ClientException {
		if(id == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", id);
			
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_USERINFO_GET_BY_ID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			UserInfo vo = (UserInfo) JacksonUtil.jsonToBean(objStr, UserInfo.class);
	 			return vo;
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
	public UserInfo getByIdNo(String idNo) throws ClientException {
		if(idNo == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("idNo", idNo);
			
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_USERINFO_GET_BY_IDNO, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			UserInfo vo = (UserInfo) JacksonUtil.jsonToBean(objStr, UserInfo.class);
	 			return vo;
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
