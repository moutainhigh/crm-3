package com.hefei.rms.contract.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.contract.manager.IContractManager;
import com.hefei.api.rms.contract.manager.impl.ContractManager;
import com.hefei.api.rms.contract.vo.ContractInfo;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

@Controller
@RequestMapping(value="/contract",produces="text/plain;charset=UTF-8")
public class ContractSearchController {
	IContractManager contractManager = new ContractManager();
	private Logger logger = Logger.getLogger(ContractController.class);
	
	@RequestMapping("find")
	public String findContract(HttpServletRequest request,HttpServletResponse response) {
		try{
			List<ContractInfo> contractInfo = contractManager.findContract(null);
			request.setAttribute("contractInfo", contractInfo);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "create/list";
	}
}
