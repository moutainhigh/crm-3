package com.hefei.api.cas.system.manger.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.system.manger.ISystemManager;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class SystemManager extends BaseManager  implements ISystemManager{
	private static Logger logger = Logger.getLogger(SystemManager.class);
	@Override
	public Pagination<SystemInfo> find(String systemName, int pageSize,
			int pageIndex) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("systemName", systemName);
			dataJson.put("pageSize", pageSize);
			dataJson.put("pageIndex", pageIndex);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_SYSTEM_FINDPAGINATION, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, SystemInfo.class);
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
	public SystemInfo create(SystemInfo systemInfo) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("systemInfo", systemInfo);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_SYSTEM_CREATE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				SystemInfo sys = (SystemInfo) JacksonUtil.jsonToBean(objStr, SystemInfo.class);
				return sys;
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
	public SystemInfo getById(Long id) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_SYSTEM_GETBYID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				SystemInfo sys = (SystemInfo) JacksonUtil.jsonToBean(objStr, SystemInfo.class);
				return sys;
			}if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
				return null;
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

}
