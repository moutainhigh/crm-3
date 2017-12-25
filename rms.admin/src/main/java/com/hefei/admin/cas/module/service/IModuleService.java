package com.hefei.admin.cas.module.service;

import java.util.List;

import com.hefei.admin.cas.module.vo.TreeNode;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.BusinessException;

public interface IModuleService {
	
	public List<TreeNode> treeNode() throws BusinessException;
	
	public ModuleInfo getById(Long moduleId) throws BusinessException;
	
	public void createModule(ModuleInfo module) throws BusinessException;
	
	public List<ModuleInfo> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws BusinessException;
	public List<ModuleInfo> getModuleByRole(List<Long> roleIds) throws BusinessException;
}
