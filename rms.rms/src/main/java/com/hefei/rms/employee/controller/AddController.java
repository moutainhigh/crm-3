package com.hefei.rms.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeCompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.employee.service.IEmployeeService;


/**
 * 添加员工信息
 * @author yinzg
 *
 */
@Controller
@RequestMapping("/employee")
@SuppressWarnings("all")
public class AddController {

	private Logger logger = Logger.getLogger(AddController.class);
	
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value="/toAdd",produces="text/plain;charset=UTF-8")
	public String toAdd(HttpServletRequest request, HttpServletResponse response){
		try {
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), CommonParameterThreadLocal.getCurrentCompanyId());
			request.setAttribute("company", company);
			
			request.setAttribute("departments", departmentService.getDepartmentsByCompany(company.getId()));
			
			Pagination<EmployeeInfo> pagination = employeeService.findEmployeePagination(null, null, EmployeeCompanyInfo.STATUS_ONBOARD, CommonParameterThreadLocal.getCurrentCompanyId(), 1, 1000);
			if(pagination != null && pagination.getTotalCount() >0){
				request.setAttribute("superviorList", pagination.getItems());
			}
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		
		return "employee/add";
	}
	@RequestMapping(value="/add",produces="text/plain;charset=UTF-8")
	public String add(HttpServletRequest request, HttpServletResponse response, SalaryDTO salaryInfo, EmployeeInfo employeeInfo,Long departmentId, Long positionId,
			String birthdayStr, String provinceCode,String cityCode,String countyCode, String provinceCode1,String cityCode1,String countyCode1){
		try {
			if(StringUtils.isEmpty(employeeInfo.getIdNo())){
				request.setAttribute("errorMsg", "身份证不能为空");
				return "employee/add";
			}
			if(StringUtils.isEmpty(employeeInfo.getName())){
				request.setAttribute("errorMsg", "姓名不能为空");
				return "employee/add";
			}
			if(StringUtils.isEmpty(employeeInfo.getEmail())){
				request.setAttribute("errorMsg", "邮箱不能为空");
				return "employee/add";
			}
			if(StringUtils.isEmpty(employeeInfo.getMobile())){
				request.setAttribute("errorMsg", "手机号码不能为空");
				return "employee/add";
			}
			if(StringUtils.isNotEmpty(birthdayStr)){
				employeeInfo.setBirthday(DateUtil.string2date(birthdayStr, DateUtil.FORMAT_YYYY_MM_DD));
			}
			
			Long companyId = CommonParameterThreadLocal.getCurrentCompanyId();
			Long userId = CommonParameterThreadLocal.getUserId();
			employeeInfo.setCompanyId(companyId);
			employeeInfo.setUserId(userId);
			employeeInfo.setLiveProvinceCode(provinceCode);
			employeeInfo.setLiveCityCode(cityCode);
			employeeInfo.setLiveAreaCode(countyCode);
			
			employeeInfo.setHukouProvinceCode(provinceCode1);
			employeeInfo.setHukouCityCode(cityCode1);
			employeeInfo.setHukouAreaCode(countyCode1);
			employeeService.createEmployee(salaryInfo, employeeInfo, companyId, departmentId, positionId);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getRequestStr() + " error ",e);
			request.setAttribute("errorMsg", e.getErrorCode());
			return "employee/add";
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr() + " add error ",e);
			request.setAttribute("errorMsg", "系统繁忙");
			return "employee/add";
		}
		
		return "employee/addResult";
	}
}
