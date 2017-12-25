package com.hefei.api.rms.salary.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryHistoryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.api.rms.salary.manager.ISalaryManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.DateUtil;
import com.hefei.common.util.JacksonUtil;

public class SalaryManager  extends BaseManager implements ISalaryManager{

	private static final Logger logger = Logger.getLogger(SalaryManager.class);

	@Override
	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId, Long userId) throws ClientException {
		if(companyId == null || employeeId== null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("employeeId", employeeId);
			dataJson.put("userId", userId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_GET, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				SalarySSDTO dto = (SalarySSDTO) JacksonUtil.jsonToBean(objStr, SalarySSDTO.class);
				
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
	public SalaryTransactionDTO getSalaryTransaction(Long transactionId)
			throws ClientException {
		if(transactionId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("transactionId", transactionId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_TRANSACTION_GET, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				SalaryTransactionDTO dto = (SalaryTransactionDTO) JacksonUtil.jsonToBean(objStr, SalaryTransactionDTO.class);
				
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
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime,
			String remark, Long currentUsrId) throws ClientException {
		if(salaryDTO == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("salaryDTO", salaryDTO);
			dataJson.put("remark", remark);
			dataJson.put("currentUserId", currentUsrId);
			dataJson.put("effectTime", DateUtil.date2String(effectTime, DateUtil.FORMAT_YYYY_MM_DD));
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_ADJUST, dataJson, timer, head);
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
	public void saveAdjustSalaryTransaction(
			SalaryTransactionDTO salaryTransactionDTO, String remark,
			Long currentUsrId) throws ClientException {
		if(salaryTransactionDTO == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("salaryTransactionDTO", salaryTransactionDTO);
			dataJson.put("remark", remark);
			dataJson.put("currentUserId", currentUsrId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_TRANSACTION_ADJUST, dataJson, timer, head);
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
	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo, String employeeName, int pageIndex, int pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("cardNo", cardNo);
			dataJson.put("companyId", companyId);
			dataJson.put("employeeName", employeeName);
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_FIND_PAGINATION, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, SalarySSDTO.class);
				pagination.setItems(list);
				return pagination;
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

	@Override
	public Pagination<SalaryHistoryDTO> findSalayAdjustHistory(Long companyId,
			Long employeeId, Long userId, String cardNo, String employeeName,int pageIndex, int pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("cardNo", cardNo);
			dataJson.put("employeeName", employeeName);
			dataJson.put("companyId", companyId);
			dataJson.put("employeeId", employeeId);
			dataJson.put("companyId", companyId);
			dataJson.put("userId", userId);
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALAY_HISTORY_FIND_PAGINATION, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, SalaryHistoryDTO.class);
				pagination.setItems(list);
				return pagination;
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

	@Override
	public Pagination<SalaryPayTransactionDTO> findSalayAndSSPayHistory(
			Long companyId, Long employeeId, Long userId,
			String employeeStatus, String salaryPayStatus, String ssPayStatus,
			String cardNo, String employeeName, int pageIndex, int pageSize)
			throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("cardNo", cardNo);
			dataJson.put("companyId", companyId);
			dataJson.put("employeeId", employeeId);
			dataJson.put("companyId", companyId);
			dataJson.put("userId", userId);
			
			dataJson.put("employeeStatus", employeeStatus);
			dataJson.put("salaryPayStatus", salaryPayStatus);
			dataJson.put("ssPayStatus", ssPayStatus);
			
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_TRANSACTION_FIND_PAGINATION, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, SalaryPayTransactionDTO.class);
				pagination.setItems(list);
				return pagination;
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

	@Override
	public String generatePayInfo(Long companyId, String employeeStatus)
			throws ClientException {
		if(companyId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("employeeStatus", employeeStatus);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_TRANSACTION_GENERATE, dataJson, timer, head);
			logger.info(" end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			return resp.getReturnCode();
		} catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public String paySalary(Long companyId, Long employeeId, Long userId,
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
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_SALARY_TRANSACTION_PAY, dataJson, timer, head);
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
