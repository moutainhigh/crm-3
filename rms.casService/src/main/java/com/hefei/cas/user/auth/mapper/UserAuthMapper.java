package com.hefei.cas.user.auth.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.user.auth.po.UserRole;
import com.hefei.service.framework.base.mapper.Mapper;

public interface UserAuthMapper  extends Mapper{

	public void removeAuth(Map map);
	
	public void saveAuth(List<UserRole> userRoles);
	
	public List<Module> getModuleMenus(Map map);
	
	public Long haveAuthorization(Map map);
	
	public List<Role>  getRoleByUserId(Map map);
}
