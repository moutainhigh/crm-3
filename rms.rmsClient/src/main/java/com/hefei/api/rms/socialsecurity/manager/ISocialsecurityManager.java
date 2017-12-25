package com.hefei.api.rms.socialsecurity.manager;

import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.common.exception.ClientException;


public interface ISocialsecurityManager {

	public EmployeeSSDTO getEmployeeSSDTO(Long companyId, Long employeeId)  throws ClientException;
	
	public EmployeeSSTransactionDTO getEmployeeSSTransaction(Long ssTransactionId)  throws ClientException;
	
	public void savAdjustSS(EmployeeSSDTO employeeSSDTO)  throws ClientException;
	
	public void saveAdjustEmployeeSSTransaction(
			EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark,
			Long currentUsrId) throws ClientException;
	
	public String paySS(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws ClientException;
}
