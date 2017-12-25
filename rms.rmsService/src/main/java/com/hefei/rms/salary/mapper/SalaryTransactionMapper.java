package com.hefei.rms.salary.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.service.framework.base.mapper.Mapper;

public interface SalaryTransactionMapper extends Mapper{

	public SalaryTransaction getSalaryTransaction(Long id);
	
	public void saveSalaryTransaction(SalaryTransaction salaryTransaction);
	
	public void saveSalaryTransactions(List<SalaryTransaction> list);
	
	public void updateSalaryTransactionPayed(Map map);
	public void updateSalaryTransaction(Map map);
	
	public List<SalaryPayTransactionDTO> getSalaryPayTransactionPaginationItems(Map map);
	
	public int getSalaryPayTransactionPaginationCount(Map map);
}
