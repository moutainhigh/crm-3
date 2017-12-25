package com.hefei.rms.cas.role.controller;

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

import com.hefei.api.cas.config.manager.ICasConfigManager;
import com.hefei.api.cas.config.manager.impl.CasConfigManager;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.cas.service.ICasRoleService;
import com.hefei.rms.cas.vo.AuthInfo;
import com.hefei.rms.common.util.RmsConstant;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.employee.service.IEmployeeService;

@Controller
@RequestMapping(value="/cas/role",produces="text/plain;charset=UTF-8")
public class UserAuthRoleController {

	private Logger logger = Logger.getLogger(UserAuthRoleController.class);
	
	@Autowired
	private ICasRoleService casRoleService;
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IEmployeeService employeeService;
	private ICasConfigManager casConfigManager = new CasConfigManager();
	
	@RequestMapping("toAuthRole")
	public String toAuthRole(HttpServletRequest request, HttpServletResponse response, Long employeeId){
		try {
			EmployeeInfo employeeInfo = employeeService.getEmployee(employeeId);
			Long userId = employeeInfo.getUserId();
			request.setAttribute("userId",userId);
			
			/**
			 * 查找当前公司超级管理员所拥有的所有模块
			 */
			Pagination<RoleInfo> pagination = casRoleService.find(casConfigManager.getSystemIdRMS(), CommonParameterThreadLocal.getCurrentCompanyId(), null, 1000, 1);
			List<RoleInfo> allCompanyRoles = new ArrayList<RoleInfo>();
			if(pagination != null && pagination.getItems() != null && pagination.getItems().size() > 0){
				for(RoleInfo ri :pagination.getItems()){
					if(!ri.getRoleName().startsWith(RoleInfo.RMS_SUPER_MANAGER)){
						allCompanyRoles.add(ri);
					}
				}
						
			}
			
			List<RoleInfo> existsRoles = casRoleService.getRoleByUserId(userId);
			setAuthInfo(request, allCompanyRoles, existsRoles);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/user/user_auth_role";
	}
	
	
	@RequestMapping(value="/authRole",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String authRole(HttpServletRequest request, HttpServletResponse response, String idAndCheck, Long userId){
		logger.info(RequestThreadLocal.getTimer() + " idAndCheck=" + idAndCheck + " userId=" + userId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<Long, String> idCheckMap= parserModuleCheck(idAndCheck);
			 casRoleService.userAuthRole(userId, idCheckMap);
			 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	/**
	 * 1-Y|2-N|
	 * @param idAndCheck
	 * @return
	 */
	private Map<Long, String> parserModuleCheck(String idAndCheck){
		if(StringUtils.isEmpty(idAndCheck))
			return null;
		String[] idAndChecks = idAndCheck.split("\\|");
		Long id;
		String check;
		Map<Long, String> map = new HashMap<Long, String>();
		for(String idAndCheckInfo : idAndChecks){
			if(StringUtils.isNotBlank(idAndCheckInfo)){//1-Y
				String[] info = idAndCheckInfo.split("-");
				id =Long.valueOf(info[0]);
				check = info[1];
				map.put(id, check);
			}
		}
		return map;
	}
	public void setAuthInfo(HttpServletRequest request, List<RoleInfo> allCompanyRoles, List<RoleInfo> existsRoles){
		
		Map<Long, RoleInfo> existsRolesMap = new HashMap<Long, RoleInfo>();
		if(existsRoles != null && existsRoles.size() > 0){
			for(RoleInfo role : existsRoles){
				existsRolesMap.put(role.getId(), role);
			}
		}
		List<AuthInfo> list = new ArrayList<AuthInfo>();
		AuthInfo info = null;
		for(RoleInfo role : allCompanyRoles){
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
}
