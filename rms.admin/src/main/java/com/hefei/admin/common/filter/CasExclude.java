package com.hefei.admin.common.filter;

import java.util.List;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigLineReader;

public class CasExclude {
	private static final Logger log = Logger.getLogger(CasExclude.class);
	
	private static final String CAS_EXCLUDE_FILE = "/casfilter.properties";
	private static List<String> excludes = null;
	
	public static String NOT_AUTHORIZATION_URL = null;
	private static final String NOT_AUTHORIZATION_URL_KEY = "not_authrization_url";
	
	public static String URL_SUBSTRING_WEB_CONTEXT = ""; 
	private static final String URL_SUBSTRING_WEB_CONTEXT_KEY = "url_substring_web_context";
	
	public static boolean inExcludes(String url){
		if(excludes == null || excludes.isEmpty())
			return false;
		return excludes.contains(url);
	}
	
	public static void init() {
		ConfigLineReader configLineReader = ConfigLineReader.read(CAS_EXCLUDE_FILE);
		excludes = configLineReader.getLines();
		log.info(" all url:" + excludes);
		
		if(excludes != null && excludes.size() >0){
			for(int i=0; i< excludes.size(); i++){
				String url = excludes.get(i);
				if(url.startsWith(NOT_AUTHORIZATION_URL_KEY)){
					NOT_AUTHORIZATION_URL = url.split("=")[1];
				}
				if(url.startsWith(URL_SUBSTRING_WEB_CONTEXT_KEY)){
					if(url.split("=").length>1){
						URL_SUBSTRING_WEB_CONTEXT = url.split("=")[1];
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
//		ConfigLineReader configLineReader = ConfigLineReader.read(CAS_EXCLUDE_FILE);
//		excludes = configLineReader.getLines();
//		
		CasExclude.init();
	}
}
