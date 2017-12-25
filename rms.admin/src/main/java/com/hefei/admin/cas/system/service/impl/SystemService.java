package com.hefei.admin.cas.system.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.admin.cas.system.service.ISystemService;
import com.hefei.api.cas.system.manger.ISystemManager;
import com.hefei.api.cas.system.manger.impl.SystemManager;
import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class SystemService implements ISystemService{

	private Logger logger = Logger.getLogger(SystemService.class);

	private ISystemManager systemManager = new SystemManager();
	@Override
	public Pagination<SystemInfo> find(String systemName, int pageSize, int pageIndex) throws BusinessException {
		try {
			return systemManager.find(systemName, pageSize, pageIndex);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}

	@Override
	public SystemInfo create(SystemInfo systemInfo) throws BusinessException {
		try {
			return systemManager.create(systemInfo);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public SystemInfo getById(Long id) throws BusinessException {
		try {
			return systemManager.getById(id);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return null;
		}
	}
}
