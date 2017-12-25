package com.hefei.api.rms.socialsecurity.manager.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.api.rms.socialsecurity.manager.ISocialsecurityManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class SocialsecurityManager extends BaseManager implements ISocialsecurityManager{

	private static final Logger logger = Logger.getLogger(SocialsecurityManager.class);

	@Override
	public EmployeeSSDTO getEmployeeSSDTO(Long companyId, Long employeeId)
			throws ClientException {
		if(companyId == null || employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("employeeId", employeeId);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SS_GET, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				EmployeeSSDTO dto = (EmployeeSSDTO) JacksonUtil.jsonToBean(objStr, EmployeeSSDTO.class);
				
	 			return dto;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public EmployeeSSTransactionDTO getEmployeeSSTransaction(
			Long ssTransactionId) throws ClientException {
		if(ssTransactionId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("ssTransactionId", ssTransactionId);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SS_TRANSACTION_GET, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				EmployeeSSTransactionDTO dto = (EmployeeSSTransactionDTO) JacksonUtil.jsonToBean(objStr, EmployeeSSTransactionDTO.class);
				
	 			return dto;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void savAdjustSS(EmployeeSSDTO employeeSSDTO) throws ClientException {
		if(employeeSSDTO == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("employeeSSDTO", employeeSSDTO);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SS_ADJUST, dataJson, timer, head);
			logger.info(" end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public void saveAdjustEmployeeSSTransaction(
			EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark,
			Long currentUsrId) throws ClientException {
		if(employeeSSTransactionDTO == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("employeeSSTransactionDTO", employeeSSTransactionDTO);
			dataJson.put("remark", remark);
			dataJson.put("currentUserId", currentUsrId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SS_TRANSACTION_ADJUST, dataJson, timer, head);
			logger.info(" end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public String paySS(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws ClientException {
		if(companyId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("userId", userId);
			dataJson.put("employeeId", employeeId);
			dataJson.put("transactionIds", transactionIds);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SS_TRANSACTION_PAY, dataJson, timer, head);
			logger.info(" end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			return resp.getReturnCode();
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
}
