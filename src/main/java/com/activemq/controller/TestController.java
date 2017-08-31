package com.activemq.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activemq.sender.queue.QueueSender;
import com.activemq.sender.topic.TopicSender;

@Controller
public class TestController {

	@Autowired
	private QueueSender queueSender;
	@Autowired
	private TopicSender topicSender;
	
	@RequestMapping("/queueSend")
	@ResponseBody
	public String queueSend() throws InterruptedException{
		for (int i = 0;i <100000;i++) {
//			TimeUnit.MILLISECONDS.sleep(1000);
			queueSender.send("发送queue测试消息:"+i);
		}
		return "success";
	}
	
	@RequestMapping("/topicSend")
	@ResponseBody
	public String topicSend(){
		queueSender.send("发送topic测试消息");
		return "success";
	}
}
