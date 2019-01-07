package com.lms.ctaa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @Description 时间操作工具类
 * @author zhengjiajia
 * @date 2017年4月13日
 * @version 1.0
 */
public class DateUtil {
	
	Calendar calendar = null;
	
	public DateUtil(){
		calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDate1(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDate2(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(Calendar.getInstance().getTime());
	}

	public static String getCurrentDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return  sdf.format(date);
	}
	
	//返回年
	public int getYear(){
		return calendar.get(Calendar.YEAR);
	}

	//返回月
	public String getMonth(){
		int m = getMonthInt();
		String[] months = new String[]{
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
		};
		if(m > 12){
			return "Uknown to Month";
		}
		return months[m -1];
	}
	
	//返回月
	public String getStrMonth(){
		int m = getMonthInt();
		String[] months = new String[]{
			"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
		};
		if(m > 12){
			return "Uknown to Month";
		}
		return months[m - 1];
	}
	
	public int getMonthInt(){
		return 1 + calendar.get(Calendar.MONTH);
	}
	
	//返回星期几
	public String getDay(){
		int x = getDayOfWeek();
		String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7"};
		if(x > 7){
			return "Unknow to Man";
		}
		return days[x - 1];
	}
	
	//返回当前时间是一个月中的哪一天
	public String getStrDayOfMonth(){
		int i = calendar.get(Calendar.DAY_OF_MONTH);
		String s= "";
		s= s.valueOf(i);
		if(s.length()==1){
			s = "0"+s;
		}
		return s;
	}
	
	public int getDayOfWeek(){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	//当前日期上周日
	public static String lastSunday(){
		Calendar cal = Calendar.getInstance();
		//n为推迟的周数，1本周，-1向前推迟一周，2,下一周，以此类推
	    int n= 0;
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY(TUESDAY..)
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		String sunday = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(cal.getTime());
		return sunday;
	}
	
	//获取上个月月初时间
	public static String lastMonthFristDay(){
		//java 代码如何获取当前时间的上一个月的月末时间
		Calendar cal = Calendar.getInstance();
		//设置天数为-1，表示当月减一天即为上一个月的月末时间
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		//格式化输出年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(cal.getTime());
	}
	
	//获取上个月的月末时间
	public static String lastMonthLastDay(){
		//java 代码如何获取当前时间的上一个月的月末时间
		Calendar cal = Calendar.getInstance();
		//设置天数为-1天，表示当月减一天即为上一个月的月末时间
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		//格式化输出年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return sdf.format(cal.getTime());
	}
	
	//获取当前时间的上一天日期
	public static String getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);//得到前一天
		Date date = cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(date);
		return time;
	}
	
	//返回 --月/日/年
	public String getDate1(){
		return getStrMonth()+"/"+getStrDayOfMonth() +"/"+ getYear();
	}
	
	//返回年/月/日
	public String getDate2(){
		return getYear()+"/"+getStrMonth()+"/"+getStrDayOfMonth();
	}
	
	//返回年-月-日
	public String getDate3(){
		return getYear()+"-"+getStrMonth()+"-"+getStrDayOfMonth();
	}
	
	//返回年月日
	public String getDate4(){
		return getYear()+getStrMonth()+getStrDayOfMonth();
	}
	
