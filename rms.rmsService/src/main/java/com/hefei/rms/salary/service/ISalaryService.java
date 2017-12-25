package com.hefei.rms.salary.service;

import java.util.Date;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.common.page.Pagination;
import com.hefei.rms.salary.po.Salary;
import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.service.framework.exception.ServiceException;

public interface ISalaryService {

	/**
	 * 保存工资信息
	 * @param salaryInfo
	 * @throws ServiceException
	 */
	public void saveSalary(Salary salaryInfo) throws ServiceException;

	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId) throws ServiceException;
	
	public SalaryTransaction getSalaryTransaction(Long transactionId) throws ServiceException;
	
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime, String remark, Long currentUsrId) throws ServiceException;
	
	public void saveAdjustSalaryTransaction(SalaryTransactionDTO salaryTransactionDTO, String remark, Long currentUsrId) throws ServiceException;
			
	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo, String employeeName, String employeeStatus,int pageIndex, int pageSize) throws ServiceException;
			
	public Pagination<SalaryHistory> findSalayAdjustHistory(Long companyId,
			Long employeeId, String cardNo, String employeeName, int pageIndex, int pageSize) throws ServiceException;
	
	public Pagination<SalaryPayTransactionDTO> findSalayAndSSPayHistory(Long companyId, Long employeeId, 
			String employeeStatus, String salaryPayStatus, String ssPayStatus, String cardNo, String employeeName, int pageIndex, int pageSize) throws ServiceException;
	
	public void generatePayInfo(Long companyId, String employeeStatus) throws ServiceException;
	
	public void paySalary(Long companyId, Long employeeId, Long userId,String transactionIds) throws ServiceException;
}
