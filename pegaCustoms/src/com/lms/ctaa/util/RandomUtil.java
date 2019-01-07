package com.lms.ctaa.util;

import java.util.Random;

/**
 * @Description 产生随机数的操作工具类
 * @author zhengjiajia
 * @date 2017年4月13日
 * @version 1.0
 */
public class RandomUtil {
	
	public static final String ALLCHAR ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";
	
	/**
	 * @Description: 返回一个定长的随机字符串（只包含大小写字母、数字）
	 * @param length  随机字符串长度
	 * @return  随机字符串
	 */
	public static String generateString(int length){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i= 0;i<length;i++){
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}
	
	/**
	 * @Description:返回一个定长随机纯字母字符串(只包含大小写字母)
	 * @param length  随机字符串长度
	 * @return     随机字符串
	 */
	public static String generateMixString(int length){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}
	
	/**
	 * @Description:返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * @param length   随机字符串长度
	 * @return    随机字符串
	 */
	public static String generateLowerString(int length){
		return generateMixString(length).toLowerCase();
	}
	
	/**
	 * @Description:返回一个定场地额随机纯小写字母的字符串(只包含大小写字母)
	 * @param length   随机字符串长度
	 * @return     随机字符串
	 */
	public static String generateUpperString(int length){
		return generateMixString(length).toUpperCase();
	}
	
	/**
	 * @Description:生成一个定长的纯0的字符串
	 * @param length   随机字符串长度
	 * @return    纯0 的字符串
	 */
	public static String generateZeroString(int length){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}
	
	/**
	 * @Description:根据数字生成一个定长的字符串，长度不够前面补0
	 * @param num  数字
	 * @param fixdlength    字符串长度
 	 * @return   定长的字符串
	 */
	public static String toFixdLengthString(long num,int fixdlength){
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if(fixdlength - strNum.length() >= 0){
			sb.append(generateZeroString(fixdlength-strNum.length()));
		} else {
			throw new RuntimeException("将数字" +num +"转化为长度为"+fixdlength +"的字符串发生异常!");
		}
		sb.append(strNum);
		return sb.toString();
	}
	
	/**
	 * @Description:每次生成的len位数都不相同
	 * @param param  
	 * @param len   长度
	 * @return   定长的数字
	 */
	public static int getNotSimple(int[] param,int len){
		Random rand = new Random();
		for (int i = param.length; i >1;i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[index];
			param[i-1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result*10 +param[i];
		}
		return result;
	}
}

 