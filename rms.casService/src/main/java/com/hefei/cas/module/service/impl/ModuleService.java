package com.hefei.cas.module.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.cas.module.dao.impl.ModuleDao;
import com.hefei.cas.module.po.Module;
import com.hefei.cas.module.service.IModuleService;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class ModuleService implements IModuleService {
	private static final Logger logger = Logger.getLogger(ModuleService.class);
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public List<Module> getModuleBySystemId(List<Long> systemIds, boolean withButton) throws ServiceException {
		try {
			return moduleDao.getModuleBySystemId(systemIds, withButton);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Module getModuleById(Long moduleId) throws ServiceException {
		try {
			return moduleDao.getModuleById(moduleId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public List<Module> getModuleByRole(List<Long> roleIds)
			throws ServiceException {
		try {
			return moduleDao.getModuleByRole(roleIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public List<Module> getModuleByParentId(Long parentModuleId)
			throws ServiceException {
		try {
			return moduleDao.getModuleByParentId(parentModuleId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Module createModule(Module module) throws ServiceException {
		try {
			Date now = Calendar.getInstance().getTime();
			module.setCreateTime(now);
			module.setUpdateTime(now);
			module.setDelFlag(ModuleInfo.DEL_FLAG_NO);
			return moduleDao.createModule(module);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void deleteModule(List<Long> moduleIds) throws ServiceException {
		try {
			moduleDao.deleteModule(moduleIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	
}
