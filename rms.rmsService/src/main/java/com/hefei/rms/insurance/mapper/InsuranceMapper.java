package com.hefei.rms.insurance.mapper;

import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.service.framework.base.mapper.Mapper;

public interface InsuranceMapper extends Mapper{

	public InsuranceInfo findInsurance(Long employeeId);
	public void saveInsurance(InsuranceInfo insuranceInfo);
	public void updateInsurance(InsuranceInfo insuranceInfo);
}
