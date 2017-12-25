package com.hefei.rms.company.controller;

import java.util.List;

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
import com.hefei.rms.company.po.Company;
import com.hefei.rms.company.po.CompanyIndustry;
import com.hefei.rms.company.service.ICompanyService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;


@Controller
@RequestMapping(value="/company", produces = "text/plain;charset=UTF-8")
public class CompanyController {
	
	private static final Logger logger = Logger.getLogger(CompanyController.class);
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/regCreateCompanyEmployee",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String regCreateCompanyEmployee(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		try{
			String plain = RequestThreadLocal.getPlain();
			JsonNode node = JacksonUtil.getJsonNode(plain);
			String companyName = JacksonUtil.getString(node, "companyName");
			Long userId = JacksonUtil.getLong(node, "userId");
			String email = JacksonUtil.getString(node, "email");
			String mobile = JacksonUtil.getString(node, "mobile");
			String username = JacksonUtil.getString(node, "username");
			
			Company info = companyService.regCreateCompanyEmployee(userId, companyName, email, mobile,username);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(info);
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
	
	@RequestMapping(value="/create",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String create(){
		BaseResponse result = new BaseResponse();
		try{
			String plain = RequestThreadLocal.getPlain();
			JsonNode node = JacksonUtil.getJsonNode(plain);
			JsonNode companyInfoNode = node.get("company");
			Company companyInfo = JacksonUtil.jsonToBean(companyInfoNode, Company.class);
			
			String industryIds = JacksonUtil.getString(node, "industryIds");
			Long userId = JacksonUtil.getLong(node, "userId");
			Company info = companyService.createCompany(userId, industryIds,companyInfo);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			result.setReturnObject(info);
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
	@RequestMapping(value="/update",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String update(){
		BaseResponse result = new BaseResponse();
		try{
			String plain = RequestThreadLocal.getPlain();
			JsonNode node = JacksonUtil.getJsonNode(plain);
			JsonNode companyInfoNode = node.get("company");
			Company companyInfo = JacksonUtil.jsonToBean(companyInfoNode, Company.class);
			
			String industryIds = JacksonUtil.getString(node, "industryIds");
			Long userId = JacksonUtil.getLong(node, "userId");
			companyService.updateCompany(userId, industryIds,companyInfo);
			result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
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
	@RequestMapping(value="/getById",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getById(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long companyId = null;
		Long userId = null;
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
			if(jsonNode.has("userId")){
				userId = JacksonUtil.getLong(jsonNode, "userId");
			}
			
			if(companyId == null || userId == null){
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			Company company = companyService.getCompany(userId, companyId);
			
			if(company != null){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(company);
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
	@RequestMapping(value="/getByEmployeeId",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getByEmployeeId(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long employeeId = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("employeeId")){
				employeeId = Long.valueOf(jsonNode.get("employeeId").asText());
				if(employeeId == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			
			List<Company> list = companyService.getCompanyByEmployeeId(employeeId);
			
			if(list != null && list.size() > 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(list);
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
	@RequestMapping(value="/getByUserId",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getByUserId(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long userId = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("userId")){
				userId = Long.valueOf(jsonNode.get("userId").asText());
				if(userId == null){
					result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
					return JacksonUtil.beanToJson(result);
				}
			}else{
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT_NOTFOUND);
				return JacksonUtil.beanToJson(result);
			}
			List<Company> list = companyService.getCompanyByUserId(userId);
			
			if(list != null && list.size() > 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(list);
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
	
	@RequestMapping(value="/findManagedPagination",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String findPagination(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long userId = null;
		String companyName = null;
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
			if(jsonNode.has("userId")){
				userId = JacksonUtil.getLong(jsonNode, "userId");
			}
			if(jsonNode.has("companyName")){
				companyName = JacksonUtil.getString(jsonNode, "companyName");
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
			Pagination<Company> pagination = companyService.findManagedPagination(userId, companyName, pageIndex, pageSize);
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
	
	@RequestMapping(value="/getCompanyIndustry",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getCompanyIndustry(){
		BaseResponse result = new BaseResponse();
		result.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long companyId = null;
		try {
			String plainString = RequestThreadLocal.getPlain();
			if(StringUtils.isBlank(plainString)){
				logger.info(RequestThreadLocal.getLoggerStr() + " Plain is null ");
				result.setReturnCode(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
				return JacksonUtil.beanToJson(result);
			}
			JsonNode jsonNode = JacksonUtil.getJsonNode(plainString);
			if(jsonNode.has("companyId")){
				companyId = Long.valueOf(jsonNode.get("companyId").asText());
			}
			List<CompanyIndustry> list = companyService.getCompanyIndustry(companyId);
			
			if(list != null && list.size() > 0){
				result.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
				result.setReturnObject(list);
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
}
