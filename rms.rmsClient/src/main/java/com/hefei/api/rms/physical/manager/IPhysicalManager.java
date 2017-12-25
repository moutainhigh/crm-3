package com.hefei.api.rms.physical.manager;

import com.hefei.api.rms.physical.vo.PhysicalInfo;
import com.hefei.common.exception.ClientException;


public interface IPhysicalManager {
	public PhysicalInfo findPhysical(Long employeeId) throws ClientException;
	public Boolean savePhysical(PhysicalInfo physicalInfo) throws ClientException;
	public Boolean updatePhysical(PhysicalInfo physicalInfo) throws ClientException;
}
