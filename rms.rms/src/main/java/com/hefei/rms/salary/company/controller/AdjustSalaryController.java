package com.hefei.rms.salary.company.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/company/employee")
@SuppressWarnings("all")
public class AdjustSalaryController {

	private Logger logger = Logger.getLogger(AdjustSalaryController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/adjustSalary",produces="text/plain;charset=UTF-8")
	public String adjustSalary(HttpServletRequest request, HttpServletResponse response,Long employeeId) {
		try{
			SalarySSDTO salaryDTO = salaryService.getSalaryAndSS(CommonParameterThreadLocal.getCurrentCompanyId(), employeeId, CommonParameterThreadLocal.getUserId());
			request.setAttribute("salaryDTO", salaryDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/company/adjustSalary" ;
	}
	
	
	@RequestMapping(value="/saveAdjustSalary",produces="text/plain;charset=UTF-8")
	public String saveAdjustSalary(HttpServletRequest request, HttpServletResponse response, SalaryDTO salaryDTO, String effectTimeStr, String remark) {
		try {
			Date effectTime = null;
			if(StringUtils.isEmpty(effectTimeStr)){
				effectTime = DateUtil.string2date(effectTimeStr, DateUtil.FORMAT_YYYY_MM_DD);
			}
			salaryDTO.setCompanyId(CommonParameterThreadLocal.getCurrentCompanyId());
			salaryService.saveAdjustSalary(salaryDTO, effectTime, remark, CommonParameterThreadLocal.getUserId());
		} catch (ParseException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/company/adjustSalaryResult" ;
	}
}
