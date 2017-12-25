package com.hefei.frontend.framework.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hefei.frontend.framework.http.request.BaseRequest;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class RequestLoggerFilter implements Filter{
	
	private static final int TIMER_COST_LIMIT = 500;

	private static Logger logger =  Logger.getLogger(RequestLoggerFilter.class);
	
	public String getIpAddr(HttpServletRequest request) {
//		logger.info("  ip  x-forwarded-for : " + request.getHeader("x-forwarded-for"));
//		logger.info("  ip  Proxy-Client-IP : " + request.getHeader("Proxy-Client-IP"));
//		logger.info("  ip  WL-Proxy-Client-IP : " + request.getHeader("WL-Proxy-Client-IP"));
		return request.getRemoteAddr();
		
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		long timer = System.currentTimeMillis();
		HttpServletRequest request = (HttpServletRequest)arg0;
		String ip = getIpAddr(request);
		String url = request.getRequestURI();
		logger.info(" ip:" + ip + " timer:" +timer  +" url:"+ url + "  Start");
		try{
			BaseRequest baseRequest = new BaseRequest(ip, timer);
			RequestThreadLocal.set(baseRequest);
			
			chain.doFilter(arg0, arg1);
		}catch(Exception e){
			logger.error("ParameterFilter Error timer:"+timer, e);
		}finally{
			try {
				long cost = (System.currentTimeMillis() - timer);
				logger.info(RequestThreadLocal.getRequestStr() +" "+ url + " cost:" + cost + (cost > TIMER_COST_LIMIT?" warning-warning-warning":""));
			} catch (Exception e) {
				logger.error(RequestThreadLocal.getRequestStr() +" ParameterFilter Error clean  timer:"+timer, e);
			}
			RequestThreadLocal.clean();
		}
	}
	
	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
