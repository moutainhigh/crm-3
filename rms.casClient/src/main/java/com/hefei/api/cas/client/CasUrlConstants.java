package com.hefei.api.cas.client;

import org.apache.log4j.Logger;

import com.hefei.common.http.client.ServiceUrlConstants;
import com.hefei.common.util.ConfigReader;

public class CasUrlConstants extends ServiceUrlConstants{

	private static final Logger log = Logger.getLogger(CasUrlConstants.class);
	public static final String CONFIG_FILE_NAME_CONF = "cas-server-url-conf";

	
	public static String URL_SERVER_CAS_ADMIN_AUTH = null;
	public static String URL_SERVER_CAS_ADMIN_MODULEMENUS = null;
	public static String URL_SERVER_CAS_AMIN_CHECK_AUTH = null;
	
	public static String URL_SERVER_CAS_SYSTEM_FINDPAGINATION = null;
	public static String URL_SERVER_CAS_SYSTEM_CREATE = null;
	public static String URL_SERVER_CAS_SYSTEM_GETBYID = null;
	
	public static String URL_SERVER_CAS_MODULE_GETMODULEBYSYSTEMID = null;
	public static String URL_SERVER_CAS_MODULE_GETMODULEBYID = null;
	public static String URL_SERVER_CAS_MODULE_GETMODULEBYROLE = null;
	public static String URL_SERVER_CAS_MODULE_GETMODULEBYPARENTID = null;
	public static String URL_SERVER_CAS_MODULE_CREATE = null;
	public static String URL_SERVER_CAS_MODULE_DELETE = null;
	
	public static String URL_SERVER_CAS_ROLE_FINDPAGINATION = null;
	public static String URL_SERVER_CAS_ROLE_CREATE = null;
	public static String URL_SERVER_CAS_ROLE_GETBYID = null;
	public static String URL_SERVER_CAS_ROLE_GET_MANAGER_ROLE_BY_COMPANYID = null;
	public static String URL_SERVER_CAS_ROLE_AUTHMODULE = null;
	
	public static String URL_SERVER_CAS_ADMIN_FINDPAGINATION = null;
	public static String URL_SERVER_CAS_ADMIN_CREATE = null;
	public static String URL_SERVER_CAS_ADMIN_GETBYID = null;
	public static String URL_SERVER_CAS_ADMIN_GETROLEBYADMIN = null;
	public static String URL_SERVER_CAS_ADMIN_AUTHROLE = null;
	
	
	public static String URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_SUPER_MANAGER = null;
	public static String URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_COMMON = null;
	public static String URL_SERVER_CAS_USER_CHECK_AUTH = null;
//	public static String URL_SERVER_CAS_USER_AUTH_DEFAULT = null;
	public static String URL_SERVER_CAS_USER_MENUS = null;
	public static String URL_SERVER_CAS_USER_GET_ROLE_BY_USERID = null;
	public static String URL_SERVER_CAS_USER_AUTHROLE = null;
	
	
	public static String URL_SERVER_CAS_CONFIG_GET = null;
	
