package com.lms.ctaa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * Calendar类型装换成JSON数据
 * @author guomingjia
 *
 */
public class JsonCalendarValueProcessor implements JsonValueProcessor{
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private String datePattern="yyyy-MM-dd";
	public JsonCalendarValueProcessor(){
		super();
	}

	public JsonCalendarValueProcessor(String format){
		super();
		if(format!=null && !"".equals(format)){
			this.datePattern =format;
		}
	}
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}
 //用于格式化Calendar类型的值
	private Object process(Object value){
		try{
			if(value instanceof Calendar){
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.CHINESE);
				return sdf.format(((Calendar)value).getTime());
			}
			return value == null?"":value.toString();
		}catch(Exception e){
			return "";
		}
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	
	public static String toJson(Object value){
		try {
			return OBJECT_MAPPER.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
}
