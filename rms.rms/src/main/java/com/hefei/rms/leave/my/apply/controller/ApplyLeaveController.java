package com.hefei.rms.leave.my.apply.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.leave.service.ILeaveService;

/**
 * 请假
 * @author 
 *
 */
@Controller
@RequestMapping("/leave/myapply")
@SuppressWarnings("all")
public class ApplyLeaveController {
	
	private Logger logger = Logger.getLogger(ApplyLeaveController.class);
	
	@Autowired
	private ILeaveService leaveService;
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize){
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			Pagination<LeaveDTO> pagination = leaveService.findLeave(null, CommonParameterThreadLocal.getEmployeeId(), null, CommonParameterThreadLocal.getCurrentCompanyId(), null, null,null, null, null, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "/leave/my/apply/index" ;
	}
	
	@RequestMapping(value="/toAdd",produces="text/plain;charset=UTF-8")
	public String toAdd(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("companyName", CommonParameterThreadLocal.getCurrentCompanyName());
		return "/leave/my/apply/apply" ;
	}
	
	@RequestMapping(value="/add",produces="text/plain;charset=UTF-8")
	public String add(HttpServletRequest request, HttpServletResponse response, LeaveDTO leaveDTO, String startTimeStr, String endTimeStr) {
		try{
			if(StringUtils.isEmpty(startTimeStr)){
				
			}
			if(StringUtils.isEmpty(endTimeStr)){
				
			}
			
			leaveDTO.setStartTime(DateUtil.string2date(startTimeStr, DateUtil.FORMAT_YYYY_MM_DDHH));
			leaveDTO.setEndTime(DateUtil.string2date(endTimeStr, DateUtil.FORMAT_YYYY_MM_DDHH));
			
			leaveDTO.setCompanyId(CommonParameterThreadLocal.getCurrentCompanyId());
			leaveDTO.setEmployeeId(CommonParameterThreadLocal.getEmployeeId());
			leaveDTO.setEmployeeName(CommonParameterThreadLocal.getUserCookie().getNickname());
			leaveService.applyLeave(leaveDTO);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "/leave/my/apply/apply" ;
		} catch (ParseException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "/leave/my/apply/apply" ;
		}
		return "/leave/my/apply/index" ;
	}
}