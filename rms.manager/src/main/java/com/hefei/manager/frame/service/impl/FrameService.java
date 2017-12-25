package com.hefei.manager.frame.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.module.util.ModuleTreeFactory;
import com.hefei.api.cas.module.util.SysTree;
import com.hefei.api.cas.user.auth.manager.IUserAuthorizationManager;
import com.hefei.api.cas.user.auth.manager.impl.UserAuthorizationManager;
import com.hefei.api.cas.user.menu.SysMenu;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.manager.frame.service.IFrameService;

@Service
public class FrameService implements IFrameService{

	private static Logger logger = Logger.getLogger(FrameService.class);
	
	private IUserAuthorizationManager userAuthorizationManager = new UserAuthorizationManager();
	
	@Override
	public List<SysTree> getMenus(Long userId, Long companyId)
			throws BusinessException {
		try {
			List<SysMenu> list = userAuthorizationManager.getMenus(userId, companyId);
			List<SysTree> sysTree = ModuleTreeFactory.generateTree(list);
			return sysTree;
		} catch (ClientException e) {
			throw new BusinessException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
}
