package com.hefei.cas.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.cas.admin.dao.IAdminDao;
import com.hefei.cas.admin.mapper.AdminMapper;
import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.po.AdminRole;
import com.hefei.cas.role.po.Role;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.monitor.Warning;

@Repository
public class AdminDao implements IAdminDao{

	private static final Logger logger = Logger.getLogger(AdminDao.class);
	private IdManager idManager = new IdManagerImpl();
	@Autowired
	AdminMapper adminMapper;
	
	public Admin getByUsername(String username) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			Admin admin = adminMapper.getByUsername(username);
			
			Warning.warnFuntionTimer("AdminDao", "getByUsername", null, (System.currentTimeMillis() - begintimer), begintimer);
			return admin;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public Admin saveAdmin(Admin admin) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			admin.setId(idManager.getNextId());
			adminMapper.saveAdmin(admin);
			Warning.warnFuntionTimer("AdminDao", "saveAdmin", null, (System.currentTimeMillis() - begintimer), begintimer);
			return admin;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}

	@Override
	public Admin getById(Long id) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Admin admin = adminMapper.getById(id);
			
			Warning.warnFuntionTimer("AdminDao", "getById", null, (System.currentTimeMillis() - begintimer), begintimer);
			return admin;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public int getTotalCount(String username, String mobile, String email) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("username", username);
			map.put("mobile", mobile);
			map.put("email", email);
			int count = adminMapper.getTotalCount(map);
			Warning.warnFuntionTimer("AdminDao", "getTotalCount", null, (System.currentTimeMillis() - begintimer), begintimer);
			return count;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public List<Admin> findAdmin(String username, String mobile, String email, int pageIndex, int pageSize) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			int startSize = (pageIndex -1) * pageSize;
			Map map = new HashMap();
			map.put("username", username);
			map.put("mobile", mobile);
			map.put("email", email);
			map.put("startSize", startSize);
			map.put("pageSize", pageSize);
			
			List<Admin> list = adminMapper.findAdmin(map);
			
			Warning.warnFuntionTimer("AdminDao", "findAdmin", null, (System.currentTimeMillis() - begintimer), begintimer);
			return list;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public List<Role>  getRoleByAdmin(Long adminId) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			List<Role> list = adminMapper.getRoleByAdmin(adminId);
			
			Warning.warnFuntionTimer("AdminDao", "getRoleByAdmin", null, (System.currentTimeMillis() - begintimer), begintimer);
			return list;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public void removeAuth(Long adminId, List<Long> roleIds)
			throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			Map map = new HashMap();
			map.put("adminId", adminId);
			map.put("roleIds", roleIds);
			adminMapper.removeAuth(map);
			Warning.warnFuntionTimer("AdminDao", "removeAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}

	@Override
	public void saveAuth(List<AdminRole> roleAdmins) throws DaoException {
		long begintimer = System.currentTimeMillis();
		try{
			for(AdminRole adminRole : roleAdmins){
				adminRole.setId(idManager.getNextId());
			}
			adminMapper.saveAuth(roleAdmins);
			Warning.warnFuntionTimer("AdminDao", "saveAuth", null, (System.currentTimeMillis() - begintimer), begintimer);
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
		
	}
	
	public boolean checkAdminAuthorization(Long adminId, String url) throws DaoException{
		long begintimer = System.currentTimeMillis();
		try{
			if(adminId == null || StringUtils.isEmpty(url))
				throw new DaoException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Map map = new HashMap();
			map.put("adminId", adminId);
			map.put("url", url);
			Long adminRoleId = adminMapper.haveAuthorization(map);
			Warning.warnFuntionTimer("AdminDao", "checkAdminAuthorization", null, (System.currentTimeMillis() - begintimer), begintimer);
			if(adminRoleId == null)
				return false;
			else
				return true;
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new DaoException(ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
}
