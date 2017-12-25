package com.hefei.cas.admin.dao;

import java.util.List;

import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.po.AdminRole;
import com.hefei.cas.role.po.Role;
import com.hefei.service.framework.exception.DaoException;

public interface IAdminDao {

	public Admin getByUsername(String username) throws DaoException;
	
	public Admin saveAdmin(Admin admin) throws DaoException;
	
	public Admin getById(Long id) throws DaoException;
	
	public int getTotalCount(String username, String mobile, String email) throws DaoException;
	
	public List<Admin> findAdmin(String username, String mobile, String email, int pageIndex, int pageSize) throws DaoException;
	
	public List<Role>  getRoleByAdmin(Long adminId) throws DaoException;
	
	public void removeAuth(Long adminId, List<Long> roleIds) throws DaoException;
	public void saveAuth(List<AdminRole> roleAdmins) throws DaoException;
	
	public boolean checkAdminAuthorization(Long adminId, String url) throws DaoException;
}
