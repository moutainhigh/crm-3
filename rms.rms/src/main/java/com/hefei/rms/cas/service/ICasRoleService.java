package com.hefei.rms.cas.service;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface ICasRoleService {

	public Pagination<RoleInfo> find(Long systemId, Long companyId, String roleName, int pageSize, int pageIndex) throws BusinessException;

	public RoleInfo create(RoleInfo roleInfo) throws BusinessException;

	public RoleInfo getById(Long id) throws BusinessException;
	
	/**
	 * 通过公司ID 查找当前公司的超级管理员角色ID
	 * @param companyId
	 */
	public List<RoleInfo> getManagerRoleByCompanyId(Long companyId) throws BusinessException;
	public List<ModuleInfo> getModuleByRole(List<RoleInfo> roleInfos) throws BusinessException;
	
	public List<ModuleInfo> getModuleByRole(Long roleId) throws BusinessException;
	
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck) throws BusinessException;
	
	public List<RoleInfo> getRoleByUserId(Long userId) throws BusinessException;
	
	public void userAuthRole(Long userId, Map<Long, String> roleIdAndCheck) throws BusinessException;
}
