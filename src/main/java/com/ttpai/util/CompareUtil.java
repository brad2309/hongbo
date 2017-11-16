package com.ttpai.util;

public class CompareUtil {

	
	public static boolean equal(Integer a,int b){
		return (a!=null&&a==b);
	}
	
	public static boolean equal(Integer a,Integer b){
		return (a==b)||(a!=null&&a.equals(b));
	}
	
	public static boolean equal(Long a,Long b){
		return (a==b)||(a!=null&&a.equals(b));
	}
	
	public static boolean equalNotEmpty(String a,Integer b){
		if(a==null||"".equals(a)){
			return false;
		}
		Integer c = Integer.parseInt(a);
		return equal(c, b);
	}
	
	public static boolean equalNotEmpty(String a,Long b){
		if(a==null||"".equals(a)){
			return false;
		}
		Long c = Long.parseLong(a);
		return equal(c, b);
	}
	
	public static boolean equalNotEmpty(Integer a,Long b){
		return (a!=null&&((Long)a.longValue()).equals(b));
	}
	
	
}
