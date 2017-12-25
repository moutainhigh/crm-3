package com.hefei.api.rms.leave.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.api.rms.leave.manager.ILeaveManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.DateUtil;
import com.hefei.common.util.JacksonUtil;

public class LeaveManager extends BaseManager implements ILeaveManager{

	private static final Logger logger = Logger.getLogger(LeaveManager.class);
	
	@Override
	public Pagination<LeaveDTO> findLeave(String employeeName, Long employeeId,
			String cardNo, Long companyId, String leaveType,
			Long auditEmployeeId, String auditStatus, Date startTime,
			Date endTime, int pageIndex, int pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeName", employeeName);
			dataJson.put("employeeId", employeeId);
			dataJson.put("cardNo", cardNo);
			dataJson.put("companyId", companyId);
			dataJson.put("leaveType", leaveType);
			dataJson.put("auditEmployeeId", auditEmployeeId);
			dataJson.put("auditStatus", auditStatus);
			dataJson.put("startTime", DateUtil.date2String(startTime, DateUtil.FORMAT_YYYY_MM_DDHHMMSS));
			dataJson.put("endTime", DateUtil.date2String(endTime, DateUtil.FORMAT_YYYY_MM_DDHHMMSS));
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_LEAVE_FIND_PAGINATION, dataJson, timer, head);
			logger.info(head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, LeaveDTO.class);
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
	public LeaveDTO applyLeave(LeaveDTO leaveDTO) throws ClientException {
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("leaveDTO", leaveDTO);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_LEAVE_APPLY, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				leaveDTO = (LeaveDTO) JacksonUtil.jsonToBean(objStr, LeaveDTO.class);
	 			return leaveDTO;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void audit(Long auditEmployeeId, Long leaveId, String auditStatus)
			throws ClientException {
		if(auditEmployeeId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info(" begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("auditEmployeeId", auditEmployeeId);
			dataJson.put("leaveId", leaveId);
			dataJson.put("auditStatus", auditStatus);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_LEAVE_AUDIT, dataJson, timer, head);
			logger.info(" end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error(" error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public LeaveDTO get(Long leaveId) throws ClientException {
		if(leaveId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("leaveId", leaveId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_LEAVE_GET_BY_ID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info("" + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			LeaveDTO leaveDTO = (LeaveDTO) JacksonUtil.jsonToBean(objStr, LeaveDTO.class);
	 			return leaveDTO;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error(" error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
