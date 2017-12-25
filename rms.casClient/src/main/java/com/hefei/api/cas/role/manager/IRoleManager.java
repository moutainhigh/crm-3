package com.hefei.api.cas.role.manager;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public interface IRoleManager {

	public Pagination<RoleInfo>  find(Long systemId, Long companyId,String roleName, int pageSize, int pageIndex ) throws ClientException;
	
	public RoleInfo create(RoleInfo roleInfo) throws ClientException;
	public RoleInfo getById(Long id) throws ClientException;
	
	/**
	 * 通过公司ID 查找当前公司的超级管理员角色ID
	 * @param companyId
	 */
	public List<RoleInfo> getManagerRoleByCompanyId(Long companyId) throws ClientException;
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck) throws ClientException;
}
