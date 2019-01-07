package com.lms.ctaa.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.thoughtworks.xstream.alias.ClassMapper.Null;

import net.sf.json.JSONObject;

public class GetAndPostUtil {
	
	public static String sendpost(String url,String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		
		try {
			URL realurl = new URL(url);
			URLConnection connection = realurl.openConnection();
			connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) ");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//获取urlconnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			out.print(param);
			//flush输出缓冲流
			out.flush();
			//定义bufferedreader输入流读取url响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line ;
			while((line = in.readLine()) !=null) {
				result += line;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(url+"发生错误"+param);
		}finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null ) {
					in.close();
				}
			}catch (Exception e) {
				System.out.println("关闭流异常");// TODO: handle exception
			}
		}
		return result;
		
	}
	
	
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			URLConnection  connection = realUrl.openConnection();
			connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) ");
		//	connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setConnectTimeout(4000);
			connection.connect();
			//定义bufferedreader输入流来读取url响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while((line = in.readLine())!= null) {
				result +=line;
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(in!=null) {
					in.close();
				}
			}catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		
		return result;
	}
	
	
}
