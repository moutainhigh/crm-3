package com.hefei.agg.id.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hefei.agg.id.service.IdService;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.service.framework.base.request.RequestThreadLocal;


/**
 *获取唯一id
 */
@Controller
public class IdController {
	
	@Autowired
	private IdService idService;
	private Logger logger = Logger.getLogger(IdController.class);
	@RequestMapping(value="/getNextId",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getNextId(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setReturnCode(ReturnCode.RETURN_CODE_ERROR);
		Long nextId = idService.getNextId();
		if(nextId!=null&&nextId>0l){
			baseResponse.setReturnCode(ReturnCode.RETURN_CODE_SUCCESS);
			baseResponse.setReturnObject(nextId);
		}
		String result = JacksonUtil.beanToJson(baseResponse);
		logger.info(RequestThreadLocal.getLoggerStr()+"getNextId result:"+result);
		return result;
	}
}