package com.ttpai.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MqContainerInit implements ApplicationListener<ContextRefreshedEvent>{

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext ctx = event.getApplicationContext();
		MqReceive receive = ctx.getBean(MqReceive.class);
		ConnectionFactory connectionFactory = ctx.getBean(ConnectionFactory.class);
		SimpleMessageListenerContainer lsn = new SimpleMessageListenerContainer(connectionFactory);
		lsn.setQueueNames("hello");
		lsn.setMessageListener(receive);
		lsn.setConcurrentConsumers(1);
		lsn.setAcknowledgeMode(AcknowledgeMode.MANUAL);//如果设置了这一句，则接收消息后必须channel.basicAck(deliveryTag, false);
		
		lsn.start();
		System.out.println("1111111111111111111111111111");
		
	}
	
}
