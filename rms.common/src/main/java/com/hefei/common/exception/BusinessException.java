package com.hefei.common.exception;

public class BusinessException extends Exception{

	private static final long serialVersionUID = -4030639900531732090L;
	
	private String errorCode;
	
	private String errorMessage;
	
	public BusinessException(String errorCode){
		this.errorCode = errorCode;
	}
	public BusinessException(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage =errorMessage;
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
