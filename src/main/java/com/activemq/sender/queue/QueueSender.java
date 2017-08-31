package com.activemq.sender.queue;

/**
 * 延迟发送消息配置参数，需要在broker节点配置schedulerSupport="true"
 *   Property name	       type	   description
	AMQ_SCHEDULED_DELAY	   long	      延迟投递的时间
	AMQ_SCHEDULED_PERIOD   long	      重复投递的时间间隔
	AMQ_SCHEDULED_REPEAT   int	      重复投递次数  如果配置n，则发送n+1次
	AMQ_SCHEDULED_CRON	   String  Cron表达式
	当然ActiveMQ也提供了一个封装的消息类型：org.apache.activemq.ScheduledMessage.
 */
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("queueSender")
public class QueueSender {

	@Autowired
	@Qualifier("jmsQueueTemplate") //使用@Qualifier指定注入的具体Bean
	private JmsTemplate jmsTemplate;
	
	public void send(final String message){
		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				Message m = session.createTextMessage(message);
				// 延迟60秒发送消息，需要在broker节点配置属性schedulerSupport="true"
				// m.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 60*1000);
				
				// 延迟30秒，投递6次，间隔10秒:
				/*m.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 3*1000);
				m.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1*1000);
				m.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 5);*/
				
				// 使用cron表达式
				// m.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
				
				/**
				 *  cron表达式的优先级高于其他的三个参数，如果设置了cron的同时，也设置了投递几次
				 *  间隔几秒，则会在每次执行cron时候，重复发送几次，间隔几秒
				 */
				System.out.println("发送消息：" + message);
				return m;
			}
		});
	}
}
