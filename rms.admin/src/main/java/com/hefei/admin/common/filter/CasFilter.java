package com.hefei.admin.common.filter;

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

import com.hefei.api.cas.admin.auth.manager.IAuthorizationManager;
import com.hefei.api.cas.admin.auth.manager.impl.AuthorizationManager;
import com.hefei.common.exception.ClientException;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;

public class CasFilter implements Filter{
	
	private static Logger logger =  Logger.getLogger(CasFilter.class);

	IAuthorizationManager authorizationManager = new AuthorizationManager();
	
	public String getIpAddr(HttpServletRequest request) {
		return request.getRemoteAddr();
		
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		long timer = System.currentTimeMillis();
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String ip = getIpAddr(request);
		String url = request.getRequestURI();
		logger.info(" timer:" + timer);
		
		if(CasExclude.inExcludes(url)){
			chain.doFilter(request, response);
		}else{
			if(haveAuthorize(request, url)){
				chain.doFilter(request, response);
			}else{
				request.getRequestDispatcher(CasExclude.NOT_AUTHORIZATION_URL).forward(request, response);
			}
		}
	}
	
	private boolean haveAuthorize(HttpServletRequest request, String url){
		Long adminId = null;
		try {
			adminId = UserCookieUtil.getUserId(request, CookieConstants.COOKIE_NAME_WEB);
			url = url.substring(CasExclude.URL_SUBSTRING_WEB_CONTEXT.length(), url.length());
			boolean check = authorizationManager.adminHasAuthorization(adminId, url);
			logger.info("adminId=" + adminId + " url=" + url + " check=" + check);
			return check;
		} catch (ClientException e) {
			logger.error(" error", e);
		} catch (Exception e) {
			logger.error(" error", e);
		}
		return false;
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			CasExclude.init();
		}catch(Exception e){
			logger.error(" error", e);
			System.exit(0);
		}
	}
}