	public void init(String configFileName) {
		try{
			ConfigReader reader = ConfigReader.read(configFileName);
			URL_SERVER_CAS_ADMIN_AUTH = reader.getConstant("URL_SERVER_CAS_ADMIN_AUTH");
			URL_SERVER_CAS_ADMIN_MODULEMENUS = reader.getConstant("URL_SERVER_CAS_ADMIN_MODULEMENUS");
			URL_SERVER_CAS_AMIN_CHECK_AUTH = reader.getConstant("URL_SERVER_CAS_AMIN_CHECK_AUTH");
			URL_SERVER_CAS_SYSTEM_FINDPAGINATION = reader.getConstant("URL_SERVER_CAS_SYSTEM_FINDPAGINATION");
			URL_SERVER_CAS_SYSTEM_CREATE = reader.getConstant("URL_SERVER_CAS_SYSTEM_CREATE");
			URL_SERVER_CAS_SYSTEM_GETBYID = reader.getConstant("URL_SERVER_CAS_SYSTEM_GETBYID");
			
			URL_SERVER_CAS_MODULE_GETMODULEBYSYSTEMID = reader.getConstant("URL_SERVER_CAS_MODULE_GETMODULEBYSYSTEMID");
			URL_SERVER_CAS_MODULE_GETMODULEBYID = reader.getConstant("URL_SERVER_CAS_MODULE_GETMODULEBYID");
			URL_SERVER_CAS_MODULE_GETMODULEBYROLE = reader.getConstant("URL_SERVER_CAS_MODULE_GETMODULEBYROLE");
			URL_SERVER_CAS_MODULE_GETMODULEBYPARENTID = reader.getConstant("URL_SERVER_CAS_MODULE_GETMODULEBYPARENTID");
			URL_SERVER_CAS_MODULE_CREATE = reader.getConstant("URL_SERVER_CAS_MODULE_CREATE");
			URL_SERVER_CAS_MODULE_DELETE = reader.getConstant("URL_SERVER_CAS_MODULE_DELETE");
			
			URL_SERVER_CAS_ROLE_FINDPAGINATION = reader.getConstant("URL_SERVER_CAS_ROLE_FINDPAGINATION");
			URL_SERVER_CAS_ROLE_CREATE = reader.getConstant("URL_SERVER_CAS_ROLE_CREATE");
			URL_SERVER_CAS_ROLE_GETBYID = reader.getConstant("URL_SERVER_CAS_ROLE_GETBYID");
			URL_SERVER_CAS_ROLE_GET_MANAGER_ROLE_BY_COMPANYID = reader.getConstant("URL_SERVER_CAS_ROLE_GET_MANAGER_ROLE_BY_COMPANYID");
			URL_SERVER_CAS_ROLE_AUTHMODULE = reader.getConstant("URL_SERVER_CAS_ROLE_AUTHMODULE");
			
			URL_SERVER_CAS_ADMIN_FINDPAGINATION = reader.getConstant("URL_SERVER_CAS_ADMIN_FINDPAGINATION");
			URL_SERVER_CAS_ADMIN_CREATE = reader.getConstant("URL_SERVER_CAS_ADMIN_CREATE");
			URL_SERVER_CAS_ADMIN_GETBYID = reader.getConstant("URL_SERVER_CAS_ADMIN_GETBYID");
			URL_SERVER_CAS_ADMIN_GETROLEBYADMIN = reader.getConstant("URL_SERVER_CAS_ADMIN_GETROLEBYADMIN");
			URL_SERVER_CAS_ADMIN_AUTHROLE = reader.getConstant("URL_SERVER_CAS_ADMIN_AUTHROLE");
			
			URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_SUPER_MANAGER =  reader.getConstant("URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_SUPER_MANAGER");
			URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_COMMON = reader.getConstant("URL_SERVER_CAS_USER_CREATE_ROLE_COMPANY_COMMON");
			
			URL_SERVER_CAS_USER_CHECK_AUTH = reader.getConstant("URL_SERVER_CAS_USER_CHECK_AUTH");
			URL_SERVER_CAS_USER_MENUS = reader.getConstant("URL_SERVER_CAS_USER_MENUS");
			URL_SERVER_CAS_USER_GET_ROLE_BY_USERID = reader.getConstant("URL_SERVER_CAS_USER_GET_ROLE_BY_USERID");
			URL_SERVER_CAS_USER_AUTHROLE = reader.getConstant("URL_SERVER_CAS_USER_AUTHROLE");
			
			URL_SERVER_CAS_CONFIG_GET = reader.getConstant("URL_SERVER_CAS_CONFIG_GET");
		}catch(Exception e){
			log.error("UrlConstants init exception", e);
			System.exit(0);
		}
		
	}
}
