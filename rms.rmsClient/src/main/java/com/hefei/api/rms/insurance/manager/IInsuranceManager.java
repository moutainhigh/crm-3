package com.hefei.api.rms.insurance.manager;

import com.hefei.api.rms.insurance.vo.InsuranceInfo;
import com.hefei.common.exception.ClientException;


public interface IInsuranceManager {
	public InsuranceInfo findInsurance(Long employeeId) throws ClientException;
	public Boolean saveInsurance(InsuranceInfo insuranceInfo) throws ClientException;
	public Boolean updateInsurance(InsuranceInfo insuranceInfo) throws ClientException;
}
