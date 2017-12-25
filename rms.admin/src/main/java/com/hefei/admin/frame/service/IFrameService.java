package com.hefei.admin.frame.service;

import java.util.List;

import com.hefei.api.cas.module.util.ModuleTree;
import com.hefei.common.exception.BusinessException;

public interface IFrameService {

	public List<ModuleTree> getMenus(Long adminId) throws BusinessException;
}
