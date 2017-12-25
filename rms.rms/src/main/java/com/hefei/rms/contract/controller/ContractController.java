package com.hefei.rms.contract.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.common.util.DateUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.contract.service.IContractService;

/**
 * 合同
 * @author dell
 *
 */
@Controller
@RequestMapping(value="/contract",produces="text/plain;charset=UTF-8")
public class ContractController {
	
	private Logger logger = Logger.getLogger(ContractController.class);
	
	@Autowired
	private IContractService contractService;
	
	@RequestMapping("create")
	public String listFile(HttpServletRequest request, HttpServletResponse response){
		return "contract/create";
	}
	
	@RequestMapping(value="/createContract",method=RequestMethod.POST)
	public String get(HttpServletRequest request) {
		try{
			Long companyId = Long.parseLong(request.getParameter("companyId"));
			Long employeeId = Long.parseLong(request.getParameter("employeeId"));
			String contractType = request.getParameter("contractType");
			String contractPics = request.getParameter("contractPics");
			Long auditUserId = Long.parseLong(request.getParameter("auditUserId"));
			String auditStatus = request.getParameter("auditStatus");
			String contractStartTimeString = request.getParameter("contractStartTime");
			Date contractStartTime = DateUtil.string2date(contractStartTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			String contractEndTimeString = request.getParameter("contractEndTime");
			Date contractEndTime = DateUtil.string2date(contractEndTimeString, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			
			ContractInfo info = new ContractInfo();
			info.setCompanyId(companyId);
			info.setEmployeeId(employeeId);
			info.setContractType(contractType);
			if(contractPics.length()>0){
			info.setContractPics(contractPics);
			} else {
				throw new Exception();
			}
			info.setAuditUserId(auditUserId);
			info.setAuditStatus(auditStatus);
			if(contractStartTime.compareTo(contractEndTime)>0){
			info.setContractStartTime(contractStartTime);
			info.setContractEndTime(contractEndTime);
			} else {
				throw new Exception();
			}
			contractService.createContract(info);
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "common/error";
	}
		return null ;
  }
}