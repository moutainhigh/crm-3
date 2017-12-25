package com.hefei.cas.admin.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.admin.vo.AdminInfo;
import com.hefei.cas.admin.dao.IAdminDao;
import com.hefei.cas.admin.po.Admin;
import com.hefei.cas.admin.po.AdminRole;
import com.hefei.cas.admin.service.IAdminService;
import com.hefei.cas.role.po.Role;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.StringUtils;
import com.hefei.common.util.encrypt.SHA256;
import com.hefei.common.util.encrypt.SHA256Version;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class AdminService implements IAdminService{
	private static final Logger logger = Logger.getLogger(AdminService.class);
	
	@Autowired
	private IAdminDao adminDao;
	
	
	public Admin getByUsername(String username) throws ServiceException{
		try {
			return adminDao.getByUsername(username);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}


	@Override
	public Admin createAdmin(Admin admin) throws ServiceException {
		try{
			if(admin == null)
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			if(StringUtils.isBlank(admin.getUsername()))
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			Admin existAdmin = getByUsername(admin.getUsername());
			if(existAdmin != null)
				throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
			
			Date now = Calendar.getInstance().getTime();
			admin.setCreateTime(now);
			admin.setUpdateTime(now);
			admin.setDelFlag(AdminInfo.DEL_FLAG_NORMAL);
			admin.setPassword(SHA256.encrtypt(admin.getPassword(), SHA256Version.V1.getVersion()));
			admin.setPwdVersion(SHA256Version.V1.getVersion());
			if(StringUtils.isBlank(admin.getRealName())){
				admin.setRealName(admin.getUsername());
			}
			admin = adminDao.saveAdmin(admin);
			return admin;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}


	@Override
	public Admin getById(Long id) throws ServiceException {
		try {
			return adminDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}


	@Override
	public Pagination<AdminInfo> find(String username, String mobile,
			String email, int pageSize, int pageIndex) throws ServiceException {
		try {
			int totalCount = adminDao.getTotalCount(username, mobile, email);
			List<Admin> list = adminDao.findAdmin(username, mobile, email, pageIndex, pageSize);
			List<AdminInfo> destList = null;
			if(list != null && list.size() > 0){
				destList = new ArrayList<AdminInfo>();
				AdminInfo adminInfo = null;
				for(Admin admin : list){
					adminInfo = new AdminInfo();
					BeanUtils.copyProperties(admin, adminInfo);
					
					adminInfo.setPassword(null);
					destList.add(adminInfo);
				}
			}
			Pagination<AdminInfo> pagination = new Pagination<AdminInfo>();
			pagination.setItems(destList);
			pagination.setPageIndex(pageIndex);
			pagination.setPageSize(pageSize);
			pagination.setTotalCount(totalCount);
			return pagination;
			
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public List<Role>  getRoleByAdmin(Long adminId) throws ServiceException{
		try {
			return adminDao.getRoleByAdmin(adminId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public void adminAuthRole(Long adminId, Map<Long, String> roleIdAndCheck) throws ServiceException{
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
			removeAuth(adminId, removedRoles);
			createAuth(adminId, addRoles);
			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	private void removeAuth(Long adminId, List<Long> roleIds) throws ServiceException{
		try {
			if(roleIds != null && roleIds.size() > 0)
				adminDao.removeAuth(adminId, roleIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	private void createAuth(Long adminId, List<Long> roleIds) throws ServiceException{
		if(adminId == null || roleIds == null || roleIds.size() <= 0)
			return;
		try {
			List<AdminRole> list = new ArrayList<AdminRole>();
			Date now = Calendar.getInstance().getTime();
			AdminRole adminRole;
			for(Long roleId : roleIds){
				adminRole = new AdminRole();
				adminRole.setAdminId(adminId);
				adminRole.setRoleId(roleId);
				adminRole.setDelFlag(AdminRole.DEL_FLAG_NO);
				adminRole.setCreateTime(now);
				adminRole.setUpdateTime(now);
				
				list.add(adminRole);
			}
			adminDao.saveAuth(list);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	public boolean checkAdminAuthorization(Long adminId, String url) throws ServiceException{
		try {
			return adminDao.checkAdminAuthorization(adminId, url);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
}
