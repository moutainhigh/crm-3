package com.hefei.api.cas.user.auth.manager;

import java.util.List;
import java.util.Map;

import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.api.cas.user.menu.SysMenu;
import com.hefei.common.exception.ClientException;

public interface IUserAuthorizationManager {

//	public void authDefault(Long userId, Long companyId, Long roleId) throws ClientException;
	
	public void createCompanySuperManager(Long userId,Long companyId) throws ClientException;
	public void createCompanyCommonRole(Long userId,Long companyId) throws ClientException;
	
	public boolean hasAuthorization(Long userId, Long companyId, String url) throws ClientException;
	
	public List<SysMenu> getMenus(Long userId, Long companyId) throws ClientException;
	
	public List<RoleInfo> getRoleByUserId(Long userId) throws ClientException;
	
	public void userAuthRole(Long userId, Map<Long, String> roleIdAndCheck) throws ClientException;
}
