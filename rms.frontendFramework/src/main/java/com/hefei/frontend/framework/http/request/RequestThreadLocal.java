package com.hefei.frontend.framework.http.request;

import org.apache.log4j.Logger;

public class RequestThreadLocal {

	private static Logger logger = Logger.getLogger(RequestThreadLocal.class);
			
	private static ThreadLocal<BaseRequest> threadLocal = new ThreadLocal<BaseRequest>();

	public static BaseRequest get() {
		return threadLocal.get();
	}
	
	public static void set(BaseRequest br) {
		threadLocal.set(br);
	}
	
	public static void clean() {
		threadLocal.remove();
	}
	
	public static String getIp(){
		try{
			BaseRequest r = get();
			if( r != null)
				return r.getIp();
			return "";
		}catch(Exception e){
			logger.error("getIp Error " , e);
			return "";
		}
	}
	
	public static String getTimer(){
		try{
			BaseRequest r = get();
			if( r != null)
				return r.toSimple();
			return "";
		}catch(Exception e){
			logger.error("getRequestStr Error " , e);
			return "";
		}
	}
	
	public static String getRequestStr(){
		try{
			BaseRequest r = get();
			if( r != null)
				return r.toString();
			return "";
		}catch(Exception e){
			logger.error("getRequestStr Error " , e);
			return "";
		}
	}
}
