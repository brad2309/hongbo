package com.ttpai.httptest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.alibaba.fastjson.JSONObject;
import com.ttpai.hongbo.test.DESUtil;
import com.ttpai.util.HttpUtil;


public class HttpTest {
	
	public static final String DES_KEY = "cxtsc@51";
	public static final String CHARSET = "UTF-8";

	static RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
	static CloseableHttpClient hc = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

	public static void main(String[] args) throws Exception{
//		testCheckerApi();
//		pubapi1002();
		logappAdd();
	}
	
	static void logappAdd(){
		String url = "http://localhost/logapp/logappapi/add";
		JSONObject jo = new JSONObject();
		jo.put("app", "dealer");
		jo.put("type", "Android");
		jo.put("version", "1.0.1");
		jo.put("content", "1234少时诵诗书");
		Map<String, String> map = new HashMap<String, String>();
//		map.put("info", encodeUrl(jo.toJSONString()));
//		url += encodeUrl(jo.toJSONString());
//		url += jo.toJSONString();
		System.out.println(url);
//		HttpUtil.get(url,map);
		map.put("info", jo.toJSONString());
		HttpUtil.post(url, map);
	}
	
	static void pubapi1002(){
		String url = "http://pubapi.ttpai.cn/v1.0/mobile/synUserDeviceTokens?appid=10014&info=";
		JSONObject jo = new JSONObject();
		jo.put("appProductType", 1);
		jo.put("deviceTokens", "AkGQCmdM1FtE8lBJzfhKkZ3RyIexSkacfu7EoBR74X5q");
		jo.put("mobileType", 0);
		jo.put("remark", "1.0.0");
		jo.put("userId", 753);
		url += jo.toJSONString();
		System.out.println(url);
		HttpUtil.get(url);
	}
	
	static void pubapi1001(){
		String url = "http://pubapi.ttpai.cn/v1.0/mobile/getLatestVersion?appid=10014&info=";
		JSONObject jo = new JSONObject();
		jo.put("type", "android_boss");
		url += jo.toJSONString();
		System.out.println(url);
		String res = HttpUtil.get(url);
		System.out.println("-----------"+res);
	}
	
	/**pai调用pubapi登录*/
	public static void paiLogin() throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		params.put("mobile", "15044444444") ;
		params.put("password", "123456") ;
		params.put("source_ip", "1270.0.1") ;//访问IP
		params.put("source", "www") ;//访问来源
		params.put("uuid", "uuid") ;//前端唯一标识
		String url = "http://localhost/pubapi/v1.0/user/login?appid=10000";
		String res = HttpUtil.post(url,params,null);
		System.out.println("-----------"+res);
	}
	
	/**调用checker api新格式接口*/
	public static void testCheckerApi() throws Exception{
		String header = "4Gic94 IDvbCPBEhemm cgkMLXqM2F1Z73d40dD0s3sd4eXTkHg2keyu5EpKtNAaZbLqH3ZsJyKapnKz7gunINdqCqYpgQqmBlq IEkoTyyJefRfIv7OFRH3//PysC9Pbu5YQRbQDWIrs949mvYYWxtWYC7jZ1puLKWUDiw pv87fGEdySHIYtu6 hKKPcoMr4Ho2QDt9HJIi/N985NawRwNdcTh08ErHhzObgOYCcxJGatC65Be7cnit2HFxWJpuJJ HfuA29TglaAOaCcyZOWh/4gVbjh1e huREhFMWwu8WKs454fciUBVXdQpLxJdqFpl7hiKOQew9UmYcd15w==";
		String info = "5sCu90McMv9O0m0tWA8jALizCp3cPAvo4a9QH6AsbLhWpX2D68ekvP ZACSIyk 6NBDiUruU62JhWlq2LPFQw/yBuXHVCwwBmDFJDYJNemr7N1ELSUTQpfOxSl2allW0";
		String url = "http://localhost/checkerServlet";
		String host = null;
		String result = HttpUtil.postInfoHeader(url,header,info,host);
		result = DESUtil.decryptDESwithBase64(result, DES_KEY);
		System.out.println("decode result:"+result);
	}
	
	static String encodeUrl(String s){
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
		
	
}
