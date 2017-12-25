package com.hefei.rms.insurance.service;

import com.hefei.rms.insurance.po.InsuranceInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IInsuranceService {
	/**
	 * 查询员工保险信息
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 */
	public InsuranceInfo findInsurance(Long employeeId) throws ServiceException;
	/**
	 * 保存员工保险信息
	 * @param insuranceInfo
	 * @throws ServiceException
	 */
	public void saveInsurance(InsuranceInfo insuranceInfo) throws ServiceException;
	/**
	 * 更新员工保险信息
	 * @param insuranceInfo
	 * @throws ServiceException
	 */
	public void updateInsurance(InsuranceInfo insuranceInfo) throws ServiceException;
}
