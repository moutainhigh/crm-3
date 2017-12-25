package com.hefei.frontend.framework.http.request;

import org.apache.log4j.Logger;

import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.base.user.UserCookie;

public class CommonParameterThreadLocal {

	private static Logger logger = Logger.getLogger(RequestThreadLocal.class);
	
	private static ThreadLocal<CommonParameter> threadLocal = new ThreadLocal<CommonParameter>();
	
	public static void set(CommonParameter commonParameter){
		threadLocal.set(commonParameter);
	}
	
	public static CommonParameter get(){
		return threadLocal.get();
	}
	public static UserCookie getUserCookie(){
		CommonParameter commonParameter = get();
		if(commonParameter == null)
			return null;
		UserCookie userCookie = commonParameter.getUserCookie();
		if(userCookie == null)
			return null;
		return userCookie;
	}
	
	public static Long getUserId(){
		UserCookie userCookie = getUserCookie();
		if(userCookie != null)
			return userCookie.getUserId();
		return null;
	}
	public static Long getEmployeeId(){
		UserCookie userCookie = getUserCookie();
		if(userCookie != null)
			return userCookie.getEmployeeId();
		return null;
	}
	public static CompanyCookie getCompanyCookie(){
		CommonParameter commonParameter = get();
		if(commonParameter == null)
			return null;
		CompanyCookie companyCookie = commonParameter.getCompanyCookie();
		if(companyCookie == null)
			return null;
		return companyCookie;
	}
	public static Long getCurrentCompanyId(){
		CompanyCookie companyCookie = getCompanyCookie();
		if(companyCookie == null)
			return null;
		return companyCookie.getCompanyId();
	}
	public static String getCurrentCompanyName(){
		CompanyCookie companyCookie = getCompanyCookie();
		if(companyCookie == null)
			return null;
		return companyCookie.getCompanyName();
	}
	public static void clean() {
		threadLocal.remove();
	}
}
