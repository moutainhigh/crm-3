package com.hefei.admin.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.admin.auth.controller.AuthController;
import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.frame.service.IFrameService;
import com.hefei.api.cas.module.util.ModuleTree;
import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/frame")
@SuppressWarnings("all")
public class FrameController extends BaseController{


	private Logger logger = Logger.getLogger(AuthController.class);
	@Autowired
	private IFrameService frameService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		
		setMenu(request, response);
		return "frame/main";
	}
	
	private void setMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			List<ModuleTree> menu = frameService.getMenus(getAdminId(request, response));
			request.setAttribute("menuTree", menu);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
	}
}

