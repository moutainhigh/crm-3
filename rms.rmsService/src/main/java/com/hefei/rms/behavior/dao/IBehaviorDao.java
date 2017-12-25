package com.hefei.rms.behavior.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.exception.DaoException;

public interface IBehaviorDao {

	/**
	 * 保存员工奖惩信息
	 * @param beInfo
	 * @throws DaoException
	 */
	public void saveBehaviorInfo(BehaviorInfo beInfo) throws DaoException;
	/**
	 * 修改员工奖惩记录状态
	 * @param id
	 * @param status
	 * @throws DaoException
	 */
	public void updateBehavior(Long id,String status, Date updateTime) throws DaoException;
	/**
	 * 查询员工奖惩记录
	 * @param employeeId
	 * @return
	 * @throws DaoException
	 */
	public List<BehaviorInfo> findBehavior(Long employeeId,String month) throws DaoException;
}
