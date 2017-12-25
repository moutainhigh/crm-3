package com.hefei.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonUtil {
	private static ObjectMapper objectMapper =null;
	private static Logger logger = Logger.getLogger(JacksonUtil.class);
	static{
		objectMapper = new ObjectMapper();
		//格式化json
//		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
		//对象属性不存在的时候跳过该属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//jackson null是否参与序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		//Include.Include.ALWAYS 默认 
		//Include.NON_DEFAULT 属性为默认值不序列化 
		//Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 
		//Include.NON_NULL 属性为NULL 不序列化 
	}
	private JacksonUtil() {}
	
	/**
	 * 
	* @Title: beanToJson 
	* @Description: TODO(Bean & List 转  JSON) 
	* @param @param obj
	* @param @return  参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String beanToJson(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("beanToJson error:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: jsonToBean 
	* @Description: TODO(JSON 转 Bean) 
	* @param @param str
	* @param @param clazz
	* @param @return  参数说明 
	* @return T    返回类型 
	* @throws
	 */
	public static <T> T jsonToBean(String str, Class<T> clazz){
		try {
			return objectMapper.readValue(str, clazz);
		} catch (IOException e) {
			logger.error("jsonToBean error:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static <T> T jsonToBean(JsonNode jsonNode,Class<T> clazz){
		try {
			if(jsonNode != null && clazz != null)
				return objectMapper.readValue(jsonNode.toString(), clazz);
			return null;
		} catch (IOException e) {
			logger.error("jsonToBean error:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static <T> List<T> jsonToPrimaryArray(JsonNode arrayNode, Class<T> clazz){
		if(arrayNode == null || !arrayNode.isArray()){
			return null;
		}
		if(arrayNode == null || arrayNode.isNull() || arrayNode.size() <= 0)
			return null;
		try {
			List<T> list = new ArrayList<T>();
			for(int i=0; i< arrayNode.size(); i++){
				JsonNode jsonNode = arrayNode.get(i);
				T value = objectMapper.readValue(jsonNode.asText(), clazz);
				list.add(value);
			}
			return list;
		} catch (Exception e) {
			logger.error("getJsonNode error:",e);
			throw new RuntimeException(e.getMessage());
		} 
		
	}
	public static <T> T jsonToCollections(String str,Class<?> collectionClass, Class<?>... elementClasses){
		try {
			
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		    return objectMapper.readValue(str, javaType);
		} catch (IOException e) {
			logger.error("jsonToBean error:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static JsonNode getJsonNode(String plain) {
		JsonNode root = null;
		try {
			root = objectMapper.readTree(plain);
		} catch (Exception e) {
			logger.error("getJsonNode error:",e);
			throw new RuntimeException(e.getMessage());
		} 
		return root; 
	}
	
	public static ObjectNode createObjectNode() {
		return objectMapper.createObjectNode();
	}
	
	public static Long getLong(JsonNode jsonNode, String nodeName) {
		if(jsonNode.has(nodeName)) {
			jsonNode = jsonNode.get(nodeName);
			if(!jsonNode.isNull()) {
				return jsonNode.asLong();
			}
		}
		return null;
	}
	
	public static Integer getInt(JsonNode jsonNode, String nodeName) {
		if(jsonNode.has(nodeName)) {
			jsonNode = jsonNode.get(nodeName);
			if(!jsonNode.isNull()) {
				return jsonNode.asInt();
			}
		}
		return null;
	}
	public static Double getDouble(JsonNode jsonNode, String nodeName) {
		if(jsonNode.has(nodeName)) {
			jsonNode = jsonNode.get(nodeName);
			if(!jsonNode.isNull()) {
				return jsonNode.asDouble();
			}
		}
		return null;
	}
	public static Date getDate(JsonNode jsonNode, String nodeName) {
		String dateStr = getString(jsonNode, nodeName);
		if(StringUtils.isNotBlank(dateStr)) {
			try {
				return DateUtil.string2date(dateStr, DateUtil.FORMAT_YYYY_MM_DDHHMMSS);
			} catch (ParseException e) {
				logger.error("The date conversion errors:",e);
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	public static String getString(JsonNode jsonNode, String nodeName) {
		if(jsonNode.has(nodeName)) {
			jsonNode = jsonNode.get(nodeName);
			if(!jsonNode.isNull()) {
				return jsonNode.asText();
			}
		}
		return null;
	}
	
	public static Boolean getBoolean(JsonNode jsonNode, String nodeName) {
		if(jsonNode.has(nodeName)) {
			jsonNode = jsonNode.get(nodeName);
			if(!jsonNode.isNull()) {
				return jsonNode.asBoolean();
			}
		}
		return null;
	}
	
	/**
	 * 将Map<String , Object>数据转为 String
	 * @param items
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String convertToJsonStrs(Map<String , Object> map){
		if(null == map || map.isEmpty()){
			return null;
		}
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("The date convertToJsonStrs errors:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 将json数据 转为Map<String , Object>
	 * @param jsonItems
	 * @return
	 */
	public static Map<String , Object> convertToMaps(String jsonItems){
		if(StringUtils.isBlank(jsonItems)){	
			return null;
		}
		try {
			TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>(){};
			return objectMapper.readValue(jsonItems, typeReference);
		} catch (Exception e) {
			logger.error("The date convertToMaps errors:",e);
			throw new RuntimeException(e.getMessage());
		}
	}
}
