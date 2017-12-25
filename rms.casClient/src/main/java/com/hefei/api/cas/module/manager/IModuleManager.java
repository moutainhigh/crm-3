package com.hefei.api.cas.module.manager;

import java.util.List;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.ClientException;

public interface IModuleManager {
	public List<ModuleInfo> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws ClientException;
	
	public ModuleInfo getModuleById(Long moduleId) throws ClientException;
	
	public List<ModuleInfo> getModuleByRole(List<Long> roleIds) throws ClientException;
	
	public List<ModuleInfo> getModuleByParentId(Long parentModuleId) throws ClientException;
	
	public ModuleInfo createModule(ModuleInfo module) throws ClientException;
	
	public void deleteModule(List<Long> moduleIds) throws ClientException;
}
