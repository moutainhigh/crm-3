package com.hefei.api.user.auth.manager.impl;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.user.auth.manager.IAuthManager;
import com.hefei.api.user.auth.vo.AuthRequest;
import com.hefei.api.user.client.UserUrlConstants;
import com.hefei.api.user.client.base.BaseManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class AuthManager extends BaseManager implements IAuthManager{

	private static Logger logger = Logger.getLogger(AuthManager.class);

	@Override
	public Long login(AuthRequest authRequest)throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("login time:" + timer );
		RequestHead head;
		BaseResponse resp = null;
		try {
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("authRequest", authRequest);
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_LOGIN_URL, dataJson, timer, head);
			logger.info("login time:" + timer + " result:" + result + " cost:" + (System.currentTimeMillis() - timer));
			resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				return  Long.valueOf(resp.getReturnObject().toString());
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
