package com.hefei.cas.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.base.controller.BaseController;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.service.IRoleService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/role", produces = "text/plain;charset=UTF-8")
public class RoleController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value="/create")
	@ResponseBody
	public String create() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		try {
			JsonNode roleInfoNode = node.get("roleInfo");
			RoleInfo roleInfo = JacksonUtil.jsonToBean(roleInfoNode, RoleInfo.class);
			Role role = new Role();
			BeanUtils.copyProperties(roleInfo, role);
			role = roleService.saveRole(role);;
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(role);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
	@RequestMapping(value="/getById")
	@ResponseBody
	public String getById() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		try {
			Long id = JacksonUtil.getLong(node, "id");
			Role role = roleService.getRoleById(id);
			if(role == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(role);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	@RequestMapping(value="/find")
	@ResponseBody
	public String find() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		try {
			JsonNode node = JacksonUtil.getJsonNode(plain);
			String roleName = JacksonUtil.getString(node, "roleName");
			Long systemId = JacksonUtil.getLong(node, "systemId");
			Long companyId = JacksonUtil.getLong(node, "companyId");
			int pageSize = JacksonUtil.getInt(node, "pageSize");
			int pageIndex = JacksonUtil.getInt(node, "pageIndex");
		
			Pagination<RoleInfo> pagination = roleService.find(systemId,companyId, roleName, pageSize, pageIndex);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(pagination);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
	@RequestMapping(value="/getManagerRoleByCompanyId")
	@ResponseBody
	public String getManagerRoleByCompanyId() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		try {
			Long companyId = JacksonUtil.getLong(node, "companyId");
			List<Role> role = roleService.getManagerRoleByCompanyId(companyId);
			if(role == null || role.size() == 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(role);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
	@RequestMapping(value="/authModule")
	@ResponseBody
	public String authModule() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		try {
			JsonNode node = JacksonUtil.getJsonNode(plain);
			Long roleId = JacksonUtil.getLong(node, "roleId");
			String moduleIdAndCheckString = JacksonUtil.getString(node, "moduleIdAndCheck");
			Map<String, String> moduleIdAndCheck = JacksonUtil.jsonToBean(moduleIdAndCheckString, HashMap.class);
			Map<Long, String> moduleIdAndCheckInfo = new HashMap<Long, String>();
			for(String moduleId:moduleIdAndCheck.keySet()){
				moduleIdAndCheckInfo.put(Long.valueOf(moduleId), moduleIdAndCheck.get(moduleId));
			}
			roleService.roleAuthModule(roleId, moduleIdAndCheckInfo);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	
}
