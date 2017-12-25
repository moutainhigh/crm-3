package com.hefei.api.user.auth.vo;


public class AuthRequest {

	private String username;
	
	private String plainPassword;
	
	private String loginType;
	
	private String loginSubType;
	
	private String loginPosition;
	
	private String ip;
	
	private String mac;
	
	private String browser;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getLoginSubType() {
		return loginSubType;
	}

	public void setLoginSubType(String loginSubType) {
		this.loginSubType = loginSubType;
	}

	public String getLoginPosition() {
		return loginPosition;
	}

	public void setLoginPosition(String loginPosition) {
		this.loginPosition = loginPosition;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", plainPassword="
				+ plainPassword + ", loginType=" + loginType
				+ ", loginSubType=" + loginSubType + ", loginPosition="
				+ loginPosition + ", ip=" + ip + ", mac=" + mac + ", browser="
				+ browser + "]";
	}
}
