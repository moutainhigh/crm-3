package com.hefei.rms.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.employee.service.IEmployeeService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/employee", produces = "text/plain;charset=UTF-8")
public class EmployeeDepartPositionController {

	private static final Logger logger = Logger.getLogger(EmployeeDepartPositionController.class);
	@Autowired
	private IEmployeeService employeeService;
	/**
	 * 保存员工信息
	 * @return
	 */
	@RequestMapping(value="/getEmployeeDepartPosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getEmployeeDepartPosition(){
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
			Long companyId = JacksonUtil.getLong(dateNode, "companyId");
			Long employeeId = JacksonUtil.getLong(dateNode, "employeeId");
			
			if(companyId == null ||employeeId == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				
				List<EmployeeDepartPositionDTO> list = employeeService.getEmployeeDepartPosition(companyId, employeeId);
				if(list !=null && list.size() > 0){
					result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
					result.setReturnObject(list);
				}else{
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
				}
				
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
	@RequestMapping(value="/updateEmployeeDepartPosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateEmployeeDepartPosition(){
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
			Long companyId = JacksonUtil.getLong(dateNode, "companyId");
			Long employeeId = JacksonUtil.getLong(dateNode, "employeeId");
			Long departmentId = JacksonUtil.getLong(dateNode, "departmentId");
			Long positionId = JacksonUtil.getLong(dateNode, "positionId");
			
			employeeService.updateEmployeeDepartPosition(companyId, employeeId, departmentId, positionId);
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
	 * 更新员工信息
	 * @return
	 */
	@RequestMapping(value="/grantPosition",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String grantPosition(){
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
			Long employeeId = JacksonUtil.getLong(dateNode, "employeeId");
			String positionIds = JacksonUtil.getString(dateNode, "positionIds");
			
			List<Long> positionIdList = new ArrayList<Long>();
			if(StringUtils.isNotEmpty(positionIds)){
				String[] positionIdString = positionIds.split(",");
				for(String pid : positionIdString){
					if(StringUtils.isNotEmpty(pid)){
						positionIdList.add(Long.valueOf(pid));
					}
				}
			}
			if(employeeId == null || positionIdList.size() == 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
			}else{
				employeeService.grantPosition(employeeId, positionIdList);
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
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
