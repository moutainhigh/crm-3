package com.hefei.admin.cas.system.service;

import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;

public interface ISystemService {

	public Pagination<SystemInfo>  find(String systemName, int pageSize, int pageIndex ) throws BusinessException;
	
	public SystemInfo create(SystemInfo systemInfo) throws BusinessException;
	public SystemInfo getById(Long id) throws BusinessException;
}
