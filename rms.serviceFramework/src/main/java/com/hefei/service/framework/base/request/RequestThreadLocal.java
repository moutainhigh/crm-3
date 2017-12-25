package com.hefei.service.framework.base.request;
public class RequestThreadLocal {

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
	
	public static BaseRequestHead getBaseRequestHead(){
		try{
			BaseRequest br = get();
			BaseRequestHead head = br.getHeadObj();
			return head;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public static String getLoggerStr(){
		try{
			BaseRequestHead head = getBaseRequestHead();
			return head.toSimpleString();
		}catch(Exception e){
			return null;
		}
	}
	
	public static BaseRequestHead getHeadStr(){
		BaseRequestHead head = getBaseRequestHead();
		return head;
	}
	
	public static String getPlain(){
		BaseRequest br = get();
		return br.getPlain();
	}
	
	public static String getIp(){
		try{
			BaseRequestHead head = getBaseRequestHead();
			return head.getIp();
		}catch(Exception e){
			return "";
		}
	}
}
