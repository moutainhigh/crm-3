package com.hefei.api.cas.system.manger;

import com.hefei.api.cas.system.vo.SystemInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;

public interface ISystemManager {

	public Pagination<SystemInfo>  find(String systemName, int pageSize, int pageIndex ) throws ClientException;
	
	public SystemInfo create(SystemInfo systemInfo) throws ClientException;
	public SystemInfo getById(Long id) throws ClientException;
}
