package com.hefei.service.framework.monitor;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hefei.service.framework.base.request.RequestThreadLocal;

@SuppressWarnings("rawtypes")
public class Warning {

	private static Logger logger = Logger.getLogger(Warning.class);
	
	public static final int SIZE_LIMIT = 50;
	//millsends
	public static final int TIME_LIMIT_FUNCTION_DEFAULT = 100;
	
	public static final int TIME_LIMIT_DB_DEFAULT = 30;
	
	public static void warnCollectionSize(Collection c, String className, String Method, String append, long beginTimer, int maxSize){
		if(c != null && c.size() > maxSize){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-size:"+ c.size() + "-maxSize:"+ maxSize + "-"+ append + "-warning-warning-size");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}
	}
	
	public static void warnCollectionSize(Collection c, String className, String Method, String append, long beginTimer){
		if(c != null && c.size() > SIZE_LIMIT){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-size:"+ c.size() + "-maxSize:"+ SIZE_LIMIT + "-"+ append + "-warning-warning-size");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}
	}
	public static void warnMapSize(Map c, String className, String Method, String append, long beginTimer, int maxSize){
		if(c != null && c.size() > maxSize){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-size:"+ c.size() + "-maxSize:"+ maxSize + "-"+ append + "-warning-warning-size");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}
	}
	public static void warnMapSize(Map c, String className, String Method, String append, long beginTimer){
		if(c != null && c.size() > SIZE_LIMIT){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-size:"+ c.size() + "-maxSize:"+ SIZE_LIMIT + "-"+ append + "-warning-warning-size");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}
	}
	
	public static void warnFuntionTimer(String className, String Method, String append, long cost, long beginTimer, int maxCost){
		if(cost > maxCost){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-cost:"+  cost  + "-maxCost:"+  maxCost + "-"+ append + "-warning-warning-timer");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}else{
			logger.info(className + "-" + Method + "-" +beginTimer + "-cost:"+  cost + "-"+ append );
		}
	}
	
	public static void warnFuntionTimer(String className, String Method, String append, long cost, long beginTimer){
		if(cost > TIME_LIMIT_FUNCTION_DEFAULT){
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error(RequestThreadLocal.getLoggerStr() + className + "-" + Method + "-" +beginTimer + "-cost:"+  cost  + "-maxCost:"+  TIME_LIMIT_FUNCTION_DEFAULT + "-"+ append + "-warning-warning-timer");
			logger.error("--------------------");
			logger.error("--------------------");
			logger.error("--------------------");
		}else{
			logger.info(className + "-" + Method + "-" +beginTimer + "-cost:"+  cost + "-"+ append );
		}
	}
}
