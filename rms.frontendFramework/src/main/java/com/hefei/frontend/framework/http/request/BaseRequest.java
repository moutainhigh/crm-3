package com.hefei.frontend.framework.http.request;


public class BaseRequest {

	private String ip;
	
	private long timer;
	
	public BaseRequest(String ip, long timer){
		this.ip = ip;
		this.timer = timer;
	}
	
	public String toSimple(){
		return " " + " timer:" + timer + " ";
	}
	public String toString(){
		return " ip:" + ip + " timer:" +timer + " ";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}
}
