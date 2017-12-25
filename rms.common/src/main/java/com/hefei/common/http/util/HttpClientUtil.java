package com.hefei.common.http.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.hefei.common.util.StringUtils;

public class HttpClientUtil {

	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	private static final int TIMEOUT = 3000;
	private static final int MAXTOTALCONNECTIONS = 400;
	private static final int CONNECTIONMANAGERTIMEOUT = 10000;
	private static final String CHARSET = "UTF-8";

	//
	//
	// private static ThreadSafeClientConnManager cm = null;
	// static {
	// SchemeRegistry schemeRegistry = new SchemeRegistry();
	// schemeRegistry.register(new Scheme("http", 80,
	// PlainSocketFactory.getSocketFactory()));
	//
	// cm = new ThreadSafeClientConnManager(schemeRegistry);
	// try {
	// int maxTotal = 100;
	// cm.setMaxTotal(maxTotal);
	// } catch (NumberFormatException e) {
	// log.error(
	// "Key[httpclient.max_total] Not Found in systemConfig.properties",
	// e);
	// }
	// // 每条通道的并发连接数设置（连接池）
	// try {
	// int defaultMaxConnection = 50;
	// cm.setDefaultMaxPerRoute(defaultMaxConnection);
	// } catch (NumberFormatException e) {
	// log.error(
	// "Key[httpclient.default_max_connection] Not Found in systemConfig.properties",
	// e);
	// }
	// }
	//
	// public static HttpClient getHttpClient() {
	// HttpParams params = new BasicHttpParams();
	// params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
	// HttpVersion.HTTP_1_1);
	// params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); //
	// 3000ms
	// return new DefaultHttpClient(cm, params);
	// }
	//
	private HttpClientUtil() {
	}

	public static String httpPost(String url, Map<String, Object> paramMap,long timer) throws Exception {
		String content = HttpClientUtil.httpPostGZip(url, paramMap, CHARSET,timer);
		return content;
	}

	private static String httpPostGZip(String url,Map<String, Object> paramMap, String code, long timer) {
		long funTimer = System.currentTimeMillis();
		logger.info(timer + " request[" + url + "]  param["+ (paramMap == null ? "NULL" : paramMap.toString()) + "]");
		String content = null;
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		method.setHeader("Connection", "close");//不做KEEP- ALIVE 
		method.setHeader("Accept-Encoding", "gzip, deflate");//数据压缩
		method.setHeader("Accept", "text/plain");
		try {
			
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(getParamsList(paramMap),code);
			method.setEntity(formEntity);
			HttpResponse response = httpclient.execute(method);
			int status = response.getStatusLine().getStatusCode();
			logger.info(timer + " status=" + status);
			if (status == HttpStatus.SC_OK) {
				boolean isGzip = false;

//				Header[] headerall = response.getAllHeaders();
//				if(headerall != null && headerall.length > 0){
//					for(Header header: headerall){
//						logger.info("resss    " + header.getName() + "   "  + header.getValue());
//					}
//				}
				
				Header[] headers = response.getHeaders("Content-Encoding");
				if(headers != null && headers.length > 0){
					for(Header header: headers){
						if(header.getValue().toLowerCase().indexOf("gzip") != -1){
							isGzip = true;
						}
					}
				}
				logger.info(timer + " isGzip=" + isGzip);
				HttpEntity httpEntity = response.getEntity();
				if (isGzip) {
					InputStream is = httpEntity.getContent();
					GZIPInputStream gzin = new GZIPInputStream(is);
					InputStreamReader isr = new InputStreamReader(gzin, code);
					java.io.BufferedReader br = new java.io.BufferedReader(isr);
					StringBuffer sb = new StringBuffer();
					String tempbf;
					while ((tempbf = br.readLine()) != null) {
						sb.append(tempbf);
						sb.append("\r\n");
					}
					isr.close();
					gzin.close();
					content = sb.toString();
				} else {
					content = EntityUtils.toString(httpEntity,code);
				}
			}else if(status == HttpStatus.SC_INTERNAL_SERVER_ERROR){
				HttpEntity httpEntity = response.getEntity();
				content = EntityUtils.toString(httpEntity,code);
			}else {
				method.abort();
			}
		} catch (Exception e) {
			method.abort();
			logger.error(timer + " request[" + url + "] ERROR", e);
		} finally {
			if(!method.isAborted()){
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(timer + " request[" + url + "] cost:" + (System.currentTimeMillis() - funTimer));
		logger.info(timer + " request[" + url + "] response[" + content + "]");
		return content;
	}
//
//	private static String httpPost(String url, Map<String, Object> paramMap,
//			String code, long timer) {
//		long funTimer = System.currentTimeMillis();
//		logger.info(timer + " request[" + url + "]  param["
//				+ (paramMap == null ? "NULL" : paramMap.toString()) + "]");
//		String content = null;
//		if (StringUtils.isEmpty(url)) {
//			return null;
//		}
//		PostMethod method = new PostMethod(url);
//		method.setRequestHeader("Connection", "close");
//		// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
//		method.addRequestHeader("Accept", "text/plain");
//		try {
//			if (paramMap != null && !paramMap.isEmpty()) {
//				for (Map.Entry<String, ?> entry : paramMap.entrySet()) {
//					method.addParameter(new BasicNameValuePair(entry.getKey(),
//							entry.getValue().toString()));
//				}
//			}
//			int status = client.executeMethod(method);
//			if (status != 200) {
//				method.abort();
//			}
//			logger.info(timer + " status=" + status + " statusline="
//					+ method.getStatusLine());
//			content = new String(method.getResponseBody(), code);
//		} catch (Exception e) {
//			method.abort();
//			logger.error(timer + " request[" + url + "] ERROR", e);
//		} finally {
//			method.releaseConnection();
//		}
//		logger.info(timer + " request[" + url + "] cost:"
//				+ (System.currentTimeMillis() - funTimer));
//		logger.info(timer + " request[" + url + "] response[" + content + "]");
//		return content;
//	}
	
	/**
	 * 将传入的键/值对参数转换为NameValuePair参数集
	 *
	 * @param paramsMap
	 *            参数集, 键/值对
	 * @return NameValuePair参数集
	 */
	private static List getParamsList(Map<String, Object> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List params = new ArrayList();
		for (Map.Entry<String, ?> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue().toString()));
		}

		return params;
	}
}
