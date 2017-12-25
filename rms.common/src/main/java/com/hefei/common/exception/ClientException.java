package com.hefei.common.exception;

public class ClientException  extends Exception{

	private static final long serialVersionUID = -4156901104530612406L;

	private String errorCode;
	
	private String errorMessage;
	
	public ClientException(String errorCode){
		this.errorCode = errorCode;
	}
	public ClientException(String errorCode, String errorMessage){
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
