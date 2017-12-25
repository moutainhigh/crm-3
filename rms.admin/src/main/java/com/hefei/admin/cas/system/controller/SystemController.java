package com.hefei.admin.cas.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.cas.system.service.ISystemService;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/system")
@SuppressWarnings("all")
public class SystemController extends BaseController{
	
	private Logger logger = Logger.getLogger(SystemController.class);
	
	@Autowired
	private ISystemService systemService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "cas/system/index";
	}
	
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(String systemName, Integer pageSize, Integer pageIndex,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(pageSize == null || pageSize == 0)
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			if(pageIndex== null || pageIndex < 1)
				pageIndex = 1;
		
			Pagination<SystemInfo> pagination = systemService.find(systemName, pageSize, pageIndex);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/system/index";
	}
	
	@RequestMapping(value="/toCreate",produces="text/plain;charset=UTF-8")
	public String toCreate(HttpServletRequest request,HttpServletResponse response) {
		return "cas/system/system";
	}
	
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String create(String systemName, String jsonCallback, HttpServletRequest request,HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			if(StringUtils.isBlank(systemName)){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			SystemInfo systemInfo = new SystemInfo();
			systemInfo.setSystemName(systemName);
			systemInfo = systemService.create(systemInfo);
			if(systemInfo != null && systemInfo.getId() != null)
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		}
		return super.returnBaseResponse(baseResponse, jsonCallback);
	}
}
