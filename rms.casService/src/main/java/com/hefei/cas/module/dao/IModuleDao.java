package com.hefei.cas.module.dao;

import java.util.List;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.exception.DaoException;

public interface IModuleDao {
	
	public List<Module> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws DaoException;
	
	public Module getModuleById(Long moduleId) throws DaoException;
	
	public List<Module> getModuleByRole(List<Long> roleIds) throws DaoException;
	
	public List<Module> getModuleByParentId(Long parentModuleId) throws DaoException;
	
	public Module createModule(Module module) throws DaoException;
	
	public void deleteModule(List<Long> moduleIds) throws DaoException;
}
