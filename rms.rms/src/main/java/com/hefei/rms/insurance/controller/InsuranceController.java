package com.hefei.rms.insurance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.rms.insurance.service.IInsuranceService;

/**
 * 保险
 * @author 
 *
 */

@Controller
@RequestMapping("/insurance")
@SuppressWarnings("all")
public class InsuranceController {
	
	private Logger logger = Logger.getLogger(InsuranceController.class);
	
	@Autowired
	private IInsuranceService insuranceService;
	
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8")
	public String get() {
		return null ;
	}
}