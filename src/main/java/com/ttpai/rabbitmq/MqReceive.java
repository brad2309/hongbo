package com.ttpai.rabbitmq;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.ttpai.hongbo.test.BossComplaintVO;

@Component
public class MqReceive implements ChannelAwareMessageListener{
	public void onMessage(Message message, Channel channel) throws Exception {
		if(message==null){
			return;
		}
		try {
			String str = new String(message.getBody(),"utf-8");
			
			long deliveryTag=message.getMessageProperties().getDeliveryTag();
			System.out.println(deliveryTag+"==============="+str);
		    String exchange=message.getMessageProperties().getReceivedExchange();
		    BasicProperties properties=cloneAMQPProperties(message.getMessageProperties());
//		    channel.basicAck(deliveryTag, false);// 如果设置AcknowledgeMode.MANUAL，处理完消息后要返回ack，表示已成功处理，否则服务器将不会删除该消息
		    
		    channel.basicReject(deliveryTag, false); //消息被删
//		    channel.basicReject(deliveryTag, true); //消息放回服务器，自己可再取到
//		    channel.basicPublish(exchange,"hello",false,false,properties,message.getBody());//消息重发服务器，自己可再取到
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public BasicProperties cloneAMQPProperties(MessageProperties messageProperties){
	    BasicProperties basicProperties=new BasicProperties();
	    BeanUtils.copyProperties(messageProperties, basicProperties);
	    if(null!=basicProperties.getHeaders() && null!=messageProperties.getHeaders()){
	      basicProperties.getHeaders().putAll(messageProperties.getHeaders());
	    }
	    return basicProperties;
	  }
	private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue,
		SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
		SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
		SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible };
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "hello");
		map.put("b", 999);
		BossComplaintVO vo = new BossComplaintVO();
		vo.setAssignTime(new Date());
		vo.setAuctionId(444);
		String str = JSON.toJSONString(vo,features);
		System.out.println(str);
		str = JSON.toJSONString(map);
		System.out.println(str);
	}
}

//@Component
//public class MqReceive implements MessageListener{
//	
//	public void onMessage(Message message) {
//		if(message==null){
//			return;
//		}
//		try {
//			String str = new String(message.getBody(),"utf-8");
//			System.out.println("==============="+str);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}