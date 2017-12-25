package com.hefei.rms.leave.company.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.leave.service.ILeaveService;

@Controller
@RequestMapping("/leave/company")
@SuppressWarnings("all")
public class CompanyManageController {

	private Logger logger = Logger.getLogger(CompanyManageController.class);
	
	@Autowired
	private ILeaveService leaveService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageIndex", 1);
		request.setAttribute("pageSize", Pagination.DEFAULT_PAGE_SIZE);
		return "/leave/company/index" ;
	}
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize
			, String employeeName,String cardNo, String leaveType, Long auditEmployeeId,String auditStatus, String startTimeStr, String endTimeStr) {
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			Date startTime = null;
			Date endTime = null;
			if(StringUtils.isNotEmpty(startTimeStr)){
				try {
					startTime = DateUtil.string2date(startTimeStr, DateUtil.FORMAT_YYYY_MM_DDHH);
				} catch (ParseException e) {
					logger.error(RequestThreadLocal.getTimer() + "error", e);
				}
			}
			if(StringUtils.isNotEmpty(endTimeStr)){
				try {
					endTime =DateUtil.string2date(endTimeStr, DateUtil.FORMAT_YYYY_MM_DDHH);
				} catch (ParseException e) {
					logger.error(RequestThreadLocal.getTimer() + "error", e);
				}
			}
			
			Pagination<LeaveDTO> pagination = leaveService.findLeave(employeeName, null, cardNo, CommonParameterThreadLocal.getCurrentCompanyId(), leaveType, auditEmployeeId, auditStatus, startTime, endTime, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "/leave/company/index" ;
	}
}
