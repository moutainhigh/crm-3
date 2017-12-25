package com.hefei.frontend.framework.http.xss;

import java.util.HashMap;
import java.util.Map;

public class XSSURL {
	//不需要过滤参数的
    private static final Map<String, String> WHITELIST_URL_MAP = new HashMap<String, String>();
    
    /**
     * 判断访问的方法是否是需要过滤
     */
    public static boolean isFilterUrl(String key) {
        if (WHITELIST_URL_MAP.containsKey(key)) {
            return false;
        }
        return true;
    }
}
