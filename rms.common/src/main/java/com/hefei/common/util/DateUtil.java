package com.hefei.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * yyyyMMdd
	 */
	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
	
	/**
	 * yyyy-MM-dd
	 */
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * yyyy/MM/dd
	 */
	public static final String FORMAT_SLASH_YYYYMMDD = "yyyy/MM/dd";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String FORMAT_YYYY_MM_DDHH = "yyyy-MM-dd HH";

	/**
	 * 获取当天日期字符串
	 * @return
	 */
	public static String getTodayStrDate(String format){
	    Date dt = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat(format);  
	    return sdf.format(dt);  
	}
	
	/**
	 * 获取当天日期
	 * @return
	 */
	public static Date getTodayDate(String format){
	    Date dt = new Date();  
	    return dt;  
	}
	
	/**
	 * 字符串转换为指定格式的日期类型
	 * @param dateStr 原始日期字
	 * @param rex	新日期格试,如:yyyyMMdd
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2date(String dateStr,String rex) throws ParseException {
		DateFormat df=new SimpleDateFormat(rex);
		if(StringUtils.isEmpty(dateStr)){
			return null;
		}else{
			Date date = df.parse(dateStr);
			return date;
		}
	}
	
	/**
	 * 日期转换为指定格式的字符串
	 * @param date
	 * @param rex
	 * @return
	 */
	public static String date2String(Date date, String rex) {
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(rex);
		return sdf.format(date);
	}
	
	
	 /** 
     * 获得指定日期的前一天 ,格式yyyyMMdd
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
	public static String getDayBefore(String curDate) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat(FORMAT_YYYYMMDD).parse(curDate);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat(FORMAT_YYYYMMDD).format(c.getTime());  
        return dayBefore;  
    }  
  
    /** 
     * 获得指定日期的后一天 ,格式yyyyMMdd
     *  
     * @param specifiedDay 
     * @return 
     */  
    public static String getDayAfter(String curDate) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat(FORMAT_YYYYMMDD).parse(curDate);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + 1);  
  
        String dayAfter = new SimpleDateFormat(FORMAT_YYYYMMDD)  .format(c.getTime());  
        return dayAfter;  
    }
    
    
    /**
     * 获取date 之后的 一段时间milseconds的日期
     */
    public static Date getDayAfter(Date date, long milseconds){
    	Calendar c = Calendar.getInstance();
    	long datemilsec = date.getTime();
    	c.setTimeInMillis(datemilsec + milseconds);
    	return c.getTime();
    }
    /**
     * 获取date 之后的 一段时间milseconds的日期
     */
    public static Date getDayBefore(Date date, long milseconds){
    	Calendar c = Calendar.getInstance();
    	long datemilsec = date.getTime();
    	c.setTimeInMillis(datemilsec - milseconds);
    	return c.getTime();
    }
    
    /**
     *  d1:2017.5.2  <br/>
     *  d2:2017.5.1  <br/>
     *  >0 <br/>
     */
    public static long compareDate(Date d1, Date d2){
    	long d1milsec = d1.getTime();
    	long d2milsec = d2.getTime();
    	return d1milsec - d2milsec;
    }
    
    /**
     * 是否是当天时间 XXXX-XX-XX 00:00:00 -  XXXX-XX-XX 23：59：59 
     * @return
     */
    public static boolean isToday(Date d){
    	try{
	    	Date now = Calendar.getInstance().getTime();
	    	String nowStr = date2String(now, FORMAT_YYYY_MM_DD);
	    	String todayBeginStr = nowStr + " 00:00:00";
	    	String todayEndStr = nowStr + " 23:59:59";
	    	Date todayBegin = string2date(todayBeginStr, FORMAT_YYYY_MM_DDHHMMSS);
	    	Date todayEnd = string2date(todayEndStr, FORMAT_YYYY_MM_DDHHMMSS);
	    	if(todayBegin.getTime() <= d.getTime() && todayEnd.getTime() >= d.getTime())
	    		return true;
    	}catch(Exception e){
    		e.printStackTrace();  
    	}
    	return false;
    }
    public static void main(String[] args){
//    	Date now = new Date();
//    	Date d = getDayBefore(now,  1*60*60*1000);
//    	System.out.println(DateUtil.date2String(d, DateUtil.FORMAT_YYYY_MM_DDHHMMSS));
//    	System.out.println(compareDate(now, d));
    	String d1 = "2017-06-01 11:11:11";
    	try {
			System.out.println(isToday(string2date(d1, FORMAT_YYYY_MM_DDHHMMSS)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
