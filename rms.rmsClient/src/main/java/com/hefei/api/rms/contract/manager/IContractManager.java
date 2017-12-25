package com.hefei.api.rms.contract.manager;

import java.util.List;

import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.common.exception.ClientException;

public interface IContractManager {
	
	public Boolean saveInfo(ContractInfo contractInfo) throws ClientException;

	public Boolean updateContract(Long id,String auditStatus,String contractType,String delFlag) throws ClientException;
	
	public List<ContractInfo> findContract(Long employeeId) throws ClientException;
}
