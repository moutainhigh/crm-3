package com.hefei.admin.cas.administrator.service;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface IAdminService {
	public Pagination<AdminInfo>  find(String username, String mobile, String email, int pageSize, int pageIndex ) throws BusinessException;
	
	public AdminInfo create(AdminInfo adminInfo) throws BusinessException;
	public AdminInfo getById(Long id) throws BusinessException;
	
	public List<RoleInfo>  getRoleByAdmin(Long adminId) throws BusinessException;
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck)  throws BusinessException;
}
