package com.hefei.cas.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.admin.auth.controller.AuthController;
import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.service.IAdminService;
import com.hefei.cas.role.po.Role;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/admin", produces = "text/plain;charset=UTF-8")
public class AdminController {
private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IAdminService adminService;
	
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
			String username = JacksonUtil.getString(node, "username");
			String mobile = JacksonUtil.getString(node, "mobile");
			String email = JacksonUtil.getString(node, "email");
			int pageSize = JacksonUtil.getInt(node, "pageSize");
			int pageIndex = JacksonUtil.getInt(node, "pageIndex");
		
			Pagination<AdminInfo> pagination = adminService.find(username, mobile, email, pageSize, pageIndex);
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
			JsonNode systemInfoNode = node.get("adminInfo");
			AdminInfo adminInfo = JacksonUtil.jsonToBean(systemInfoNode, AdminInfo.class);
			Admin admin = new Admin();
			BeanUtils.copyProperties(adminInfo, admin);
			admin = adminService.createAdmin(admin);
			admin.setPassword(null);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(admin);
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
			Admin admin =  adminService.getById(id);
			if(admin == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				admin.setPassword(null);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(admin);
			}
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
	@RequestMapping(value="/getRoleByAdmin",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getRoleByAdmin(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			Long adminId = JacksonUtil.getLong(node, "adminId");
			
			List<Role> existsRole = adminService.getRoleByAdmin(adminId);
			List<RoleInfo> roles = new ArrayList<RoleInfo>();
			if(existsRole != null && existsRole.size() > 0){
				for(Role  role: existsRole){
					RoleInfo ri = new RoleInfo();
					BeanUtils.copyProperties(role, ri);
					roles.add(ri);
				}
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(roles);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	@RequestMapping(value="/authRole")
	@ResponseBody
	public String authRole() {
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
			Long adminId = JacksonUtil.getLong(node, "adminId");
			String roleIdAndCheckString = JacksonUtil.getString(node, "roleIdAndCheck");
			Map<String, String> roleIdAndCheck = JacksonUtil.jsonToBean(roleIdAndCheckString, HashMap.class);
			Map<Long, String> roleIdAndCheckInfo = new HashMap<Long, String>();
			for(String moduleId:roleIdAndCheck.keySet()){
				roleIdAndCheckInfo.put(Long.valueOf(moduleId), roleIdAndCheck.get(moduleId));
			}
			adminService.adminAuthRole(adminId, roleIdAndCheckInfo);
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
