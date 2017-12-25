package com.hefei.rms.contract.service;

import com.hefei.api.rms.contract.vo.ContractInfo;


public interface IContractService {
	public void createContract(ContractInfo info) throws Exception;
}
