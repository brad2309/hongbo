package com.ttpai.rabbitmq;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ttpai.util.MyJson;

@Component
public class MqTest {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MqTest test = context.getBean(MqTest.class);
//		test.send();
		test.receive();
		System.exit(0);
	}
	
	public void send(){
		MyJson j = MyJson.create().set("auctionId", 560000290).set("bidDate", "2017-10-13").set("marketId", 1004032).set("todayBid", 1).set("userId", 4);
//		rabbitTemplate.convertAndSend("boss.bussiness.today.marketbid", j.toJsonString());
//		rabbitTemplate.convertAndSend("hezuo.buike.signup.invitation", j.toJsonString());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("a", 99);
		rabbitTemplate.convertAndSend("hello",JSON.toJSONString(map));
		System.out.println("send success.");
	}
	public void receive(){
		Message msg = rabbitTemplate.receive("hello");
		if(msg==null){
			return;
		}
		try {
			String str = new String(msg.getBody(),"utf-8");
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
