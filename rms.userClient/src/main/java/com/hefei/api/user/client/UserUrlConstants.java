package com.hefei.api.user.client;

import org.apache.log4j.Logger;

import com.hefei.common.http.client.ServiceUrlConstants;
import com.hefei.common.util.ConfigReader;

public class UserUrlConstants extends ServiceUrlConstants{

	private static final Logger log = Logger.getLogger(UserUrlConstants.class);
	public static final String CONFIG_FILE_NAME_CONF = "user-server-url-conf";
	/**人事系统登录地址**/
	public static String USER_SERVER_LOGIN_URL = null;
	/**注册系统用户**/
	public static String USER_SERVER_REGISTER_EMAIL = null;
	
	public static String USER_SERVER_REGISTER_MOBILE = null;
	
//	public static String USER_SERVER_REGISTER_EMAIL_MOBILE = null;
	
	public static String USER_SERVER_REGISTER_RMS_COMPANY_ADD_EMPLOYEE = null;

	public static String USER_SERVER_MOBILE_GET_LOGIN_MOBILE_BY_USERID = null;
	
	public static String USER_SERVER_EMAIL_GET_LOGIN_EMAIL_BY_USERID = null;
	
	public static String USER_SERVER_EMAIL_GET_BY_EMAIL = null;
	public static String USER_SERVER_MOBILE_GET_BY_MOBILE = null;
	
	
	public static String USER_SERVER_USERINFO_GET_BY_ID = null;
	public static String USER_SERVER_USERINFO_GET_BY_IDNO = null;
	public static String USER_SERVER_USERINFO_UPDATE_BY_ID = null;
	
	public void init(String configFileName) {
		try{
			ConfigReader reader = ConfigReader.read(configFileName);
			USER_SERVER_LOGIN_URL = reader.getConstant("USER_SERVER_LOGIN_URL");
			USER_SERVER_REGISTER_EMAIL = reader.getConstant("USER_SERVER_REGISTER_EMAIL");
			
			USER_SERVER_REGISTER_MOBILE = reader.getConstant("USER_SERVER_REGISTER_MOBILE");
//			USER_SERVER_REGISTER_EMAIL_MOBILE = reader.getConstant("USER_SERVER_REGISTER_EMAIL_MOBILE");
			USER_SERVER_REGISTER_RMS_COMPANY_ADD_EMPLOYEE = reader.getConstant("USER_SERVER_REGISTER_RMS_COMPANY_ADD_EMPLOYEE");
			
			USER_SERVER_MOBILE_GET_LOGIN_MOBILE_BY_USERID = reader.getConstant("USER_SERVER_MOBILE_GET_LOGIN_MOBILE_BY_USERID");
			USER_SERVER_EMAIL_GET_LOGIN_EMAIL_BY_USERID = reader.getConstant("USER_SERVER_EMAIL_GET_LOGIN_EMAIL_BY_USERID");
			
			USER_SERVER_EMAIL_GET_BY_EMAIL = reader.getConstant("USER_SERVER_EMAIL_GET_BY_EMAIL");
			USER_SERVER_MOBILE_GET_BY_MOBILE = reader.getConstant("USER_SERVER_MOBILE_GET_BY_MOBILE");
			
			USER_SERVER_USERINFO_GET_BY_ID = reader.getConstant("USER_SERVER_USERINFO_GET_BY_ID");
			USER_SERVER_USERINFO_GET_BY_IDNO = reader.getConstant("USER_SERVER_USERINFO_GET_BY_IDNO");
			USER_SERVER_USERINFO_UPDATE_BY_ID = reader.getConstant("USER_SERVER_USERINFO_UPDATE_BY_ID");
		}catch(Exception e){
			log.error("UrlConstants init exception", e);
			System.exit(0);
		}
		
	}
}
