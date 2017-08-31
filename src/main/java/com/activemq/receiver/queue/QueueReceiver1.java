package com.activemq.receiver.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class QueueReceiver1 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到的消息是：" + ((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
