package com.hefei.cas.user.auth.dao;

import java.util.List;

import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.user.auth.po.UserRole;
import com.hefei.service.framework.exception.DaoException;

public interface IUserAuthDao {

	public void removeAuth(Long userId, List<Long> roleIds) throws DaoException;
	public void saveAuth(List<UserRole> userRoles) throws DaoException;
	public boolean checkAuth(Long userId, Long companyId, String url)  throws DaoException;
	public List<Module> getModuleMenus(Long userId, Long companyId) throws DaoException;
	
	public List<Role>  getRoleByUserId(Long userId) throws DaoException;
}
