package com.ttpai.httptest;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ttpai.hongbo.test.DESUtil;

public class AppRequest {
	
	public static final String DES_KEY = "cxtsc@51";

	public static final String header1001 = "{\"createTime\":\"2017-05-11 15:02:48\",\"dataSize\":0,\"deviceType\":\"android_checker_ttp\"," +
			"\"ext\":{\"scnHeight\":0,\"scnWidth\":0},\"service\":100001,\"sign\":\"87e8c3c22f2eaf7d635ad07adbad81ab\",\"timestamp\":1494486168665," +
			"\"uuUserId\":\"867993025715523\",\"version\":\"2.5.6\"}";
	public static final String ttp1001 = "{\"isDebug\":1,\"type\":\"android_checker_ttp\",\"uuUserId\":\"867993025715523\",\"version\":\"2.5.6\"}";
	
	public static String buildHeader(String service){
		String s = "{\"createTime\":\"2017-05-11 15:02:48\",\"dataSize\":0,\"deviceType\":\"android_checker_ttp\"," +
				"\"ext\":{\"scnHeight\":0,\"scnWidth\":0},\"service\":100001,\"sign\":\"87e8c3c22f2eaf7d635ad07adbad81ab\",\"timestamp\":1494486168665," +
				"\"uuUserId\":\"867993025715523\",\"version\":\"2.5.6\"}";
		s = s.replace("100001", service);
		return s;
	}
	
	public static String buildHeaderEn(String service){
		String s = buildHeader(service);
		return wrapperData(s);
	}
	public static String buildInfoEn(Map<String, Object> map){
		String s = JSON.toJSONString(map);
		return wrapperData(s);
	}
	
	private static String wrapperData(String str) {
		try{
			return DESUtil.encryptDESwithBase64(str, DES_KEY);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
