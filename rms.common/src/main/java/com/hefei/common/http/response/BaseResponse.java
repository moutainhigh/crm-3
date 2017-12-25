package com.hefei.common.http.response;

import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;

public class BaseResponse implements java.io.Serializable{
	
	private static final long serialVersionUID = -7762509511351276580L;

	private String returnCode;
	
	private String returnMessage;
	
	private Object returnObject;

	
	public String toString(){
		try{
			return JacksonUtil.beanToJson(this);
		}catch(Exception e){
			return "returnCode:" + returnCode;
		}
	}
	public BaseResponse(){}
	
	public BaseResponse(String returnCode){
		this.returnCode = returnCode;
	}
	
	public BaseResponse(String returnCode, Object returnObject) {
		this.returnCode = returnCode;
		this.returnObject = returnObject;
	}
	
	public BaseResponse(String returnCode, String returnMessage, Object returnObject) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
		this.returnObject = returnObject;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public BaseResponse setReturnCode(String returnCode) {
		this.returnCode = returnCode;
		return this;
	}

	public String getReturnMessage() {
		if(StringUtils.isNotBlank(returnMessage)){
			return returnMessage;
		}
		if(StringUtils.isBlank(returnCode)){
			return ReturnCode.MESSAGES.get("");
		}
		return ReturnCode.MESSAGES.get(returnCode);
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

}
