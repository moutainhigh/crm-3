package com.hefei.rms.position.service;

import java.util.List;

import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.BusinessException;


public interface IPositionService {
	public PositionInfo createPosition(PositionInfo info) throws BusinessException;
	
	public PositionInfo getPosition(Long id) throws BusinessException;

	public List<PositionInfo> getPositionByDepartment(Long departmentId) throws BusinessException;
}
