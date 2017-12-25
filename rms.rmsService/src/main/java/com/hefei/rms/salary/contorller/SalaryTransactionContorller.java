package com.hefei.rms.salary.contorller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.hefei.api.rms.salary.dto.SalaryPayTransactionDTO;
import com.hefei.api.rms.salary.dto.SalaryTransactionDTO;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.rms.salary.po.SalaryTransaction;
import com.hefei.rms.salary.service.ISalaryService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/salary/transaction", produces = "text/plain;charset=UTF-8")
public class SalaryTransactionContorller {
	private static final Logger logger = Logger.getLogger(SalaryTransactionContorller.class);
	
	@Autowired
	private ISalaryService salaryService;

	/**
	 * 查询员工信息
	 * @return
	 */
	@RequestMapping(value="/get",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String get(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try {
			Long transactionId=null;
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			
			JsonNode jsonNode = JacksonUtil.jsonToBean(plainString, JsonNode.class);
			transactionId = JacksonUtil.getLong(jsonNode, "transactionId");
			
			SalaryTransaction salaryTransaction = salaryService.getSalaryTransaction(transactionId);
			if(salaryTransaction == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS);
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(salaryTransaction);
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
	 * 查找工资信息
	 * @return
	 */
	@RequestMapping(value="/find",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String find(){
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
			String employeeName = null;
			Long companyId = null;
			Long employeeId = null;
			Long userId = null;
			String employeeStatus = null;
			String salaryPayStatus = null;
			String ssPayStatus = null;
			Integer pageIndex = null;
			Integer pageSize = null;
			
			if(jsonNode.has("cardNo")){
				cardNo = JacksonUtil.getString(jsonNode, "cardNo");
			}
			
			if(jsonNode.has("employeeName")){
				employeeName = JacksonUtil.getString(jsonNode, "employeeName");
			}
			if(jsonNode.has("employeeStatus")){
				employeeStatus = JacksonUtil.getString(jsonNode, "employeeStatus");
			}
			if(jsonNode.has("salaryPayStatus")){
				salaryPayStatus = JacksonUtil.getString(jsonNode, "salaryPayStatus");
			}
			if(jsonNode.has("ssPayStatus")){
				ssPayStatus = JacksonUtil.getString(jsonNode, "ssPayStatus");
			}
			if(jsonNode.has("companyId")){
				companyId = JacksonUtil.getLong(jsonNode, "companyId");
			}
			if(jsonNode.has("employeeId")){
				employeeId = JacksonUtil.getLong(jsonNode, "employeeId");
			}
			if(jsonNode.has("userId")){
				userId = JacksonUtil.getLong(jsonNode, "userId");
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
			Pagination<SalaryPayTransactionDTO> pagination = salaryService.findSalayAndSSPayHistory(companyId, employeeId, employeeStatus, salaryPayStatus, ssPayStatus, cardNo, employeeName, pageIndex, pageSize);
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
			JsonNode adjustInfoJson = dateNode.get("salaryTransactionDTO");
			SalaryTransactionDTO adjustInfo = JacksonUtil.jsonToBean(adjustInfoJson, SalaryTransactionDTO.class);
			
			String remark = JacksonUtil.getString(dateNode, "remark");
			Long currentUserId = JacksonUtil.getLong(dateNode, "currentUserId");
			
			salaryService.saveAdjustSalaryTransaction(adjustInfo, remark, currentUserId);;
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
	
	@RequestMapping(value="/generate",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String generate(){
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
			
			String employeeStatus = JacksonUtil.getString(dateNode, "employeeStatus");
			Long companyId = JacksonUtil.getLong(dateNode, "companyId");
			
			salaryService.generatePayInfo(companyId, employeeStatus);
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
	
	@RequestMapping(value="/pay",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String pay(){
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
			String transactionIds = JacksonUtil.getString(dateNode, "transactionIds");
			Long companyId = JacksonUtil.getLong(dateNode, "companyId");
			Long employeeId = JacksonUtil.getLong(dateNode, "employeeId");
			Long userId = JacksonUtil.getLong(dateNode, "userId");
			
			salaryService.paySalary(companyId, employeeId, userId, transactionIds);
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
