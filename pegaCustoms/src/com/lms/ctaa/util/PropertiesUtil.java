package com.lms.ctaa.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/**
 * @Description 读取配置文件的操作工具类
 * @author zhengjiajia
 * @date 2017年4月13日
 * @version 1.0
 */
public class PropertiesUtil {
   
	static PropertiesUtil pu;//创建对象pu
	private static Hashtable<String,Properties> register = new Hashtable<String,Properties>();
	private PropertiesUtil(){
		super();
	}
	
	/**
	 * @Description:取得PropertiesUtil的一个实例
	 * @return
	 */
	public static PropertiesUtil getInstance(){
		if(pu==null)
			pu = new PropertiesUtil();
		return pu;
	}
	
	//读取配置文件
	public Properties getProperties(String fileName){
		InputStream is = null;
		Properties p = null;
		try {
			p = (Properties)register.get(fileName);
			if(p == null){
				try{
					is = new FileInputStream(fileName);
				}catch(Exception e){
					if(fileName.startsWith("/"))
						is = PropertiesUtil.class.getResourceAsStream(fileName);
					else 
						is = PropertiesUtil.class.getResourceAsStream("/"+fileName);
				}
				if(is==null){
					System.out.println("未找到名称为"+fileName+"的资源!");
				}
				p = new Properties();
				p.load(is);
				register.put(fileName, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
					is=null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return p;
	}
	
	//根据文件名和key值获得value的值
	public String getPropertyValue(String fileName,String strKey){
		Properties p = getProperties(fileName);
		try {
			return (String)p.getProperty(strKey);
			//return new String(((String) p.getProperty(strKey)).getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String servicePath(HttpServletRequest req){
		String realPath = req.getSession().getServletContext().getRealPath("/");
		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));// 截取最后一个\
		// 截取项目名
		realPath=realPath.substring(0, realPath.lastIndexOf("\\"));
		return realPath;
	}
	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	public static void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public  static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
}

 