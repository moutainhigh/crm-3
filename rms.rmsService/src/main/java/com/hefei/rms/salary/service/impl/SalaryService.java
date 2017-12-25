package com.hefei.rms.salary.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.api.rms.salary.util.IndividualIncomeMonthlyTaxDict;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.MoneyUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.salary.dao.ISalaryDao;
import com.hefei.rms.salary.dao.ISalaryHistoryDao;
import com.hefei.rms.salary.dao.ISalaryTransactionDao;
import com.hefei.rms.salary.po.Salary;
import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.rms.salary.service.ISalaryService;
import com.hefei.rms.socialsecurity.dao.ISSTransactionDao;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.po.EmployeeSSTransaction;
import com.hefei.rms.socialsecurity.service.ISocialsecurityService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class SalaryService implements ISalaryService{

	private static final Logger logger = Logger.getLogger(SalaryService.class);
	@Autowired
	private ISalaryDao salaryDao;
	@Autowired
	private ISalaryHistoryDao salaryHistoryDao;
	@Autowired
	private ISalaryTransactionDao salaryTransactionDao;
	@Autowired
	private ISSTransactionDao ssTransactionDao;
	@Autowired
	private ISocialsecurityService socialsecurityService;
	private IdManager idManager = new IdManagerImpl();
	
	@Override
	public void saveSalary(Salary salaryInfo) throws ServiceException {
		if(salaryInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			salaryInfo.setDelFlag(SalaryDTO.DEL_FLAG_TRUE);
			salaryInfo.setCreateTime(now);
			salaryInfo.setUpdateTime(now);
			
			salaryDao.saveSalary(salaryInfo);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}

	@Override
	public SalarySSDTO getSalaryAndSS(Long companyId, Long employeeId) throws ServiceException {
		try {
			Salary salary = salaryDao.getSalary(companyId, employeeId);
			EmployeeSS employeeSS = socialsecurityService.getEmployeeSS(companyId, employeeId);
			
			SalarySSDTO salarySSDTO = new SalarySSDTO();
			BeanUtils.copyProperties(salarySSDTO, salary);
			BeanUtils.copyProperties(salarySSDTO, employeeSS);
			salarySSDTO.setId(salary.getId());
			salarySSDTO.setSsId(employeeSS.getId());
			
			return salarySSDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + "  error ",e );
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public SalaryTransaction getSalaryTransaction(Long transactionId) throws ServiceException {
		try {
			return salaryTransactionDao.getSalaryTransaction(transactionId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public void saveAdjustSalary(SalaryDTO salaryDTO, Date effectTime, String remark, Long currentUsrId) throws ServiceException {
		try {
			Date now = Calendar.getInstance().getTime();
			
			salaryDao.updateSalary(salaryDTO.getId(), salaryDTO.getMonthlyBaseSalary(), salaryDTO.getMonthlyBonus(), salaryDTO.getQuarterlyBonus(), salaryDTO.getYearlyBonus(), now);
			SalarySSDTO oldSalary = getSalaryAndSS(salaryDTO.getCompanyId(), salaryDTO.getEmployeeId());
			
			SalaryHistory salaryHistory = new SalaryHistory();
			salaryHistory.setCompanyId(oldSalary.getCompanyId());
			
			salaryHistory.setEffectTime(effectTime);
			salaryHistory.setEmployeeId(oldSalary.getEmployeeId());
			salaryHistory.setEmployeeName(oldSalary.getEmployeeName());
			salaryHistory.setMonthlyBaseSalaryAfter(salaryDTO.getMonthlyBaseSalary());
			salaryHistory.setMonthlyBaseSalaryBefore(oldSalary.getMonthlyBaseSalary());
			salaryHistory.setMonthlyBonusAfter(salaryDTO.getMonthlyBonus());
			salaryHistory.setMonthlyBonusBefore(oldSalary.getMonthlyBaseSalary());
			salaryHistory.setQuarterlyBonusAfter(salaryDTO.getQuarterlyBonus());
			salaryHistory.setQuarterlyBonusBefore(oldSalary.getQuarterlyBonus());
			salaryHistory.setYearlyBonusAfter(salaryDTO.getYearlyBonus());
			salaryHistory.setYearlyBonusBefore(oldSalary.getYearlyBonus());
			salaryHistory.setRemark(remark);
			salaryHistory.setCreateTime(now);
			salaryHistory.setCreator(currentUsrId);
			salaryHistory.setUpdateTime(now);
			
			salaryHistoryDao.saveSalaryHistory(salaryHistory);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
		
	}

	@Override
	public void saveAdjustSalaryTransaction(SalaryTransactionDTO salaryTransactionDTO, String remark, Long currentUsrId) throws ServiceException {
		try {
			Date now = Calendar.getInstance().getTime();
			salaryTransactionDao.updateSalaryTransaction(salaryTransactionDTO.getId(), salaryTransactionDTO.getMonthlyBaseSalary(), salaryTransactionDTO.getMonthlyBonus(), salaryTransactionDTO.getQuarterlyBonus(), salaryTransactionDTO.getYearlyBonus(), salaryTransactionDTO.getDeductAmount()
					, salaryTransactionDTO.getTaxRate(), salaryTransactionDTO.getTaxAmount(), salaryTransactionDTO.getPayedAmount(), now);
			} catch (DaoException e) {
				throw new ServiceException(e.getErrorCode());
			}
		}

	@Override
	public Pagination<SalarySSDTO> findSalary(Long companyId, String cardNo, String employeeName,String employeeStatus,  int pageIndex, int pageSize) throws ServiceException {
		Pagination<SalarySSDTO> pagination = new Pagination<SalarySSDTO>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			List<String> employeeCompanyStatus = new ArrayList<String>();
			if(StringUtils.isNotBlank(employeeStatus)){
				String[] statusStr = employeeStatus.split(",");
				for(String status: statusStr){
					if(StringUtils.isNotBlank(status)){
						employeeCompanyStatus.add(status);
					}
				}
			}
			List<SalarySSDTO> list = salaryDao.findSalaryPaginationItems(companyId, cardNo, employeeName, employeeCompanyStatus,pageIndex, pageSize);
			int count = salaryDao.findSalaryPaginationCount(companyId, cardNo, employeeName,employeeCompanyStatus);
			pagination.setItems(list);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Pagination<SalaryHistory> findSalayAdjustHistory(Long companyId, Long employeeId, String cardNo, String employeeName, int pageIndex, int pageSize) throws ServiceException {
		Pagination<SalaryHistory> pagination = new Pagination<SalaryHistory>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			
			List<SalaryHistory> list = salaryHistoryDao.findSalaryHistoryPaginationItems(companyId, employeeId, cardNo, employeeName, pageIndex, pageSize);
			int count = salaryHistoryDao.findSalaryHistoryPaginationCount(companyId, employeeId, cardNo, employeeName);
			pagination.setItems(list);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public Pagination<SalaryPayTransactionDTO> findSalayAndSSPayHistory(Long companyId, Long employeeId,
			String employeeStatus, String salaryPayStatus, String ssPayStatus,String cardNo, String employeeName, int pageIndex, int pageSize)throws ServiceException {
		Pagination<SalaryPayTransactionDTO> pagination = new Pagination<SalaryPayTransactionDTO>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			
			List<String> employeeCompanyStatus = new ArrayList<String>();
			if(StringUtils.isNotBlank(employeeStatus)){
				String[] statusStr = employeeStatus.split(",");
				for(String status: statusStr){
					if(StringUtils.isNotBlank(status)){
						employeeCompanyStatus.add(status);
					}
				}
			}
			List<String> salaryPayStatuses = new ArrayList<String>();
			if(StringUtils.isNotBlank(salaryPayStatus)){
				String[] statusStr = salaryPayStatus.split(",");
				for(String status: statusStr){
					if(StringUtils.isNotBlank(status)){
						salaryPayStatuses.add(status);
					}
				}
			}
			List<String> ssPayStatuses = new ArrayList<String>();
			if(StringUtils.isNotBlank(ssPayStatus)){
				String[] statusStr = ssPayStatus.split(",");
				for(String status: statusStr){
					if(StringUtils.isNotBlank(status)){
						ssPayStatuses.add(status);
					}
				}
			}
			
			
			List<SalaryPayTransactionDTO> list = salaryTransactionDao.getSalaryPayTransactionPaginationItems(companyId, employeeId, employeeCompanyStatus, salaryPayStatuses, ssPayStatuses, cardNo, employeeName, pageIndex, pageSize);
			int count = salaryTransactionDao.getSalaryPayTransactionPaginationCount(companyId, employeeId, employeeCompanyStatus, salaryPayStatuses, ssPayStatuses, cardNo, employeeName);
			pagination.setItems(list);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	@Override
	public void generatePayInfo(Long companyId, String employeeStatus) throws ServiceException {
		try{
			int pageIndex = 1;
			Date now = Calendar.getInstance().getTime();
			SalaryTransaction salaryTransaction = null;
			EmployeeSSTransaction employeeSSTransaction = null;
			List<SalaryTransaction> salaryTransactionList = null;
			List<EmployeeSSTransaction> employeeSSTransactionList = null;
			while(true){
				Pagination<SalarySSDTO> list = findSalary(companyId, null, null, employeeStatus, pageIndex, Pagination.DEFAULT_PAGE_SIZE);
				if(list == null || list.getItems() == null || list.getItems().size() ==0)
					break;
				List<SalarySSDTO> salarys = list.getItems();
				salaryTransactionList = new ArrayList<SalaryTransaction>();
				employeeSSTransactionList = new ArrayList<EmployeeSSTransaction>();
				for(SalarySSDTO salary : salarys){
					employeeSSTransaction = new EmployeeSSTransaction();
					employeeSSTransaction.setId(idManager.getNextId());
					employeeSSTransaction.setCompanyId(companyId);
					employeeSSTransaction.setPayedTime(null);
					employeeSSTransaction.setRemark(null);
					employeeSSTransaction.setSsBaseSalary(salary.getSsBaseSalary());
					employeeSSTransaction.setSsCard(salary.getSsCard());
					employeeSSTransaction.setStatus(EmployeeSSTransactionDTO.STATUS_TO_PAY);
					employeeSSTransaction.setCreateTime(now);
					employeeSSTransaction.setUpdateTime(now);
					employeeSSTransaction.setEmployeeId(salary.getEmployeeId());
					employeeSSTransaction.setEmployeeName(salary.getEmployeeName());
					
					employeeSSTransaction.setGjjAdd(salary.getGjjAdd());
					employeeSSTransaction.setGjjComCash(salary.getGjjComCash());
					employeeSSTransaction.setGjjComRate(salary.getGjjComRate());
					employeeSSTransaction.setGjjInsurance(salary.getGjjInsurance());
					employeeSSTransaction.setGjjPersonalCash(salary.getGjjPersonalCash());
					employeeSSTransaction.setGjjPersonalRate(salary.getGjjPersonalRate());
					
					employeeSSTransaction.setGshComCash(salary.getGshComCash());
					employeeSSTransaction.setGshComRate(salary.getGshComRate());
					employeeSSTransaction.setGshInsurance(salary.getGshInsurance());
					employeeSSTransaction.setGshPersonalCash(salary.getGshPersonalCash());
					employeeSSTransaction.setGshPersonalRate(salary.getGshPersonalRate());
					
					employeeSSTransaction.setSyeComCash(salary.getSyeComCash());
					employeeSSTransaction.setSyeComRate(salary.getSyeComRate());
					employeeSSTransaction.setSyeInsurance(salary.getSyeInsurance());
					employeeSSTransaction.setSyePersonalCash(salary.getSyePersonalCash());
					employeeSSTransaction.setSyePersonalRate(salary.getSyePersonalRate());
					
					employeeSSTransaction.setSyuComCash(salary.getSyuComCash());
					employeeSSTransaction.setSyuComRate(salary.getSyuComRate());
					employeeSSTransaction.setSyuInsurance(salary.getSyuInsurance());
					employeeSSTransaction.setSyuPersonalCash(salary.getSyuPersonalCash());
					employeeSSTransaction.setSyuPersonalRate(salary.getSyuPersonalRate());
					
					employeeSSTransaction.setYlaoComCash(salary.getYlaoComCash());
					employeeSSTransaction.setYlaoComRate(salary.getYlaoComRate());
					employeeSSTransaction.setYlaoInsurance(salary.getYlaoInsurance());
					employeeSSTransaction.setYlaoPersonalCash(salary.getYlaoPersonalCash());
					employeeSSTransaction.setYlaoPersonalRate(salary.getYlaoPersonalRate());
					
					employeeSSTransaction.setYliaoComCash(salary.getYliaoComCash());
					employeeSSTransaction.setYliaoComRate(salary.getYliaoComRate());
					employeeSSTransaction.setYliaoInsurance(salary.getYliaoInsurance());
					employeeSSTransaction.setYliaoPersonalCash(salary.getYliaoPersonalCash());
					employeeSSTransaction.setYliaoPersonalRate(salary.getYliaoPersonalRate());
					
					employeeSSTransaction.setSsAmount(getSSTotal(employeeSSTransaction));
					employeeSSTransactionList.add(employeeSSTransaction);
					
					salaryTransaction = new SalaryTransaction();
					salaryTransaction.setId(idManager.getNextId());
					salaryTransaction.setCompanyId(companyId);
					salaryTransaction.setDeductAmount(0d);
					salaryTransaction.setEmployeeId(salary.getEmployeeId());
					salaryTransaction.setEmployeeName(salary.getEmployeeName());
					salaryTransaction.setMonthlyBaseSalary(salary.getMonthlyBaseSalary());
					salaryTransaction.setMonthlyBonus(salary.getMonthlyBonus());
					salaryTransaction.setQuarterlyBonus(0d);
					salaryTransaction.setYearlyBonus(0d);
					
					salaryTransaction.setSsId(employeeSSTransaction.getId());
					salaryTransaction.setSsAmount(employeeSSTransaction.getSsAmount());
					salaryTransaction.setStatus(SalaryTransactionDTO.STATUS_TO_PAY);
					
					double monthlyTotal = MoneyUtil.add(salaryTransaction.getMonthlyBaseSalary(), salaryTransaction.getMonthlyBonus());
					double afterSSAmount =  MoneyUtil.subtract(monthlyTotal, employeeSSTransaction.getSsAmount());
					salaryTransaction.setTaxRate(IndividualIncomeMonthlyTaxDict.get(afterSSAmount).getRate());
					salaryTransaction.setTaxAmount(IndividualIncomeMonthlyTaxDict.calTax(afterSSAmount));
					
					double payedAmount = MoneyUtil.subtract(afterSSAmount, salaryTransaction.getTaxAmount());
					salaryTransaction.setPayedAmount(payedAmount);
					salaryTransaction.setPayedTime(null);
					salaryTransaction.setCreateTime(now);
					salaryTransaction.setUpdateTime(now);
					salaryTransactionList.add(salaryTransaction);
				}
				salaryTransactionDao.saveSalaryTransaction(salaryTransactionList);
				ssTransactionDao.saveSSTransaction(employeeSSTransactionList);
				pageIndex ++;
			}
		}catch(DaoException e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DAO);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	private double getSSTotal(EmployeeSSTransaction employeeSSTransaction){
		double ssTotal = 0d;
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getYlaoInsurance());
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getYliaoInsurance());
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getGshInsurance());
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getSyuInsurance());
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getSyeInsurance());
		ssTotal = MoneyUtil.add(ssTotal, employeeSSTransaction.getGjjInsurance());
		return ssTotal;
	}
	
	@Override
	public void paySalary(Long companyId, Long employeeId, Long userId, String transactionIds) throws ServiceException {
		try{
			List<Long> transactionIdList = new ArrayList<Long>();
			if(StringUtils.isNotBlank(transactionIds)){
				String[] transactionIdsStr = transactionIds.split(",");
				for(String id: transactionIdsStr){
					if(StringUtils.isNotBlank(id)){
						transactionIdList.add(Long.valueOf(id));
					}
				}
			}
			if(transactionIdList.size() > 0)
				salaryTransactionDao.updateSalaryTransactionPayed(transactionIdList, SalaryTransactionDTO.STATUS_PAYED, userId, new Date());
		}catch(DaoException e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DAO);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
