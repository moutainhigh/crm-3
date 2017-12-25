package com.hefei.api.agg.id.worker;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigReader;

public class IdWorkerConstants {

	private static final Logger log = Logger.getLogger(IdWorkerConstants.class);
	private static final String CONFIG_FILE_NAME_CONF = "idworker-conf";
	
	public static Long WORKER_ID = null;
	public static Long DATA_CENTER_ID = null;
	public static String IDWORKER_LOCAL= null;
	
	public static final String IDWORKER_LOCAL_YES = "1";
	
	static{
		if(ConfigReader.fileExists(CONFIG_FILE_NAME_CONF)){
			try{
				ConfigReader reader = ConfigReader.read(CONFIG_FILE_NAME_CONF);
				IDWORKER_LOCAL = reader.getConstant("IDWORKER_LOCAL");
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
}
