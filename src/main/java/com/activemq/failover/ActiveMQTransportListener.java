package com.activemq.failover;

import java.io.IOException;

import org.apache.activemq.transport.TransportListener;

public class ActiveMQTransportListener implements TransportListener {

	// 对消息传输命令进行监控
	@Override
	public void onCommand(Object command) {}

	// 监听到异常触发
	@Override
	public void onException(IOException error) {
		System.out.println("消息服务器连接错误。。。");
	}

	// 当failover时触发
	@Override
	public void transportInterupted() {
		System.out.println("消息服务器连接发生中断");
	}

	// 当failover恢复时触发
	@Override
	public void transportResumed() {
		System.out.println("消息服务器连接恢复");
	}

}
