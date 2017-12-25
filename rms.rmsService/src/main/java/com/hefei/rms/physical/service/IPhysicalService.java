package com.hefei.rms.physical.service;

import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IPhysicalService {

	public PhysicalInfo findPhysical(Long employeeId) throws ServiceException;
	public void savePhysical(PhysicalInfo physicalInfo) throws ServiceException;
	public void updatePhysical(PhysicalInfo physicalInfo) throws ServiceException;
}
