package com.ttpai.httptest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;
import com.ttpai.hongbo.test.DESUtil;
import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.HttpUtil;
import com.ttpai.util.MyJsonObject;


public class HttpTest {
	
	public final String DES_KEY = "cxtsc@51";
	public final String CHARSET = "UTF-8";
	public static String API_HOST = "https://api.ttpai.cn/";
	public static String PUBAPI_HOST = "http://pubapi.ttpai.cn/v1.0/";
	static{
//		API_HOST = "http://localhost/";
//		PUBAPI_HOST = "http://localhost/v1.0/";
	}

	void doTest(){
//		pubapi1002();
//		logappAdd();
//		handlerReferer();
//		pubapi1001();
//		customerLoginWithHeader();
//		customerLoginNoHeader();
//		pubapiSendMessage();
//		versionWithHeader();
		customer1012();
//		test();
	}
	public static void main(String[] args) throws Exception{
		new HttpTest().doTest();
	}
	
	void test(){
		String url = PUBAPI_HOST+"dept/checker?appid=10003";
//		url = "http://pubapi.ttpai.cn/v1.0/maintenance/data/updateAdmin?appid=10003";
		HttpUtil.post(url);
	}
	
	void pubapiSendMessage(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", "aaa");
		params.put("passwd", "bbb");
		params.put("templateId","26");
		params.put("appid", "10003");
//		params.put("phone","13811112222");
		String url = "http://localhost/v1.0/messageCenter/sendMessage";
		HttpUtil.post(url, params);
		
	}
	
	void dealerapp4010(){
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
	
	void versionWithHeader(){
		//customer.app.ttpai.cn
		//bossapp.ttpai.cn
		//checker.ttpai.cn
		String url = API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJsonObject b = new MyJsonObject().set("type", "android_checker_ttp").set("version", "2.5.6").set("isdebug", 1);
		MyJsonObject h = new MyJsonObject().set("version", "2.0.0").set("service", 100001).set("deviceType", "iphone_ttp_personal").set("timestamp", 1504751469397L).set("uuUserId", "222").setSign(b.getObj());
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	
	void customerLoginWithHeader(){
		String url = API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJsonObject b = new MyJsonObject().set("mobile", "18621668065").set("vcode", "123456");
		MyJsonObject h = new MyJsonObject().set("version", "2.0.0").set("service", 1007).set("deviceType", "iphone_ttp_personal").set("timestamp", 1504751469397L).set("uuUserId", "222").setSign(b.getObj());
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void customerLoginNoHeader(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJsonObject b = new MyJsonObject().set("mobile", "18621668065").set("vcode", "123456");
		MyJsonObject h = new MyJsonObject().set("version", "2.0.8").set("service", 1007).set("deviceType", "iphone_ttp_personal").set("timestamp", 1504751469397L).set("uuUserId", "222").setSign(b.getObj());
		MyJsonObject param = new MyJsonObject();
		param.set("header", h.getObj());
		param.set("body", b.getObj());
		String result = HttpUtil.postInfoHeader(url, null, wrapperData(param.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void customer1012(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJsonObject b = new MyJsonObject();
		MyJsonObject h = new MyJsonObject().set("version", "2.0.8").set("service", 1012).set("deviceType", "iphone_ttp_personal")
				.set("timestamp", 1504770254107L).set("uuUserId", "174e6e5a96dd345c6b0c2108f8f1f40a49fd7cba").setSign(b.getObj());
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	
	void handlerReferer(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("referer", "https://servicewechat.com/122/1.2.3/page-frame.html");
		map.put("host", "wxapp.ttpai.cn");
		String url = API_HOST+"wxminiapp/handlerReferer";
		HttpUtil.post(url, null, map);
	}
	
	void logappAdd(){
		String url = API_HOST+"logappapi/add";
		MyJsonObject b = new MyJsonObject().set("app", "checker").set("type", "Android").set("version", "2.5.8").set("isServer", "0")
				.set("content", "测试日志----"+DateFormatUtil.format(new Date()));
		HttpUtil.postInfoHeader(url, null, b.toJsonString(), "logapp.ttpai.cn");
	}
	
	void pubapi1002(){
		String url = PUBAPI_HOST+"mobile/synUserDeviceTokens?appid=10014&info=";
		MyJsonObject b = new MyJsonObject().set("appProductType", 1).set("deviceTokens", "AkGQCmdM1FtE8lBJzfhKkZ3RyIexSkacfu7EoBR74X5q")
				.set("mobileType", 0).set("remark", "1.0.0").set("userId", 753);
		url += b.toJsonString();
		HttpUtil.get(url);
	}
	
	void pubapi1001(){
		String url = PUBAPI_HOST+"mobile/getLatestVersion?appid=10014&info=";
//		url = "http://localhost/v1.0/mobile/getLatestVersion?appid=10014&info=";
		JSONObject jo = new JSONObject();
//		jo.put("type", "android_boss");
		jo.put("type", "android_bid_hall");
//		jo.put("version", "1.0.0");
		url += encodeUrl(jo.toJSONString());
		System.out.println(url);
		HttpUtil.get(url);
	}
	
	/**pai调用pubapi登录*/
	public void paiLogin() throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		params.put("mobile", "15044444444") ;
		params.put("password", "123456") ;
		params.put("source_ip", "1270.0.1") ;//访问IP
		params.put("source", "www") ;//访问来源
		params.put("uuid", "uuid") ;//前端唯一标识
		MyJsonObject b = new MyJsonObject().set("mobile", "").set("password", "123456").set("source_ip", "127.0.0.1").set("source", "www").set("uuid", "uuid1");
		String url = PUBAPI_HOST+"user/login?appid=10000";
		HttpUtil.post(url,b.getObj());
	}
		
	private String encodeUrl(String s){
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	private String unWrappedData(String str) {
		try{
			return DESUtil.decryptDESwithBase64(str, DES_KEY);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String wrapperData(String str) {
		try{
			return DESUtil.encryptDESwithBase64(str, DES_KEY);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String encodeBase64(String result) {
		try{
			return new String(Base64.encodeBase64(result.getBytes("UTF-8")), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public String decodeBase64(String result) {
        try{
        	return new String(Base64.decodeBase64(result.getBytes("UTF-8")), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
   
	
}
