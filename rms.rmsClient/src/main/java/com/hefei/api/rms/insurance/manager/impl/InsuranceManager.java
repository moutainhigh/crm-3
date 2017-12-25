package com.hefei.api.rms.insurance.manager.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.insurance.manager.IInsuranceManager;
import com.hefei.api.rms.insurance.vo.InsuranceInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class InsuranceManager implements IInsuranceManager{

	private static final Logger logger = Logger.getLogger(InsuranceManager.class);
	
	public InsuranceManager() throws ClientException{
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e) {
			logger.error("error", e);
		}
	}
	
	@Override
	public InsuranceInfo findInsurance(Long employeeId) throws ClientException {
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("findInsurance begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INSURANCE_FIND, dataJson, timer, head);
			logger.info("findInsurance end time:" + timer + " result:" + result);
			logger.info("findInsurance " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			InsuranceInfo insuranceInfo = (InsuranceInfo) JacksonUtil.jsonToBean(objStr, InsuranceInfo.class);
	 			return insuranceInfo;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findInsurance error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public Boolean saveInsurance(InsuranceInfo insuranceInfo) throws ClientException {
		if(insuranceInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveSecurity begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("insuranceInfo", insuranceInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INSURANCE_SAVE, dataJson, timer, head);
			logger.info("saveSecurity end time:" + timer + " result:" + result);
			logger.info("saveSecurity " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("saveSecurity error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
		
	}

	@Override
	public Boolean updateInsurance(InsuranceInfo insuranceInfo) throws ClientException {
		if(insuranceInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveSecurity begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("insuranceInfo", insuranceInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INSURANCE_UPDATE, dataJson, timer, head);
			logger.info("saveSecurity end time:" + timer + " result:" + result);
			logger.info("saveSecurity " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("saveSecurity error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
		
	}

}
