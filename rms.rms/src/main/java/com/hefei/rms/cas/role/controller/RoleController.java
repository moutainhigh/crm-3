package com.hefei.rms.cas.role.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.cas.config.manager.ICasConfigManager;
import com.hefei.api.cas.config.manager.impl.CasConfigManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.cas.service.ICasRoleService;
import com.hefei.rms.common.base.controller.BaseController;
import com.hefei.rms.common.util.RmsConstant;
import com.hefei.rms.company.service.ICompanyService;

@Controller
@RequestMapping(value="/cas/role",produces="text/plain;charset=UTF-8")
public class RoleController extends BaseController{

	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICasRoleService casRoleService;
	
	private ICasConfigManager casConfigManager = new CasConfigManager();
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize){
		if(pageIndex ==null || pageIndex == 0)
			pageIndex = 1;
		if(pageSize == null || pageSize == 0)
			pageSize = Pagination.DEFAULT_PAGE_SIZE;
		try {
			Pagination<RoleInfo> pagination =  casRoleService.find(casConfigManager.getSystemIdRMS(), CommonParameterThreadLocal.getCurrentCompanyId(), null, pageSize, pageIndex);
			
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pagination", pagination);
			
			request.setAttribute("companySuperManagerName", casConfigManager.getRMSSuperManagerRoleName());
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "cas/role/index";
	}
	
	@RequestMapping(value="/toAdd",produces="text/plain;charset=UTF-8")
	public String toAdd(HttpServletRequest request, HttpServletResponse response){
		try {
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), CommonParameterThreadLocal.getCurrentCompanyId());
			request.setAttribute("company", company);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "cas/role/role";
	}
	
	@RequestMapping(value="/add",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String add(String roleName,String remark, String jsonCallback, HttpServletRequest request,HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if(StringUtils.isBlank(roleName)){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setCompanyId(CommonParameterThreadLocal.getCurrentCompanyId());
			roleInfo.setRemark(remark);
			roleInfo.setRoleName(roleName);
			roleInfo.setSystemId(casConfigManager.getSystemIdRMS());
			roleInfo = casRoleService.create(roleInfo);
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
