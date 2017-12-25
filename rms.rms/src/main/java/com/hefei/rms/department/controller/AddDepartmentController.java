package com.hefei.rms.department.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.department.service.IDepartmentService;

@Controller
@RequestMapping(value="/department",produces="text/plain;charset=UTF-8")
public class AddDepartmentController {
	
	private Logger logger = Logger.getLogger(AddDepartmentController.class);
	
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Long departmentId){
		try{
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), CommonParameterThreadLocal.getCurrentCompanyId());
			request.setAttribute("company", company);
			
			if(departmentId != null && departmentId != 0){
				DepartmentInfo parentDepartment =  departmentService.getDepartment(departmentId);
				request.setAttribute("parentDepartment", parentDepartment);
			}
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "department/departmentAdd";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request, DepartmentInfo departmentInfo) {
		try{
			Long companyId = CommonParameterThreadLocal.getCurrentCompanyId();
			departmentInfo.setCompanyId(companyId);
			if(departmentInfo.getParentId() == null){
				departmentInfo.setParentId(0L);
			}
			departmentService.createDepartment(departmentInfo);
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "common/error";
		}
		return "department/departmentAddSuccess" ;
	}
}