	//获得当天时间
	public static String getTime(String parrten){
		String timeStr ;
		if(parrten == null || ("").equals(parrten)){
			parrten = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		Date cday = new Date();
		timeStr = sdf.format(cday);
		return timeStr;
	}
	
	//返回当前时间:时：分：秒
	//如: 12:02:03
	public String getTime(){
		return getStrHour() + getStrMinute() + getStrSecond();
	}
	
	//返回小时(字符型)
	public String getStrHour(){
		int i = calendar.get(Calendar.HOUR_OF_DAY);
		String s = "";
		s = s.valueOf(i);
		if(s.length() == 1){
			s = "0"+s;
		}
		return s;
	}
	
	//返回分钟(整数)
	public int getMinute(){
		return calendar.get(Calendar.MINUTE);
	}
	
	//返回分钟(字符型)
	public String getStrMinute(){
		int i = calendar.get(Calendar.MINUTE);
		String s = "";
		s = s.valueOf(i);
		if (s.length() == 1){
			s = "0"+ s;
		}
		return s;
	}
	
	//返回秒(整形)
	public int getSecond(){
		return calendar.get(Calendar.SECOND);
	}
	
	//返回秒(字符型)
	public String getStrSecond(){
		int i = calendar.get(Calendar.SECOND);
		String s = "";
		s = s.valueOf(i);
		if(s.length() == 1){
			s = "0"+1;
		}
		return s;
	}
	
	/**
	 * @Description:比较两个字符串时间的大小，不取结果地绝对值
	 * @param t1     时间1
	 * @param t2     时间2
	 * @param parrten  时间格式 yyyy-MM-dd
	 * @return  返回long = 0 相等     >0 t1>t2  <0  t1<t2 
	 */
	public static long compareStringTimeEx(String t1,String t2,String parrten){
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		long dt1 = 0;
		long dt2 = 0;
		try {
			dt1 = sdf.parse(t1).getTime();
			dt2 = sdf.parse(t2).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long lg = dt1 - dt2 ;
		long days = lg /(long)(1000 * 60 * 60 * 24);
		return days;
	}
	
	/**
	 * @Description:比较两个字符串时间的大小，不取结果地绝对值
	 * @param t1     时间1
	 * @param t2     时间2
	 * @param parrten  时间格式 yyyy-MM-dd HH:mm:ss
	 * @return  返回long = 0 相等     >0 t1>t2  <0  t1<t2 
	 */
	public static long compareStringTimeEx2(String t1,String t2,String parrten){
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		long startT = 0;
		long endT = 0;
		try {
			startT = sdf.parse(t1).getTime();
			endT = sdf.parse(t2).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long ss = (startT - endT)/(1000) ;//共计秒数
		int hh = (int)ss /3600;//共计小时数
		return ss;
	}
	
	//比较两个日期相差的天数
	public static long compareTime(String time1,String time2){
		return compareStringTimeEx(time1,time2,"yyyy-MM-dd");
	}
	
	//返回当前时间
	public String getNow(){
		return getDate3()+" "+getTime();
	}
	
	//返回 年——月——日加天数
	public String getDate(int iDate){
		calendar.add(Calendar.DAY_OF_MONTH, iDate);
		return getYear()+ "-" +getStrMonth() +"-"  + getStrDayOfMonth(); 
	}
	
	//返回格式化后的日期
	public static String getDateFormatStr(Date p_Date,String p_Format){
		SimpleDateFormat sdf = new SimpleDateFormat(p_Format);
		if(p_Date == null){
			p_Date = new Date();
		}
		return sdf.format(p_Date);
	}
	
	/**
	 * @Description:将日期字符串加制定天数转变换位日期返回
	 * @param strDate  日期字符串
	 * @param rd       添加天数
	 * @return Date    返回加rd天后的日期
	 */
	public static Date addDay(String strDate,int rd){
		if(strDate == null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date =sdf.parse(strDate);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(GregorianCalendar.DATE, rd);
				date = calendar.getTime();
				return date;
			    } catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * @Description:字符串转日期
	 * @param str 需要转的字符串
	 * @return    日期类型
	 */
	public static Date stringToDate(String str){
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * @Description:根据字符串的格式转换成日期格式
	 * @param str 制定格式的字符串
	 * @return    日期类型
	 */	
   public static Date stringToDate(String strDate, String pattern) throws ParseException{
     SimpleDateFormat sdf = new SimpleDateFormat();
     sdf.applyPattern(pattern);
     Date result = sdf.parse(strDate);
     return result;
   }
	
	/**
	 * @Description:根据字符串的格式转换成日期格式
	 * @param str 制定格式的字符串
	 * @return    日期类型
	 */
	public static Date strToDate(String str){
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * @Description:根据制定的日期格式转换成字符串
	 * @param date  制定格式的日期
	 * @return      字符串
	 */
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = "";
		if (date == null)
			return formatDate;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
}

 