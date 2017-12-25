package com.hefei.admin.cas.administrator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.admin.base.controller.BaseController;
import com.hefei.admin.cas.administrator.service.IAdminService;
import com.hefei.admin.cas.administrator.vo.AuthInfo;
import com.hefei.admin.cas.role.service.IRoleService;
import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.config.manager.ICasConfigManager;
import com.hefei.api.cas.config.manager.impl.CasConfigManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/admin")
@SuppressWarnings("all")
public class AdminAuthRoleController extends BaseController{
	
	private Logger logger = Logger.getLogger(AdminAuthRoleController.class);
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAdminService adminService;
	
	private ICasConfigManager casConfigManager = new CasConfigManager();
	
	@RequestMapping(value="/toAuthRole",produces="text/plain;charset=UTF-8")
	public String toAuthRole(Long adminId, HttpServletRequest request,HttpServletResponse response) {
		try {
			if(adminId == null){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			AdminInfo admin = adminService.getById(adminId);
			if(admin == null){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			request.setAttribute("admin",admin);
			
			Pagination<RoleInfo> pagination = roleService.find(casConfigManager.getSystemIdAdmin(), null, 1000, 1);
			List<RoleInfo> allSystemRoles = pagination.getItems();
			if(allSystemRoles == null){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			List<RoleInfo> existsRoles = adminService.getRoleByAdmin(adminId);
			
			setAuthInfo(request, allSystemRoles, existsRoles);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/admin/admin_auth_role";
	}
	
	public void setAuthInfo(HttpServletRequest request, List<RoleInfo> allSystemRoles, List<RoleInfo> existsRoles){
		Map<Long, RoleInfo> existsRolesMap = new HashMap<Long, RoleInfo>();
		if(existsRoles != null && existsRoles.size() > 0){
			for(RoleInfo role : existsRoles){
				existsRolesMap.put(role.getId(), role);
			}
		}
		List<AuthInfo> list = new ArrayList<AuthInfo>();
		AuthInfo info = null;
		for(RoleInfo role : allSystemRoles){
			info = new AuthInfo();
			info.setRoleId(role.getId());
			info.setRoleName(role.getRoleName());
			if(existsRolesMap.containsKey(role.getId())){
				info.setCheck(AuthInfo.CHECK_YES);
			}else{
				info.setCheck(AuthInfo.CHECK_NO);
			}
			list.add(info);
		}
		
		request.setAttribute("authInfo", list);
		request.setAttribute("authInfoJson", JacksonUtil.beanToJson(list));
	}
	@RequestMapping(value="/authRole",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String authRole(Long adminId, String idAndCheck,HttpServletRequest request,HttpServletResponse response) {
		
		logger.info(RequestThreadLocal.getTimer() + " adminId=" + adminId + " idAndCheck=" + idAndCheck);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			if(adminId == null){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			if(StringUtils.isEmpty(idAndCheck)){
				throw new BusinessException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}
			
			 Map<Long, String> idCheckMap= parserModuleCheck(idAndCheck);
			 
			 adminService.adminAuthRole(adminId, idCheckMap);
			 
			 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	/**
	 * id=1-check=Y_id=2-check=N
	 * @param idAndCheck
	 * @return
	 */
	private Map<Long, String> parserModuleCheck(String idAndCheck){
		if(StringUtils.isEmpty(idAndCheck))
			return null;
		String[] idAndChecks = idAndCheck.split("#");
		Long id;
		String check;
		Map<Long, String> map = new HashMap<Long, String>();
		for(String idAndCheckInfo : idAndChecks){
			if(StringUtils.isNotBlank(idAndCheckInfo)){
				String[] info = idAndCheckInfo.split("-");
				String[] idinfo = info[0].split("=");
				id =Long.valueOf(idinfo[1]);
				
				String[] checkinfo = info[1].split("=");
				check = checkinfo[1];
				map.put(id, check);
			}
		}
		return map;
	}
}
