package com.hefei.rms.employee.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.user.reg.manager.IRegisterManager;
import com.hefei.api.user.reg.manager.impl.RegisterManager;
import com.hefei.api.user.reg.vo.RegisterRequestByEmailMobile;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.MoneyUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.employee.dao.IEmployeeDao;
import com.hefei.rms.employee.po.Employee;
import com.hefei.rms.employee.po.EmployeeCompany;
import com.hefei.rms.employee.po.EmployeePosition;
import com.hefei.rms.employee.po.UserEmployee;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.rms.salary.po.Salary;
import com.hefei.rms.salary.service.ISalaryService;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.po.SSRate;
import com.hefei.rms.socialsecurity.service.ISSRateService;
import com.hefei.rms.socialsecurity.service.ISocialsecurityService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.DaoException;
import com.hefei.service.framework.exception.ServiceException;
import com.hefei.user.api.info.manager.IUserinfoManager;
import com.hefei.user.api.info.manager.impl.UserinfoManager;
import com.hefei.user.api.info.vo.UserInfo;

@Service
public class EmployeeService implements IEmployeeService {

	private static final Logger logger = Logger.getLogger(EmployeeService.class);
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private ISocialsecurityService socialsecurityService;
	
	@Autowired
	private ISSRateService ssRateService;
	
	@Autowired
	private ICompanyService companyService;
	
	private IRegisterManager registerManager = new RegisterManager();
	private IUserinfoManager userinfoManager = new UserinfoManager();
	
