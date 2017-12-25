package com.hefei.rms.salary.service;

import java.util.Date;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryHistoryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;


public interface ISalaryService {
	
	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId, Long userId) throws BusinessException;
	
	public EmployeeSSDTO getEmployeeSSDTO(Long companyId, Long employeeId) throws BusinessException;
	
	public SalaryTransactionDTO getSalaryTransaction(Long transactionId) throws BusinessException;
	public EmployeeSSTransactionDTO getEmployeeSSTransaction(Long ssTransactionId) throws BusinessException;
	public void savAdjustSS( EmployeeSSDTO employeeSSDTO) throws BusinessException;
	
	public Pagination<SalaryHistoryDTO> findSalaryAdjustHistory(Long companyId, Long employeeId, Long userId, String cardNo, String employeeName,int pageIndex, int pageSize)throws BusinessException;
	
	public Pagination<SalaryPayTransactionDTO> findSalaryAndSSPayHistory(Long companyId, Long employeeId, Long userId, String employeeStatus, String salaryPayStatus, String ssPayStatus, String cardNo, String employeeName,int pageIndex, int pageSize)throws BusinessException;

	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo, String employeeName,int pageIndex, int pageSize)throws BusinessException;
	
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime, String remark, Long currentUsrId)throws BusinessException;
	public void saveAdjustSalaryTransaction(SalaryTransactionDTO salaryTransactionDTO, String remark, Long currentUsrId)throws BusinessException;
	public void saveAdjustEmployeeSSTransaction(EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark, Long currentUsrId)throws BusinessException;
	
	public String generatePayInfo(Long companyId, String employeeStatus)throws BusinessException;
	
	public String paySalary(Long companyId, Long employeeId, Long userId, String transactionIds) throws BusinessException;
	public String paySS(Long companyId, Long employeeId, Long userId, String transactionIds) throws BusinessException;
	
}
