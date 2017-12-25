package com.hefei.rms.contract.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.contract.po.ContractInfo;
import com.hefei.service.framework.exception.DaoException;

public interface IContractDao {
	/**
	 * 保存合同信息
	 * @param Info
	 * @throws DaoException
	 */
	public void saveInfo(ContractInfo info) throws DaoException;
	/**
	 * 更新合同信息
	 * @param
	 * @throws DaoException
	 */
	public void updateContract(Long id, String auditStatus,String contractType, String delFlag, Date updateTime) throws DaoException;
	/**
	 * 查看合同信息
	 * @param
	 * @throws DaoException
	 */
	public List<ContractInfo> findContract(Long employeeId) throws DaoException;

}
