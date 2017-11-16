package com.ttpai.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatUtil {

	private static Map<String, SimpleDateFormat> dfMap = new HashMap<String, SimpleDateFormat>();
	static{
		dfMap.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
		dfMap.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		dfMap.put("HH:mm:ss", new SimpleDateFormat("HH:mm:ss"));
		dfMap.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
		dfMap.put("HHmmss", new SimpleDateFormat("HHmmss"));
	}
	private DateFormatUtil() {
	}
	
	public static SimpleDateFormat getSimpleDateFormat(String pattern){
		if(dfMap.containsKey(pattern)){
			return dfMap.get(pattern);
		}
		SimpleDateFormat newInstance = new SimpleDateFormat(pattern);
		dfMap.put(pattern, newInstance);
		return newInstance;
	}
	
	/**格式为yyyy-MM-dd HH:mm:ss*/
	public static String format2DateTime(Date date){
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**格式为yyyy-MM-dd*/
	public static String format2Date(Date date){
		return format(date, "yyyy-MM-dd");
	}
	
	/**格式为yyyy-MM-dd HH:mm:ss*/
	public static String format(Date date){
		return format2DateTime(date);
	}
	
	public static String format(Date date,String pattern){
		if(date==null){
			return null;
		}
		SimpleDateFormat df = getSimpleDateFormat(pattern);
		return df.format(date);
	}

	/**格式为yyyy-MM-dd HH:mm:ss*/
	public static Date parse4DateTime(String dateStr) throws ParseException{
		return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**格式为yyyy-MM-dd*/
	public static Date parse4Date(String dateStr) throws ParseException{
		return parse(dateStr, "yyyy-MM-dd");
	}
	
	/**格式为yyyy-MM-dd HH:mm:ss*/
	public static Date parse(String dateStr) throws ParseException{
		return parse4DateTime(dateStr);
	}
	
	public static Date parse(String dateStr,String pattern) throws ParseException{
		if(dateStr==null||"".equals(dateStr)){
			return null;
		}
		SimpleDateFormat df = getSimpleDateFormat(pattern);
		return df.parse(dateStr);
	}
	
	
}
