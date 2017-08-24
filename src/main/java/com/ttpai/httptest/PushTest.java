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
	 * dealer表 ttapi_sso.SSO_USERS
	 * 消费者表ttapi_www.WWW_USERS
	 * 
	 * 1经销商 2boss 3消费者
	 */
	public static void main(String[] args) throws Exception{
		String msg = dealer(6);
		msg = boss(753);
		String url="http://mobilevc.ttpai.cn/api/push?params="+ URLEncoder.encode(msg, "UTF-8");
//		String url="http://localhost/api/push?params="+ URLEncoder.encode(msg, "UTF-8");
		HttpUtil.post(url);
	}
	static String dealer(int userId){
		PushTest test= new PushTest();
		String displayType = "message";//notification
		int appProductType = 1;
		int messageType = 6;
		return test.buildAppMsg(userId, displayType, appProductType, messageType);
	}
	static String boss(int userId){
		PushTest test= new PushTest();
		String displayType = "message";//notification
		int appProductType = 2;
		int messageType = 9;
		return test.buildAppMsg(userId, displayType, appProductType, messageType);
	}
	static String customer(int userId){
		PushTest test= new PushTest();
		String displayType = "message";//notification
		int appProductType = 3;
		int messageType = 10;
		return test.buildAppMsg(userId, displayType, appProductType, messageType);
	}
	
	public String buildAppMsg(int userId,String displayType,int appProductType,int messageType) {
		PushMessageBean msg = new PushMessageBean();
		String title = "标题标题";
		String content = "内容内容"+DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
		msg.setUserIds(userId+"");//671882 753 288386 676811
		msg.setSendType("unicast");
		msg.setDisplayType(displayType);
		msg.setAppProductType(appProductType+"");
		msg.setTicker(title);
		msg.setTitle(title);
		msg.setText(content);
		msg.getExtra().put("id", "14");
		msg.getExtra().put("messageType", messageType+"");
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
		
	}

}
