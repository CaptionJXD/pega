package com.lms.ctaa.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 类型转换工具类
 * @author gaoang
 *
 */
public class TransformUtils {
	
	/**
	 * 将实体对象集合转化成JSON
	 * @param t
	 * @return
	 */
	public static <T>String transformObject2Json(List<T> t){
		JSONArray jsonArray = JSONArray.fromObject(t);
		String jsonString = jsonArray.toString();
		return jsonString;
	}
	
	public static String transformMap2Json(Map<String,Object> t){
		JSONArray jsonArray = JSONArray.fromObject(t);
		String jsonString = jsonArray.toString();
		return jsonString;
	}
	
	/**
	 * 将JSON字符串转化成实体类对象
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2Object(String jsonString,Class<T> pojoClass) throws Exception{
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoClass);
		return (T)pojo;
	}
}
