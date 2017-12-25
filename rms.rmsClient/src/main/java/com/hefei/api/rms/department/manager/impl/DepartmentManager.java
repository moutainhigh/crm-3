package com.hefei.api.rms.department.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.department.manager.IDepartmentManager;
import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class DepartmentManager  extends BaseManager implements IDepartmentManager{
	
	private static final Logger logger = Logger.getLogger(DepartmentManager.class);
	
	@Override
	public DepartmentInfo saveDepartment(DepartmentInfo departmentInfo) throws ClientException {
		if(departmentInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("saveDepartment begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("departmentInfo", departmentInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_DEPARTMENT_SAVE, dataJson, timer, head);
			logger.info("saveDepartment end time:" + timer + " result:" + result);
			logger.info("saveDepartment " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				departmentInfo = (DepartmentInfo) JacksonUtil.jsonToBean(objStr, DepartmentInfo.class);
	 			return departmentInfo;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
		} catch (Exception e) {
			logger.error("savePosition error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public void updateDepartment(Long id, String delFlag) throws ClientException {
		if(id == null || StringUtils.isBlank(delFlag)){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("updateDepartment begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			dataJson.put("delFlag", delFlag);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_DEPARTMENT_UPDATE, dataJson, timer, head);
			logger.info("updateDepartment end time:" + timer + " result:" + result);
			logger.info("updateDepartment " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("updateDepartment error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public DepartmentInfo getDepartmentById(Long id) throws ClientException {
		if(id == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		try {
			long timer = System.currentTimeMillis();
			logger.info("getDepartmentById begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", id);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_DEPARTMENT_GET_BY_ID, dataJson, timer, head);
			logger.info("getDepartmentById end time:" + timer + " result:" + result);
			logger.info("getDepartmentById " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				DepartmentInfo departmentInfo = (DepartmentInfo) JacksonUtil.jsonToBean(objStr, DepartmentInfo.class);
	 			return departmentInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (Exception e) {
			logger.error("findDepartment error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	@Override
	public Pagination<DepartmentInfo> findDepartmentPaginationByCompany(Long companyId, int pageIndex, int pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("findDepartmentPaginationByCompany time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("companyId", companyId);
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_DEPARTMENT_FIND_DEPARTMENT_PAGINATION_BY_COMPANY, dataJson, timer, head);
			logger.info("findDepartmentPaginationByCompany" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, DepartmentInfo.class);
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
}
