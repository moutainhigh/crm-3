package com.hefei.cas.user.auth.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.cas.common.CasConstants;
import com.hefei.cas.config.service.IConfigService;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.service.IRoleService;
import com.hefei.cas.user.auth.dao.IUserAuthDao;
import com.hefei.cas.user.auth.po.UserRole;
import com.hefei.cas.user.auth.service.IUserAuthService;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class UserAuthService implements IUserAuthService{
	
	private static final Logger logger = Logger.getLogger(UserAuthService.class);
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserAuthDao userAuthDao;
	
	@Autowired
	private IConfigService configService;
	
	public void createCompanySuperManager(Long userId,Long companyId) throws ServiceException {
		createCompanyRMSSuperManager(userId, companyId);
		createCompanyFMSSuperManager(userId, companyId);
	}
	
	private void createCompanyRMSSuperManager(Long userId,Long companyId) throws ServiceException {
		try {
			Role role = copyRole(companyId, CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID);
			List<Long> roleIds = new ArrayList<Long>();
			roleIds.add(role.getId());
			createAuth(userId, roleIds);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	private void createCompanyFMSSuperManager(Long userId,Long companyId) throws ServiceException {
		try {
			Role role = copyRole(companyId, CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID);
			List<Long> roleIds = new ArrayList<Long>();
			roleIds.add(role.getId());
			createAuth(userId, roleIds);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public void createCompanyCommonRole(Long userId,Long companyId) throws ServiceException {
		try {
			Role role = roleService.getByName(configService.getSystemIdRMS(), companyId, CasConstants.DEFAULT_RMS_USER_ROLENAME);
			if(role == null){
				role = copyRole(companyId, CasConstants.DEFAULT_RMS_USER_ROLEID);
			}
			List<Long> roleIds = new ArrayList<Long>();
			roleIds.add(role.getId());
			createAuth(userId, roleIds);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
//	@Override
//	public void authDefault(Long userId,Long companyId,Long roleId) throws ServiceException {
//		try {
//			Role role = copyRole(companyId, roleId);
//			List<Long> roleIds = new ArrayList<Long>();
//			roleIds.add(role.getId());
//			createAuth(userId, roleIds);
//		} catch (ServiceException e) {
//			throw e;
//		} catch (Exception e) {
//			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
//			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
//		}
//		
//	}
	
	private Role copyRole(Long companyId,Long roleId) throws ServiceException{
		Role role = roleService.copyRole(roleId, companyId);
		return role;
	}
	
	
	public List<Role>  getRoleByUserId(Long userId) throws ServiceException{
		try {
			return userAuthDao.getRoleByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public void authUserRole(Long userId, Map<Long, String> roleIdAndCheck) throws ServiceException{
		try {
			List<Long> removedRoles = new ArrayList<Long>();
			List<Long> addRoles = new ArrayList<Long>();
			
			String check;
			for(Long moduleId : roleIdAndCheck.keySet()){
				check = roleIdAndCheck.get(moduleId);
				if("Y".equalsIgnoreCase(check)){
					addRoles.add(moduleId);
				}else{
					removedRoles.add(moduleId);
				}
			}
			removeAuth(userId, removedRoles);
			createAuth(userId, addRoles);
			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
//	private void removeAuth(Long adminId, List<Long> roleIds) throws ServiceException{
//		try {
//			if(roleIds != null && roleIds.size() > 0)
//				adminDao.removeAuth(adminId, roleIds);
//		} catch (DaoException e) {
//			throw new ServiceException(e.getErrorCode());
//		} catch (Exception e) {
//			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
//			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
//		}
//	}
//	private void createAuth(Long adminId, List<Long> roleIds) throws ServiceException{
//		if(adminId == null || roleIds == null || roleIds.size() <= 0)
//			return;
//		try {
//			List<AdminRole> list = new ArrayList<AdminRole>();
//			Date now = Calendar.getInstance().getTime();
//			AdminRole adminRole;
//			for(Long roleId : roleIds){
//				adminRole = new AdminRole();
//				adminRole.setAdminId(adminId);
//				adminRole.setRoleId(roleId);
//				adminRole.setDelFlag(AdminRole.DEL_FLAG_NO);
//				adminRole.setCreateTime(now);
//				adminRole.setUpdateTime(now);
//				
//				list.add(adminRole);
//			}
//			adminDao.saveAuth(list);
//		} catch (DaoException e) {
//			throw new ServiceException(e.getErrorCode());
//		} catch (Exception e) {
//			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
//			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
//		}
//		
//	}
//	
	private void removeAuth(Long userId, List<Long> roleIds) throws ServiceException{
		try {
			if(roleIds != null && roleIds.size() > 0)
				userAuthDao.removeAuth(userId, roleIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	private void createAuth(Long userId, List<Long> roleIds) throws ServiceException{
		if(userId == null || roleIds == null || roleIds.size() <= 0)
			return;
		try {
			List<UserRole> list = new ArrayList<UserRole>();
			Date now = Calendar.getInstance().getTime();
			UserRole userRole;
			for(Long roleId : roleIds){
				userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRole.setDelFlag(UserRole.DEL_FLAG_NO);
				userRole.setCreateTime(now);
				userRole.setUpdateTime(now);
				
				list.add(userRole);
			}
			userAuthDao.saveAuth(list);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	@Override
	public boolean checkAuth(Long userId, Long companyId, String url) throws ServiceException {
		try {
			return userAuthDao.checkAuth(userId, companyId, url);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public List<Module> getModuleMenus(Long userId, Long companyId) throws ServiceException {
		try {
			return userAuthDao.getModuleMenus(userId,companyId);
		}catch(DaoException e){
			throw new ServiceException(e.getErrorCode());
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

}
