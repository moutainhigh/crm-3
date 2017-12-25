package com.hefei.rms.socialsecurity.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SSTransactionMapper extends Mapper {

	public EmployeeSSTransaction getEmployeeSSTransaction(Long id);
	
	public void saveSSTransaction(List<EmployeeSSTransaction> employeeSSTransactionList);
	
	public void updateSSTransaction(EmployeeSSTransaction ssTransaction);
	public void updateSSTransactionPayed(Map map);
	
	
}
