package com.hefei.frontend.framework.cookie.util;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class CookieUtil {

	private final static Logger log = Logger.getLogger(CookieUtil.class);
	
	/**
	 *读取COOKIE, AND decode
	 */
	public static String getCookieValue(final HttpServletRequest request, String cookieName){
		Cookie cookie = getCookie(request, cookieName);
		if(cookie == null)
			return null;
		String c = cookie.getValue();
		try {
			log.error(RequestThreadLocal.getTimer() + "cookieName:"+cookieName + " value:" + c);
			c = URLDecoder.decode(c, "UTF-8");
		} catch (Exception e) {
			log.error(RequestThreadLocal.getTimer() + "cookie decode error", e);
			c = null;
		}
		log.error(RequestThreadLocal.getTimer() + "cookieName:"+cookieName + " value:" + c);
		return c;
	}

	
    /**
     * 删除客户端的名称为name的Cookie。
     * @return true:删除成功；false:删除失败。
     */
    public static void delCookieByName(HttpServletRequest request, HttpServletResponse response, final String name) throws Exception{
        if (null == request || null == response || null == name || "".equals(name)) {
           throw new Exception("Parameter is null");
        }
        try{
	        Cookie cookie = getCookie(request, name);
	        cookie.setMaxAge(0);
	        cookie.setPath("/");
	        response.addCookie(cookie);
        }catch(Exception e){
        	log.error(RequestThreadLocal.getTimer() + " error", e);
        	throw new Exception(" delete cookie error :" + name);
        }
    }
    
    /**
     * 添加Cookie至用户客户端。
     *
     * @param request
     * @param response
     * @param name     Cookie名
     * @param value    值
     * @param age      生存周期
     * @return true：添加成功，false：添加失败。
     */
    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
    		final String domain, final String name, final String value, final int age, boolean httpOnly, boolean secure) throws Exception{
        if (null == request || null == response || null == name || "".equals(name) || null == value) {
        	throw new Exception("Parameter is null");
        }
    	try {
    		StringBuffer buf = new StringBuffer();
    		CookieHelper.appendCookieValue(buf, 1, name, java.net.URLEncoder.encode(value, "UTF-8"), "/", domain, "", age, secure, httpOnly);
        	response.addHeader("Set-Cookie", buf.toString());
    	 } catch (Exception e) {
             log.error(RequestThreadLocal.getTimer() + " name:" + name + " value:" + value + " error", e);
             throw new Exception("SetCookie Error");
         }
    }
    

	/**
	 *读取COOKIE
	 */
	private static Cookie getCookie(final HttpServletRequest request, String cookieName) {
		if (null == request) {
			log.error(RequestThreadLocal.getTimer() + " ERROR: the Request is empty!");
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			log.error(RequestThreadLocal.getTimer() + "ERROR: the Cookie is empty!");
			return null;
		}
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
			return null;
		}
		return null;
	}
}
