package com.hefei.rms.employee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.employee.service.IEmployeeService;

/**
 * 员工信息查询页显示
 * @author yinzg
 *
 */
@Controller
@RequestMapping("/employee")
@SuppressWarnings("all")
public class EmployeeController {
	
	private Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value="/index",produces="text/plain;charset=UTF-8")
	public String index(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageIndex", 1);
		request.setAttribute("pageSize", Pagination.DEFAULT_PAGE_SIZE);
		return "employee/index";
	}
	
	@RequestMapping(value="/search",produces="text/plain;charset=UTF-8")
	public String search(HttpServletRequest request, HttpServletResponse response, String cardNo, String sex, String status, Integer pageIndex, Integer pageSize){
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("cardNo", cardNo);
			
			Pagination<EmployeeInfo> pagination = employeeService.findEmployeePagination(cardNo, sex, status, CommonParameterThreadLocal.getCurrentCompanyId(), pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "employee/index";
	}
	
	@RequestMapping(value="/getInfoByIdNo",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getInfoByIdNo(HttpServletRequest request, HttpServletResponse response, String idNo){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			if(StringUtils.isNotBlank(idNo)){
				 Map<String, Object> map= new HashMap<String, Object>();
				 
				 EmployeeInfo employeeInfo = employeeService.getEmployeeByIdNo(idNo);
				 if(employeeInfo == null){
					 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
				 }else{
					 map.put("name", employeeInfo.getName());
					 map.put("mobile", employeeInfo.getMobile());
					 map.put("email", employeeInfo.getEmail());
					 map.put("sex", employeeInfo.getSex());
					 map.put("userId", employeeInfo.getUserId());
					 baseResponse.setReturnObject(map);
					 baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				 }
			}
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			baseResponse.setReturnCode(e.getErrorCode());
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
	
}