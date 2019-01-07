package com.lms.ctaa.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @Description: 解析其他系统平台下返回的报文
 * @author:zhengjiajia
 * @time:2017年4月12日
 * @return:Map<String,Object>
 */
public class ParseXMLUtil {

@SuppressWarnings("unchecked")
 public static Map<String,Object> parseStatuXml(String xmlStr) throws Exception{
	 InputStream iStream =  new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
	 SAXReader saxReader = new SAXReader();
	 Reader r = new InputStreamReader(iStream,"UTF-8");
	 Document document = saxReader.read(r);
	 Element employees = document.getRootElement();
	 Map<String,Object> map = new HashMap<String ,Object>();
	 for(Iterator<Element> i = employees.elementIterator(); i.hasNext();){
		 Element employee = (Element)i.next();
		 for(Iterator<Element> j = employee.elementIterator(); i.hasNext();){
			 Element node = (Element)j.next();
			 for(Iterator<Element> k = node.elementIterator();k.hasNext();){
				 Element node_child = (Element)k.next();
				 for(Iterator<Element> m = node_child.elementIterator();m.hasNext();){
					 Element node_m = (Element)m.next();
					 map.put(node_m.getName(), node_m.getText());
				 }
			 }
		 }
	 }
	 return map;
	 
 }

}
