package com.ttpai.httptest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.ttpai.hongbo.test.DESUtil;
import com.ttpai.util.DateFormatUtil;
import com.ttpai.util.HttpUtil;
import com.ttpai.util.MyJson;


public class HttpTest {
	
	public final String DES_KEY = "cxtsc@51";
	public final String CHARSET = "UTF-8";
	public static String API_HOST = "https://api.ttpai.cn/";
	public static String PUBAPI_HOST = "http://pubapi.ttpai.cn/v1.0/";
	static{
		API_HOST = "http://localhost/";
		PUBAPI_HOST = "http://localhost/v1.0/";
	}

	void doTest(){
//		pubapi1002();
//		logappAdd();
//		handlerReferer();
		pubapi1001();
//		customerLogin();
//		pubapiSendMessage();
//		lastVersion100001();
//		customer1012();
//		customer1017();
//		customer1022();
//		customer1013();
//		test();
//		syncTocken100002();
//		customer1004();
//		mobile1002();
//		mobile1001();
//		customer1010();
//		paiList();
//		dealer4002();//竞拍大厅
//		dealer4042();//推荐拍品
	}
	public static void main(String[] args) throws Exception{
		new HttpTest().doTest();
	}
	
	void test(){
		String url = PUBAPI_HOST+"pinggu/appraisalCar?appid=10000";
		HttpUtil.post(url);
	}
	
	void paiList(){
		String url = PUBAPI_HOST+"pai/hall/list?appid=10000";
		MyJson p = MyJson.create().set("status", 0).set("order", "registration").set("order_type", "desc");//set("dealerId", "1").set("page_size", 200);//.set("regiser_address", "沪").set("zone_id", "2").set("age", "3-6").set("brand", "宝马");
		HttpUtil.post(url,p);
	}
	
	void lastVersion100001(){
		//customer.app.ttpai.cn		app
		//bossapp.ttpai.cn			bossapp
		//dealer.app.ttpai.cn		ttpBidHallController
		//checker.ttpai.cn
		String url = API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("type", "android_ttp_personal").set("version", "2.0.8").set("isdebug", 0);
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 100001).set("deviceType", "zzz").set("timestamp", 2L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void syncTocken100002(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("mobileType", "1").set("remark","2.0.6").set("appProductType", "3").set("userId", "").set("deviceTokens", "222");
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 100002).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	
	void customerLogin(){
		String url = API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("mobile", "18621668065").set("vcode", "123456");
		MyJson h = MyJson.create().set("version", "2.0.0").set("service", 1007).set("deviceType", "zzz").set("timestamp", 2L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	
	void customer1012(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create();
		MyJson h = MyJson.create().set("version", "2.0.5").set("service", 1012).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
		MyJson param = MyJson.create().set("header", h.getObj()).set("body", b.getObj());
		String result2 = HttpUtil.postInfoHeader(url, null, wrapperData(param.toJsonString()), host);
		System.out.println(unWrappedData(result2));
	}
	void customer1013(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create();
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1013).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void dealer4002(){
		String url =  API_HOST+"ttpBidHallController",host = "dealer.app.ttpai.cn";
		//{"star":"","brand":"","carYear":"","cityIds":"1,3","distince":"0-5","family":"","licenseFirst":"","pageNum":1,"userId":2,"auctionStatus":2}
		MyJson b = MyJson.create().set("auctionStatus",2).set("userId", 288378).set("licenseFirst", "沪C,津");//.set("distince", "0-5").set("cityIds", "1,3");
		MyJson h = MyJson.create().set("version", "2.4.1").set("service", 4002).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void dealer4042(){
		String url =  API_HOST+"ttpBidHallController",host = "dealer.app.ttpai.cn";
		MyJson b = MyJson.create().set("dealerId", 0);
		MyJson h = MyJson.create().set("version", "2.4.1").set("service", 4042).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void customer1010(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create();
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1010).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
		MyJson param = MyJson.create().set("header", h.getObj()).set("body", b.getObj());
		String result2 = HttpUtil.postInfoHeader(url, null, wrapperData(param.toJsonString()), host);
		System.out.println(unWrappedData(result2));
	}
	void customer1017(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("city", "");
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1017).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
//		MyJsonObject param = new MyJsonObject().set("header", h.getObj()).set("body", b.getObj());
//		String result = HttpUtil.postInfoHeader(url, null, wrapperData(param.toJsonString()), host);
//		System.out.println(unWrappedData(result));
	}
	void customer1022(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("brand", "奥迪").set("mobile", "18621888064").set("city", "上海").set("family", "奥迪A4");
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1022).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	void customer1004(){
		String url =  API_HOST+"app",host = "customer.app.ttpai.cn";
		MyJson b = MyJson.create().set("mobile", "18621668065");
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1004).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
//		String result = HttpUtil.postInfoHeader(url, wrapperData(h.toJsonString()), wrapperData(b.toJsonString()), host);
//		System.out.println(unWrappedData(result));
		MyJson param = MyJson.create().set("header", h.getObj()).set("body", b.getObj());
		String result = HttpUtil.postInfoHeader(url, null, wrapperData(param.toJsonString()), host);
		System.out.println(unWrappedData(result));
	}
	
	void logappAdd(){
		MyJson b = MyJson.create().set("app", "checker").set("type", "Android").set("version", "2.5.8").set("isServer", "0")
				.set("content", "测试日志----"+DateFormatUtil.format(new Date()));
		HttpUtil.postInfoHeader(API_HOST+"logappapi/add", null, b.toJsonString(), "logapp.ttpai.cn");
	}
	
	void pubapi1002(){
		String url = PUBAPI_HOST+"mobile/synUserDeviceTokens?appid=10014&info=";
		MyJson b = MyJson.create().set("appProductType", 1).set("deviceTokens", "11")
				.set("mobileType", 0).set("remark", "1.0.0").set("userId", 8);
		url += encodeUrl(b.toJsonString());
		HttpUtil.post(url);
	}
	void mobile1002(){
		String url = "http://mobile.ttpai.cn/api/userNew";
		MyJson b = MyJson.create().set("appProductType", 1).set("deviceTokens", "11")
				.set("mobileType", 0).set("remark", "1.0.0").set("userId", 6);
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1002).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, encodeBase64(h.toJsonString()), encodeBase64(b.toJsonString()), null);
		System.out.println(decodeBase64(result));
	}
	void mobile1001(){
		String url = "http://mobile.ttpai.cn/version/new";
//		url = "http://localhost/version/new";
		MyJson b = MyJson.create().set("type", "android_checker_ttp");
		MyJson h = MyJson.create().set("version", "2.0.8").set("service", 1002).set("deviceType", "zzz").set("timestamp", 1L).set("uuUserId", "1").setSign(b);
		String result = HttpUtil.postInfoHeader(url, encodeBase64(h.toJsonString()), encodeBase64(b.toJsonString()), null);
		System.out.println(decodeBase64(result));
	}
	
	void pubapi1001(){
		String url = PUBAPI_HOST+"mobile/getLatestVersion?appid=10014&info=";
		MyJson b = MyJson.create().set("type", "android_checker_new").set("version", "1.0.0");
		url += encodeUrl(b.toJsonString());
		HttpUtil.get(url);
	}
	
	/**pai调用pubapi登录*/
	public void paiLogin() throws Exception{
		MyJson b = MyJson.create().set("mobile", "").set("password", "123456").set("source_ip", "127.0.0.1").set("source", "www").set("uuid", "uuid1");
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
