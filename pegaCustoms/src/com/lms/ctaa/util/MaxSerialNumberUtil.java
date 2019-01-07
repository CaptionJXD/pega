package com.lms.ctaa.util;
/**
 * 流水号生成工具类
 * @author guomingjia
 *
 */
public class MaxSerialNumberUtil {

	/**
	 * 
	 * @param maxnumber  当前最大行号
	 * @return
	 */
	public static String getMaxSerialNumber(int maxnumber){
		String result="";
		if(maxnumber<=0){
   		     result="00001";
		}else if(maxnumber>0 && maxnumber<9999998){
			 maxnumber=maxnumber+1;
	   		 String maxString = maxnumber+"";
	   		 if(maxString.length()==1){
	   			 result="0000"+maxString;
	   		 }
	   		 if(maxString.length()==2){
	   			 result="000"+maxString;
	   		 }
	   		 if(maxString.length()==3){
	   			 result="00"+maxString;
	   		 }
	   		 if(maxString.length()==4){
	   			 result="0"+maxString;
	   		 }
	   		 if(maxString.length()==5){
	   			 result=maxString;
	   		 }
		}else if(maxnumber==9999998){
				 result="99999";
		}
		return result;
   }
}
