package com.hefei.api.rms.contract.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.contract.manager.IContractManager;
import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class ContractManager implements IContractManager{
private static final Logger logger = Logger.getLogger(ContractManager.class);
	
	public ContractManager() {
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e) {
		logger.error("error", e);
		}
	}
	@Override
	public Boolean saveInfo(ContractInfo contractInfo) throws ClientException {
		if(contractInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveInfo begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("contractInfo", contractInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_CONTRACT_SAVE, dataJson, timer, head);
			logger.info("saveInfo end time:" + timer + " result:" + result);
			logger.info("saveInfo " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("saveInfo error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		return returnBoolean;
	}
	@Override
	public Boolean updateContract(Long id, String auditStatus,String contractType, String delFlag) throws ClientException {
		if(id == null || StringUtils.isBlank(auditStatus) || StringUtils.isBlank(contractType) || StringUtils.isBlank(delFlag)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		try {
			long timer = System.currentTimeMillis();
			logger.info("updateContract begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("auditStatus", auditStatus);
			dataJson.put("contractType", contractType);
			dataJson.put("delFlag", delFlag);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_CONTRACT_UPDATE, dataJson, timer, head);
			logger.info("updateContract end time:" + timer + " result:" + result);
			logger.info("updateContract " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
		} catch (Exception e) {
			logger.error("updateContract error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		return returnBoolean;
	}
	@Override
	public List<ContractInfo> findContract(Long employeeId) throws ClientException {
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("findContract begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_CONTRACT_FIND, dataJson, timer, head);
			logger.info("findContract end time:" + timer + " result:" + result);
			logger.info("findContract " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<ContractInfo> list =  (List<ContractInfo>)JacksonUtil.jsonToCollections(listStr, List.class, ContractInfo.class);
	 			return list;
			}else {
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findContract error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
