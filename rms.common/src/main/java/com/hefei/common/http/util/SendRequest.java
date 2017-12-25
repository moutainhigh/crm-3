package com.hefei.common.http.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.common.http.client.Client;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.util.JacksonUtil;
import com.hefei.common.util.StringUtils;
import com.hefei.common.util.encrypt.AESUtil;

public class SendRequest {
	
	//private static Logger logger = Logger.getLogger(SendRequest.class);

	public static String send(String url, Object dataJson, long timer, RequestHead head) throws Exception{
		return send(url, HeadUtil.getHeadStr(head), dataJson, null, timer);
	}
	
	public static String send(String url, Object dataJson, Object operationLogJson, long timer, RequestHead head) throws Exception{
		return send(url, HeadUtil.getHeadStr(head), dataJson, operationLogJson, timer);
	}
	
	public static String send(String url, ObjectNode dataJson, Object operationLogJson, long timer, RequestHead head) throws Exception{
		return send(url, HeadUtil.getHeadStr(head), dataJson, operationLogJson, timer);
	}
	
	public static String send(String url, String head, Object dataJson, Object operationLogJson, long timer) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		ObjectNode plainJson = JacksonUtil.createObjectNode();
		if(dataJson instanceof String) {
			dataJson = JacksonUtil.jsonToBean(dataJson.toString(), JsonNode.class);
		}
		if(dataJson != null){
			plainJson.putPOJO("data",dataJson);
		}
		if(operationLogJson != null){
			plainJson.putPOJO("operationLog", operationLogJson);
		}
		paramMap.put("head", URLEncoder.encode(head, "UTF-8"));
		paramMap.put("plain", JacksonUtil.beanToJson(plainJson));
		String content = HttpClientUtil.httpPost(url, paramMap, timer);
		if(content.indexOf("returnCode")!=-1){
			return content;
		}
		return content;
	}
}