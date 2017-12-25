package com.hefei.cas.admin.cas.dao;

import java.util.List;

import com.hefei.cas.module.po.Module;
import com.hefei.service.framework.exception.DaoException;

public interface IAdminCasDao {

	public List<Module> getModuleMenus(Long adminId, Long systemId) throws DaoException;
}
