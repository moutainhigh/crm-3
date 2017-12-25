package com.hefei.api.cas.admin.auth.manager.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.admin.auth.manager.IAdminAuthManager;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class AdminAuthManager extends BaseManager implements IAdminAuthManager{
	private static Logger logger = Logger.getLogger(AdminAuthManager.class);
	
	public AdminInfo auth(String username, String password) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("username", username);
			dataJson.put("password", password);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_AUTH, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				AdminInfo adminInfo = (AdminInfo) JacksonUtil.jsonToBean(objStr, AdminInfo.class);
				return adminInfo;
			}else{
				throw new ClientException(baseResponse.getReturnCode());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
}
