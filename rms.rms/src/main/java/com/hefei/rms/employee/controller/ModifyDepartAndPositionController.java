package com.hefei.rms.employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.employee.manager.IEmployeeManager;
import com.hefei.api.rms.employee.manager.impl.EmployeeManager;
import com.hefei.api.rms.employee.vo.EmployeeCompanyInfo;
import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.common.util.DateUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.employee.service.IEmployeeService;

/**
 * 修改员工信息
 * @author yinzg
 *
 */
@Controller
@RequestMapping("/employee")
@SuppressWarnings("all")
public class ModifyDepartAndPositionController {

	private Logger logger = Logger.getLogger(ModifyDepartAndPositionController.class);
	
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value="/modifyDepartAndPosition",produces="text/plain;charset=UTF-8")
	public String modifyDepartAndPosition(HttpServletRequest request, HttpServletResponse response, Long employeeId){
		try {
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), CommonParameterThreadLocal.getCurrentCompanyId());
			request.setAttribute("company", company);
			
			request.setAttribute("departments", departmentService.getDepartmentsByCompany(company.getId()));
			
			request.setAttribute("employeeId", employeeId);
			request.setAttribute("companyId", CommonParameterThreadLocal.getCurrentCompanyId());
			
			
			List<EmployeeDepartPositionDTO>  edpList = employeeService.getEmployeeDepartPosition(CommonParameterThreadLocal.getCurrentCompanyId(), employeeId);
			if(edpList != null && edpList.size() > 0){
				request.setAttribute("employeeDepartPosition", edpList.get(0));
			}
			
			EmployeeInfo employee = employeeService.getEmployee(employeeId);
			request.setAttribute("employee", employee);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		
		return "employee/modifyDepartAndPosition";
	}
	
	@RequestMapping(value="/saveDepartAndPosition",produces="text/plain;charset=UTF-8")
	public String saveDepartAndPosition(HttpServletRequest request, HttpServletResponse response, Long employeeId, Long departmentId, Long positionId, 
			Long selectedDepartmentValue,Long selectedPositionValue){
		try {
			if(selectedPositionValue == null){
				employeeService.grantEmployeePosition(employeeId, positionId.toString());
			}else{
				employeeService.updateEmployeeDepartPosition(CommonParameterThreadLocal.getCurrentCompanyId(), employeeId, selectedDepartmentValue, positionId);
			}
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getRequestStr() + " error ",e);
			request.setAttribute("errorMsg", e.getErrorCode());
			return "employee/modifyDepartAndPosition";
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getRequestStr() + " add error ",e);
			request.setAttribute("errorMsg", "系统繁忙");
			return "employee/modifyDepartAndPosition";
		}
		
		return "employee/modifyDepartAndPositionResult";
	}
}
