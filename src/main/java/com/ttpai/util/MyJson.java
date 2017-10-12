package com.ttpai.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MyJson {

	private MyJson() {
	}

	public static MyJson create() {
		return new MyJson();
	}

	private JSONObject obj = new JSONObject();

	public MyJson set(String key, Object value) {
		obj.put(key, value);
		return this;
	}

	public JSONObject getObj() {
		return obj;
	}

	public String toJsonString() {
		return obj.toJSONString();
	}

	public Object get(String key) {
		return obj.get(key);
	}

	public Long getLong(String key) {
		return obj.getLong(key);
	}

	public String getString(String key) {
		return obj.getString(key);
	}

	public MyJson setSign(MyJson body) {
		StringBuilder str = new StringBuilder();
		Set<Map.Entry<String, Object>> mapSet = body.getObj().entrySet();
		if (mapSet != null && mapSet.size() > 0) {
			Map<String, Object> paramsMap = new TreeMap<String, Object>();
			for (Map.Entry<String, Object> entry : mapSet) {
				// 过滤掉对象类型
				if (!(entry.getValue() instanceof JSON)) {
					paramsMap.put(entry.getKey(), entry.getValue());
				}
			}
			for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
				str.append(entry.getValue());
			}
		}
		str.append(obj.get("uuUserId")).append(obj.get("timestamp"));
		String text = str.toString().replace("/", "\\/");
		String encoderStr = MD5.encoder(text, "UTF-8");
		set("sign", encoderStr);
		return this;
	}

}
