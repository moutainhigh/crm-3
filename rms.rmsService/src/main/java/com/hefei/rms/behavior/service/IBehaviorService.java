package com.hefei.rms.behavior.service;

import java.util.Date;
import java.util.List;

import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IBehaviorService {
	/**
	 * 保存员工奖惩信息
	 * @param info
	 * @throws ServiceException
	 */
	public void saveBehaviorInfo(BehaviorInfo info) throws ServiceException;
	/**
	 * 修改奖惩记录状态
	 * @param id
	 * @param status 0 撤销  1 正常
	 * @throws ServiceException
	 */
	public void updateBehavior(Long id,String status, Date updateTime) throws ServiceException;
	/**
	 * 查找员工奖惩信息
	 * @param employeeId 员工编号
	 * @param month 月
	 * @return
	 * @throws ServiceException
	 */
	public List<BehaviorInfo> findBehavior(Long employeeId, String month) throws ServiceException;
}
