package com.hefei.agg.util;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;


public class AggConstants {

	private static final Logger log = Logger.getLogger(AggConstants.class);
	private static final String CONFIG_FILE_NAME_CONF = "hefei-agg-conf";
	

	
	public static Long WORKER_ID = null;
	public static Long DATA_CENTER_ID = null;
	
	static{
		try{
			ConfigReader reader = ConfigReader.read(CONFIG_FILE_NAME_CONF);
			/**机器id**/
			WORKER_ID = Long.parseLong(reader.getConstant("WORKER_ID"));
			/**机房id**/
			DATA_CENTER_ID = Long.parseLong(reader.getConstant("DATA_CENTER_ID"));
			
		}catch(Exception e){
			log.error("UrlConstants init exception", e);
			System.exit(0);
		}
		
	}
}
