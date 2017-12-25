package com.hefei.rms.qualification.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.rms.qualification.service.IQualificationService;

/**
 * 权限
 * @author 
 *
 */
@Controller
@RequestMapping("/qualification")
@SuppressWarnings("all")
public class QualificationController {
	
	private Logger logger = Logger.getLogger(QualificationController.class);
	
	@Autowired
	private IQualificationService qualificationService;
	
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8")
	public String get() {
		return null ;
	}
}