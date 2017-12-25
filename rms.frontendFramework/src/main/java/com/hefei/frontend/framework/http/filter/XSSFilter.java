package com.hefei.frontend.framework.http.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hefei.frontend.framework.http.filter.util.XSSExclude;
import com.hefei.frontend.framework.http.xss.XssHttpServletRequestWrapper;

public class XSSFilter implements  javax.servlet.Filter{
	
	private static Logger logger =  Logger.getLogger(XSSFilter.class);
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpServletResponse resp = (HttpServletResponse)response;
    	String url = req.getRequestURI();
    	
    	if(XSSExclude.inExcludes(url)){
    		chain.doFilter(req, resp);
    	}else{
    		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
    	}
    }

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
		try{
			XSSExclude.init();
		}catch(Exception e){
			logger.error(" error", e);
			System.exit(0);
		}
	}

}
