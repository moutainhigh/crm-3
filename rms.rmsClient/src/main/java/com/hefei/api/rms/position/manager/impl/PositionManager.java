package com.hefei.api.rms.position.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.position.manager.IPositionManager;
import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class PositionManager extends BaseManager implements IPositionManager{
	
	private static final Logger logger = Logger.getLogger(PositionManager.class);
	
	
	@Override
	public PositionInfo savePosition(PositionInfo positionInfo) throws ClientException {
		if(positionInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("savePosition begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("positionInfo", positionInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_POSITION_SAVE, dataJson, timer, head);
			logger.info("savePosition end time:" + timer + " result:" + result);
			logger.info("savePosition " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				positionInfo = (PositionInfo) JacksonUtil.jsonToBean(objStr, PositionInfo.class);
	 			return positionInfo;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("savePosition error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public void updatePosition(Long id, String delFlag) throws ClientException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("updatePosition begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("delFlag", delFlag);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_POSITION_UPDATE, dataJson, timer, head);
			logger.info("updatePosition end time:" + timer + " result:" + result);
			logger.info("updatePosition " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("updatePosition error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	

	public List<PositionInfo> getPostionByDepartment(Long departmentId)  throws ClientException{
		if(departmentId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("getPostionByDepartment begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("departmentId", departmentId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_POSITION_GET_BY_DEPARTMENT, dataJson, timer, head);
			logger.info("getPostionByDepartment end time:" + timer + " result:" + result);
			logger.info("getPostionByDepartment " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<PositionInfo> list =  (List<PositionInfo>)JacksonUtil.jsonToCollections(listStr, List.class, PositionInfo.class);
	 			return list;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findPosition error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public PositionInfo getPosition(Long id) throws ClientException{
		if(id == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("getPosition begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_POSITION_GET_BY_ID, dataJson, timer, head);
			logger.info("getPosition end time:" + timer + " result:" + result);
			logger.info("getPosition " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				PositionInfo position = (PositionInfo) JacksonUtil.jsonToBean(objStr, PositionInfo.class);
				
	 			return position;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("getPosition error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
