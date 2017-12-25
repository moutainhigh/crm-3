package com.hefei.rms.salary.my.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.salary.dto.SalarySSDTO;
import com.hefei.api.rms.salary.util.IndividualIncomeMonthlyTaxDict;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.util.MoneyUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/my")
@SuppressWarnings("all")
public class MySalaryController {

	private Logger logger = Logger.getLogger(MySalaryController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response){
		try {
			SalarySSDTO salarySSDTO = salaryService.getSalaryAndSS(CommonParameterThreadLocal.getCurrentCompanyId(), CommonParameterThreadLocal.getEmployeeId(), CommonParameterThreadLocal.getUserId());
			request.setAttribute("salarySSDTO", salarySSDTO);
			
			double monthlyTotal = MoneyUtil.add(salarySSDTO.getMonthlyBaseSalary(), salarySSDTO.getMonthlyBonus());
			double afterSSAmount =  MoneyUtil.subtract(monthlyTotal, getSSTotal(salarySSDTO));
			request.setAttribute("afterSSAmount", afterSSAmount);
			
			Double ssBaseSalary = salarySSDTO.getSsBaseSalary();
			request.setAttribute("individualIncomeTaxRange", IndividualIncomeMonthlyTaxDict.get(ssBaseSalary));
			request.setAttribute("taxAmount", MoneyUtil.multiply(afterSSAmount, IndividualIncomeMonthlyTaxDict.calTax(afterSSAmount)));
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		
		return "salary/my/index" ;
	}
	private double getSSTotal(SalarySSDTO salarySSDTO ){
		double ssTotal = 0d;
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getYlaoInsurance());
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getYliaoInsurance());
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getGshInsurance());
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getSyuInsurance());
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getSyeInsurance());
		ssTotal = MoneyUtil.add(ssTotal, salarySSDTO.getGjjInsurance());
		
		return ssTotal;
	}
}
