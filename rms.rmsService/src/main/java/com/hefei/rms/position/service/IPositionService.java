package com.hefei.rms.position.service;

import java.util.Date;
import java.util.List;

import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.exception.ServiceException;

public interface IPositionService {
	/**
	 * 保存岗位信息
	 * @param info
	 * @throws ServiceException
	 */
	public Position savePositionInfo(Position info) throws ServiceException;
	/**
	 * 更新岗位信息
	 * @param info
	 * @throws ServiceException
	 */
	public void updatePosition(Long id, String delFlag, Date updateTime) throws ServiceException;
	/**
	 * 查看岗位信息
	 * @param info
	 * @throws ServiceException
	 */
	public Position getPositionById(Long id) throws ServiceException;

	public List<Position> getPositionByDepartment(Long departmentId) throws ServiceException;
	
	
}
