package com.hefei.api.rms.attendance.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.behavior.vo.BehaviorInfo;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.DateUtil;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class AttendanceManager implements IAttendanceManager{

	private static Logger logger = Logger.getLogger(AttendanceManager.class);
	
	public AttendanceManager(){
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e){
			logger.error("error", e);
		}
	}

	@Override
	public Boolean createAttendance(AttendanceInfo attendanceInfo) throws ClientException {
		if(attendanceInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
			try {
			long timer = System.currentTimeMillis();
			logger.info("createAttendance time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("attendanceInfo", attendanceInfo);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_ATTENDANCE_CREATE, dataJson, timer, head);
			logger.info("createAttendance end time:" + timer + " result:" + result);
			logger.info("createAttendance " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			} catch (Exception e) {
				logger.error("createAttendance error",e);
				throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
			}
		return returnBoolean;
	}
	@Override
	public Boolean updateAttendance(Long id, String delFlag) throws ClientException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("updateAttendance begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("delFlag", delFlag);
			//String goTime = DateUtil.date2String(goworkTime, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_ATTENDANCE_UPDATE, dataJson, timer, head);
			logger.info("updateAttendance end time:" + timer + " result:" + result);
			logger.info("updateAttendance " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("updateAttendance error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		return returnBoolean;
	}
	@Override
	public List<AttendanceInfo> findAttendance(Long employeeId) throws ClientException {
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("findAttendance begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_ATTENDANCE_FIND, dataJson, timer, head);
			logger.info("findAttendance end time:" + timer + " result:" + result);
			logger.info("findAttendance " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<AttendanceInfo> list =  (List<AttendanceInfo>)JacksonUtil.jsonToCollections(listStr, List.class, AttendanceInfo.class);
	 			return list;
			}else {
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findAttendance error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
