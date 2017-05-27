package com.ttpai.hongbo.test;

public class ClassLoadTest {

	public final static int a = 2;
	public static int b = 3;
	public static final String c = "ccc";
	public static final Integer d = 4;
	public static final String e = new String("ee");
	static{
		System.out.println("static block");
		
	}
	
}
