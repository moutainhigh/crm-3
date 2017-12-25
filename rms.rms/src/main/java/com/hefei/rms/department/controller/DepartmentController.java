package com.hefei.rms.department.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.department.vo.DepartmentTreeNode;

@Controller
@RequestMapping(value="/department",produces="text/plain;charset=UTF-8")
public class DepartmentController {
	
	private Logger logger = Logger.getLogger(DepartmentController.class);
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String findDepartment(HttpServletRequest request) {
		try{
			CompanyInfo company = companyService.getCompany(CommonParameterThreadLocal.getUserId(), CommonParameterThreadLocal.getCurrentCompanyId());
			request.setAttribute("company", company);
			
			List<DepartmentTreeNode> treeNodes = departmentService.treeNode(CommonParameterThreadLocal.getCurrentCompanyId());
			String zTreeNodes = JacksonUtil.beanToJson(treeNodes);
			request.setAttribute("zTreeNodes", zTreeNodes);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "department/index";
	}
}
