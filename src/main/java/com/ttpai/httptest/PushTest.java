package com.ttpai.httptest;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

import com.alibaba.fastjson.JSONObject;
import com.ttpai.util.HttpUtil;

public class PushTest {
	
	/**
	 * 测试环境
	 * 经销商18149705970/288329 13311111111/6
	 * 
	 * 1-7经销商 89boss 10消费者
	 * 经销商表 ttapi_sso.SSO_USERS 消费者表ttapi_www.WWW_USERS
	 * 1经销商 2boss 3消费者
	 */
	public static void main(String[] args) throws Exception{
		String msg = dealer("6,288378");//6,288378
		msg = dealer("288378");
		msg = dealer("6");
		msg = boss("112,119,252,1168,1048,277,106,203,1085,105,206,153,1318,1317,142,1329,1331,1326,1335,1249,1322,136,1343,288387,1315,752,288389,1325,135,1344,1051,1330,1333,288394,107,1340,1338,1323,1321,1252,1314,1332,176,1342,753,288390,288393,1324,1079,751,1316,1289,1320,1336,193,1334,750,288397,1345,288388");//753
		String url="http://mobilevc.ttpai.cn/api/push?params="+ URLEncoder.encode(msg, "UTF-8");
//		url="http://localhost/v1.0/mobile/push?appid=10013&params="+ URLEncoder.encode(msg, "UTF-8");
		url="http://pubapi.ttpai.cn/v1.0/mobile/push?appid=10013&params="+ URLEncoder.encode(msg, "UTF-8");
//		url="http://localhost/v1.0/mobile/push?appid=10013&params="+ URLEncoder.encode(msg, "UTF-8");
		HttpUtil.post(url);
	}
	static String dealer(String userIds){
		PushTest test= new PushTest();
		String displayType = "message";//notification
//		displayType = "notification";
		int appProductType = 1;
		int messageType = 6;
		return test.buildAppMsg(userIds, displayType, appProductType, messageType);
	}
	static String boss(String userIds){
		PushTest test= new PushTest();
		String displayType = "message";//notification
		int appProductType = 2;
		int messageType = 9;
		return test.buildAppMsg(userIds, displayType, appProductType, messageType);
	}
	static String customer(String userIds){
		PushTest test= new PushTest();
		String displayType = "message";//notification
		int appProductType = 3;
		int messageType = 10;
		return test.buildAppMsg(userIds, displayType, appProductType, messageType);
	}
	
	public String buildAppMsg(String userIds,String displayType,int appProductType,int messageType) {
		PushMessageBean msg = new PushMessageBean();
		String title = "hongboceshi-biaoti";
		String content = "hongboceshi-neirong"+DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
		msg.setUserIds(userIds);//671882 753 288386 676811
		msg.setSendType("unicast");
//		msg.setSendType("listcast");
		msg.setDisplayType(displayType);
		msg.setAppProductType(appProductType+"");
		msg.setTicker(title);
		msg.setTitle(title);
		msg.setText(content);
		msg.getExtra().put("id", "14");
		msg.getExtra().put("messageType", messageType+"");
		msg.setExpireTime(60);
		return JSONObject.toJSONString(msg);
	}
	
	class PushMessageBean{
		private String userIds ;
		private String sendType ;
		private String displayType ;
		private String appProductType ;
		private String ticker ;
		private String title ;
		private String text ;
		private Integer expireTime;//过期时间（分钟），默认半小时
		private Map<String,Object> extra = new HashMap<String,Object>();
		public String getUserIds() {
			return userIds;
		}
		public void setUserIds(String userIds) {
			this.userIds = userIds;
		}
		public String getSendType() {
			return sendType;
		}
		public void setSendType(String sendType) {
			this.sendType = sendType;
		}
		public String getDisplayType() {
			return displayType;
		}
		public void setDisplayType(String displayType) {
			this.displayType = displayType;
		}
		public String getAppProductType() {
			return appProductType;
		}
		public void setAppProductType(String appProductType) {
			this.appProductType = appProductType;
		}
		public String getTicker() {
			return ticker;
		}
		public void setTicker(String ticker) {
			this.ticker = ticker;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public Map<String, Object> getExtra() {
			return extra;
		}
		public void setExtra(Map<String, Object> extra) {
			this.extra = extra;
		}
	    public Integer getExpireTime() {
			return expireTime;
		}
	    public void setExpireTime(Integer expireTime) {
			this.expireTime = expireTime;
		}
		
	}

}
