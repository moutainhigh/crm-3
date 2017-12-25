package com.hefei.cas.role.service;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.role.po.Role;
import com.hefei.common.page.Pagination;
import com.hefei.service.framework.exception.ServiceException;

public interface IRoleService {

	public Role saveRole(Role role) throws ServiceException;
	
	public Role getRoleById(Long id) throws ServiceException;
	
	public Role getByName(Long systemId, Long companyId,String roleName) throws ServiceException;
	
	public Pagination<RoleInfo> find(Long systemId, Long companyId,String roleName, int pageSize, int pageIndex) throws ServiceException;
	
	public List<Role> getManagerRoleByCompanyId(Long companyId) throws ServiceException;
	
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck)  throws ServiceException;
	
	public Role copyRole(Long roleId,Long companyId) throws ServiceException;
}
