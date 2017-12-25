package com.hefei.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ConfigLineReader {
	
	private static final Logger log = Logger.getLogger(ConfigLineReader.class);
	
	private List<String> lines = new ArrayList<String>();
	
	public static ConfigLineReader read(String propertyFileName){
		ConfigLineReader  configLineReader = new ConfigLineReader();
		BufferedReader reader = null;
		try{
			URL url = ConfigLineReader.class.getResource(propertyFileName);
			String fileName = url.getFile();
			File file = new File(fileName);
		    reader = new BufferedReader(new FileReader(file));
		    String tempString = null;
	// 一次读入一行，直到读入null为文件结束
		    while ((tempString = reader.readLine()) != null) {
		    	if(StringUtils.isNotBlank(tempString)){
		    		if(!tempString.startsWith("#")){
		    			configLineReader.addLine(tempString.trim());
		    		}
		    	}
		    }
		    return configLineReader;
		} catch (Exception e) {
        	log.error(" error " + propertyFileName, e);
        	return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	public void addLine(String line){
		lines.add(line);
	}
	
	public List<String> getLines(){
		return this.lines;
	}
}
