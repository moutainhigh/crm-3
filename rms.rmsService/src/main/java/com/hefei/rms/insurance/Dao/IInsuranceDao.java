package com.hefei.rms.insurance.Dao;

import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.service.framework.exception.DaoException;

public interface IInsuranceDao {

	public InsuranceInfo findInsurance(Long employeeId) throws DaoException;
	public void saveInsurance(InsuranceInfo insuranceInfo) throws DaoException;
	public void updateInsurance(InsuranceInfo insuranceInfo) throws DaoException;
}
