package com.hefei.service.framework.base.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.common.http.response.BaseResponse;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.service.framework.base.request.BaseRequest;
import com.hefei.service.framework.base.request.BaseRequestHead;
import com.hefei.service.framework.base.request.RequestThreadLocal;

public class ParameterFilter  implements Filter {

	private static Logger logger = Logger.getLogger(ParameterFilter.class);

	private BaseRequestHead getHead(HttpServletRequest request, String ip, long timer, String url) throws Exception{
		String headPara = request.getParameter("head");
		logger.debug("url:" + url + " ip:" + ip + " timer:" + timer + " head-encoder:" + headPara);
		headPara = URLDecoder.decode(headPara, "UTF-8");
		logger.debug("url:" + url + " ip:" + ip + " timer:" + timer + " head:" + headPara);
		
		JsonNode obj = JacksonUtil.getJsonNode(headPara);
		String client = obj.get("client").asText();
		String token = obj.get("token").asText();
		return new BaseRequestHead(client, ip, timer, token);
	}

	private BaseRequest getBody(BaseRequestHead headObj, HttpServletRequest request) throws Exception{
		String plain = request.getParameter("plain");
		ObjectNode obj = JacksonUtil.jsonToBean(plain,ObjectNode.class);
		if (obj.has("data")) {
			JsonNode data = obj.get("data");
			plain = JacksonUtil.beanToJson(data);
		}
		return new BaseRequest(headObj, plain);
	}

	private void returnError(HttpServletResponse response, String returnCode, long timer) {
		try {
			BaseResponse res = new BaseResponse(returnCode);
			response.setContentType("text/xml; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setStatus(500);
			response.getWriter().write(JacksonUtil.beanToJson(res));
			response.getWriter().close();
		} catch (Exception e) {
			logger.info(" timer:" + timer + " error write", e);
		}
	}

	private boolean excludePath(String path) {
		return false;
	}

	public String getIpAddr(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		long timer = System.currentTimeMillis();
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String ip = getIpAddr(request);
		String url = request.getRequestURI();
		if(excludePath(url)){
			chain.doFilter(request, response);
		}else{
			try{
				BaseRequestHead headObj = getHead(request, ip, timer, url);
				if (headObj == null) {
					logger.info(" timer:" + timer + " head null param");
					returnError(response, ReturnCode.RETURN_CODE_ERROR_PARAM_NULL_CLIENT, timer);
				}else{
					BaseRequest requestObj = getBody(headObj, request);
					logger.info(" timer:" + timer +" params:"+ JacksonUtil.beanToJson(requestObj));
					if(StringUtils.isBlank(requestObj.getPlain())){
						returnError(response, ReturnCode.RETURN_CODE_ERROR_PARAM_NULL, timer);
					}else{
						RequestThreadLocal.set(requestObj);
						chain.doFilter(request, response);
					}
				}
			}catch(Exception e){
				logger.error("ParameterFilter Error timer:"+timer, e);
				returnError(response, ReturnCode.RETURN_CODE_ERROR, timer);
			}finally{
				try {
					logger.info(RequestThreadLocal.getLoggerStr() +" "+ url + " cost:" + (System.currentTimeMillis() - timer));
				} catch (Exception e) {
					logger.error(RequestThreadLocal.getLoggerStr() +" ParameterFilter Error clean  timer:"+timer, e);
				}
				RequestThreadLocal.clean();
			}
		}
	}
	
	@Override
	public void destroy() {
		
	}  
    
}
