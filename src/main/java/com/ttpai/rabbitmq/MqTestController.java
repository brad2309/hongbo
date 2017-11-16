package com.ttpai.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mq")
public class MqTestController {

	public MqTestController() {
		System.out.println(8888);
	}
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@ResponseBody
	@RequestMapping("test")
	public String test(){
		System.out.println("999999");
		rabbitTemplate.convertAndSend("hello","cbd");
		return "ok";
	}

	@RequestMapping("test2")
	public String test2(){
//		System.out.println("999999");
//		rabbitTemplate.convertAndSend("hello","cbd");
		return "a";
	}
	
	
	
}
