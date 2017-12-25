package com.hefei.rms.position.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.base.mapper.Mapper;
import com.hefei.service.framework.exception.ServiceException;

public interface PositionMapper extends Mapper{

	public void savePositionInfo(Position beInfo);

	public void updatePosition(Map<String, Object> map);

	/**
	 * 查看岗位信息
	 * @param info
	 * @throws ServiceException
	 */
	public Position getPositionById(Map map);

	public List<Position> getPositionByDepartment(Map map);
	
}
