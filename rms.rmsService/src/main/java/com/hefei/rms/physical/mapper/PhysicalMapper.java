package com.hefei.rms.physical.mapper;

import com.hefei.rms.physical.po.PhysicalInfo;
import com.hefei.service.framework.base.mapper.Mapper;

public interface PhysicalMapper extends Mapper {

	public PhysicalInfo findPhysical(Long employeeId);
	public void savePhysical(PhysicalInfo physicalInfo);
	public void updatePhysical(PhysicalInfo physicalInfo);
}
