package com.hefei.api.rms.employee.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.api.rms.client.RmsUrlConstants;
import com.hefei.api.rms.client.base.BaseManager;
import com.hefei.api.rms.employee.manager.IEmployeeManager;
import com.hefei.api.rms.employee.vo.EmployeeDepartPositionDTO;
import com.hefei.api.rms.employee.vo.EmployeeInfo;
import com.hefei.api.rms.salary.dto.SalaryDTO;
import com.hefei.common.exception.ClientException;
import com.hefei.common.http.client.ClientFactory;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.http.util.SendRequest;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class EmployeeManager  extends BaseManager implements IEmployeeManager{

	private static final Logger logger = Logger.getLogger(EmployeeManager.class);

	@Override
	public EmployeeInfo createEmployee(SalaryDTO salaryInfo, EmployeeInfo employeeInfo, Long companyId, Long departmentId, Long positionId) throws ClientException {
		if(salaryInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(employeeInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(companyId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(departmentId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		if(positionId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("employee", employeeInfo);
			dataJson.putPOJO("salary", salaryInfo);
			dataJson.put("companyId", companyId);
			dataJson.put("departmentId", departmentId);
			dataJson.put("positionId", positionId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_SAVE, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
				EmployeeInfo employee = (EmployeeInfo) JacksonUtil.jsonToBean(objStr, EmployeeInfo.class);
	 			return employee;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (ClientException e) {
			throw e;
		} catch (Exception e) {
			logger.error(timer + "  error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	@Override
	public void updateEmployee(EmployeeInfo employeeInfo) throws ClientException {
		if(employeeInfo == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.putPOJO("employeeInfo", employeeInfo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_UPDATE, dataJson, timer, head);
			logger.info("time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			throw new ClientException(resp.getReturnCode());
			}
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
	@Override
	public Pagination<EmployeeInfo> findEmployeePagination(String cardNo,String sex, String status, Long companyId, int pageIndex,int  pageSize) throws ClientException {
		long timer = System.currentTimeMillis();
		logger.info("findEmployeePagination time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("cardNo", cardNo);
			dataJson.put("sex", sex);
			dataJson.put("status", status);
			dataJson.put("companyId", companyId);
			dataJson.put("pageIndex", pageIndex);
			dataJson.put("pageSize", pageSize);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_FIND_PAGINATION, dataJson, timer, head);
			logger.info("findEmployeePagination" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) baseResponse.getReturnObject());
				Pagination pagination = (Pagination) JacksonUtil.jsonToBean(objStr, Pagination.class);
				List list = pagination.getItems();
				list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson(list), List.class, EmployeeInfo.class);
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
	
	@Override
	public EmployeeInfo getEmployee(Long employeeId) throws ClientException {
		if(employeeId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("id", employeeId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GET_BY_ID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			EmployeeInfo employeeInfo = (EmployeeInfo) JacksonUtil.jsonToBean(objStr, EmployeeInfo.class);
	 			return employeeInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public EmployeeInfo getEmployee(Long companyId,Long employeeId) throws ClientException{
		if(employeeId == null || companyId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			dataJson.put("companyId", companyId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GET_BY_EMP_COM_ID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			EmployeeInfo employeeInfo = (EmployeeInfo) JacksonUtil.jsonToBean(objStr, EmployeeInfo.class);
	 			return employeeInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	public EmployeeInfo getEmployeeByIdNo(String idNo) throws ClientException{
		if(StringUtils.isBlank(idNo) ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			
			logger.info("getEmployeeByIdNo begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("idNo", idNo);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GET_BY_IDNO, dataJson, timer, head);
			logger.info("getEmployeeByIdNo end time:" + timer + " result:" + result);
			logger.info("getEmployeeByIdNo " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			EmployeeInfo employeeInfo = (EmployeeInfo) JacksonUtil.jsonToBean(objStr, EmployeeInfo.class);
	 			return employeeInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public EmployeeInfo getEmployeeByUserId(Long userId) throws ClientException{
		if(userId == null ){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info(" begin time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("userId", userId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GET_BY_USERID, dataJson, timer, head);
			logger.info("end time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			String objStr = JacksonUtil.convertToJsonStrs((Map<String, Object>) resp.getReturnObject());
	 			EmployeeInfo employeeInfo = (EmployeeInfo) JacksonUtil.jsonToBean(objStr, EmployeeInfo.class);
	 			return employeeInfo;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.endsWith(resp.getReturnCode())){
				return null;
			}else{
				throw new ClientException(resp.getReturnCode());
			}
			
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public List<EmployeeDepartPositionDTO> getEmployeeDepartPosition(Long companyId, Long employeeId) throws ClientException{
		long timer = System.currentTimeMillis();
		logger.info(" time:" + timer );
		try {
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			dataJson.put("companyId", companyId);
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GET_DEPARTMENT_POSITION, dataJson, timer, head);
			logger.info("" + head.getLoggerStr()  + " result:" + result+ " cost:" + (System.currentTimeMillis() - timer));

			BaseResponse baseResponse = JacksonUtil.jsonToBean(result, BaseResponse.class);
			if(ReturnCode.RETURN_CODE_SUCCESS.equals(baseResponse.getReturnCode())){
				List<EmployeeDepartPositionDTO>  list = JacksonUtil.jsonToCollections(JacksonUtil.beanToJson( baseResponse.getReturnObject()), List.class, EmployeeDepartPositionDTO.class);
				return list;
			}else if(ReturnCode.RETURN_CODE_ERROR_DATA_NOTEXISTS.equals(baseResponse.getReturnCode())){
				return null;
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
	public void updateEmployeeDepartPosition(Long companyId, Long employeeId, Long departmentId,Long positionId) throws ClientException{
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			dataJson.put("companyId", companyId);
			dataJson.put("departmentId", departmentId);
			dataJson.put("positionId", positionId);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_UPDATE_DEPARTMENT_POSITION, dataJson, timer, head);
			logger.info("time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			throw new ClientException(resp.getReturnCode());
			}
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
	
	public void grantEmployeePosition(Long employeeId, String positionIds) throws ClientException{
		if(employeeId == null){
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_PARAM_NULL);
		}
		long timer = System.currentTimeMillis();
		try {
			logger.info("time:" + timer );
			RequestHead head = RequestHead.get(timer,ClientFactory.client);
			ObjectNode dataJson = JacksonUtil.createObjectNode();
			dataJson.put("employeeId", employeeId);
			dataJson.put("positionIds", positionIds);
			
			String result = SendRequest.send(RmsUrlConstants.RMSCLIENT_SERVER_EMPLOYEE_GRANT_POSITION, dataJson, timer, head);
			logger.info("time:" + timer + " result:" + result);
			logger.info(" " + head.getLoggerStr() + " cost:" + (System.currentTimeMillis() - timer));
			BaseResponse resp = JacksonUtil.jsonToBean(result, BaseResponse.class);

	 		if(!ReturnCode.RETURN_CODE_SUCCESS.equals(resp.getReturnCode())){
	 			throw new ClientException(resp.getReturnCode());
			}
		} catch (ClientException e) {
			throw e;
		}  catch (Exception e) {
			logger.error(timer + " error",e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR);
		}
	}
}
