package com.hefei.admin.cas.role.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.cas.role.service.IRoleService;
import com.hefei.admin.cas.system.service.ISystemService;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/role")
@SuppressWarnings("all")
public class RoleController extends BaseController{
	
	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private ISystemService systemService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		setSystems(request, response);
		return "cas/role/index";
	}
	
	private void setSystems(HttpServletRequest request,HttpServletResponse response){
		try {
			Pagination<SystemInfo> pagination = systemService.find(null, 100, 1);
			request.setAttribute("systems", pagination.getItems());
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
	}
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(Long systemId, String roleName, Integer pageSize, Integer pageIndex,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(pageSize == null || pageSize == 0)
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			if(pageIndex== null || pageIndex < 1)
				pageIndex = 1;
			
			Pagination<RoleInfo> pagination = roleService.find(systemId, roleName, pageSize, pageIndex);
			request.setAttribute("pagination", pagination);
			
			setSystems(request, response);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/role/index";
	}
	
	@RequestMapping(value="/toCreate",produces="text/plain;charset=UTF-8")
	public String toCreate(HttpServletRequest request,HttpServletResponse response) {
		setSystems(request, response);
		return "cas/role/role";
	}
	
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String create(Long companyId,Long systemId, String roleName,String remark, String jsonCallback, HttpServletRequest request,HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if(StringUtils.isBlank(roleName)){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setCompanyId(companyId);
			roleInfo.setRemark(remark);
			roleInfo.setRoleName(roleName);
			roleInfo.setSystemId(systemId);
			roleInfo = roleService.create(roleInfo);
			if(roleInfo != null && roleInfo.getId() != null)
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
