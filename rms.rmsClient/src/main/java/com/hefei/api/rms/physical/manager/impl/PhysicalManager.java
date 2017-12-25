package com.hefei.api.rms.physical.manager.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.insurance.vo.InsuranceInfo;
import com.hefei.api.rms.physical.manager.IPhysicalManager;
import com.hefei.api.rms.physical.vo.PhysicalInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class PhysicalManager implements IPhysicalManager{

	private static final Logger logger = Logger.getLogger(PhysicalManager.class);
	
	public PhysicalManager() throws ClientException{
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e) {
			logger.error("error", e);
		}
	}
	@Override
	public PhysicalInfo findPhysical(Long employeeId) throws ClientException {
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("findPhysical begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_PHYSICAL_FIND, dataJson, timer, head);
			logger.info("findPhysical end time:" + timer + " result:" + result);
			logger.info("findPhysical " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			PhysicalInfo physicalInfo = (PhysicalInfo) JacksonUtil.jsonToBean(objStr, PhysicalInfo.class);
	 			return physicalInfo;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findPhysical error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public Boolean savePhysical(PhysicalInfo physicalInfo) throws ClientException {
		if(physicalInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("savePhysical begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("physicalInfo", physicalInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_PHYSICAL_SAVE, dataJson, timer, head);
			logger.info("savePhysical end time:" + timer + " result:" + result);
			logger.info("savePhysical " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("savePhysical error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}

	@Override
	public Boolean updatePhysical(PhysicalInfo physicalInfo) throws ClientException {
		if(physicalInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("updatePhysical begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("physicalInfo", physicalInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_PHYSICAL_UPDATE, dataJson, timer, head);
			logger.info("updatePhysical end time:" + timer + " result:" + result);
			logger.info("updatePhysical " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("updatePhysical error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}
	

}
