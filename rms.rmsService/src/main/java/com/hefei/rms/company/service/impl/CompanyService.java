package com.hefei.rms.company.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.cas.user.auth.manager.IUserAuthorizationManager;
import com.hefei.api.cas.user.auth.manager.impl.UserAuthorizationManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeCompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.rms.company.dao.ICompanyDao;
import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.rms.salary.po.Salary;
import com.hefei.rms.salary.service.ISalaryService;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.service.ISocialsecurityService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;

@Service
public class CompanyService implements ICompanyService {
	
	private Logger logger = Logger.getLogger(CompanyService.class);
	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private ISalaryService salaryService;
	@Autowired
	private ISocialsecurityService socialsecurityService;
	
	private IUserAuthorizationManager userAuthorizationManager = new UserAuthorizationManager();
	
	@Override
	public Company regCreateCompanyEmployee(Long userId, String companyName, String email, String mobile, String username) throws ServiceException {
		Date now = Calendar.getInstance().getTime();
		
		Company company = new Company();
		company.setCompanyName(companyName);
		company = createCompany(company);
		
		Employee employee = new Employee();
		employee = employeeService.createEmployee(employee);
		
		EmployeeCompany employeeCompany = new EmployeeCompany();
		employeeCompany.setCompanyId(company.getId());
		employeeCompany.setEmployeeId(employee.getId());
		employeeCompany.setCardNo(null);
		employeeCompany.setEmail(email);
		employeeCompany.setMobile(mobile);
		employeeCompany.setStatus(EmployeeCompanyInfo.STATUS_ONBOARD);
		employeeCompany.setOnBoardDate(now);
		employeeCompany.setLeaveDate(null);
		employeeCompany.setCreateTime(now);
		employeeCompany.setUpdateTime(now);
		employeeCompany = employeeService.addEmployeeToCompany(employeeCompany);
		
		Salary salary = new Salary();
		salary.setCompanyId(company.getId());
		salary.setEmployeeId(employee.getId());
		salary.setEmployeeName(username);
		salary.setDelFlag(SalaryDTO.DEL_FLAG_TRUE);
		salaryService.saveSalary(salary);
		
		EmployeeSS employeeSS = new EmployeeSS();
		employeeSS.setCompanyId(company.getId());
		employeeSS.setEmployeeId(employee.getId());
		employeeSS.setEmployeeName(username);
		employeeSS.setSsBaseSalary(0d);
		employeeSS.setGjjAdd(0d);
		employeeSS.setGjjComCash(0d);
		employeeSS.setGjjComRate(0d);
		employeeSS.setGjjInsurance(0d);
		employeeSS.setGjjPersonalCash(0d);
		employeeSS.setGjjPersonalRate(0d);
		employeeSS.setGshComCash(0d);
		employeeSS.setGshComRate(0d);
		employeeSS.setGshInsurance(0d);
		employeeSS.setGshPersonalCash(0d);
		employeeSS.setGshPersonalRate(0d);
		employeeSS.setSyeComCash(0d);
		employeeSS.setSyeComRate(0d);
		employeeSS.setSyeInsurance(0d);
		employeeSS.setSyePersonalCash(0d);
		employeeSS.setSyePersonalRate(0d);
		employeeSS.setSyuComCash(0d);
		employeeSS.setSyuComRate(0d);
		employeeSS.setSyuInsurance(0d);
		employeeSS.setSyuPersonalCash(0d);
		employeeSS.setSyuPersonalRate(0d);
		employeeSS.setYlaoComCash(0d);
		employeeSS.setYlaoComRate(0d);
		employeeSS.setYlaoInsurance(0d);
		employeeSS.setYlaoPersonalCash(0d);
		employeeSS.setYlaoPersonalRate(0d);
		employeeSS.setYliaoComCash(0d);
		employeeSS.setYliaoComRate(0d);
		employeeSS.setYliaoInsurance(0d);
		employeeSS.setYliaoPersonalCash(0d);
		employeeSS.setYliaoPersonalRate(0d);
		employeeSS.setSsCard(null);
		employeeSS.setCreator(null);
		socialsecurityService.createEmployeeSS(employeeSS);
		employeeService.createEmployeeLogin(userId, employee.getId());
		return company;
	}
	@Override
	public Company createCompany(Long userId, String industryIds, Company company) throws ServiceException{
		company = createCompany(company);
		
		EmployeeInfo employeeInfo = employeeService.getEmployeeInfoByUserId(userId);
		Date now = Calendar.getInstance().getTime();
		
//		EmployeeCompany oldEmployeeCompany = employeeService.getEmployeeCompany(company.getId(), employee.getId());
		
		EmployeeCompany employeeCompany = new EmployeeCompany();
		employeeCompany.setCompanyId(company.getId());
		employeeCompany.setEmployeeId(employeeInfo.getEmployeeId());
//		employeeCompany.setCardNo(employeeInfo.getCardNo());//添加新公司时，没有员工卡好
//		employeeCompany.setEmail(oldEmployeeCompany.getEmail());//员工的邮箱、手机号码使用原来的
//		employeeCompany.setMobile(oldEmployeeCompany.getMobile());
		employeeCompany.setStatus(EmployeeCompanyInfo.STATUS_ONBOARD);
		employeeCompany.setOnBoardDate(now);
		employeeCompany.setLeaveDate(null);
		employeeCompany.setCreateTime(now);
		employeeCompany.setUpdateTime(now);
		employeeService.addEmployeeToCompany(employeeCompany);
		
		if(StringUtils.isNotBlank(industryIds)){
			String industryIdArr[] = industryIds.split(",");
			for(String industryId : industryIdArr){
				if(StringUtils.isNotBlank(industryId)){
					createCompanyIndustry(company.getId(), Long.valueOf(industryId));
				}
			}
		}
		authrize(userId, company.getId());
		return company;
	}
	
