package com.hefei.frontend.framework.base;

public class CookieConstants {

	public static final int COOKIE_AGE_SESSION = -1;
	public static final int COOKIE_AGE_DELETE = 0;
	public static final int COOKIE_AGE_ONE_DAY = 24*60*60*1000;
	public static final int COOKIE_AGE_ONE_WEEK = 7*24*60*60*1000;
	public static final int COOKIE_AGE_ONE_MONTH = 30*24*60*60*1000;
	public static final int COOKIE_AGE_FOREVER = Integer.MAX_VALUE;
	
	public static String COOKIE_NAME_WEB=null;
	/**COOKIE 作用域名**/
	public static String COOKIE_DOMAIN = null;
	/**登录用户名**/
	public static String COOKIE_NAME_USER_LOGIN_USERNAME = null;
	/**登录用户昵称**/
	public static String COOKIE_NAME_USER_LOGIN_NICKNAME = null;
	/**登录用户id**/
	public static String COOKIE_NAME_USER_LOGIN_USERID = null;
	
	public static String COOKIE_NAME_CURRENT_COMPANY=null;
}
