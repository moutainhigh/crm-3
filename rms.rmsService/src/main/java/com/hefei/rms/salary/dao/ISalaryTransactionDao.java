package com.hefei.rms.salary.dao;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.service.framework.exception.DaoException;

public interface ISalaryTransactionDao {

	public SalaryTransaction getSalaryTransaction(Long transactionId) throws DaoException;
	
	public SalaryTransaction saveSalaryTransaction(SalaryTransaction salaryTransaction) throws DaoException;
	
	public void saveSalaryTransaction(List<SalaryTransaction> list) throws DaoException;
	
	public void updateSalaryTransactionPayed(List<Long> transactionIds, String status, Long userId, Date payedTime) throws DaoException;
			
	public void updateSalaryTransaction(Long id,Double monthlyBaseSalary,Double monthlyBonus, Double quarterlyBonus,
			Double yearlyBonus,Double deductAmount,Double taxRate,Double taxAmount,Double payedAmount,Date updateTime) throws DaoException;
	
	
	public List<SalaryPayTransactionDTO> getSalaryPayTransactionPaginationItems(Long companyId, Long employeeId, List<String> employeeStatus, List<String> salaryPayStatus,
			List<String> ssPayStatus,String cardNo, String employeeName, int pageIndex, int pageSize) throws DaoException;
	
	public int getSalaryPayTransactionPaginationCount(Long companyId, Long employeeId, List<String> employeeStatus, List<String> salaryPayStatus,
			List<String> ssPayStatus,String cardNo, String employeeName) throws DaoException;
}