	public void updateCompany(Long userId, String industryIds, Company company) throws ServiceException{
		try {
			List<CompanyIndustry> oldIndustryList = getCompanyIndustry(company.getId());
			List<Long> removedIndustryIds =  new ArrayList<Long>();
			List<Long> addedIndustryIds =  new ArrayList<Long>();
			String industryIdArr[] = null;
			if(StringUtils.isNotBlank(industryIds)){
				industryIdArr = industryIds.split(",");
			}
			
			if(industryIdArr == null || industryIdArr.length == 0){
				if(oldIndustryList != null && oldIndustryList.size() > 0){
					for(CompanyIndustry ind : oldIndustryList){
						removedIndustryIds.add(ind.getIndustryId());
					}
				}
			}
			if(oldIndustryList == null || oldIndustryList.size() == 0){
				if(industryIdArr != null && industryIdArr.length > 0){
					for(String industryId : industryIdArr){
						if(StringUtils.isNotBlank(industryId)){
							addedIndustryIds.add(Long.valueOf(industryId));
						}
					}
				}
			}
			
			if(industryIdArr != null && industryIdArr.length > 0 && oldIndustryList != null && oldIndustryList.size() > 0){
				for(int i =0; i< industryIdArr.length; i++){
					String industryId = industryIdArr[i];
					boolean find = false;
					if(StringUtils.isNotBlank(industryId)){
						for(CompanyIndustry ind : oldIndustryList){
							if(ind.getIndustryId().longValue() == Long.valueOf(industryId)){
								oldIndustryList.remove(ind);
								find = true;
								break;
							}
						}
						if(!find){
							addedIndustryIds.add(Long.valueOf(industryId));
						}
					}
				}
				for(CompanyIndustry ind : oldIndustryList){
					removedIndustryIds.add(ind.getIndustryId());
				}
			}
			
			if(addedIndustryIds.size() > 0){
				for(Long industryId: addedIndustryIds){
					createCompanyIndustry(company.getId(), industryId);
				}
			}
			if(removedIndustryIds.size() > 0){
				removeCompanyIndustry(company.getId(), removedIndustryIds);
			}
		
			companyDao.updateCompany(company);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	
	private void removeCompanyIndustry(Long companyId, List<Long> industryIds) throws ServiceException{
		try {
			companyDao.removeCompanyIndustry(companyId, industryIds);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	private CompanyIndustry createCompanyIndustry(Long companyId, Long industryId) throws ServiceException{
		try {
			CompanyIndustry companyIndustry = new CompanyIndustry();
			companyIndustry.setCompanyId(companyId);
			companyIndustry.setIndustryId(industryId);
			companyIndustry.setDelFlag(com.hefei.api.rms.company.vo.CompanyIndustryDTO.DEL_FLAG_NO);
			
			Date now = Calendar.getInstance().getTime();
			
			companyIndustry.setCreateTime(now);
			companyIndustry.setUpdateTime(now);
			companyDao.saveCompanyIndustry(companyIndustry);
			return companyIndustry;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public Company createCompany(Company company) throws ServiceException {
		Company exists = getCompanyByName(company.getCompanyName());
		if( exists != null)
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_DATA_EXISTS);
		try {
			Date now = Calendar.getInstance().getTime();
			company.setDelFlag(CompanyInfo.DELFLAG_NO);
			company.setCreateTime(now);
			company.setUpdateTime(now);
			company = companyDao.saveCompany(company);
			return company;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	
	private void authrize(Long userId, Long companyId) {
		try {
			userAuthorizationManager.createCompanySuperManager(userId, companyId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
		}
	}
	
	@Override
	public Company getCompany(Long userId, Long companyId) throws ServiceException {
		try {
			Company company = companyDao.getCompany(userId,companyId);
			return company;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public Company getCompany(Long companyId) throws ServiceException {
		try {
			Company company = companyDao.getCompany(companyId);
			return company;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	
	public Company getCompanyByName(String companyName) throws ServiceException {
		try {
			Company company = companyDao.getCompanyByName(companyName);
			return company;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public List<Company> getCompanyByUserId(Long userId) throws ServiceException {
		try {
			List<Company> companys = companyDao.getCompanyByUserId(userId);
			return companys;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public List<Company> getCompanyByEmployeeId(Long employeeId) throws ServiceException {
		try {
			List<Company> companys = companyDao.getCompanyByEmployeeId(employeeId);
			return companys;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}	
	public Pagination<Company> findManagedPagination(Long userId, String companyName, int pageIndex, int pageSize)throws ServiceException{
		Pagination<Company> pagination = new Pagination<Company>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			List<Company> companys = companyDao.getManagedPaginationItems(userId, companyName, CompanyInfo.DELFLAG_NO, pageIndex, pageSize);
			int count = companyDao.getManagedPaginationCount(userId, companyName, CompanyInfo.DELFLAG_NO);
			pagination.setItems(companys);
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
	public List<CompanyIndustry> getCompanyIndustry(Long companyId) throws ServiceException {
		try {
			List<CompanyIndustry> companyIndustry = companyDao.getCompanyIndustry(companyId);
			return companyIndustry;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
}
