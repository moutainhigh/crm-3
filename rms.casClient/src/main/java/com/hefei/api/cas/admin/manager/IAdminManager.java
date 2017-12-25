package com.hefei.api.cas.admin.manager;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public interface IAdminManager {
	public Pagination<AdminInfo>  find(String username, String mobile, String email, int pageSize, int pageIndex ) throws ClientException;
	
	public AdminInfo create(AdminInfo adminInfo) throws ClientException;
	public AdminInfo getById(Long id) throws ClientException;
	
	public List<RoleInfo> getRoleByAdmin(Long adminId) throws ClientException;
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck) throws ClientException;
}
