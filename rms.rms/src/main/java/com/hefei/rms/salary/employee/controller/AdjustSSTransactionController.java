package com.hefei.rms.salary.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSTransactionDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/adjustSSTransaction")
@SuppressWarnings("all")
public class AdjustSSTransactionController {

	private Logger logger = Logger.getLogger(AdjustSSTransactionController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/toAdjustSS",produces="text/plain;charset=UTF-8")
	public String toAdjustSS(HttpServletRequest request, HttpServletResponse response,Long ssTransactionId) {
		try{
			EmployeeSSTransactionDTO employeeSSTransactionDTO = salaryService.getEmployeeSSTransaction(ssTransactionId);
			request.setAttribute("employeeSSTransactionDTO", employeeSSTransactionDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		
		return "salary/pay/adjustSSTransaction" ;
	}
	
	/**
	 * 生成预发信息
	 * @param request
	 * @param response
	 * @param employeeStatus 员工状态 ：离职，在职。。。
	 * @return
	 */
	@RequestMapping(value="/saveAdjustSS",produces="text/plain;charset=UTF-8")
	public String saveAdjustSS(HttpServletRequest request, HttpServletResponse response, EmployeeSSTransactionDTO employeeSSTransactionDTO, String remark) {
		try{
			salaryService.saveAdjustEmployeeSSTransaction(employeeSSTransactionDTO, remark, CommonParameterThreadLocal.getUserId());
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/pay/adjustSSTransactionResult" ;
	}
	
}
