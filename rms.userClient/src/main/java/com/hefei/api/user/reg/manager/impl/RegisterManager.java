package com.hefei.api.user.reg.manager.impl;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.user.client.UserUrlConstants;
import com.hefei.api.user.client.base.BaseManager;
import com.hefei.api.user.reg.manager.IRegisterManager;
import com.hefei.api.user.reg.vo.RegisterRequestByEmail;
import com.hefei.api.user.reg.vo.RegisterRequestByEmailMobile;
import com.hefei.api.user.reg.vo.RegisterRequestByMobile;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class RegisterManager extends BaseManager implements IRegisterManager{
	
	private static Logger logger = Logger.getLogger(RegisterManager.class);

	@Override
	public Long regByEmail(RegisterRequestByEmail registerRequestByEmail)throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("regUserEmail time:" + timer );
		RequestHead head;
		BaseResponse resp = null;
		try {
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("registerRequest", registerRequestByEmail);
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_REGISTER_EMAIL, dataJson, timer, head);
			logger.info("regUserEmail time:" + timer + " result:" + result);
			logger.info("regUserEmail " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				return Long.valueOf(resp.getReturnObject().toString());
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public Long regByMobile(RegisterRequestByMobile registerRequestByMobile)throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("regByMobile time:" + timer );
		RequestHead head;
		BaseResponse resp = null;
		try {
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("registerRequest", registerRequestByMobile);
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_REGISTER_MOBILE, dataJson, timer, head);
			logger.info("regByMobile time:" + timer + " result:" + result);
			logger.info("regByMobile " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				return Long.valueOf(resp.getReturnObject().toString());
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public Long companyAddEmployee(RegisterRequestByEmailMobile registerRequestByEmailMobile)throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );
		RequestHead head;
		BaseResponse resp = null;
		try {
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("registerRequest", registerRequestByEmailMobile);
			String result = SendRequest.send(UserUrlConstants.USER_SERVER_REGISTER_RMS_COMPANY_ADD_EMPLOYEE, dataJson, timer, head);
			logger.info("time:" + timer + " result:" + result);
			logger.info("time:" + timer + "  "+ head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				return Long.valueOf(resp.getReturnObject().toString());
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
