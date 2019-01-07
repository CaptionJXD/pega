package com.lms.ctaa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 对于一些格式进行验证
 * @author zhengjiajia
 * @date 2017年4月14日
 * @version 1.0
 */
public class PatternUtil {
	
	public static boolean isNumber(String str){
		//判断小数点后一位的数字的正则表达式
		Pattern pattern =Pattern.compile("^+(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		Matcher match = pattern.matcher(str);
		if(match.matches()==false){
			return false;
		}else{
			return true;
		}
	}
	
	//取出字符串中的数字
	public static String isNumberd(String str){
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher match = pattern.matcher(str);
		System.out.println(match.replaceAll("").trim());
		return match.replaceAll("").trim();
	}
	
	public static void main(String[] args) {
		PatternUtil.isNumberd("dsf3432dsgfsdg45");
	}
}

 