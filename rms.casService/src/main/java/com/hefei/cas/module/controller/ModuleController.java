package com.hefei.cas.module.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.cas.base.controller.BaseController;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.module.service.IModuleService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/module", produces = "text/plain;charset=UTF-8")
public class ModuleController extends BaseController{
	private static final Logger logger = Logger.getLogger(ModuleController.class);
	
	@Autowired
	private IModuleService moduleService;
	
	@RequestMapping(value="/getModuleById",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getModuleById(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			Long moduleId = JacksonUtil.getLong(node, "moduleId");
			Module module = moduleService.getModuleById(moduleId);
			
			ModuleInfo moduleInfo = new ModuleInfo();
			BeanUtils.copyProperties(module, moduleInfo);
			if(module != null){
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(moduleInfo);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	@RequestMapping(value="/getModuleByRole",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getModuleByRole(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			List<Long>  roleIds= JacksonUtil.jsonToPrimaryArray(node.get("roleIds"), Long.class);
			List<Module> modules = moduleService.getModuleByRole(roleIds);
			List<ModuleInfo> menus = new ArrayList<ModuleInfo>();
			if(modules != null && modules.size() > 0){
				for(Module  module: modules){
					ModuleInfo menu = new ModuleInfo();
					BeanUtils.copyProperties(module, menu);
					menus.add(menu);
				}
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(menus);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}

	@RequestMapping(value="/getModuleByParentId",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getModuleByParentId(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			Long parentId = JacksonUtil.getLong(node, "parentId");
			
			List<Module> modules = moduleService.getModuleByParentId(parentId);
			
			List<ModuleInfo> menus = new ArrayList<ModuleInfo>();
			if(modules != null && modules.size() > 0){
				for(Module  module: modules){
					ModuleInfo menu = new ModuleInfo();
					BeanUtils.copyProperties(module, menu);
					menus.add(menu);
				}
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(menus);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	

	@RequestMapping(value="/getModuleBySystemId",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getModuleBySystemId(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			List<Long>  systemIds= JacksonUtil.jsonToPrimaryArray(node.get("systemIds"), Long.class);
			boolean withButton = JacksonUtil.getBoolean(node, "withButton");
			
			List<Module> modules = moduleService.getModuleBySystemId(systemIds, withButton);
			
			List<ModuleInfo> menus = new ArrayList<ModuleInfo>();
			if(modules != null && modules.size() > 0){
				for(Module  module: modules){
					ModuleInfo menu = new ModuleInfo();
					BeanUtils.copyProperties(module, menu);
					menus.add(menu);
				}
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(menus);
			}else{
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String createModule(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			JsonNode moduleInfoNode = node.get("moduleInfo");
			ModuleInfo moduleInfo = JacksonUtil.jsonToBean(moduleInfoNode, ModuleInfo.class);
			Module module =  new Module();
			BeanUtils.copyProperties(moduleInfo, module);
			module = moduleService.createModule(module);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			baseResponse.setReturnObject(module);
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	@RequestMapping(value="/delete",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteModule(HttpServletRequest request, HttpServletResponse response){
		BaseResponse baseResponse = new BaseResponse();
		try {
			String plain = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plain)) {
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(baseResponse);
			}
			JsonNode node = JacksonUtil.getJsonNode(plain);
			List<Long>   moduleIds= JacksonUtil.jsonToPrimaryArray(node.get("moduleIds"), Long.class);
			moduleService.deleteModule(moduleIds);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (ServiceException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "getFrontModule error!", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
}
