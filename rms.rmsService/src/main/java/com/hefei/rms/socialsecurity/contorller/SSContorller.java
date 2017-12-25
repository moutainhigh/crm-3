package com.hefei.rms.socialsecurity.contorller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.rms.socialsecurity.dto.EmployeeSSDTO;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.socialsecurity.po.EmployeeSS;
import com.hefei.rms.socialsecurity.service.ISocialsecurityService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/ss", produces = "text/plain;charset=UTF-8")
public class SSContorller {
	private static final Logger logger = Logger.getLogger(SSContorller.class);
	
	@Autowired
	private ISocialsecurityService socialsecurityService;


	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String get(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			Long companyId=null;
			Long employeeId=null;
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			companyId = JacksonUtil.getLong(jsonNode, "companyId");
			employeeId = JacksonUtil.getLong(jsonNode, "employeeId");
			
			EmployeeSS employeeSSDTO = socialsecurityService.getEmployeeSS(companyId, employeeId);
			if(employeeSSDTO == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(employeeSSDTO);
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
	 * 调整工资
	 * @return
	 */
	@RequestMapping(value="/adjust",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String adjust(){
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
			JsonNode adjustInfoJson = dateNode.get("employeeSSDTO");
			EmployeeSSDTO employeeSSDTO = JacksonUtil.jsonToBean(adjustInfoJson, EmployeeSSDTO.class);
			
			String remark = JacksonUtil.getString(dateNode, "remark");
			Long currentUserId = JacksonUtil.getLong(dateNode, "currentUserId");
			
			socialsecurityService.saveAdjustSS(employeeSSDTO);
			
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
	
}
