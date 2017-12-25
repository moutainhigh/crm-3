package com.hefei.admin.frame.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.admin.frame.service.IFrameService;
import com.hefei.api.cas.admin.cas.manager.IAdminCasManager;
import com.hefei.api.cas.admin.cas.manager.impl.AdminCasManager;
import com.hefei.api.cas.config.manager.ICasConfigManager;
import com.hefei.api.cas.config.manager.impl.CasConfigManager;
import com.hefei.api.cas.module.util.ModuleTree;
import com.hefei.api.cas.module.util.ModuleTreeFactory;
import com.hefei.api.cas.module.vo.ModuleInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Service
public class FrameService implements IFrameService{
	
	private static Logger logger = Logger.getLogger(FrameService.class);
	private IAdminCasManager adminCasManager = new AdminCasManager();
	private ICasConfigManager casConfigManager = new CasConfigManager();
	@Override
	public List<ModuleTree> getMenus(Long adminId) throws BusinessException {
		try {
			List<ModuleInfo> list = adminCasManager.getModuleMenus(adminId, casConfigManager.getSystemIdAdmin());
			List<ModuleTree> moduleTree = ModuleTreeFactory.generate(list);
			return moduleTree;
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

}
