package com.hefei.rms.position.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.position.service.IPositionService;


/**
 * 员工岗位
 * @author dell
 *
 */
@Controller
@RequestMapping(value="/position",produces="text/plain;charset=UTF-8")
public class AddPositionController {
	
	private Logger logger = Logger.getLogger(AddPositionController.class);
	
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Long departmentId){
		try{
			DepartmentInfo department =  departmentService.getDepartment(departmentId);
			request.setAttribute("department", department);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "position/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request, PositionInfo positionInfo) {
		try{
			positionService.createPosition(positionInfo);
			
			request.setAttribute("departmentId", positionInfo.getDepartmentId());
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "position/add";
		}
		return "position/addResult";
	}
}