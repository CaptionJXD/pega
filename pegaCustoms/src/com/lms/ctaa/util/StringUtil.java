package com.lms.ctaa.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 字符串操作工具类
 * @author zhengjiajia
 * @date 2017年4月13日
 * @version 1.0
 */
public class StringUtil {

	/**
	 * @Description:把字符串数组转换乘字符串
	 * @param temp  字符串数组
	 * @return   拼接好的字符串
	 */
	public static String getString(String[] temp){
		StringBuffer sb = new StringBuffer();
		for(String obj : temp){
			sb.append(obj).append(",");
		}
		if(sb.length()>0){
			return sb.substring(0,sb.length()-1);
		}else{
			return null;
		}
	}

	//数组转字符串
	public static String array2string(String[] array){
		String temp = "";
		for(String data : array){
			temp+=data+",";
		}
		return temp;
	}
	
	/**
	 * @Description:将一符字符串数组以某特定的字符串作为分割来变成字符串
	 * @param strs     字符串数组
	 * @param token    分隔字符串
	 * @return    以token为分隔的字符串
	 */
	public static String join(String[] strs,String token){
		if(strs==null)
		   return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if(i != 0)
				sb.append(token);
		   sb.append(strs[i]);
		}
		return sb.toString();
	}
	
	/**
	 * @Description:验证字符串合法性
	 * @param str  需要验证的字符串
	 * @param test     非法字符串(如:"~！@#‘；，。...")
	 * @return   true:非法，false:合法
	 */
	public static boolean check(String str,String test){
		if(str == null || ("").equals(str))
			return true;
		boolean flag = false;
		for (int i = 0; i < test.length(); i++) {
			if(str.indexOf(test.charAt(i)) != -1){
				flag = true;
				break;
			}
		}
		 return flag;
	}
	
	/**
	 * @Description:字符串数组是否包含制定的字符串
	 * @param strings   字符串数组
	 * @param string     字符串
	 * @param caseSensitive   是否大小写敏感
	 * @return   包含时返回true，否则返回false
	 */
	public static boolean contains(String[] strings,String string,boolean caseSensitive){
		for(int i=0;i<strings.length;i++){
			if(caseSensitive == true){
				if(strings[i].equals(string)){
					return true;
				}
			} else {
				if(strings[i].equalsIgnoreCase(string)){
					return true;
				}
			}
		}
		 return false;
	}
	
	/**
	 * @Description:将字符串转成日期   日期格式：yyyy-MM-dd
	 * @param str   字符串
	 * @return
	 */
	public static Date parseDate(String str){
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return (Date)df.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @Description:字符填充
	 * @param source  源字符串
	 * @param filter  填充字符，如0或者*等
	 * @param length  最终填充后字符串的长度
	 * @return        最终填充后字符串
	 */
	public static String fill(String source,String filter,int length){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length-source.length(); i++) {
			sb.append(filter);
		}
		sb.append(source);
		return sb.toString();
	}
	
	//判断字符串是否为空
	public static boolean isEmpty(String str){
		if(str == null){
			return true;
		} else if (str.trim().length() == 0){
			return true;
		} else {
			return false;
		}
	}
	
	//判断字符串是否为空
	public static boolean isNotEmpty(String str){
		if(str == null){
			return false;
		} else if(str.trim().length() == 0){
			return false;
		}else {
			return true;
		}
	}
	
	//默认的空值
	public static final String EMPTY = "";
	
	/**
	 * @Description:截取并保留标志位之前按的字符串
	 * @param str  字符串
	 * @param expr   分隔符
	 * @return
	 */
	public static String substringBefore(String str,String expr){
	     if(isEmpty(str) || expr == null){
	    	 return str;
	     }
	     if(expr.length()==0){
	    	 return EMPTY;
	     }
	     int pos = str.indexOf(expr);
	     if(pos == -1){
	    	 return str;
	     }
	     return str.substring(0,pos);
	}
	
	/**
	 * @Description:截取并保留标志位之后的字符串
	 * @param str  字符串
	 * @param expr  分隔符
	 * @return
	 */
	public static String substringAfter(String str,String expr){
		if(isEmpty(str)){
			return str;
		}
		if(expr == null){
			return EMPTY;
		}
		int pos = str.indexOf(expr);
		if(pos == -1){
			return EMPTY;
		}
		return str.substring(pos+expr.length());
	}
	
	/**
	 * @Description:截取并保留最后一个标志位之前的字符串
	 * @param str   字符串
	 * @param expr   分隔符
	 * @return
	 */
	public static String substringBeforeLast(String str,String expr){
		if(isEmpty(str) || isEmpty(expr)){
			return str;
		}
		int pos = str.lastIndexOf(expr);
		if(pos ==-1){
			return str;
		}
		return str.substring(0,pos);
	}
	
	/**
	 * @Description:截取并保留最后一个标志位之后的字符串
	 * @param str  字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringAfterLast(String str,String expr){
		if(isEmpty(str)){
			return str;
		}
		if(isEmpty(expr)){
			return EMPTY;
		}
		int pos = str.lastIndexOf(expr);
		if(pos==-1 ||pos == (str.length() - expr.length())){
			return EMPTY;
		}
		return str.substring(pos+expr.length());
	}
	
	//改变字符串编码到UTF-8
	public static String toUTF8(String src){
		if(isEmpty(src))
		   return "";
		String s = null;
		try {
			s = new String(src.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//改变字符串编码到GBK
	public static String toGBK(String src){
		if(isEmpty(src))
			   return "";
			String s = null;
			try {
				s = new String(src.getBytes("ISO-8859-1"),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		  }
		return s;
	}
	
	//截取字符串(最后位置的制定字符到字符结尾)
	public static String getLastString(String str,String charset){
		return str.substring(str.lastIndexOf(".")+1);
	}
	
	//判断字符串中是否含有中文
	public static boolean containsCn(String s){
		if(s!=null){
			Pattern pattern = Pattern.compile("[\\u4e00=\\u9fa5]");
			Matcher matcher = pattern.matcher(s);
			return matcher.find();
		}
		return false;
	}
	/**
	 * @Description:将字符串的制定位置替换成新的字符
	 * @param str   原字符串
	 * @param n     指定要替换的位数
	 * @param newChar 要替换的字符
	 * @return        替换后的字符串
	 * @throws Exception
	 */
	public static String replace(String str,int n,String newChar) throws Exception{
		String s1 = "";
		String s2 = "";
			s1 = str.substring(0,n-1);
			s2 = str.substring(n,str.length());
		return s1 + newChar + s2;
	}
	
	//转换String到int，null或空字符，返回0，true返回1，false返回0
	public static int parseInt(String flag){
		if(isEmpty(flag))
			return 0;
		else if(flag.equals("true"))
			return 1;
		else if(flag.equals("false"))
			return 0;
		return Integer.parseInt(flag);
	}
	
	//转换String到long
	public static long parseLong(String flag){
		if(isEmpty(flag))
			return 0;
		return Long.parseLong(flag);
	}
	
	//返回一个整数数组
	public static int[] parseInt(String[] s){
		if(s==null){
			return (new int[0]);
		}
		int[] result = new int[s.length];
		try {
			for (int i = 0; i < s.length; i++) {
				result[i] = Integer.parseInt(s[i]);
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	//判断字符串数组是否为空
	public static boolean nil(String[] s){
		return (s==null || s.length ==0);
	}
	//将null转换成为'--'
	public static Object replaceNull(Object obj){
		if(obj == null || ("").equals(obj)){
			obj = "--";
		}
		return obj;
	}
	
	//格式化金额
	public static String formatMoney(String s,int len){
		if(s ==null || s.length() < 1){
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if(len == 0){
			formater = new DecimalFormat("###,###");
		} else{
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for(int i= 0;i<len;i++){
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if(result.indexOf(".")==-1){
			result = "￥" + result +".00";
		}else{
			result = "￥" + result;
		}
		return result;
	}
	
	/**
	 * 字母数字组合生成随机字符串
	 * @param length  字符串的位数
	 * @return
	 */
	public static String getStrNumRandom(Integer length){
		StringBuilder str= new StringBuilder();
		Random random = new Random();
		for(int i=0;i<length;i++){
			boolean b= random.nextBoolean();
			if(b){
				int choice = random.nextBoolean()?65:97;
				str.append((char)(choice+random.nextInt(26)));
			}else{
				str.append(random.nextInt(10));
			}
		}
		return str.toString();
	}
	
	//添加空格方法
		public static String  addSpace(String str,int len){
			   if(null!=str && !"".equals(str)){
				   int strlen= str.length();
					int countlen= len-strlen;
					   for(int i=0;i<countlen;i++){
						   str+=" ";
					   }
			   }else{
				   for(int i=0;i<len;i++){
					   str+=" ";
				   }
			   }
		   return str;
			   
		}
	
 }

 