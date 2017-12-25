package com.hefei.cas.system.service;

import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.cas.system.po.Sys;
import com.hefei.common.page.Pagination;
import com.hefei.service.framework.exception.ServiceException;

public interface ISystemService {

	public Sys createSystem(SystemInfo sys) throws ServiceException;
	
	public Sys getById(Long id) throws ServiceException;
	
	public Pagination<SystemInfo> find(String systemName, int pageSize, int pageIndex) throws ServiceException;
}
