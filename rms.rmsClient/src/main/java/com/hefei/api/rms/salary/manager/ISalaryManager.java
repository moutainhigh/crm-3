package com.hefei.api.rms.salary.manager;

import java.util.Date;

import com.hefei.api.rms.salary.dto.SalaryHistoryDTO;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;


public interface ISalaryManager {
	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId,Long userId) throws ClientException;
	
	public SalaryTransactionDTO getSalaryTransaction(Long transactionId) throws ClientException;
	
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime,
			String remark, Long currentUsrId) throws ClientException;
	
	public void saveAdjustSalaryTransaction(
			SalaryTransactionDTO salaryTransactionDTO, String remark,
			Long currentUsrId) throws ClientException;
			
	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo,
			String employeeName, int pageIndex, int pageSize) throws ClientException;
			
	public Pagination<SalaryHistoryDTO> findSalayAdjustHistory(Long companyId,
			Long employeeId, Long userId, String cardNo, String employeeName,
			int pageIndex, int pageSize) throws ClientException;
	
	public Pagination<SalaryPayTransactionDTO> findSalayAndSSPayHistory(
			Long companyId, Long employeeId, Long userId,
			String employeeStatus, String salaryPayStatus, String ssPayStatus,
			String cardNo, String employeeName, int pageIndex, int pageSize) throws ClientException;
	
	public String generatePayInfo(Long companyId, String employeeStatus)
			throws ClientException;
	
	public String paySalary(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws ClientException;
}
