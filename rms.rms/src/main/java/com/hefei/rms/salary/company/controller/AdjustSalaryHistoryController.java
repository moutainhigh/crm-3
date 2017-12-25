package com.hefei.rms.salary.company.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.api.rms.salary.dto.SalaryHistoryDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.salary.service.ISalaryService;

@Controller
@RequestMapping("/salary/adjusthistory/")
@SuppressWarnings("all")
public class AdjustSalaryHistoryController {

	private Logger logger = Logger.getLogger(AdjustSalaryHistoryController.class);
	
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response,Long employeeId) {
		
		Pagination<SalaryHistoryDTO> pagination = new Pagination<SalaryHistoryDTO>();
		pagination.setPageIndex(1);
		pagination.setPageSize(Pagination.DEFAULT_PAGE_SIZE);
		request.setAttribute("pagination", pagination);
		return "salary/company/adjustSalaryHistory" ;
	}
	
	
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize, String cardNo, String employeeName) {
		try{	
			Pagination<SalaryHistoryDTO> pagination = salaryService.findSalaryAdjustHistory(CommonParameterThreadLocal.getCurrentCompanyId(), null, null,cardNo, employeeName, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("employeeName", employeeName);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}
		return "salary/company/adjustSalaryHistory" ;
	}
}
