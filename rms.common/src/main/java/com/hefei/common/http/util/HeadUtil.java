package com.hefei.common.http.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hefei.common.http.request.RequestHead;
import com.hefei.common.util.JacksonUtil;

public class HeadUtil {

	public static String getHeadStr(RequestHead head){
		ObjectNode json = getHeadJson(head);
		return JacksonUtil.beanToJson(json);
	}
	public static ObjectNode getHeadJson(RequestHead head){
		ObjectNode json = JacksonUtil.createObjectNode();
		json.put("client", head.getClientId());
		json.put("token", head.getToken());
		return json;
	}
}
