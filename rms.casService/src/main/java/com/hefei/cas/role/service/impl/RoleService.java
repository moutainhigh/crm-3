package com.hefei.cas.role.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.role.vo.RoleInfo;
import com.hefei.cas.common.CasConstants;
import com.hefei.cas.role.dao.IRoleDao;
import com.hefei.cas.role.po.Role;
import com.hefei.cas.role.po.RoleModule;
import com.hefei.cas.role.service.IRoleService;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class RoleService implements IRoleService{
	
	private static Logger log = Logger.getLogger(RoleService.class);
	
	@Autowired
	private IRoleDao roleDao;
	
	public Role saveRole(Role role) throws ServiceException{
		log.info(RequestThreadLocal.getLoggerStr() + " role:" + (role==null?"null":role));
		if(role == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		Role existRole = getByName(role.getSystemId(), role.getCompanyId(), role.getRoleName());
		if(existRole != null)
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
		try {
			Date now = Calendar.getInstance().getTime();
			role.setCreateTime(now);
			role.setUpdateTime(now);
			role.setDelFlag(RoleInfo.DEL_FLAG_NO);
			return roleDao.saveRole(role);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	
	public Role getByName(Long systemId, Long companyId,String roleName) throws ServiceException{
		try {
			return roleDao.getByName(systemId,companyId, roleName);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}


	@Override
	public Role getRoleById(Long id) throws ServiceException {
		try {
			return roleDao.getRoleById(id);
		} catch (DaoException e) {
			throw new ServiceException("saveRole error:"+ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}


	@Override
	public Pagination<RoleInfo> find(Long systemId, Long companyId,String roleName, int pageSize, int pageIndex) throws ServiceException {
		try {
			int totalCount = roleDao.findRolePaginationCount(systemId, companyId, roleName);
			List<Role> list = roleDao.findRolePaginationItems(systemId, companyId, roleName, pageSize, pageIndex);
			List<RoleInfo> destList = null;
			if(list != null && list.size() > 0){
				destList = new ArrayList<RoleInfo>();
				RoleInfo roleInfo = null;
				for(Role role : list){
					roleInfo = new RoleInfo();
					BeanUtils.copyProperties(role, roleInfo);
					destList.add(roleInfo);
				}
			}
			Pagination<RoleInfo> pagination = new Pagination<RoleInfo>();
			pagination.setItems(destList);
			pagination.setPageIndex(pageIndex);
			pagination.setPageSize(pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
			
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public List<Role> getManagerRoleByCompanyId(Long companyId) throws ServiceException{
		try {
			return roleDao.getManagerRoleByCompanyId(companyId);
		} catch (DaoException e) {
			throw new ServiceException("saveRole error:"+ReturnCode.RETURN_CODE_ERROR_DAO);
		}
	}
	
	public void roleAuthModule(Long roleId, Map<Long, String> moduleIdAndCheck)  throws ServiceException{
		try {
			List<Long> removedModules = new ArrayList<Long>();
			List<Long> addModules = new ArrayList<Long>();
			
			String check;
			for(Long moduleId : moduleIdAndCheck.keySet()){
				check = moduleIdAndCheck.get(moduleId);
				if("Y".equalsIgnoreCase(check)){
					addModules.add(moduleId);
				}else{
					removedModules.add(moduleId);
				}
			}
			removeAuth(roleId, removedModules);
			createAuth(roleId, addModules);
			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	private void removeAuth(Long roleId, List<Long> moduleIds) throws ServiceException{
		try {
			if(moduleIds != null && moduleIds.size() > 0)
				roleDao.removeAuth(roleId, moduleIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	private void createAuth(Long roleId, List<Long> moduleIds) throws ServiceException{
		if(roleId == null || moduleIds == null || moduleIds.size() <= 0)
			return;
		try {
			List<RoleModule> list = new ArrayList<RoleModule>();
			Date now = Calendar.getInstance().getTime();
			RoleModule roleModule;
			for(Long moduleId : moduleIds){
				roleModule = new RoleModule();
				roleModule.setModuleId(moduleId);
				roleModule.setRoleId(roleId);
				roleModule.setDelFlag(RoleModule.DEL_FLAG_NO);
				roleModule.setCreateTime(now);
				roleModule.setUpdateTime(now);
				
				list.add(roleModule);
			}
			roleDao.saveAuth(list);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public Role copyRole(Long roleId,Long companyId) throws ServiceException {
		try{
			Role defaultModeRole = getRoleById(roleId);
			Role newRole = new Role();
			if(defaultModeRole.getId().equals(CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLEID)){
				newRole.setRoleName(CasConstants.DEFAULT_RMS_SUPER_MANAGER_ROLENAME);
			}else if(defaultModeRole.getId().equals(CasConstants.DEFAULT_RMS_USER_ROLEID)){
				newRole.setRoleName(CasConstants.DEFAULT_RMS_USER_ROLENAME);
			}else if(defaultModeRole.getId().equals(CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLEID)){
				newRole.setRoleName(CasConstants.DEFAULT_FMS_SUPER_MANAGER_ROLENAME);
			}else {
				newRole.setRoleName(defaultModeRole.getRoleName());
			}
			
			newRole.setSystemId(defaultModeRole.getSystemId());
			newRole.setCompanyId(companyId);
			newRole = saveRole(newRole);
			
			List<RoleModule> list = roleDao.findRoleModuleByRoleId(roleId);
			if(list != null && list.size() >0){
				List<Long> moduleIds = new ArrayList<Long>();
				for(RoleModule rm:list){
					moduleIds.add(rm.getModuleId());
				}
				createAuth(newRole.getId(), moduleIds);
			}
			return newRole;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			log.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}

