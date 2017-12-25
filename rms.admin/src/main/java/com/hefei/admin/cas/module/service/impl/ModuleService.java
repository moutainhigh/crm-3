package com.hefei.admin.cas.module.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.admin.cas.module.service.IModuleService;
import com.hefei.admin.cas.module.vo.TreeNode;
import com.hefei.admin.cas.system.service.ISystemService;
import com.hefei.api.cas.module.manager.IModuleManager;
import com.hefei.api.cas.module.manager.impl.ModuleManager;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class ModuleService implements IModuleService{

	private Logger logger = Logger.getLogger(ModuleService.class);
	
	@Autowired
	private ISystemService systemService;
	
	private IModuleManager moduleManager = new ModuleManager();
	
//	public List<TreeNode> treeNode(Long systemId) throws BusinessException{
//		try {
//			List<Long> systemIds = new ArrayList<Long>();
//			systemIds.add(systemId);
//			
//			List<ModuleInfo> modules = moduleManager.getModuleBySystemId(systemIds, true);
//			if(modules == null || modules.size() <= 0){
//				return null;
//			}
//			List<TreeNode> trees = new ArrayList<TreeNode>();
//			TreeNode tree = null;
//			for(ModuleInfo module : modules){
//				tree = new TreeNode();
//				tree.setSystemId(module.getSystemId());
//				tree.setId(module.getSystemId() + "_" + module.getId());
//				tree.setName(module.getName());
//				tree.setNodeType(module.getType());
//				tree.setOpen(false);
//				if(module.getParentId() == null){
//					tree.setpId(module.getSystemId().toString());
//				}else{
//					tree.setpId(module.getSystemId() + "_" + module.getParentId());
//				}
//				
//				trees.add(tree);
//			}
//			return trees;
//		} catch (ClientException e) {
//			throw new BusinessException(e.getErrorCode());
//		} catch (Exception e) {
//			logger.error(RequestThreadLocal.getTimer() + "error", e);
//			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
//		}
//	}
	@Override
	public List<TreeNode> treeNode() throws BusinessException {
		
		try {
			Pagination<SystemInfo> pagination = systemService.find(null, 100, 1);
			if(pagination == null || pagination.getItems() == null || pagination.getItems().size() <=0){
				return null;
			}
			List<TreeNode> trees = new ArrayList<TreeNode>();
			TreeNode tree = null;
			
			List<SystemInfo> systems = pagination.getItems();
			List<Long> systemIds = new ArrayList<Long>();
//			Map<Long, SystemInfo> sysMap = new HashMap<Long, SystemInfo>();
			for(SystemInfo sys : systems){
				systemIds.add(sys.getId());
//				sysMap.put(sys.getId(), sys);
				
				tree = new TreeNode();
				tree.setSystemId(sys.getId());
				tree.setId(sys.getId().toString());
				tree.setName(sys.getSystemName());
				tree.setNodeType(TreeNode.NODE_TYPE_SYSTEM);
				tree.setOpen(false);
				tree.setpId(null);
				trees.add(tree);
			}
			List<ModuleInfo> modules = moduleManager.getModuleBySystemId(systemIds, true);
			if(modules == null || modules.size() <= 0){
				return null;
			}
			
			for(ModuleInfo module : modules){
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
				
				
				trees.add(tree);
			}
			return trees;
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		} catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public ModuleInfo getById(Long moduleId) throws BusinessException {
		try {
			return moduleManager.getModuleById(moduleId);
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public void createModule(ModuleInfo module) throws BusinessException {
		try {
			moduleManager.createModule(module);
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		}
	}
	public List<ModuleInfo> getModuleByRole(List<Long> roleIds) throws BusinessException {
		try {
			return moduleManager.getModuleByRole(roleIds);
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		}
	}
	@Override
	public List<ModuleInfo> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws BusinessException {
		try {
			List<ModuleInfo> modules = moduleManager.getModuleBySystemId(systemIds, true);
			return modules;
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		}
	}
}
