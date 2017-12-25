package com.hefei.rms.salary.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/pay")
@SuppressWarnings("all")
public class PaySalaryController {

	private Logger logger = Logger.getLogger(PaySalaryController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Pagination<SalaryTransactionDTO> pagination = new Pagination<SalaryTransactionDTO>();
		pagination.setPageIndex(1);
		pagination.setPageSize(Pagination.DEFAULT_PAGE_SIZE);
		request.setAttribute("pagination", pagination);
		return "salary/pay/pay" ;
	}
	
	/**
	 * 生成预发信息
	 * @param request
	 * @param response
	 * @param employeeStatus 员工状态 ：离职，在职。。。
	 * @return
	 */
	@RequestMapping(value="/generatePayInfo",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String generatePayInfo(HttpServletRequest request, HttpServletResponse response, String employeeStatus) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String returnCode = salaryService.generatePayInfo(CommonParameterThreadLocal.getCurrentCompanyId(), employeeStatus);
			baseResponse.setReturnCode(returnCode);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize,String employeeStatus, String salaryPayStatus, String ssPayStatus, String cardNo, String employeeName) {
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			Pagination<SalaryPayTransactionDTO> pagination = salaryService.findSalaryAndSSPayHistory(CommonParameterThreadLocal.getCurrentCompanyId(), null, null, employeeStatus, salaryPayStatus, ssPayStatus, cardNo, employeeName, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "salary/pay/pay" ;
	}
	
	/**
	 * 发放工资
	 * @param employeeIds
	 * @return
	 */
	@RequestMapping(value="/paySalary",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String paySalary(HttpServletRequest request, HttpServletResponse response, String transactionIds) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String returnCode = salaryService.paySalary(CommonParameterThreadLocal.getCurrentCompanyId(), CommonParameterThreadLocal.getEmployeeId(), CommonParameterThreadLocal.getUserId(), transactionIds);
			baseResponse.setReturnCode(returnCode);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	/**
	 * 
	 * @param transactionIds 不是ss_transaction.id
	 * @return
	 */
	@RequestMapping(value="/paySS",produces="text/plain;charset=UTF-8")
	public String paySS(HttpServletRequest request, HttpServletResponse response, String transactionIds) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String returnCode = salaryService.paySS(CommonParameterThreadLocal.getCurrentCompanyId(), CommonParameterThreadLocal.getEmployeeId(), CommonParameterThreadLocal.getUserId(), transactionIds);
			baseResponse.setReturnCode(returnCode);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
	
}
