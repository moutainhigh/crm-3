package com.hefei.rms.position.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.position.manager.IPositionManager;
import com.hefei.api.rms.position.manager.impl.PositionManager;
import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.position.service.IPositionService;

@Service
public class PositionService implements IPositionService{
	
	private static final Logger logger = Logger.getLogger(PositionService.class);
	
	private IPositionManager positionManager = new PositionManager();
	
	@Override
	public PositionInfo createPosition(PositionInfo info) throws BusinessException {
		try {
			return positionManager.savePosition(info);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	@Override
	public PositionInfo getPosition(Long id) throws BusinessException {
		try {
			return positionManager.getPosition(id);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	@Override
	public List<PositionInfo> getPositionByDepartment(Long departmentId) throws BusinessException {
		try {
			return positionManager.getPostionByDepartment(departmentId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
}
