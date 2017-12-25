package com.hefei.api.cas.admin.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.admin.manager.IAdminManager;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class AdminManager extends BaseManager  implements IAdminManager{
	private static Logger logger = Logger.getLogger(AdminManager.class);

	@Override
	public Pagination<AdminInfo> find(String username, String mobile,
			String email, int pageSize, int pageIndex) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("username", username);
			dataJson.put("mobile", mobile);
			dataJson.put("email", email);
			dataJson.put("pageSize", pageSize);
			dataJson.put("pageIndex", pageIndex);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_FINDPAGINATION, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, AdminInfo.class);
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
	public AdminInfo create(AdminInfo adminInfo) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("adminInfo", adminInfo);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_CREATE, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				AdminInfo sys = (AdminInfo) JacksonUtil.jsonToBean(objStr, AdminInfo.class);
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
	public AdminInfo getById(Long id) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_GETBYID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				AdminInfo sys = (AdminInfo) JacksonUtil.jsonToBean(objStr, AdminInfo.class);
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
	public List<RoleInfo> getRoleByAdmin(Long adminId) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("adminId", adminId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_GETROLEBYADMIN, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<RoleInfo> roles =  (List<RoleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, RoleInfo.class);
				return roles;
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
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("adminId", adminId);
			dataJson.put("roleIdAndCheck", JacksonUtil.beanToJson(roleIdAndCheck));
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_AUTHROLE, dataJson, timer, head);
			
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
