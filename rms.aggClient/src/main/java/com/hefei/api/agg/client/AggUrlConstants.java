package com.hefei.api.agg.client;

import org.apache.log4j.Logger;

import com.hefei.common.http.client.ServiceUrlConstants;
import com.hefei.common.util.ConfigReader;

public class AggUrlConstants extends ServiceUrlConstants{

	private static final Logger log = Logger.getLogger(AggUrlConstants.class);
	public static final String CONFIG_FILE_NAME_CONF = "agg-server-url-conf";

	/**获取表自增id**/
	public static String AGG_SERVER_ID_GET_NEXTID = null;
	/**获取地址编码*/
	public static String AGG_SERVER_LOCATION_GETCODE = null;
	/**获取地址信息*/
	public static String AGG_SERVER_LOCATION_GETADDRESS = null;
	/**系统用户登录登出历史记录**/
	public static String AGG_SERVER_USER_LOGIN_HISTORY = null;


	public void init(String configFileName) {
		try{
			ConfigReader reader = ConfigReader.read(configFileName);
			AGG_SERVER_ID_GET_NEXTID = reader.getConstant("AGG_SERVER_ID_GET_NEXTID");
			AGG_SERVER_LOCATION_GETCODE = reader.getConstant("AGG_SERVER_LOCATION_GETCODE");
			AGG_SERVER_LOCATION_GETADDRESS = reader.getConstant("AGG_SERVER_LOCATION_GETADDRESS");
			AGG_SERVER_USER_LOGIN_HISTORY = reader.getConstant("AGG_SERVER_USER_LOGIN_HISTORY");
		}catch(Exception e){
			log.error("UrlConstants init exception", e);
			System.exit(0);
		}
	}
}
