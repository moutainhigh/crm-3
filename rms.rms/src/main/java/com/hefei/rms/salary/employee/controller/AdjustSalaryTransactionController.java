package com.hefei.rms.salary.employee.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/adjustSalaryTransaction")
@SuppressWarnings("all")
public class AdjustSalaryTransactionController {

	private Logger logger = Logger.getLogger(AdjustSalaryTransactionController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/toAdjustSalary",produces="text/plain;charset=UTF-8")
	public String toAdjustSalary(HttpServletRequest request, HttpServletResponse response,Long transactionId) {
		try{
			SalaryTransactionDTO salaryTransactionDTO = salaryService.getSalaryTransaction(transactionId);
			request.setAttribute("salaryTransactionDTO", salaryTransactionDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/pay/adjustSalaryTransaction" ;
	}
	
	/**
	 * 生成预发信息
	 * @param request
	 * @param response
	 * @param employeeStatus 员工状态 ：离职，在职。。。
	 * @return
	 */
	@RequestMapping(value="/saveAdjustSalary",produces="text/plain;charset=UTF-8")
	public String saveAdjustSalary(HttpServletRequest request, HttpServletResponse response, SalaryTransactionDTO salaryTransactionDTO, String remark) {
		try{
			salaryService.saveAdjustSalaryTransaction(salaryTransactionDTO, remark, CommonParameterThreadLocal.getUserId());
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/pay/adjustSalaryTransactionResult" ;
	}
	
}
