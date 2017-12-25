package com.hefei.rms.recruit.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.rms.recruit.service.IRecruitService;

/**
 * 招聘
 * @author dell
 *
 */
@Controller
@RequestMapping("/recruit")
@SuppressWarnings("all")
public class RecruitController {
	
	private Logger logger = Logger.getLogger(RecruitController.class);
	
	@Autowired
	private IRecruitService recruitService;
	
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8")
	public String get() {
		return null ;
	}
}