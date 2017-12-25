package com.hefei.rms.employee.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/employee", produces = "text/plain;charset=UTF-8")
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	private IEmployeeService employeeService;
	/**
	 * 保存员工信息
	 * @return
	 */
	@RequestMapping(value="/createEmployee",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String createEmployee(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
		
			JsonNode dateNode = JacksonUtil.getJsonNode(plainString);
			JsonNode employeeJson = dateNode.get("employee");
			EmployeeInfo employeeInfo = JacksonUtil.jsonToBean(employeeJson, EmployeeInfo.class);
			
			JsonNode salaryJson = dateNode.get("salary");
			SalaryDTO salaryInfo = JacksonUtil.jsonToBean(salaryJson, SalaryDTO.class);
			Long companyId = JacksonUtil.getLong(dateNode, "companyId");
			Long departmentId = JacksonUtil.getLong(dateNode, "departmentId");
			Long positionId = JacksonUtil.getLong(dateNode, "positionId");
			
			if(employeeInfo == null ||salaryInfo == null ||companyId == null ||departmentId == null ||positionId == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				employeeInfo = employeeService.createEmployee(salaryInfo, employeeInfo, companyId, departmentId, positionId);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employeeInfo);
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
	
	/**
	 * 更新员工信息
	 * @return
	 */
	@RequestMapping(value="/update",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String update(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode dateNode = JacksonUtil.getJsonNode(plainString);
			JsonNode employeeJson = dateNode.get("employeeInfo");
			EmployeeInfo info = JacksonUtil.jsonToBean(employeeJson, EmployeeInfo.class);
			
			employeeService.updateEmployee(info);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			
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
	
	/**
	 * 查询员工信息列表
	 * @return
	 */
	@RequestMapping(value="/findPagination",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findPagination(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			String cardNo = null;
			String sex = null;
			String status = null;
			Long companyId = null;
			Integer pageIndex = null;
			Integer pageSize = null;
			
			if(jsonNode.has("cardNo")){
				cardNo = JacksonUtil.getString(jsonNode, "cardNo");
			}
			if(jsonNode.has("sex")){
				sex = JacksonUtil.getString(jsonNode, "sex");
			}
			if(jsonNode.has("status")){
				status = JacksonUtil.getString(jsonNode, "status");
			}
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
			Pagination<EmployeeInfo> pagination = employeeService.findPagination(cardNo, sex, status, companyId, pageIndex, pageSize);
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
	
	
	/**
	 * 查询员工信息
	 * @return
	 */
	@RequestMapping(value="/getById",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getById(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			Long id = null;
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("id")) {
				String idString = jsonNode.get("id").asText();
				if(StringUtils.isNotBlank(idString)){
					id = Long.valueOf(idString);
				}
				
				if(id == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}
			
			EmployeeInfo employee = employeeService.getEmployeeInfo(id);
			if(employee == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employee);
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
	
	@RequestMapping(value="/getByEmpComId",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getByEmpComId(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			Long companyId = JacksonUtil.getLong(jsonNode, "companyId");
			Long employeeId = JacksonUtil.getLong(jsonNode, "employeeId");
			
			EmployeeInfo employee = employeeService.getEmployeeInfo(companyId, employeeId);
			if(employee == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employee);
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
	
	@RequestMapping(value="/getByIdNo",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getByIdNo(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			String idNo = null;
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("idNo")) {
				idNo = jsonNode.get("idNo").asText();
				
				if(StringUtils.isBlank(idNo)){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}
			
			EmployeeInfo employee = employeeService.getByIdNo(idNo);
			if(employee == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employee);
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
	
	@RequestMapping(value="/getByUserId",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getByUserId(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			Long userId = null;
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			if( jsonNode.has("userId")) {
				userId = JacksonUtil.getLong(jsonNode, "userId");
				if(userId == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}
			
			EmployeeInfo employee = employeeService.getEmployeeInfoByUserId(userId);
			if(employee == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employee);
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
	
}
