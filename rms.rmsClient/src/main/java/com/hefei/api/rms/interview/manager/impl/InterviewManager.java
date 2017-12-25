package com.hefei.api.rms.interview.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsClient;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.api.rms.interview.manager.IInterviewManager;
import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class InterviewManager implements IInterviewManager{
private static final Logger logger = Logger.getLogger(InterviewManager.class);
	
	public InterviewManager() {
		try {
			ClientFactory.init(RmsClient.class, RmsClient.CONFIG_FILE_NAME_CLIENT, RmsUrlConstants.class, RmsUrlConstants.CONFIG_FILE_NAME_CONF);
		} catch (ClientException e) {
		logger.error("error", e);
		}
	}

	@Override
	public Boolean saveInterviewInfo(InterviewInfo interviewInfo) throws ClientException {
		
		if(interviewInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveInterviewInfo begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("interviewInfo", interviewInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INTERVIEW_SAVE, dataJson, timer, head);
			logger.info("saveInterviewInfo end time:" + timer + " result:" + result);
			logger.info("saveInterviewInfo " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("saveInterviewInfo error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}

	@Override
	public Boolean updateInterview(Long id, String interviewStatus) throws ClientException {
		if(id == null || StringUtils.isBlank(interviewStatus)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Boolean returnBoolean = false;
		
		try {
			long timer = System.currentTimeMillis();
			logger.info("updateInterview begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("interviewStatus", interviewStatus);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INTERVIEW_UPDATE, dataJson, timer, head);
			logger.info("updateInterview end time:" + timer + " result:" + result);
			logger.info("updateInterview " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			returnBoolean = true;
			}
			
		} catch (Exception e) {
			logger.error("updateInterview error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
		return returnBoolean;
	}

	@Override
	public List<InterviewInfo> findInterview(Long id, String interviewStatus) throws ClientException {
		if(id == null || StringUtils.isBlank(interviewStatus)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("findInterview begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("interviewStatus", interviewStatus);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_INTERVIEW_FIND, dataJson, timer, head);
			logger.info("findInterview end time:" + timer + " result:" + result);
			logger.info("findInterview " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<InterviewInfo> list =  (List<InterviewInfo>)JacksonUtil.jsonToCollections(listStr, List.class, InterviewInfo.class);
	 			return list;
			}else {
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findInterview error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
