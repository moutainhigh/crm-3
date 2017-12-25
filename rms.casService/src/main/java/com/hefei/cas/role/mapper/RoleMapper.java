package com.hefei.cas.role.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.po.RoleModule;
import com.hefei.service.framework.base.mapper.Mapper;

public interface RoleMapper extends Mapper{
	
	public void saveRole(Role role);
	public List<Role> getRoleBySystemId(Map map);
	
	public Role getRoleById(Long id);
	
	public List<Role> getManagerRoleByCompanyId(Map map);
	
	public Role getByName(Map map);
	
	public int findRolePaginationCount(Map map);
	
	public List<Role> findRolePaginationItems(Map map);
	
	public void removeAuth(Map map);
	
	public void saveAuth(List<RoleModule> roleModules);
	
	public List<RoleModule> findRoleModuleByRoleId(Map map);
}
