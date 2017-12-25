package com.hefei.rms.physical.dao;

import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.service.framework.exception.DaoException;

public interface IPhysicalDao {

	public PhysicalInfo findPhysical(Long employeeId) throws DaoException;
	public void savePhysical(PhysicalInfo physicalInfo) throws DaoException;
	public void updatePhysical(PhysicalInfo physicalInfo) throws DaoException;
}
