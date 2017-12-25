package com.hefei.cas.admin.service;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.role.po.Role;
import com.hefei.common.page.Pagination;
import com.hefei.service.framework.exception.ServiceException;

public interface IAdminService {

	public Admin getByUsername(String username) throws ServiceException;
	
	public Admin createAdmin(Admin admin) throws ServiceException;
	
	public Admin getById(Long id) throws ServiceException;
	
	public Pagination<AdminInfo> find(String username, String mobile, String email, int pageSize, int pageIndex) throws ServiceException;
	
	public List<Role>  getRoleByAdmin(Long adminId) throws ServiceException;
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck) throws ServiceException;
	
	public boolean checkAdminAuthorization(Long adminId, String url) throws ServiceException;
}
