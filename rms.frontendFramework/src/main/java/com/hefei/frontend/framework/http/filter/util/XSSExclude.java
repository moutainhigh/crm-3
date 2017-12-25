package com.hefei.frontend.framework.http.filter.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.hefei.common.util.ConfigLineReader;

public class XSSExclude {
	private static final Logger log = Logger.getLogger(XSSExclude.class);
	
	private static final String CAS_EXCLUDE_FILE = "/xssfilter.properties";
	private static List<String> excludes = null;
	
	public static boolean inExcludes(String url){
		if(excludes == null || excludes.isEmpty())
			return false;
		return excludes.contains(url);
	}
	
	public static void init() {
		ConfigLineReader configLineReader = ConfigLineReader.read(CAS_EXCLUDE_FILE);
		excludes = configLineReader.getLines();
		log.info(" all url:" + excludes);
	}
	
	public static void main(String[] args){
//		ConfigLineReader configLineReader = ConfigLineReader.read(CAS_EXCLUDE_FILE);
//		excludes = configLineReader.getLines();
//		
		XSSExclude.init();
	}
}
