package com.hefei.rms.position.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

public interface IPositionDao {

	/**
	 * 保存岗位信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public Position savePositionInfo(Position beInfo) throws DaoException;
	/**
	 * 更新岗位信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public void updatePosition(Long id, String delFlag, Date updateTime) throws DaoException;
	/**
	 * 查看岗位信息
	 * @param info
	 * @throws ServiceException
	 */
	public Position getPositionById(Long id) throws DaoException;

	public List<Position> getPositionByDepartment(Long departmentId) throws DaoException;
}
