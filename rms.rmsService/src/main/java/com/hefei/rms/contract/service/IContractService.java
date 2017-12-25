package com.hefei.rms.contract.service;

import java.util.List;

import com.hefei.rms.contract.po.ContractInfo;
import com.hefei.service.framework.exception.ServiceException;

public interface IContractService {
	/**
	 *  保存
	 * @param info
	 * @throws ServiceException
	 */
	public void saveInfo(ContractInfo info) throws ServiceException;
	/**
	 *  更新
	 * @param 
	 * @throws ServiceException
	 */
	public void updateContract(Long id, String auditStatus,String contractType, String delFlag) throws ServiceException;
	/**
	 *  查看
	 * @param 
	 * @throws ServiceException
	 */
	public List<ContractInfo> findContract(Long employeeId) throws ServiceException;
}
