package com.hefei.admin.cas.role.service;

import java.util.Map;

import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface IRoleService {

	public Pagination<RoleInfo>  find(Long systemId, String roleName, int pageSize, int pageIndex ) throws BusinessException;
	
	public RoleInfo create(RoleInfo roleInfo) throws BusinessException;
	public RoleInfo getById(Long id) throws BusinessException;
	
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck) throws BusinessException;
}
