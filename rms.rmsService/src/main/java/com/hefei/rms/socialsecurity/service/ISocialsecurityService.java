package com.hefei.rms.socialsecurity.service;

import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.service.framework.exception.ServiceException;

public interface ISocialsecurityService {
	
	public EmployeeSS getEmployeeSS(Long companyId, Long employeeId)  throws ServiceException;
	
	public EmployeeSSTransaction getEmployeeSSTransaction(Long ssTransactionId)  throws ServiceException;
	
	public EmployeeSS createEmployeeSS(EmployeeSS employeeSS) throws ServiceException;
	
	public void saveAdjustSS(EmployeeSSDTO employeeSSDTO)  throws ServiceException;
	
	public void saveAdjustEmployeeSSTransaction(
			EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark,
			Long currentUsrId) throws ServiceException;
	
	public void paySS(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws ServiceException;
}
