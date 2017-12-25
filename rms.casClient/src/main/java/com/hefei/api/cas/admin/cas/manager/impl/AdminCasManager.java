package com.hefei.api.cas.admin.cas.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.cas.admin.cas.manager.IAdminCasManager;
import com.hefei.api.cas.client.CasUrlConstants;
import com.hefei.api.cas.common.BaseManager;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class AdminCasManager extends BaseManager implements IAdminCasManager{
	private static Logger logger = Logger.getLogger(AdminCasManager.class);
	
	public List<ModuleInfo> getModuleMenus(Long adminId, Long systemId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );	
		
		try {
			RequestHead head = RequestHead.get(timer, ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("adminId", adminId);
			dataJson.put("systemId", systemId);
			String result = SendRequest.send(CasUrlConstants.URL_SERVER_CAS_ADMIN_MODULEMENUS, dataJson, timer, head);
			
	 		logger.info(" time:" + timer + " result:" + result);
			logger.info( head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			
			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(baseResponse.getReturnObject());
				List<ModuleInfo> moduleMenus =  (List<ModuleInfo>)JacksonUtil.jsonToCollections(listStr, List.class, ModuleInfo.class);
				return moduleMenus;
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
