package com.hefei.admin.cas.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.cas.module.service.IModuleService;
import com.hefei.admin.cas.module.vo.TreeNode;
import com.hefei.admin.cas.system.controller.SystemController;
import com.hefei.admin.cas.system.service.ISystemService;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/module")
@SuppressWarnings("all")
public class ModuleController  extends BaseController{
	
	private Logger logger = Logger.getLogger(SystemController.class);
	
	@Autowired
	private IModuleService moduleService;
	
	@Autowired
	private ISystemService systemService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<TreeNode> treeNodes = moduleService.treeNode();
			String zTreeNodes = JacksonUtil.beanToJson(treeNodes);
			request.setAttribute("zTreeNodes", zTreeNodes);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		
		
		return "cas/module/index";
	}
	
	@RequestMapping(value="/toAddModule",produces="text/plain;charset=UTF-8")
	public String toAddModule(String moduleId,HttpServletRequest request,HttpServletResponse response) {
		try{
			String[] params = moduleId.split("_");
			Long systemId = Long.valueOf(params[0]);
			Long id = null;
			if(moduleId.indexOf("_") ==-1){//系统下加菜单
			}else{//菜单下增加菜单
				id = Long.valueOf(params[1]);
			}
			
			SystemInfo system = systemService.getById(systemId);
			request.setAttribute("system", system);
			if(id !=null){
				ModuleInfo parentModule = moduleService.getById(id);
				request.setAttribute("parentModule", parentModule);
			}
			
			ModuleInfo module = new ModuleInfo();
			module.setType(ModuleInfo.TYPE_MENU);
			request.setAttribute("module", module);
			
			request.setAttribute("type", ModuleInfo.TYPE_MENU);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}	catch(Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/module/moduleAdd";
	}
	@RequestMapping(value="/toAddButton",produces="text/plain;charset=UTF-8")
	public String toAddButton(String moduleId,HttpServletRequest request,HttpServletResponse response) {
		try{
			String[] params = moduleId.split("_");
			Long systemId = Long.valueOf(params[0]);
			Long id = Long.valueOf(params[1]);
			SystemInfo system = systemService.getById(systemId);
			request.setAttribute("system", system);
			
			ModuleInfo parentModule = moduleService.getById(id);
			request.setAttribute("parentModule", parentModule);
			
			ModuleInfo module = new ModuleInfo();
			module.setType(ModuleInfo.TYPE_BUTTON);
			request.setAttribute("module", module);
			
			request.setAttribute("type", ModuleInfo.TYPE_BUTTON);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}	catch(Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/module/moduleAdd";
	}
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8")
	public String create(ModuleInfo module, HttpServletRequest request,HttpServletResponse response) {
		try{
			moduleService.createModule(module);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}	catch(Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/module/moduleAddSuccess";
	}
	
	@RequestMapping(value="/delModule",produces="text/plain;charset=UTF-8")
	public String delModule(String moduleId,HttpServletRequest request,HttpServletResponse response) {
		return "cas/system/system";
	}
	@RequestMapping(value="/toEditModule",produces="text/plain;charset=UTF-8")
	public String toEditModule(String moduleId,HttpServletRequest request,HttpServletResponse response) {
		return "cas/system/system";
	}
	@RequestMapping(value="/viewModule",produces="text/plain;charset=UTF-8")
	public String viewModule(String moduleId,HttpServletRequest request,HttpServletResponse response) {
		return "cas/system/system";
	}
}
