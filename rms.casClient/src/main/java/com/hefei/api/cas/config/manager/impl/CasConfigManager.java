package com.hefei.api.cas.config.manager.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.common.CasConstants;
import com.hefei.api.cas.config.manager.ICasConfigManager;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CasConfigManager extends BaseManager implements ICasConfigManager {
	
	private static Logger logger = Logger.getLogger(CasConfigManager.class);
	
	public Long getRMSSuperManagerRoleId() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}
	public String getRMSSuperManagerRoleName() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLENAME);
		map = getConfig(map);
		if(map != null){
			return String.valueOf(map.get("value"));
		}
		return null;
	}
	
	public Long getFMSSuperManagerRoleId() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}
	public String getFMSSuperManagerRoleName() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLENAME);
		map = getConfig(map);
		if(map != null){
			return String.valueOf(map.get("value"));
		}
		return null;
	}
	
	public Long getRMSUserRoleId() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_RMS_USER_ROLEID);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}
	public String getRMSUserRoleName() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.DEFAULT_RMS_USER_ROLENAME);
		map = getConfig(map);
		if(map != null){
			return String.valueOf(map.get("value"));
		}
		return null;
	}
	@Override
	public Long getSystemIdAdmin() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.SYSTEM_ID_ADMIN);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}

	@Override
	public Long getSystemIdRMS() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.SYSTEM_ID_RMS);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}
	
	public Long getSystemIdFMS() throws ClientException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", CasConstants.SYSTEM_ID_FMS);
		map = getConfig(map);
		if(map != null){
			return Long.valueOf(String.valueOf(map.get("value")));
		}
		return null;
	}
	
	private Map getConfig(Map<String, String> map) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );
		RequestHead head;
		BaseResponse resp = null;
		try {
			if(map == null || map.size() == 0)
				return null;
			head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			Iterator<String> keyIt = map.keySet().iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
				dataJson.put(key, map.get(key));
			}
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_CONFIG_GET, dataJson, timer, head);
			logger.info("time:" + timer + " result:" + result);
			logger.info("time:" + timer + "  "+ head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				return (Map<String, Object>) resp.getReturnObject();
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
