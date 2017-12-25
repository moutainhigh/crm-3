package com.hefei.rms.behavior.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.rms.behavior.service.IBehaviorService;



/**
 * 员工表现
 * @author dell
 *
 */
@Controller
@RequestMapping("/behavior")
@SuppressWarnings("all")
public class BehaviorController {
	
	private Logger logger = Logger.getLogger(BehaviorController.class);
	
	@Autowired
	private IBehaviorService behaviorService;
	
	@RequestMapping(value="/login",produces="text/plain;charset=UTF-8")
	public String get() {
		return null ;
	}
}