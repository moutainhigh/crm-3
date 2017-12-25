package com.hefei.admin.cas.role.controller;

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
import com.hefei.admin.cas.module.service.IModuleService;
import com.hefei.admin.cas.module.vo.TreeNode;
import com.hefei.admin.cas.role.service.IRoleService;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping("/cas/role")
@SuppressWarnings("all")
public class RoleAuthModuleController extends BaseController{
	
	private Logger logger = Logger.getLogger(RoleAuthModuleController.class);
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IModuleService moduleService;
	
	
	@RequestMapping(value="/toAuthModule",produces="text/plain;charset=UTF-8")
	public String index(Long roleId, HttpServletRequest request,HttpServletResponse response) {
		try {
			RoleInfo role = roleService.getById(roleId);
			request.setAttribute("role",role);
			
			List<Long> systemIds = new ArrayList<Long>();
			systemIds.add(role.getSystemId());
			List<ModuleInfo> allSystemModule = moduleService.getModuleBySystemId(systemIds, true);
			
			List<Long> roleIds = new ArrayList<Long>();
			roleIds.add(role.getId());
			List<ModuleInfo> existRoleModule = moduleService.getModuleByRole(roleIds);
			
			String zTreeNodes = JacksonUtil.beanToJson(treeNode(allSystemModule, existRoleModule));
			request.setAttribute("zTreeNodes", zTreeNodes);
			
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "cas/role/role_auth_module";
	}
	
	private List<TreeNode> treeNode(List<ModuleInfo> allSystemModule, List<ModuleInfo> existRoleModule){
		Map<Long, ModuleInfo> existsRoleModuleMap = new HashMap<Long, ModuleInfo>();
		
		if(existRoleModule != null && existRoleModule.size() > 0){
			for(ModuleInfo moduleInfo : existRoleModule){
				existsRoleModuleMap.put(moduleInfo.getId(), moduleInfo);
			}
		}
		
		List<TreeNode> trees = new ArrayList<TreeNode>();
		TreeNode tree = null;
		for(ModuleInfo module : allSystemModule){
			tree = new TreeNode();
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
	
	@RequestMapping(value="/authModule",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String authModule(Long roleId, String idAndCheck,HttpServletRequest request,HttpServletResponse response) {
		
		logger.info(RequestThreadLocal.getTimer() + " roleId=" + roleId + " idAndCheck=" + idAndCheck);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			 Map<Long, String> idCheckMap= parserModuleCheck(idAndCheck);
			 
			 roleService.roleAuthModule(roleId, idCheckMap);
			 
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
}
