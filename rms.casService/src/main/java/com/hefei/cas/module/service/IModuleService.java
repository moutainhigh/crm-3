package com.hefei.cas.module.service;

import java.util.List;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.exception.ServiceException;

public interface IModuleService {
	
	/**
	 * 
	 * @param systemId
	 * @param withButton true：返回内容包括按钮
	 * @return
	 * @throws ServiceException
	 */
	public List<Module> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws ServiceException;
	
	public Module getModuleById(Long moduleId) throws ServiceException;
	
	public List<Module> getModuleByRole(List<Long> roleIds) throws ServiceException;
	
	public List<Module> getModuleByParentId(Long parentModuleId) throws ServiceException;
	
	public Module createModule(Module module) throws ServiceException;
	
	public void deleteModule(List<Long> moduleIds) throws ServiceException;
}
