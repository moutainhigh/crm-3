package com.hefei.rms.common.base.controller;

import org.apache.log4j.Logger;

import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class BaseController {

	private Logger logger = Logger.getLogger(BaseController.class);
	
	protected String returnBaseResponse(BaseResponse baseResponse, String jsonCallback){
		String rtn = JacksonUtil.beanToJson(baseResponse);
		logger.info(RequestThreadLocal.getTimer() + " return:" + rtn);
		if(StringUtils.isNotBlank(jsonCallback))
			return jsonCallback + "(" + rtn + ")";
		return rtn;
	}
}
