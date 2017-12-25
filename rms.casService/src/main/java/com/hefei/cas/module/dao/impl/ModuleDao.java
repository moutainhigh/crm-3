package com.hefei.cas.module.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.cas.module.dao.IModuleDao;
import com.hefei.cas.module.mapper.ModuleMapper;
import com.hefei.cas.module.po.Module;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class ModuleDao implements IModuleDao{
	
	private static final Logger logger = Logger.getLogger(ModuleDao.class);
	
	@Autowired
	private ModuleMapper moduleMapper;

	private IdManager idManager = new IdManagerImpl();
	
	@Override
	public List<Module> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(systemIds == null || systemIds.size() <=0 )
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("systemIds", systemIds);
			if(!withButton){
				map.put("type", ModuleInfo.TYPE_MENU);
			}
			
			List<Module> modules = moduleMapper.getModuleBySystemId(map);
			
			Warning.warnFuntionTimer("ModuleDao", "getModuleBySystemId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return modules;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Module getModuleById(Long moduleId) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(moduleId == null)
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			
			Module module = moduleMapper.getModuleById(moduleId);
			
			Warning.warnFuntionTimer("ModuleDao", "getModuleById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return module;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<Module> getModuleByRole(List<Long> roleIds) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(roleIds == null || roleIds.size() <=0 )
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("roleIds", roleIds);
			
			List<Module> modules = moduleMapper.getModuleByRole(map);
			
			Warning.warnFuntionTimer("ModuleDao", "getModuleByRole", null, (System.currentTimeMillis() - begintimer), begintimer);
			return modules;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<Module> getModuleByParentId(Long parentModuleId)
			throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(parentModuleId == null)
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			List<Module> modules = moduleMapper.getModuleByParentId(parentModuleId);
			Warning.warnFuntionTimer("ModuleDao", "getModuleByParentId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return modules;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Module createModule(Module module) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			module.setId(idManager.getNextId());
			moduleMapper.saveModule(module);
			Warning.warnFuntionTimer("ModuleDao", "createModule", null, (System.currentTimeMillis() - begintimer), begintimer);
			return module;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public void deleteModule(List<Long> moduleIds) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(moduleIds == null || moduleIds.size() <=0 )
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("moduleIds", moduleIds);
			map.put("delFlag", ModuleInfo.DEL_FLAG_YES);
			
			moduleMapper.deleteModule(map);
			
			Warning.warnFuntionTimer("ModuleDao", "deleteModule", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
}
