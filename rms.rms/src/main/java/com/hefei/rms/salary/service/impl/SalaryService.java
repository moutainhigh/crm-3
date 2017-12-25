package com.hefei.rms.salary.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryHistoryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.api.rms.salary.manager.ISalaryManager;
import com.hefei.api.rms.salary.manager.impl.SalaryManager;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.api.rms.socialsecurity.manager.ISocialsecurityManager;
import com.hefei.api.rms.socialsecurity.manager.impl.SocialsecurityManager;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;


@Service
public class SalaryService implements ISalaryService{

	private Logger logger = Logger.getLogger(SalaryService.class);

	private ISalaryManager salaryManager = new SalaryManager();
	private ISocialsecurityManager socialsecurityManager = new SocialsecurityManager();
	
	@Override
	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId,Long userId) throws BusinessException {
		try {
			return salaryManager.getSalaryAndSS(companyId, employeeId, userId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public EmployeeSSDTO getEmployeeSSDTO(Long companyId, Long employeeId)throws BusinessException {
		try {
			return socialsecurityManager.getEmployeeSSDTO(companyId, employeeId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public SalaryTransactionDTO getSalaryTransaction(Long transactionId)
			throws BusinessException {
		try {
			return salaryManager.getSalaryTransaction(transactionId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public EmployeeSSTransactionDTO getEmployeeSSTransaction(Long ssTransactionId) throws BusinessException {
		try {
			return socialsecurityManager.getEmployeeSSTransaction(ssTransactionId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public void savAdjustSS(EmployeeSSDTO employeeSSDTO) throws BusinessException {
		try {
			socialsecurityManager.savAdjustSS(employeeSSDTO);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
		
	}
	
	@Override
	public Pagination<SalaryHistoryDTO> findSalaryAdjustHistory(Long companyId,
			Long employeeId, Long userId, String cardNo, String employeeName,int pageIndex, int pageSize) throws BusinessException {
		try {
			return salaryManager.findSalayAdjustHistory(companyId, employeeId, userId, cardNo, employeeName, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public Pagination<SalaryPayTransactionDTO> findSalaryAndSSPayHistory(
			Long companyId, Long employeeId, Long userId,
			String employeeStatus, String salaryPayStatus, String ssPayStatus,
			String cardNo, String employeeName, int pageIndex, int pageSize)
			throws BusinessException {
		try {
			return salaryManager.findSalayAndSSPayHistory(companyId, employeeId, userId, employeeStatus, salaryPayStatus, ssPayStatus, cardNo, employeeName, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo,
			String employeeName, int pageIndex, int pageSize)
			throws BusinessException {
		try {
			return salaryManager.findSalary(companyId, cardNo, employeeName, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime,
			String remark, Long currentUsrId) throws BusinessException {
		try {
			salaryManager.saveAdjustSalary(salaryDTO, effectTime, remark, currentUsrId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
		
	}

	@Override
	public void saveAdjustSalaryTransaction(
			SalaryTransactionDTO salaryTransactionDTO, String remark,
			Long currentUsrId) throws BusinessException {
		try {
			salaryManager.saveAdjustSalaryTransaction(salaryTransactionDTO, remark, currentUsrId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
		
	}

	@Override
	public void saveAdjustEmployeeSSTransaction(
			EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark,
			Long currentUsrId) throws BusinessException {
		try {
			socialsecurityManager.saveAdjustEmployeeSSTransaction(employeeSSTransactionDTO, remark, currentUsrId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
		
	}

	@Override
	public String generatePayInfo(Long companyId, String employeeStatus)
			throws BusinessException {
		try {
			return salaryManager.generatePayInfo(companyId, employeeStatus);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public String paySalary(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws BusinessException {
		try {
			return salaryManager.paySalary(companyId, employeeId, userId, transactionIds);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}

	@Override
	public String paySS(Long companyId, Long employeeId, Long userId,
			String transactionIds) throws BusinessException {
		try {
			return socialsecurityManager.paySS(companyId, employeeId, userId, transactionIds);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
}
