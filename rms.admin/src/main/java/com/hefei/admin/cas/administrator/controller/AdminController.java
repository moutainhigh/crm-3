package com.hefei.admin.cas.administrator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.cas.administrator.service.IAdminService;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/admin")
@SuppressWarnings("all")
public class AdminController extends BaseController{
	
	private Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "cas/admin/index";
	}
	
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(String username, String mobile, String email, Integer pageSize, Integer pageIndex,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(pageSize == null || pageSize == 0)
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			if(pageIndex== null || pageIndex < 1)
				pageIndex = 1;
		
			Pagination<AdminInfo> pagination = adminService.find(username, mobile, email, pageSize, pageIndex);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/admin/index";
	}
	
	@RequestMapping(value="/toCreate",produces="text/plain;charset=UTF-8")
	public String toCreate(HttpServletRequest request,HttpServletResponse response) {
		return "cas/admin/admin";
	}
	
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String create(AdminInfo admin, String jsonCallback, HttpServletRequest request,HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			if(StringUtils.isBlank(admin.getUsername())){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			admin.setCreator(getAdminId(request, response));
			admin.setUpdater(getAdminId(request, response));
			admin = adminService.create(admin);
			if(admin != null && admin.getId() != null)
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
