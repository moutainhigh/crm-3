package com.hefei.service.framework.exception;

public class DaoException extends Exception{

	private static final long serialVersionUID = 295203642107486130L;
	
	private String errorCode;
	
	private String errorMessage;
	
	public DaoException(){}
	
	public DaoException(String errorCode){
		this.errorCode = errorCode;
	}
	public DaoException(String errorCode, String errorMessage){
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
