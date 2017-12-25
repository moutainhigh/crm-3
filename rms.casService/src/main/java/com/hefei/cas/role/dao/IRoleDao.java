package com.hefei.cas.role.dao;

import java.util.List;

import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.po.RoleModule;
import com.hefei.service.framework.exception.DaoException;


public interface IRoleDao {
	
	public Role saveRole(Role role) throws DaoException;
	
	public List<Role> getRoleBySystemId(List<Long> systemId) throws DaoException;
	
	public Role getRoleById(Long id) throws DaoException;
	
	public Role getByName(Long systemId, Long companyId, String roleName) throws DaoException;
	
	public List<Role> getManagerRoleByCompanyId(Long companyId) throws DaoException;
	
	public int findRolePaginationCount(Long systemId, Long companyId,String roleName) throws DaoException;
	
	public List<Role> findRolePaginationItems(Long systemId, Long companyId,String roleName, int pageSize, int pageIndex) throws DaoException;
	
	public void removeAuth(Long roleId, List<Long> moduleIds) throws DaoException;
	public void saveAuth(List<RoleModule> roleModules) throws DaoException;

	public List<RoleModule> findRoleModuleByRoleId(Long roleId) throws DaoException;
}
