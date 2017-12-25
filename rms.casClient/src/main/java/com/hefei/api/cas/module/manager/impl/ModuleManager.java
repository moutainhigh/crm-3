package com.hefei.api.cas.module.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.module.manager.IModuleManager;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class ModuleManager extends BaseManager implements IModuleManager {
	
	private static Logger logger = Logger.getLogger(ModuleManager.class);

	@Override
	public List<ModuleInfo> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("systemIds", systemIds);
			dataJson.putPOJO("withButton", withButton);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_GETMODULEBYSYSTEMID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<ModuleInfo> modules =  (List<ModuleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, ModuleInfo.class);
				return modules;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
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

	@Override
	public ModuleInfo getModuleById(Long moduleId) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("moduleId", moduleId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_GETMODULEBYID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				ModuleInfo mInfo = (ModuleInfo) JacksonUtil.jsonToBean(objStr, ModuleInfo.class);
				return mInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
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

	@Override
	public List<ModuleInfo> getModuleByRole(List<Long> roleIds) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("roleIds", roleIds);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_GETMODULEBYROLE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<ModuleInfo> modules =  (List<ModuleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, ModuleInfo.class);
				return modules;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
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

	@Override
	public List<ModuleInfo> getModuleByParentId(Long parentModuleId) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("parentId", parentModuleId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_GETMODULEBYPARENTID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<ModuleInfo> modules =  (List<ModuleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, ModuleInfo.class);
				return modules;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
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

	@Override
	public ModuleInfo createModule(ModuleInfo module) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("moduleInfo", module);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_CREATE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				ModuleInfo mInfo = (ModuleInfo) JacksonUtil.jsonToBean(objStr, ModuleInfo.class);
				return mInfo;
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
	public void deleteModule(List<Long> moduleIds) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("moduleIds", moduleIds);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_MODULE_DELETE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
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
