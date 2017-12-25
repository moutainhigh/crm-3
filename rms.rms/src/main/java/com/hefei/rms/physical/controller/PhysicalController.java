package com.hefei.rms.physical.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.rms.physical.service.IPhysicalService;

/**
 * 体检
 * @author dell
 *
 */
@Controller
@RequestMapping("/physical")
@SuppressWarnings("all")
public class PhysicalController {
	
	private Logger logger = Logger.getLogger(PhysicalController.class);
	
	@Autowired
	private IPhysicalService physicalService;
	
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8")
	public String get() {
		return null ;
	}
}