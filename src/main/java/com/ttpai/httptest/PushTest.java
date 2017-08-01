package com.ttpai.httptest;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;

import com.alibaba.fastjson.JSONObject;
import com.ttpai.util.HttpUtil;

public class PushTest {
	
	public static void main(String[] args) throws Exception{
		PushTest test= new PushTest();
		String msg=test.buildAppMsg("biaoti---hb", "neirong-----"+DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(msg);
		String url="http://mobilevc.ttpai.cn/api/push?params="+ URLEncoder.encode(msg, "UTF-8");
//		String url="http://localhost/mobile/api/push?params="+ URLEncoder.encode(msg, "UTF-8");
		String response = HttpUtil.get(url);
		System.out.println(response);
	}
	
	public String buildAppMsg(String title, String content) {
		PushMessageBean msg = new PushMessageBean();
		msg.setUserIds("1844");//671882 753 288386 676811
		msg.setSendType("unicast");
		msg.setDisplayType("message");
		msg.setAppProductType("2");
		msg.setTicker(title);
		msg.setTitle(title);
		msg.setText(content);
		// 设置bossapp消息推送类型为8
		msg.getExtra().put("id", "14");
		msg.getExtra().put("messageType", "11");
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
		/**
		 * @return the userIds
		 */
		public String getUserIds() {
			return userIds;
		}
		/**
		 * @param userIds the userIds to set
		 */
		public void setUserIds(String userIds) {
			this.userIds = userIds;
		}
		/**
		 * @return the sendType
		 */
		public String getSendType() {
			return sendType;
		}
		/**
		 * @param sendType the sendType to set
		 */
		public void setSendType(String sendType) {
			this.sendType = sendType;
		}
		/**
		 * @return the displayType
		 */
		public String getDisplayType() {
			return displayType;
		}
		/**
		 * @param displayType the displayType to set
		 */
		public void setDisplayType(String displayType) {
			this.displayType = displayType;
		}
		/**
		 * @return the appProductType
		 */
		public String getAppProductType() {
			return appProductType;
		}
		/**
		 * @param appProductType the appProductType to set
		 */
		public void setAppProductType(String appProductType) {
			this.appProductType = appProductType;
		}
		/**
		 * @return the ticker
		 */
		public String getTicker() {
			return ticker;
		}
		/**
		 * @param ticker the ticker to set
		 */
		public void setTicker(String ticker) {
			this.ticker = ticker;
		}
		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		/**
		 * @return the extra
		 */
		public Map<String, Object> getExtra() {
			return extra;
		}
	}

}
