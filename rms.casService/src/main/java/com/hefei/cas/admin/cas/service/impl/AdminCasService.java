package com.hefei.cas.admin.cas.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.cas.admin.cas.dao.IAdminCasDao;
import com.hefei.cas.admin.cas.service.IAdminCasService;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.role.po.Role;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class AdminCasService implements IAdminCasService{
	private static final Logger logger = Logger.getLogger(AdminCasService.class);
	
	@Autowired
	private IAdminCasDao adminCasDao;
	
	public List<Role> getRoles(Long adminId) throws ServiceException{
		return null;
	}
	
	public List<Module> getModuleMenus(Long adminId, Long systemId) throws ServiceException{
		try {
			return adminCasDao.getModuleMenus(adminId,systemId);
		}catch(DaoException e){
			throw new ServiceException(e.getErrorCode());
		}catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
