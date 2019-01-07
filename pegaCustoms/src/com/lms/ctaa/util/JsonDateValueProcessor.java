package com.lms.ctaa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * date类型装换成JSON数据
 * @author guomingjia
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor{
	
	private String datePattern="yyyy-MM-dd";
	public JsonDateValueProcessor(){
		super();
	}

	public JsonDateValueProcessor(String format){
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
 //用于格式化date类型的值
	private Object process(Object value){
		try{
			if(value instanceof Date){
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.CHINESE);
				return sdf.format((Date)value);
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
	
}
