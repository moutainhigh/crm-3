package com.hefei.service.framework.base.request;

public class BaseRequestHead implements java.io.Serializable{

	private static final long serialVersionUID = -1108945433139346485L;

	private String client;
	
	private String token;
	
	private String ip;
	
	private long timer;
	
	public BaseRequestHead(String client, String ip, long timer, String token){
		this.client = client;
		this.ip = ip;
		this.timer = timer;
		this.token = token;
	}
	
	public String toSimpleString(){
		return " client=" + client  + " timer="+timer + " ";
	}
	public String toString(){
		return " client=" + client + " ip=" + ip + " timer="+timer + " token=" + token + " ";
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
