package com.hefei.cas.admin.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.po.AdminRole;
import com.hefei.cas.role.po.Role;
import com.hefei.service.framework.base.mapper.Mapper;

public interface AdminMapper extends Mapper{

	public Admin getByUsername(String username);
	public void saveAdmin(Admin admin);
	
	public Admin getById(Long id);
	public int getTotalCount(Map map);
	
	public List<Admin> findAdmin(Map map);
	
	public List<Role> getRoleByAdmin(Long adminId);
	
	public void removeAuth(Map map);
	
	public void saveAuth(List<AdminRole> adminRoles);
	
	public Long haveAuthorization(Map map);
}
