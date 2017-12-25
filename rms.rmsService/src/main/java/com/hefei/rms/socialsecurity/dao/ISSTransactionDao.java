package com.hefei.rms.socialsecurity.dao;

import java.util.Date;
import java.util.List;

import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.service.framework.exception.DaoException;

public interface ISSTransactionDao {

	public EmployeeSSTransaction getEmployeeSSTransaction(Long ssTransactionId) throws DaoException;
	
	public void saveSSTransaction(List<EmployeeSSTransaction> employeeSSTransactionList) throws DaoException;
	
	public void updateSSTransaction(EmployeeSSTransaction ssTransaction)throws DaoException;
	
	public void updateSSTransactionPayed(List<Long> transactionIds, String status, Long userId, Date payedTime) throws DaoException;
	
}
