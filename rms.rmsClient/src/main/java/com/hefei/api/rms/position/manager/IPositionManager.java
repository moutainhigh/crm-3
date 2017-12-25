package com.hefei.api.rms.position.manager;

import java.util.List;

import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.ClientException;


public interface IPositionManager {
	public PositionInfo savePosition(PositionInfo positionInfo) throws ClientException;

	public void updatePosition(Long id,String delFlag) throws ClientException;
	
	public PositionInfo getPosition(Long id) throws ClientException;

	public List<PositionInfo> getPostionByDepartment(Long departmentId) throws ClientException;
}
