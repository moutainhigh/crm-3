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

import com.hefei.common.util.StringUtils;
import com.hefei.frontend.framework.base.CookieConstants;
import com.hefei.frontend.framework.base.FrontendConstants;
import com.hefei.frontend.framework.base.UserCookieUtil;
import com.hefei.frontend.framework.base.user.UserCookie;
import com.hefei.frontend.framework.http.request.CommonParameter;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;

public class LoginFilter implements Filter{
	
	private static Logger logger =  Logger.getLogger(LoginFilter.class);

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		try{
			String url = request.getRequestURI();
			
			UserCookie userCookie = UserCookieUtil.getUserCookie(request, CookieConstants.COOKIE_NAME_WEB);
			if(userCookie != null){
				CommonParameter commonParameter = new CommonParameter();
				commonParameter.setUserCookie(userCookie);
				CommonParameterThreadLocal.set(commonParameter);
			}
			
			if(CasExclude.inExcludes(url)){
				chain.doFilter(request, response);
			}else{
				Long userId = CommonParameterThreadLocal.getUserId();
				logger.info(RequestThreadLocal.getTimer() + " userId:"+userId);
				if(userId == null){
					String param = request.getQueryString();
					if(StringUtils.isNotBlank(param)){
						url = url + "?" + param;
					}
					url = java.net.URLEncoder.encode(url, "UTF-8");
					response.sendRedirect(FrontendConstants.LOGIN_URL + "?backurl=" + url);
				}else{
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
		}finally{
			CommonParameterThreadLocal.clean();
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			LoginExclude.init();
		}catch(Exception e){
			logger.error(" error", e);
			System.exit(0);
		}
	}
}