package com.hefei.cas.user.auth.controller;

import java.util.ArrayList;
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
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.user.menu.SysMenu;
import com.hefei.cas.admin.auth.controller.AuthController;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.system.po.Sys;
import com.hefei.cas.system.service.ISystemService;
import com.hefei.cas.user.auth.service.IUserAuthService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/cas/user/menu", produces = "text/plain;charset=UTF-8")
public class UserMenuController {

private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private IUserAuthService userAuthService;
	@Autowired
	private ISystemService systemService;
	
	@RequestMapping(value="/get")
	@ResponseBody
	public String get() {
		BaseResponse result = new BaseResponse();
		String plain = RequestThreadLocal.getPlain();
		//参数判断非空
		if(StringUtils.isBlank(plain)) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
			return JacksonUtil.beanToJson(result);
		}
		JsonNode node = JacksonUtil.getJsonNode(plain);
		Long userId = JacksonUtil.getLong(node, "userId");
		Long companyId = JacksonUtil.getLong(node, "companyId");
		try {
			List<Module> moduleMenus = userAuthService.getModuleMenus(userId, companyId);
			
			Map<Long, SysMenu> sysMap = new HashMap<Long, SysMenu>();
			List<ModuleInfo> menus = null;
			if(moduleMenus != null && moduleMenus.size() > 0){
				for(Module  module: moduleMenus){
					Long systemId = module.getSystemId();
					SysMenu sysMenu = sysMap.get(systemId);
					if(sysMenu == null){
						Sys sys = systemService.getById(systemId);
						sysMenu = new SysMenu();
						sysMenu.setSystemId(sys.getId());
						sysMenu.setSystemName(sys.getSystemName());
						sysMap.put(systemId, sysMenu);
					}
					menus = sysMenu.getModules();
					if(menus == null){
						 menus = new ArrayList<ModuleInfo>();
						 sysMenu.setModules(menus);
					}
					
					ModuleInfo menu = new ModuleInfo();
					BeanUtils.copyProperties(module, menu);
					menus.add(menu);
				}
			}
			

			List<SysMenu> sysMenus = new ArrayList<SysMenu>();
			sysMenus.addAll(sysMap.values());
			
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(sysMenus);
		} catch (ServiceException e) {
			result.setReturnCode(e.getErrorCode());
		}
		logger.info(RequestThreadLocal.getLoggerStr()+" result "+result);
		return JacksonUtil.beanToJson(result);
	}
}
