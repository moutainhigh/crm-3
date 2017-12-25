package com.hefei.api.cas.user.auth.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.cas.user.auth.manager.IUserAuthorizationManager;
import com.hefei.api.cas.user.menu.SysMenu;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class UserAuthorizationManager extends BaseManager implements IUserAuthorizationManager{
	
	private static Logger logger = Logger.getLogger(UserAuthorizationManager.class);
	
	public void createCompanySuperManager(Long userId,Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_SUPER_MANAGER, dataJson, timer, head);
			
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
	public void createCompanyCommonRole(Long userId,Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_COMMON, dataJson, timer, head);
			
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
	
//	public void authDefault(Long userId, Long companyId, Long roleId) throws ClientException{
//		long timer = System.currentTimeMillis();
//		logger.info("time:" + timer );	
//		
//		try {
//			RequestHead head = RequestHead.get(timer, ClientFactory.client);
//			ObjectNode dataJson = JacksonUtil.createObjectNode();
//			dataJson.put("userId", userId);
//			dataJson.put("companyId", companyId);
//			dataJson.put("roleId", roleId);
//			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_AUTH_DEFAULT, dataJson, timer, head);
//			
//	 		logger.info(" time:" + timer + " result:" + result);
//			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
//			
//			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
//			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
//				throw new ClientException(baseResponse.getReturnCode());
//			}
//		} catch (ClientException e) {
//			throw e;
//		} catch (Exception e) {
//			logger.error(timer + " error", e);
//			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
//		}
//	}
	
	@Override
	public boolean hasAuthorization(Long userId,Long companyId,  String url) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyId", companyId);
			dataJson.put("url", url);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_CHECK_AUTH, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				return true;
			}else if(ReturnCode.RETURN_CODE_ERROR_CAS_FAIL.equals(baseResponse.getReturnCode())){
				return false;
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
	
	public List<SysMenu> getMenus(Long userId, Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_MENUS, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<SysMenu> menus = (List<SysMenu>)JacksonUtil.jsonToCollections(listStr, List.class, SysMenu.class);
				if(menus != null){
					for(int i=0; i<menus.size();i++){
						SysMenu sysMenu = menus.get(i);
						String moduleStr = JacksonUtil.beanToJson(sysMenu.getModules());
						if(StringUtils.isNotBlank(moduleStr)){
							List<ModuleInfo> moduleMenus =  (List<ModuleInfo>)JacksonUtil.jsonToCollections(moduleStr, List.class, ModuleInfo.class);
							sysMenu.setModules(moduleMenus);
						}
					}
				}
				
				return menus;
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
	public List<RoleInfo> getRoleByUserId(Long userId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_GET_ROLE_BY_USERID, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<RoleInfo> roleInfos =  (List<RoleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, RoleInfo.class);
				return roleInfos;
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
	public void userAuthRole(Long userId, Map<Long, String> roleIdAndCheck) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("roleIdAndCheck", JacksonUtil.beanToJson(roleIdAndCheck));
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_USER_AUTHROLE, dataJson, timer, head);
			
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
