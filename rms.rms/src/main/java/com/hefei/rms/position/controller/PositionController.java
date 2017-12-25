package com.hefei.rms.position.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.api.rms.position.vo.PositionInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.position.service.IPositionService;
import com.hefei.rms.position.vo.DepartmentPosition;

@Controller
@RequestMapping(value="/position",produces="text/plain;charset=UTF-8")
public class PositionController {
	
	private Logger logger = Logger.getLogger(AddPositionController.class);

	@Autowired
	private IPositionService positionService;

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ICompanyService companyService;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, Long departmentId){
		try{
			DepartmentInfo department =  departmentService.getDepartment(departmentId);
			request.setAttribute("department", department);
			
			List<PositionInfo> list = positionService.getPositionByDepartment(departmentId);
			request.setAttribute("list", list);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "position/index";
	}
	
	
	@RequestMapping("getPositionByDepartment")
	@ResponseBody
	public String getPositionByDepartment(HttpServletRequest request, Long departmentId, String jsonCallback){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			List<PositionInfo> list = positionService.getPositionByDepartment(departmentId);
			if(list != null && list.size() > 0){
				List<DepartmentPosition> dpList = new ArrayList<DepartmentPosition>();
				DepartmentPosition dp;
				for(PositionInfo pi : list){
					dp = new DepartmentPosition();
					dp.setId(String.valueOf(pi.getId()));
					dp.setDepartmentId(String.valueOf(pi.getDepartmentId()));
					dp.setPositionName(pi.getPositionName());
					dpList.add(dp);
				}
				baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				baseResponse.setReturnObject(dpList);
			}
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
		return JacksonUtil.beanToJson(baseResponse);
	}
}
