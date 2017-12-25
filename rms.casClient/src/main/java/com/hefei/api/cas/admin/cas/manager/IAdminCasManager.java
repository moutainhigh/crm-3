package com.hefei.api.cas.admin.cas.manager;

import java.util.List;

import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.ClientException;

public interface IAdminCasManager {

	public List<ModuleInfo> getModuleMenus(Long adminId, Long systemId) throws ClientException;
}
