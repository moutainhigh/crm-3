package com.hefei.rms.contract.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.contract.manager.IContractManager;
import com.hefei.api.rms.contract.manager.impl.ContractManager;
import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.rms.contract.service.IContractService;


@Service
public class ContractService implements IContractService{
	private IContractManager contractManager = new ContractManager();

	@Override
	public void createContract(ContractInfo info) throws Exception {
		ContractInfo contractInfo = new ContractInfo();
		BeanUtils.copyProperties(info, contractInfo);
		contractManager.saveInfo(contractInfo);
	}
}
