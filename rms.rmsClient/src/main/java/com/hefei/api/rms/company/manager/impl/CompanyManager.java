package com.hefei.api.rms.company.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.vo.CompanyIndustryDTO;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;

public class CompanyManager extends BaseManager implements ICompanyManager{

	private static Logger logger = Logger.getLogger(CompanyManager.class);
	
	//创建公司
	public CompanyInfo regCreateCompanyEmployee(Long userId, String companyName,String email, String mobile,String username) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyName", companyName);
			dataJson.put("email", email);
			dataJson.put("mobile", mobile);
			dataJson.put("username", username);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_REG_CREATE_COMPANY_EMPLOYEE, dataJson, timer, head);
			logger.info(head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				CompanyInfo mInfo = (CompanyInfo) JacksonUtil.jsonToBean(objStr, CompanyInfo.class);
				return mInfo;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	/**
	 * @param 
	 * @desc 添加公司
	 * @author zn
	 * 
	 */
	@Override
	public CompanyInfo createCompany(Long userId, String industryIds, CompanyInfo info) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("createCompany time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("company", info);
			dataJson.put("userId", userId);
			dataJson.put("industryIds", industryIds);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_CREATE, dataJson, timer, head);
			logger.info("createCompany" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				CompanyInfo mInfo = (CompanyInfo) JacksonUtil.jsonToBean(objStr, CompanyInfo.class);
				return mInfo;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public void updateCompany(Long userId, String industryIds, CompanyInfo info) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("company", info);
			dataJson.put("userId", userId);
			dataJson.put("industryIds", industryIds);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_UPDATE, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}

	//查找公司
	public CompanyInfo getCompany(Long userId, Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("getCompany time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("userId", userId);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_GETBYCOMPANYID, dataJson, timer, head);
			logger.info("getCompany" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				CompanyInfo mInfo = (CompanyInfo) JacksonUtil.jsonToBean(objStr, CompanyInfo.class);
				return mInfo;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	
	public List<CompanyInfo> getCompanyByEmployeeId(Long employeeId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("getCompanyByEmployeeId time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_GETBYEMPLOYEEID, dataJson, timer, head);
			logger.info("getCompanyByEmployeeId" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<CompanyInfo> companyInfos =  (List<CompanyInfo>)JacksonUtil.jsonToCollections(listStr, List.class, CompanyInfo.class);
				return companyInfos;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public List<CompanyInfo> getCompanyByUserId(Long userId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("getCompanyByUserId time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_GETBYUSERID, dataJson, timer, head);
			logger.info("getCompanyByUserId" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<CompanyInfo> companyInfos =  (List<CompanyInfo>)JacksonUtil.jsonToCollections(listStr, List.class, CompanyInfo.class);
				return companyInfos;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public Pagination<CompanyInfo> findManagePagination(Long userId, String companyName, int pageIndex, int pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("findManagePagination time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			dataJson.put("companyName", companyName);
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_FIND_MANAGED_PAGINATION, dataJson, timer, head);
			logger.info("findManagePagination" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, CompanyInfo.class);
				pagination.setItems(list);
				return pagination;
			}else{
				throw new ClientException(baseResponse.getReturnCode());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public List<CompanyIndustryDTO> getCompanyIndustry(Long companyId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info("time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(RmsUrlConstants.RMS_SERVER_COMPANY_GET_INDUSTRY, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String listStr = JacksonUtil.beanToJson(resp.getReturnObject());
				List<CompanyIndustryDTO> industryList =  (List<CompanyIndustryDTO>)JacksonUtil.jsonToCollections(listStr, List.class, CompanyIndustryDTO.class);
				return industryList;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode(),resp.getReturnMessage());
			}
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + " error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
