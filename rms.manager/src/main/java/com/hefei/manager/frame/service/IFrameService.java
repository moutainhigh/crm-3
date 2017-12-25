package com.hefei.manager.frame.service;

import java.util.List;

import com.hefei.api.cas.module.util.SysTree;
import com.hefei.common.exception.BusinessException;


public interface IFrameService {
	public List<SysTree> getMenus(Long userId, Long companyId) throws BusinessException;
}
