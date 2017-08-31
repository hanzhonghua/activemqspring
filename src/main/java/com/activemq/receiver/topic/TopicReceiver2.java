package com.activemq.receiver.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class TopicReceiver2 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2订阅的消息是：" + ((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
