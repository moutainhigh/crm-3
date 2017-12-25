package com.hefei.cas.role.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.role.dao.IRoleDao;
import com.hefei.cas.role.mapper.RoleMapper;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.po.RoleModule;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;
import com.hefei.user.api.info.vo.UserInfo;

@Repository
public class RoleDao implements IRoleDao{
	
	private static final Logger logger = Logger.getLogger(RoleDao.class);
	
	@Autowired
	private RoleMapper mapper;
	private IdManager idManager = new IdManagerImpl();
	
	public Role saveRole(Role role) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			role.setId(idManager.getNextId());
			mapper.saveRole(role);
			Warning.warnFuntionTimer("RoleDaoImpl", "saveRole", null, (System.currentTimeMillis() - begintimer), begintimer);
			return role;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public List<Role> getRoleBySystemId(List<Long> systemIds) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(systemIds == null || systemIds.size() <=0 )
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("systemIds", systemIds);
			map.put("delFlag", RoleInfo.DEL_FLAG_NO);
			List<Role> roles= mapper.getRoleBySystemId(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "getRoleBySystemId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return roles;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public Role getRoleById(Long id) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Role role= mapper.getRoleById(id);
			Warning.warnFuntionTimer("RoleDaoImpl", "getRoleById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return role;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public List<Role> getManagerRoleByCompanyId(Long companyId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("type", UserInfo.TYPE_MANAGER);
			map.put("companyId", companyId);
			List<Role> role= mapper.getManagerRoleByCompanyId(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "getManagerRoleByCompanyId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return role;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public Role getByName(Long systemId, Long companyId, String roleName) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("systemId", systemId);
			map.put("companyId", companyId);
			map.put("roleName", roleName);
			Role role= mapper.getByName(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "getByName", null, (System.currentTimeMillis() - begintimer), begintimer);
			return role;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public int findRolePaginationCount(Long systemId, Long companyId, String roleName) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("systemId", systemId);
			map.put("roleName", roleName);
			map.put("companyId", companyId);
			map.put("delFlag", RoleInfo.DEL_FLAG_NO);
			int count = mapper.findRolePaginationCount(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "getTotalCount", null, (System.currentTimeMillis() - begintimer), begintimer);
			return count;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<Role> findRolePaginationItems(Long systemId, Long companyId, String roleName, int pageSize, int pageIndex) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			int startSize = (pageIndex -1) * pageSize;
			Map map = new HashMap();
			map.put("systemId", systemId);
			map.put("companyId", companyId);
			map.put("roleName", roleName);
			map.put("delFlag", RoleInfo.DEL_FLAG_NO);
			map.put("startSize", startSize);
			map.put("pageSize", pageSize);
			
			List<Role> list = mapper.findRolePaginationItems(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "findRole", null, (System.currentTimeMillis() - begintimer), begintimer);
			return list;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public void removeAuth(Long roleId, List<Long> moduleIds) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("roleId", roleId);
			map.put("moduleIds", moduleIds);
			mapper.removeAuth(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "removeAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void saveAuth(List<RoleModule> roleModules) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			for(RoleModule roleModule : roleModules){
				roleModule.setId(idManager.getNextId());
			}
			mapper.saveAuth(roleModules);
			Warning.warnFuntionTimer("RoleDaoImpl", "saveAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public List<RoleModule> findRoleModuleByRoleId(Long roleId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("roleId", roleId);
			map.put("delFlag", RoleModule.DEL_FLAG_NO);
			List<RoleModule> rms=mapper.findRoleModuleByRoleId(map);
			Warning.warnFuntionTimer("RoleDaoImpl", "findRoleModuleByRoleId", null, (System.currentTimeMillis() - begintimer), begintimer);
			return rms;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
