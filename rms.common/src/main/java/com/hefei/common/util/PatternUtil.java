package com.hefei.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	private static final String NUMERIC_PATTERN = "[0-9]*"; // 数字
	private static final String MOBILE = "^1(3[0-9]|4[57]|5[0-9]|7[0-9]|8[0123456789])\\d{8}$";
	private static final String IDNO15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	private static final String IDNO18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
	private static final String PATTERN_EMAIL ="\\s*[0-9A-Za-z][_.0-9A-Za-z-]{0,31}@([0-9a-z][0-9a-z-]{0,30}\\.){1,4}[a-z]{2,4}\\s*$";
	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile(NUMERIC_PATTERN);
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean isMobileNO(String mobile) {
		if (StringUtils.isEmpty(mobile))
			return false;
		Pattern p = Pattern.compile(MOBILE);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static boolean isIDNO(String idNo) {
		if (StringUtils.isEmpty(idNo))
			return false;
		if(idNo.length() == 15){
			Pattern p = Pattern.compile(IDNO15);
			Matcher m = p.matcher(idNo);
			return m.matches();
		}else if(idNo.length() == 18) {
			Pattern p = Pattern.compile(IDNO18);
			Matcher m = p.matcher(idNo);
			return m.matches();
		}else{
			return false;
		}
		
	}
	
	public static boolean isEmail(String email) {
		if (StringUtils.isEmpty(email))
			return false;
		Pattern p = Pattern.compile(PATTERN_EMAIL);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	public static String formatEmail(String email){
		return formatEmail(email, 2, 1);
	}
	public static String formatEmail(String email,int headDigit,int endDigit){
		if(StringUtils.isEmpty(email)){
			return null;
		}
		String emailHead = email.split("@")[0];
		String hideEmailHead = getHideStrNo(emailHead,headDigit,endDigit);
		if(hideEmailHead!=null){
			String emailSuffix = email.substring(emailHead.length());
			return (hideEmailHead + emailSuffix).trim();
		}
		return email;
	}

	public static String getHideStrNo(String strNo,int headDigit,int endDigit){
		if(StringUtils.isEmpty(strNo)){
			return null;
		}
		if(headDigit + endDigit<=strNo.length()){
			StringBuffer sbHideNo = new StringBuffer();
			int middle = strNo.length()-(headDigit + endDigit);
			for (int i = 0; i < middle; i++) {
				sbHideNo.append("*");
			}
			return strNo.substring(0,headDigit) + sbHideNo + strNo.substring(strNo.length()-endDigit);
		}
		return strNo;
	}
	
	public static String formatMobile(String mobile){
		return formatMobile(mobile, "****");
	}
	
	public static String formatMobile(String mobile, String mask){
		if(StringUtils.isEmpty(mobile))
			return "";
		mobile = mobile.substring(0, 3) + mask + mobile.substring(7);
		return mobile;
	}
	public static String formatString(String str, int pre,int fix, String suffix){
		if(StringUtils.isEmpty(str))
			return "";
		int i =str.length()-(pre+fix);
		if(i <=0)
			return str;
		if(null==suffix || "".equals(suffix.trim())){
			suffix="*";
		}
		String end=str.substring(str.length()-fix);
		StringBuffer sbf = new StringBuffer(str.substring(0, pre));
		for(int j=0;j<i;j++){
			sbf.append(suffix);
		}
		return sbf.toString()+end;
	}
	
}
