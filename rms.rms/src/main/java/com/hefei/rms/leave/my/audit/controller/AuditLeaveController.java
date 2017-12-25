package com.hefei.rms.leave.my.audit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.rms.leave.dto.LeaveDTO;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.leave.service.ILeaveService;

/**
 * 请假
 * @author 
 *
 */
@Controller
@RequestMapping("/leave/myaudit")
@SuppressWarnings("all")
public class AuditLeaveController {
	
	private Logger logger = Logger.getLogger(AuditLeaveController.class);
	
	@Autowired
	private ILeaveService leaveService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response, Integer pageIndex, Integer pageSize, String auditStatus){
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			if(StringUtils.isBlank(auditStatus)){
				auditStatus = LeaveDTO.AUDIT_STATUS_TODO;
			}
			Pagination<LeaveDTO> pagination = leaveService.findLeave(null, null, null, CommonParameterThreadLocal.getCurrentCompanyId(), null, CommonParameterThreadLocal.getEmployeeId(), auditStatus, null, null, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "/leave/my/audit/index" ;
	}
	
	
	@RequestMapping(value="/audit",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String audit(HttpServletRequest request, HttpServletResponse response, String auditStatus, Long leaveId) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			leaveService.audit(CommonParameterThreadLocal.getEmployeeId(), leaveId, auditStatus);
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
}