	public Employee createEmployee(Employee employee) throws ServiceException {
		if(employee == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Date now = Calendar.getInstance().getTime();
			employee.setCreateTime(now);
			employee.setUpdateTime(now);
			employee = employeeDao.saveEmployee(employee);
			return employee;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	

	@Override
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws ServiceException {
		if(employeeInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			Company company = companyService.getCompany(companyId);
			Date now = Calendar.getInstance().getTime();
			
			RegisterRequestByEmailMobile registerRequestByEmailMobile = new RegisterRequestByEmailMobile();
			registerRequestByEmailMobile.setEmail(employeeInfo.getEmail());
			registerRequestByEmailMobile.setMobile(employeeInfo.getMobile());
			registerRequestByEmailMobile.setPlainPassword("123456");
			registerRequestByEmailMobile.setType(UserInfo.TYPE_COMMON);
			registerRequestByEmailMobile.setCompanyId(employeeInfo.getCompanyId());
			registerRequestByEmailMobile.setIdNo(employeeInfo.getIdNo());
			registerRequestByEmailMobile.setName(employeeInfo.getName());
			registerRequestByEmailMobile.setSex(employeeInfo.getSex());
			registerRequestByEmailMobile.setBirthday(employeeInfo.getBirthday());
			registerRequestByEmailMobile.setLiveProvinceCode(employeeInfo.getLiveProvinceCode());
			registerRequestByEmailMobile.setLiveCityCode(employeeInfo.getLiveCityCode());
			registerRequestByEmailMobile.setLiveAreaCode(employeeInfo.getLiveAreaCode());
			registerRequestByEmailMobile.setLiveAddress(employeeInfo.getLiveAddress());
			registerRequestByEmailMobile.setHukouType(employeeInfo.getHukouType());
			registerRequestByEmailMobile.setHukouProvinceCode(employeeInfo.getHukouProvinceCode());
			registerRequestByEmailMobile.setHukouCityCode(employeeInfo.getHukouCityCode());
			registerRequestByEmailMobile.setHukouAreaCode(employeeInfo.getHukouAreaCode());
			Long userId = registerManager.companyAddEmployee(registerRequestByEmailMobile);
			
			Employee employee = new Employee();
			employee = createEmployee(employee);
			createEmployeeLogin(userId, employee.getId());
			
			EmployeeCompany employeeCompany = new EmployeeCompany();
			employeeCompany.setCompanyId(employeeInfo.getCompanyId());
			employeeCompany.setEmployeeId(employee.getId());
			employeeCompany.setCardNo(employeeInfo.getCardNo());
			employeeCompany.setEmail(employeeInfo.getEmail());
			employeeCompany.setMobile(employeeInfo.getMobile());
			employeeCompany.setSuperior(employeeInfo.getSuperior());
			employeeCompany.setSuperiorEmployeeName(employeeInfo.getSuperiorEmployeeName());
			employeeCompany.setStatus(employeeInfo.getStatus());
			employeeCompany.setOnBoardDate(now);
			employeeCompany.setLeaveDate(null);
			employeeCompany.setCreateTime(now);
			employeeCompany.setUpdateTime(now);
			employeeCompany = addEmployeeToCompany(employeeCompany);
			
			Salary salary = new Salary();
			salary.setCompanyId(companyId);
			salary.setEmployeeId(employee.getId());
			salary.setEmployeeName(employeeInfo.getName());
			salary.setMonthlyBaseSalary(salaryInfo.getMonthlyBaseSalary());
			salary.setMonthlyBonus(salaryInfo.getMonthlyBonus());
			salary.setQuarterlyBonus(salaryInfo.getQuarterlyBonus());
			salary.setYearlyBonus(salaryInfo.getYearlyBonus());
			
			salary.setDelFlag(SalaryDTO.DEL_FLAG_TRUE);
			salaryService.saveSalary(salary);
			
			EmployeeSS employeeSS = new EmployeeSS();
			employeeSS.setCompanyId(companyId);
			employeeSS.setEmployeeId(employee.getId());
			employeeSS.setEmployeeName(employeeInfo.getName());
			employeeSS.setSsBaseSalary(MoneyUtil.add(salary.getMonthlyBaseSalary(), salary.getMonthlyBonus()));
			
			SSRate ssRate =  null;
			if(company.getProvinceCode() != null && company.getCityCode() != null){
				ssRate = ssRateService.getSSRate(company.getProvinceCode(), company.getCityCode());
			}else if(employeeInfo.getLiveProvinceCode() != null && employeeInfo.getLiveCityCode() != null){
				ssRate = ssRateService.getSSRate(employeeInfo.getLiveProvinceCode(), employeeInfo.getLiveCityCode());
			}
			if(ssRate != null){
				employeeSS.setGjjAdd(0d);
				employeeSS.setGjjComRate(ssRate.getGjjComRate());
				employeeSS.setGjjComCash(MoneyUtil.multiply(employeeSS.getGjjComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setGjjPersonalRate(ssRate.getGjjPersonalRate());
				employeeSS.setGjjPersonalCash(MoneyUtil.multiply(employeeSS.getGjjPersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setGjjInsurance(MoneyUtil.add(employeeSS.getGjjComCash(), employeeSS.getGjjPersonalCash()));
				
				employeeSS.setGshComRate(ssRate.getGshComRate());
				employeeSS.setGshComCash(MoneyUtil.multiply(employeeSS.getGshComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setGshPersonalRate(ssRate.getGshPersonalRate());
				employeeSS.setGshPersonalCash(MoneyUtil.multiply(employeeSS.getGshPersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setGshInsurance(MoneyUtil.add(employeeSS.getGshComCash(), employeeSS.getGshPersonalCash()));
				
				employeeSS.setSyeComRate(ssRate.getSyeComRate());
				employeeSS.setSyeComCash(MoneyUtil.multiply(employeeSS.getSyeComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setSyePersonalRate(ssRate.getSyePersonalRate());
				employeeSS.setSyePersonalCash(MoneyUtil.multiply(employeeSS.getSyePersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setSyeInsurance(MoneyUtil.add(employeeSS.getSyeComCash(), employeeSS.getSyePersonalCash()));
				
				employeeSS.setSyuComRate(ssRate.getSyuComRate());
				employeeSS.setSyuComCash(MoneyUtil.multiply(employeeSS.getSyuComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setSyuPersonalRate(ssRate.getSyuPersonalRate());
				employeeSS.setSyuPersonalCash(MoneyUtil.multiply(employeeSS.getSyuPersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setSyuInsurance(MoneyUtil.add(employeeSS.getSyuComCash(), employeeSS.getSyuPersonalCash()));
				
				
				employeeSS.setYlaoComRate(ssRate.getYlaoComRate());
				employeeSS.setYlaoComCash(MoneyUtil.multiply(employeeSS.getYlaoComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setYlaoPersonalRate(ssRate.getYlaoPersonalRate());
				employeeSS.setYlaoPersonalCash(MoneyUtil.multiply(employeeSS.getYlaoPersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setYlaoInsurance(MoneyUtil.add(employeeSS.getYlaoComCash(), employeeSS.getYlaoPersonalCash()));
				
				employeeSS.setYliaoComRate(ssRate.getYliaoComRate());
				employeeSS.setYliaoComCash(MoneyUtil.multiply(employeeSS.getYliaoComRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setYliaoPersonalRate(ssRate.getYliaoPersonalRate());
				employeeSS.setYliaoPersonalCash(MoneyUtil.multiply(employeeSS.getYliaoPersonalRate(), employeeSS.getSsBaseSalary()));
				employeeSS.setYliaoInsurance(MoneyUtil.add(employeeSS.getYliaoComCash(), employeeSS.getYliaoPersonalCash()));
			}else{
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
			}
			
			
			employeeSS.setSsCard(null);
			employeeSS.setCreator(null);
			socialsecurityService.createEmployeeSS(employeeSS);
			List<Long> list = new ArrayList<Long>();
			list.add(positionId);
			grantPosition(employee.getId(), list);
			return employeeInfo;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	public EmployeeCompany getEmployeeCompany(Long id) throws ServiceException{
		if(id == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeCompany employeeCompany = employeeDao.getEmployeeCompany(id);
			return employeeCompany;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeCompany getEmployeeCompany(Long companyId, Long employeeId) throws ServiceException{
		if(companyId == null || employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeCompany employeeCompany = employeeDao.getEmployeeCompany(companyId, employeeId);
			return employeeCompany;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public EmployeeCompany addEmployeeToCompany(EmployeeCompany employeeCompany)  throws ServiceException{
		try{
			employeeCompany = employeeDao.saveEmployeeCompany(employeeCompany);
			return employeeCompany;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " saveEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	
	public List<EmployeePosition> grantPosition(Long employeeId, List<Long> positionIds)  throws ServiceException{
		List<EmployeePosition> list = new ArrayList<EmployeePosition>();
		try {
			Date now = Calendar.getInstance().getTime();
			for(int i=0; i<positionIds.size(); i++){
				Long positionId = positionIds.get(i);
				if(positionId == null || positionId == 0){
					continue;
				}
				EmployeePosition employeePosition = new EmployeePosition();
				employeePosition.setEmployeeId(employeeId);
				employeePosition.setPositionId(positionId);
				employeePosition.setDelFlag(EmployeeDepartPositionDTO.POSITION_DEL_FLAG_NO);
				employeePosition.setCreateTime(now);
				employeePosition.setUpdateTime(now);
				
				employeePosition = employeeDao.saveEmployeePosition(employeePosition);
				list.add(employeePosition);
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
		return list;
	}
	
	public UserEmployee createEmployeeLogin(Long userId, Long employeeId) throws ServiceException{
		try {
			Date now = Calendar.getInstance().getTime();
			UserEmployee ue = new UserEmployee();
			ue.setEmployeeId(employeeId);
			ue.setUserId(userId);
			ue.setDelFlag(UserEmployee.DEL_FLAG_NO);
			ue.setCreator(userId);
			ue.setUpdater(userId);
			ue.setCreateTime(now);
			ue.setUpdateTime(now);
			employeeDao.saveUserEmployee(ue);
			return ue;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		}
	}
	@Override
	public void updateEmployee(EmployeeInfo employeeInfo) throws ServiceException {
		if(employeeInfo == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			UserInfo user = new UserInfo();
			user.setId(employeeInfo.getUserId());
			BeanUtils.copyProperties(employeeInfo, user);
			userinfoManager.updateUser(user);
			
			Date now = Calendar.getInstance().getTime();
			Employee employee = new Employee();
			employee.setId(employeeInfo.getEmployeeId());
			employee.setUpdateTime(now);
			employee.setUpdater(employeeInfo.getUpdater());
			employeeDao.updateEmployee(employee);
			
			
			EmployeeCompany employeeCompany = new EmployeeCompany();
			BeanUtils.copyProperties(employeeInfo, employeeCompany);
			employeeCompany.setUpdateTime(now);
			employeeDao.updateEmployeeCompany(employeeCompany);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " updateEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	
	public Pagination<EmployeeInfo> findPagination(String cardNo, String sexs, String statuses, Long companyId, Integer pageIndex, Integer pageSize) throws ServiceException{
		Pagination<EmployeeInfo> pagination = new Pagination<EmployeeInfo>();
		pagination.setPageIndex(pageIndex);
		pagination.setPageSize(pageSize);
		try {
			
			List<String> employeeSex = new ArrayList<String>();
			if(StringUtils.isNotBlank(sexs)){
				String[] sexStr = sexs.split(",");
				for(String sex: sexStr){
					if(StringUtils.isNotBlank(sex)){
						employeeSex.add(sex);
					}
				}
			}
			
			List<String> employeeCompanyStatus = new ArrayList<String>();
			if(StringUtils.isNotBlank(statuses)){
				String[] statusStr = statuses.split(",");
				for(String status: statusStr){
					if(StringUtils.isNotBlank(status)){
						employeeCompanyStatus.add(status);
					}
				}
			}
			
			List<EmployeeInfo> employees = employeeDao.getEmployeePaginationItems(cardNo, employeeSex, employeeCompanyStatus, companyId, pageIndex, pageSize);
			int count = employeeDao.getEmployeePaginationCount(cardNo, employeeSex, employeeCompanyStatus, companyId);
			pagination.setItems(employees);
			pagination.setTotalCount(count);
			return pagination;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch(Exception e){
			logger.error(RequestThreadLocal.getLoggerStr() + " error", e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	private EmployeeInfo getEmployee(Long id) throws ServiceException {
		if(id == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeInfo employee = employeeDao.getEmployee(id);
			return employee;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeInfo getByIdNo(String idNo) throws ServiceException{
		try {
			EmployeeInfo employee = employeeDao.getEmployeeByIdNo(idNo);
			return employee;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeInfo getEmployeeInfo(Long employeeId) throws ServiceException {
		if(employeeId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeInfo employee = employeeDao.getEmployee(employeeId);
			return employee;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeInfo getEmployeeInfoByUserId(Long userId) throws ServiceException {
		if(userId == null){
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			EmployeeInfo employee = employeeDao.getEmployeeByUserId(userId);
			return employee;
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorCode());
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " findEmployee error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeInfo getEmployeeInfo(Long companyId, Long employeeId) throws ServiceException{
		EmployeeInfo employeeInfo = getEmployeeInfo(employeeId);
		if(employeeInfo != null){
			EmployeeCompany employeeCompany = getEmployeeCompany(companyId, employeeId);
			employeeInfo.setCompanyId(companyId);
			employeeInfo.setCardNo(employeeCompany.getCardNo());
			employeeInfo.setEmail(employeeCompany.getEmail());
			employeeInfo.setMobile(employeeCompany.getMobile());
			employeeInfo.setLeaveDate(employeeCompany.getLeaveDate());
			employeeInfo.setOnboardDate(employeeCompany.getOnBoardDate());
			employeeInfo.setStatus(employeeCompany.getStatus());
			employeeInfo.setSuperior(employeeCompany.getSuperior());
			employeeInfo.setSuperiorEmployeeName(employeeCompany.getSuperiorEmployeeName());
		}
		return employeeInfo;
	}

	@Override
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws ServiceException {
		try {
			return employeeDao.getEmployeeDepartPosition(companyId, employeeId);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
	}


	@Override
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId,Long departmentId, Long positionId) throws ServiceException {
		try {
			employeeDao.updateEmployeeDepartPosition(companyId, employeeId, departmentId, positionId);
		} catch (DaoException e) {
			logger.error(RequestThreadLocal.getLoggerStr() + " error ",e);
			throw new ServiceException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
}
