package com.hefei.cas.user.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.user.auth.dao.IUserAuthDao;
import com.hefei.cas.user.auth.mapper.UserAuthMapper;
import com.hefei.cas.user.auth.po.UserRole;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class UserAuthDao implements IUserAuthDao{

	private static final Logger logger = Logger.getLogger(UserAuthDao.class);
	
	@Autowired
	private UserAuthMapper userAuthMapper;
	private IdManager idManager = new IdManagerImpl();
	
	public void removeAuth(Long userId, List<Long> roleIds) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("roleIds", roleIds);
			userAuthMapper.removeAuth(map);
			Warning.warnFuntionTimer("UserAuthDao", "removeAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	public void saveAuth(List<UserRole> userRoles) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			for(UserRole userRole : userRoles){
				userRole.setId(idManager.getNextId());
			}
			userAuthMapper.saveAuth(userRoles);
			Warning.warnFuntionTimer("UserAuthDao", "saveAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public List<Role>  getRoleByUserId(Long userId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("roleDelFlag", RoleInfo.DEL_FLAG_NO);
			map.put("userRoleDelFlag", UserRole.DEL_FLAG_NO);
			List<Role> roles = userAuthMapper.getRoleByUserId(map);
			Warning.warnFuntionTimer("UserAuthDao", "getRoleByUserId", null, (System.currentTimeMillis() - begintimer), begintimer);
			Warning.warnCollectionSize(roles, "UserAuthDao", "getRoleByUserId", null,  begintimer);
			return roles;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	@Override
	public List<Module> getModuleMenus(Long userId, Long companyId) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("moduleType", ModuleInfo.TYPE_MENU);
			map.put("companyId", companyId);
			List<Module> modules = userAuthMapper.getModuleMenus(map);
			Warning.warnFuntionTimer("UserAuthDao", "getModuleMenus", null, (System.currentTimeMillis() - begintimer), begintimer);
			Warning.warnCollectionSize(modules, "UserAuthDao", "getModuleMenus", null,  begintimer);
			return modules;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	@Override
	public boolean checkAuth(Long userId, Long companyId, String url)
			throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			if(userId == null || companyId == null ||StringUtils.isEmpty(url))
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("companyId", companyId);
			map.put("url", url);
			Long userRoleId = userAuthMapper.haveAuthorization(map);
			Warning.warnFuntionTimer("UserAuthDao", "checkAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
			if(userRoleId == null)
				return false;
			else
				return true;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
