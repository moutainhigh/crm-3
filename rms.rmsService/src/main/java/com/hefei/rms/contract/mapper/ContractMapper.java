package com.hefei.rms.contract.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.contract.po.ContractInfo;
import com.hefei.service.framework.base.mapper.Mapper;

public interface ContractMapper extends Mapper{
	
	public void saveContract(ContractInfo info);

	public void updateContract(Map<String, Object> map);

	public List<ContractInfo> findContract(Long employeeId);
}
