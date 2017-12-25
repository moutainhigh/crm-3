package com.hefei.rms.salary.company.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/company/employee")
@SuppressWarnings("all")
public class AdjustSSController {

	private Logger logger = Logger.getLogger(AdjustSSController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/adjustSS",produces="text/plain;charset=UTF-8")
	public String adjustSS(HttpServletRequest request, HttpServletResponse response,Long employeeId) {
		try{
			EmployeeSSDTO employeeSSDTO = salaryService.getEmployeeSSDTO(CommonParameterThreadLocal.getCurrentCompanyId(), employeeId);
			request.setAttribute("employeeSSDTO", employeeSSDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/company/adjustSS" ;
	}
	
	@RequestMapping(value="/saveAdjustSS",produces="text/plain;charset=UTF-8")
	public String savAdjustSS(HttpServletRequest request, HttpServletResponse response,EmployeeSSDTO employeeSSDTO) {
		try{
			employeeSSDTO.setCompanyId(CommonParameterThreadLocal.getCurrentCompanyId());
			salaryService.savAdjustSS(employeeSSDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/company/adjustSSResult" ;
	}
}
