package com.hefei.frontend.framework.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.encrypt.AESUtil;
import com.hefei.frontend.framework.base.company.CompanyCookie;
import com.hefei.frontend.framework.cookie.util.CookieHelper;
import com.hefei.frontend.framework.cookie.util.CookieUtil;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class CompanyCookieUtil extends CookieUtil{

	private final static Logger log = Logger.getLogger(CompanyCookieUtil.class);
	
	private static byte[] iv = { 'C', 'O', 'M', 'M', 'O', 'N', 'U','L','E','A','E','S','U','T','I','L'};  //向量
	private static String encryptKey = "ol.ce3";
	/**
	 * 一个账号管理多公司时 ，有一个当前工作中公司
	 */
	public static void setCurrentCompanyCookie(final HttpServletRequest request, final HttpServletResponse response,
    		final String domain, final String name, final CompanyCookie companyCookie, final int age, boolean httpOnly, boolean secure) throws Exception{
        if (null == request || null == response || null == name || "".equals(name) || null == companyCookie) {
        	throw new Exception("Parameter is null");
        }
    	try {
    		String value = JacksonUtil.beanToJson(companyCookie);
    		StringBuffer buf = new StringBuffer();
    		String encValue = AESUtil.encrypt(value, encryptKey, iv);
    		CookieHelper.appendCookieValue(buf, 1, name, java.net.URLEncoder.encode(encValue, "UTF-8"), "/", domain, "", age, secure, httpOnly);
        	response.addHeader("Set-Cookie", buf.toString());
    	 } catch (Exception e) {
             log.error(RequestThreadLocal.getTimer() + " name:" + name + " value:" + companyCookie + " error", e);
             throw new Exception("SetCookie Error");
         }
    }
	
	public static CompanyCookie getCurrentCompanyCookie(final HttpServletRequest request, final String name) throws Exception{
		String encValue = getCookieValue(request, name);
		if(encValue == null)
			return null;
		String plainValue =  AESUtil.decrypt(encValue, encryptKey, iv);
		return JacksonUtil.jsonToBean(plainValue, CompanyCookie.class);
    }
	public static Long getCompanyId(final HttpServletRequest request, final String name) throws Exception{
		CompanyCookie companyCookie = getCurrentCompanyCookie(request, name);
		if(companyCookie == null)
			return null;
		return companyCookie.getCompanyId();
	}
}
