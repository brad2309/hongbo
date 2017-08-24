package com.ttpai.httptest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateFormatUtils;
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
//		logappAdd();
//		handlerReferer();
//		testCustomerApi();
		pubapi1001();
//		dealer();
//		boss();
//		pubapi1003();
//		pubapiSendMessage();
//		test();
	}
	
	static void test(){
		String url = "http://pubapi.ttpai.cn/v1.0/user/access?appid=10003&mobile=13248010486";
		HttpUtil.post(url);
	}
	
	static void pubapiSendMessage(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", "aaa");
		params.put("passwd", "bbb");
		params.put("templateId","26");
		params.put("appid", "10003");
//		params.put("phone","13811112222");
		String url = "http://localhost/v1.0/messageCenter/sendMessage";
		HttpUtil.post(url, params);
		
	}
	
	static void dealerapp4010(){
		String header = AppRequest.buildHeaderEn("4010");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dealerId", 288378);
		map.put("type", 2);
		String info = AppRequest.buildInfoEn(map);
		String url = "http://localhost/ttpBidHallController";
		String result = HttpUtil.postInfoHeader(url,header,info,null);
		result = unWrappedData(result);
		System.out.println("decode result:"+result);
	}
	
	static void dealer(){
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String> header = new HashMap<String,String>();
		header.put("host", "checker.ttpai.cn");
		String url = "http://api.ttpai.cn/checkerServlet";
		HttpUtil.post(url, params, header);
	}
	static void boss(){
		String header = wrapperData(AppRequest.header1001);
		String info = wrapperData(AppRequest.ttp1001);
		String url = "http://api.ttpai.cn/bossapp";
		String host = "bossapp.ttpai.cn";
		String result = HttpUtil.postInfoHeader(url,header,info,host);
		System.out.println("----------------------");
		result = unWrappedData(result);
		System.out.println("decode result:"+result);
	}
	
	static void handlerReferer(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("referer", "https://servicewechat.com/122/1.2.3/page-frame.html");
		map.put("host", "wxapp.ttpai.cn");
		String url = "http://api.ttpai.cn/wxminiapp/handlerReferer";
		HttpUtil.post(url, null, map);
	}
	
	static void logappAdd(){
//		String url = "http://localhost/logapp/logappapi/add";
		String url = "http://api.ttpai.cn/logappapi/add";
		JSONObject jo = new JSONObject();
		jo.put("app", "checker");
		jo.put("type", "Android");
		jo.put("version", "2.5.8");
		jo.put("isServer", "0");
		jo.put("content", "测试日志----"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("info", jo.toJSONString());
		Map<String, String> header = new HashMap<String, String>();
		header.put("host", "logapp.ttpai.cn");
		HttpUtil.post(url, map,header);
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
//		String url = "http://localhost/v1.0/mobile/getLatestVersion?appid=10014&info=";
		JSONObject jo = new JSONObject();
//		jo.put("type", "android_boss");
		jo.put("type", "android_bid_hall");
//		jo.put("version", "1.0.0");
		url += encodeUrl(jo.toJSONString());
		System.out.println(url);
		HttpUtil.get(url);
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
	
	/**调用checker api新格式接口100001*/
	public static void testCheckerApi() throws Exception{
		String header = wrapperData(AppRequest.header1001);
		String info = wrapperData(AppRequest.ttp1001);
		String url = "http://localhost/checkerServlet";
		String host = null;
		String result = HttpUtil.postInfoHeader(url,header,info,host);
		result = unWrappedData(result);
		System.out.println("decode result:"+result);
	}
	/**调用customer api新格式接口100001*/
	public static void testCustomerApi() throws Exception{
		String header = wrapperData(AppRequest.header1001);
		String info = wrapperData(AppRequest.ttp1001);
		String url = "http://localhost/app";
		String host = null;
		String result = HttpUtil.postInfoHeader(url,header,info,host);
		result = unWrappedData(result);
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
	
	private static String unWrappedData(String str) {
		try{
			return DESUtil.decryptDESwithBase64(str, DES_KEY);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String wrapperData(String str) {
		try{
			return DESUtil.encryptDESwithBase64(str, DES_KEY);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encodeBase64(String result) {
		try{
			return new String(Base64.encodeBase64(result.getBytes("UTF-8")), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public static String decodeBase64(String result) {
        try{
        	return new String(Base64.decodeBase64(result.getBytes("UTF-8")), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
   
	
}
