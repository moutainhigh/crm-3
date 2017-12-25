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

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.cas.service.ICasRoleService;
import com.hefei.rms.cas.vo.ModuleTreeNode;
import com.hefei.rms.company.service.ICompanyService;

@Controller
@RequestMapping(value="/cas/role",produces="text/plain;charset=UTF-8")
public class RoleAuthModuleController {

	private Logger logger = Logger.getLogger(RoleAuthModuleController.class);
	
	@Autowired
	private ICasRoleService casRoleService;
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("toAuthModule")
	public String toAuthRole(HttpServletRequest request, HttpServletResponse response, Long roleId){
		try {
			RoleInfo role = casRoleService.getById(roleId);
			request.setAttribute("role",role);
			
			/**
			 * 查找当前公司超级管理员所拥有的所有模块
			 */
			List<RoleInfo> managerRoleInfos = casRoleService.getManagerRoleByCompanyId(CommonParameterThreadLocal.getCurrentCompanyId());
			List<ModuleInfo> allCompanyManagerModule = casRoleService.getModuleByRole(managerRoleInfos);
			
			List<ModuleInfo> existRoleModule = casRoleService.getModuleByRole(roleId);
			
			String zTreeNodes = JacksonUtil.beanToJson(treeNode(allCompanyManagerModule, existRoleModule));
			request.setAttribute("zTreeNodes", zTreeNodes);
			
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/role/role_auth_module";
	}
	
	
	@RequestMapping(value="/authModule",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String authRole(HttpServletRequest request, HttpServletResponse response, Long roleId, String idAndCheck){
		logger.info(RequestThreadLocal.getTimer() + " roleId=" + roleId + " idAndCheck=" + idAndCheck);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<Long, String> idCheckMap= parserModuleCheck(idAndCheck);
			 
			 casRoleService.roleAuthModule(roleId, idCheckMap);
			 
			 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	/**
	 * 200_2000-Y|200_2001-N|
	 * 
	 * ##
	 * @param idAndCheck
	 * @return
	 */
	private static Map<Long, String> parserModuleCheck(String idAndCheck){
		if(StringUtils.isEmpty(idAndCheck))
			return null;
		String[] idAndChecks = idAndCheck.split("\\|");
		Long id;
		String check;
		Map<Long, String> map = new HashMap<Long, String>();
		for(String idAndCheckInfo : idAndChecks){
			if(StringUtils.isNotBlank(idAndCheckInfo)){//200_2000-Y
				String[] info = idAndCheckInfo.split("-");
				id =Long.valueOf(info[0].split("_")[1]);
				
				check = info[1];
				map.put(id, check);
			}
		}
		return map;
	}
	private List<ModuleTreeNode> treeNode(List<ModuleInfo> allSystemModule, List<ModuleInfo> existRoleModule){
		Map<Long, ModuleInfo> existsRoleModuleMap = new HashMap<Long, ModuleInfo>();
		
		if(existRoleModule != null && existRoleModule.size() > 0){
			for(ModuleInfo moduleInfo : existRoleModule){
				existsRoleModuleMap.put(moduleInfo.getId(), moduleInfo);
			}
		}
		
		List<ModuleTreeNode> trees = new ArrayList<ModuleTreeNode>();
		ModuleTreeNode tree = null;
		for(ModuleInfo module : allSystemModule){
			tree = new ModuleTreeNode();
			tree.setSystemId(module.getSystemId());
			tree.setId(module.getSystemId() + "_" + module.getId());
			tree.setName(module.getName());
			tree.setNodeType(module.getType());
			tree.setOpen(false);
			tree.setIcon(module.getIconPath());
			if(module.getParentId() == null){
				tree.setpId(module.getSystemId().toString());
			}else{
				tree.setpId(module.getSystemId() + "_" + module.getParentId());
			}
			
			tree.setNocheck(false);
			if(existsRoleModuleMap.containsKey(module.getId())){
				tree.setChecked(true);
			}else{
				tree.setChecked(false);
			}
			trees.add(tree);
		}
		return trees;
	}
}
