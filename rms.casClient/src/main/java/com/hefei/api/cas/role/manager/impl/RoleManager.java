package com.hefei.api.cas.role.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.role.manager.IRoleManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class RoleManager extends BaseManager implements IRoleManager{

	private static Logger logger = Logger.getLogger(RoleManager.class);

	@Override
	public Pagination<RoleInfo> find(Long systemId, Long companyId,String roleName, int pageSize, int pageIndex) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			
			dataJson.put("roleName", roleName);
			dataJson.put("systemId", systemId);
			dataJson.put("companyId", companyId);
			dataJson.put("pageSize", pageSize);
			dataJson.put("pageIndex", pageIndex);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ROLE_FINDPAGINATION, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, RoleInfo.class);
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
	public RoleInfo create(RoleInfo roleInfo) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("roleInfo", roleInfo);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ROLE_CREATE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				RoleInfo role = (RoleInfo) JacksonUtil.jsonToBean(objStr, RoleInfo.class);
				return role;
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
	public RoleInfo getById(Long id) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ROLE_GETBYID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				RoleInfo sys = (RoleInfo) JacksonUtil.jsonToBean(objStr, RoleInfo.class);
				return sys;
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
	/**
	 * 通过公司ID 查找当前公司的超级管理员角色ID
	 * @param companyId
	 */
	public List<RoleInfo> getManagerRoleByCompanyId(Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ROLE_GET_MANAGER_ROLE_BY_COMPANYID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<RoleInfo> list =  (List<RoleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, RoleInfo.class);
				return list;
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
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("roleId", roleId);
			dataJson.put("moduleIdAndCheck", JacksonUtil.beanToJson(moduleIdAndCheck));
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ROLE_AUTHMODULE, dataJson, timer, head);
			
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
