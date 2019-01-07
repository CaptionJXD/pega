package com.lms.ctaa.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 获取IP地址工具类
 * @author zhengjiajia
 * @date 2017年4月17日
 * @version 1.0
 */
public class IpUtil {

	public static String getIpAddr(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader("WL-Proxy-Client_IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getRemoteAddr();
			if(("127.0.0.1").equals(ipAddress)||("0:0:0:0:0:0:0:1").equals(ipAddress)){
				//根据网卡取本机配置IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真是IP,多个IP按照','分割
		if(ipAddress != null && ipAddress.length() > 15){
			if(ipAddress.indexOf(",") > 0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
		
	}
	
	/**
	 * 获取当前ip地址
	 * 
	 * @return
	 */
	public static String getIP() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().toString();
			ip = ip.substring(ip.indexOf("/") + 1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
}

 