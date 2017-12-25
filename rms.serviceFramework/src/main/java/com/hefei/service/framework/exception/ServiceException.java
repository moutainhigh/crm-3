package com.hefei.service.framework.exception;

import com.hefei.common.returncode.ReturnCode;

public class ServiceException  extends Exception{

	private static final long serialVersionUID = -6437673299380044255L;

	private String errorCode;
	
	private String errorMessage;

	public ServiceException(){}
	
	public ServiceException(String errorCode){
		this.errorCode = errorCode;
	}
	public ServiceException(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return "errorCode=" + errorCode + " errorMessage="+errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
