package com.hefei.cas.config.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.cas.common.CasConstants;
import com.hefei.cas.config.service.IConfigService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;


@Controller
@RequestMapping(value="/cas/config", produces = "text/plain;charset=UTF-8")
public class ConfigController {

	private static Logger logger = Logger.getLogger(ConfigController.class);
	
	@Autowired
	private IConfigService configService;
	
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			String key = JacksonUtil.getString(node, "key");
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(key.equals(CasConstants.SYSTEM_ID_ADMIN)){
				Long systemIdAdmin = configService.getSystemIdAdmin();
				if(systemIdAdmin != null)
					map.put("value", systemIdAdmin);
			}
			if(key.equals(CasConstants.SYSTEM_ID_RMS)){
				Long systemIdRms = configService.getSystemIdRMS();
				if(systemIdRms != null)
					map.put("value", systemIdRms);
			}
			if(key.equals(CasConstants.SYSTEM_ID_FMS)){
				Long systemIdFms = configService.getSystemIdFMS();
				if(systemIdFms != null)
					map.put("value", systemIdFms);
			}
			if(key.equals(CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID)){
				Long value = configService.getRMSSuperManagerRoleId();
				if(value != null)
					map.put("value", value);
			}
			if(key.equals(CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLENAME)){
				String value = configService.getRMSSuperManagerRoleName();
				if(value != null)
					map.put("value", value);
			}
			if(key.equals(CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID)){
				Long value = configService.getFMSSuperManagerRoleId();
				if(value != null)
					map.put("value", value);
			}
			if(key.equals(CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLENAME)){
				String value = configService.getFMSSuperManagerRoleName();
				if(value != null)
					map.put("value", value);
			}
			if(key.equals(CasConstants.DEFAULT_RMS_USER_ROLEID)){
				Long value = configService.getRMSUserRoleId();
				if(value != null)
					map.put("value", value);
			}
			if(key.equals(CasConstants.DEFAULT_RMS_USER_ROLENAME)){
				String value = configService.getRMSUserRoleName();
				if(value != null)
					map.put("value", value);
			}
			if(map != null && map.size() > 0){
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(map);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
}
