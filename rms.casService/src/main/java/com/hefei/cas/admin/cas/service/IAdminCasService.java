package com.hefei.cas.admin.cas.service;

import java.util.List;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.exception.ServiceException;

public interface IAdminCasService {

	public List<Module> getModuleMenus(Long adminId, Long systemId) throws ServiceException;
}
