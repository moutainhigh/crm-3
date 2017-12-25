package com.hefei.rms.salary.contorller;

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
import com.hefei.rms.salary.po.SalaryHistory;
import com.hefei.rms.salary.service.ISalaryService;
import com.hefei.service.framework.base.request.RequestThreadLocal;
import com.hefei.service.framework.exception.ServiceException;

@Controller
@RequestMapping(value="/salary/history", produces = "text/plain;charset=UTF-8")
public class SalaryHistoryContorller {
	private static final Logger logger = Logger.getLogger(SalaryHistoryContorller.class);
	
	@Autowired
	private ISalaryService salaryService;

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
			int pageIndex = 0;
			int pageSize = 0;
			
			if(jsonNode.has("cardNo")){
				cardNo = JacksonUtil.getString(jsonNode, "cardNo");
			}
			
			if(jsonNode.has("employeeName")){
				employeeName = JacksonUtil.getString(jsonNode, "employeeName");
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
			Pagination<SalaryHistory> pagination = salaryService.findSalayAdjustHistory(companyId, employeeId, cardNo, employeeName, pageIndex, pageSize);
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
