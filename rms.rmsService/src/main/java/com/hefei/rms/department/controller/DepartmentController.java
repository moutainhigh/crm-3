package com.hefei.rms.department.controller;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.department.po.Department;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/department", produces = "text/plain;charset=UTF-8")
public class DepartmentController {
	
	private static final Logger logger = Logger.getLogger(DepartmentController.class);
	
	@Autowired
	private IDepartmentService departmentService;
	
	/**
	 * 保存岗位信息
	 * @return
	 */
	@RequestMapping(value="/saveDepartment",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String saveDepartment(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try{
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode dataJson = JacksonUtil.getJsonNode(plainString);
			JsonNode departmentJson = dataJson.get("departmentInfo");
			Department departmentInfo = JacksonUtil.jsonToBean(departmentJson, Department.class);
			
			if(departmentInfo == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				departmentInfo = departmentService.saveDepartmentInfo(departmentInfo);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(departmentInfo);
			}
			
		}catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
	@RequestMapping(value="/updateDepartment",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateDepartment(){
		BaseResponse result = new BaseResponse();
 		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		String delFlag = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("id") && jsonNode.has("delFlag")){
				id = Long.valueOf(jsonNode.get("id").asText());
				delFlag = jsonNode.get("delFlag").asText();
				if(id == null && StringUtils.isBlank(delFlag)){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
				
				Date updateTime = Calendar.getInstance().getTime();
				
				departmentService.updateDepartment(id,delFlag,updateTime);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}	
	@RequestMapping(value="/getDepartmentById",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getDepartmentById(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long id = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("id")){
				id = Long.valueOf(jsonNode.get("id").asText());
				if(id == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			Department department = departmentService.getDepartmentById(id);
			if(department != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(department);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
	@RequestMapping(value="/findDepartmentPaginationByCompany",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findDepartmentPaginationByCompany(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long companyId = null;
		int pageIndex =0;
		int pageSize = 0;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("companyId")){
				companyId = JacksonUtil.getLong(jsonNode, "companyId");
			}
			
			if(jsonNode.has("pageIndex")){
				pageIndex = JacksonUtil.getInt(jsonNode, "pageIndex");
			}
			if(jsonNode.has("pageSize")){
				pageSize = JacksonUtil.getInt(jsonNode, "pageSize");
			}
			if(pageSize ==0 ){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			Pagination<Department> pagination = departmentService.findDepartmentPaginationByCompany(companyId, pageIndex, pageSize);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(pagination);
			
		} catch(ServiceException e){
			result.setReturnCode(e.getErrorCode());
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
			logger.error(RequestThreadLocal.getLoggerStr()+"error:",e);
		}
		
		String resultStr = JacksonUtil.beanToJson(result);
		logger.info(RequestThreadLocal.getLoggerStr()+" result:"+resultStr);
		return resultStr ;
	}
	
}
