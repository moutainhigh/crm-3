package com.hefei.cas.module.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.base.mapper.Mapper;

public interface ModuleMapper extends Mapper{
	
	public List<Module> getModuleBySystemId(Map map);
	
	public Module getModuleById(Long id);
	
	public List<Module> getModuleByRole(Map map);
	
	public List<Module> getModuleByParentId(Long parentId);
	
	public void saveModule(Module module);
	
	public void deleteModule(Map map);
	
}